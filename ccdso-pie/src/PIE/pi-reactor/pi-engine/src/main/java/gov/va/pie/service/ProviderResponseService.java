package gov.va.pie.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import gov.va.pie.common.entities.App;
import gov.va.pie.common.entities.PPMSProviderResponse;
import gov.va.pie.common.entities.PPMSProviderResponseDetail;
import gov.va.pie.common.entities.Provider;
import gov.va.pie.common.model.MessageType;
import gov.va.pie.common.repositories.PPMSProviderResponseDetailRepository;
import gov.va.pie.common.repositories.PPMSProviderResponseRepository;
import gov.va.pie.common.repositories.ProvidersRepository;
import gov.va.pie.common.service.PieErrorService;
import gov.va.pie.ppms.model.ProviderResponse;
import gov.va.pie.ppms.model.ProviderResponses;
import gov.va.pie.ppms.model.Result;
import gov.va.pie.ppms.model.Results;
import gov.va.pie.utils.PieUtils;

/**
 * 
 * @author AbleVets
 *
 */
@Service
public class ProviderResponseService {

	static final Logger LOG = LogManager.getLogger(ProviderResponseService.class);
	public static final String NPI = "Npi";

	@Autowired
	private PPMSProviderResponseRepository ppmsProviderResponseRepo;

	@Autowired
	private PPMSProviderResponseDetailRepository ppmsProviderResponseDetailRepo;

	@Autowired
	private ProvidersRepository providersRepository;
	
	@Autowired
	private PieErrorService pieErrorService;
	

	/**
	 * Add PPMS_ProviderResponse and PPMS_ProviderResponseDetail to tables with
	 * details from a ProviderResponses Object.
	 * 
	 * @param responses
	 *            - ProviderResponses object.
	 *            {@link gov.va.pie.ppms.model.ProviderResponses}
	 */
	public boolean processProviderResponses(ProviderResponses responsesObject, String conversationNumber) {
		boolean saved = false;
		if (responsesObject != null && !CollectionUtils.isEmpty(responsesObject.getProviderResponse()) 
				&& !StringUtils.isEmpty(conversationNumber)) {
			Timestamp addedAt = new Timestamp((new Date()).getTime());
			// build each response entity
			for (ProviderResponse response : responsesObject.getProviderResponse()) {
				// this is the staffSID we sent across
				int providerId = Integer.parseInt(response.getProviderId());
				Provider matchingProvider = providersRepository.findById(providerId);
				if (matchingProvider != null) {

					PPMSProviderResponse responseEntity = new PPMSProviderResponse();
					responseEntity.setFail(!response.isSuccess());
					responseEntity.setProvider(matchingProvider);
					responseEntity.setCreatedBy(PieUtils.SYSTEM);
					responseEntity.setCreatedDate(addedAt);
					responseEntity.setModifiedBy(PieUtils.SYSTEM);
					responseEntity.setModifiedDate(addedAt);
					responseEntity.setConversationNumber(conversationNumber);
					responseEntity = ppmsProviderResponseRepo.save(responseEntity);
					if (responseEntity != null)
						saved = true;
					List<PPMSProviderResponseDetail> responseDetails = new ArrayList<>();
					Results results = response.getResults();
					if (results != null && !CollectionUtils.isEmpty(results.getItem())) {
							List<Result> resultList = results.getItem();
							for (Result result : resultList) {
								PPMSProviderResponseDetail detail = new PPMSProviderResponseDetail();
								detail.setCreatedBy(PieUtils.SYSTEM);
								detail.setCreatedDate(addedAt);
								detail.setModifiedBy(PieUtils.SYSTEM);
								detail.setModifiedDate(addedAt);
								detail.setFail(!result.isSuccess());
								detail.setResponseIdText(result.getId());
								detail.setResponseTypeText(result.getType());
								String header = result.getHeader();
								if (header != null) {
									if (header.length() > PieUtils.MAX_RESULT_HEADER_LENGTH)
										detail.setResponseHeaderText(
												header.substring(0, PieUtils.MAX_RESULT_HEADER_LENGTH));
									else
										detail.setResponseHeaderText(header);
								}
								String message = result.getMessage();
								if (message != null) {
									if (message.length() > PieUtils.MAX_RESULT_HEADER_LENGTH)
										detail.setResponseMessageText(
												message.substring(0, PieUtils.MAX_RESULT_HEADER_LENGTH));
									else
										detail.setResponseMessageText(message);
								}
								detail.setDetailCorrelationId(result.getCorrelationId());
								detail.setPpmsProviderResponse(responseEntity);
								responseDetails.add(detail);
							}
							List<PPMSProviderResponseDetail> savedResponseDetails = new ArrayList<>();
							try {
								savedResponseDetails = ppmsProviderResponseDetailRepo.save(responseDetails);
							} catch (DataAccessException e) {
								LOG.info("No response details could be saved");
								pieErrorService.captureError(e, App.PIE);	
							}
							saved = saved && (savedResponseDetails.size() == responseDetails.size());
						} else {
							LOG.info("No responses to processed for provider: " + providerId);
							
					}
				} else {
					LOG.info("No provider found for the providerId: " + providerId);
					
				}
			}
		}
		return saved;
	}
}
