package gov.va.pie.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceException;
import javax.persistence.StoredProcedureQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import gov.va.pie.common.entities.App;
import gov.va.pie.common.entities.OutboundStatus;
import gov.va.pie.common.repositories.OutBoundStatusRepository;
import gov.va.pie.common.service.PieErrorService;
import gov.va.pie.common.utils.CommonConstants;
import gov.va.pie.ppms.model.Provider;
import gov.va.pie.utils.PieUtils;

/**
 * 
 * @author AbleVets
 *
 */
@Service
public class OutBoundMessagesService {

	@Autowired
	private OutBoundStatusRepository outBoundStatusRepository;

	private Map<String, OutboundStatus> outboundStatusMap;

	@Autowired
	private EntityManagerFactory emf;
	
	@Autowired
	private PieErrorService pieErrorService;

	private static final Logger LOG = LogManager.getLogger(OutBoundMessagesService.class);

	;
	private static final String PROC = "App.sp_InsOutboundStatusMsg";

	/**
	 * Populate outboundstatus map with Outbound_Stattus table values.
	 *
	 */
	@PostConstruct
	private void init() {
		outboundStatusMap = new HashMap<>();
		List<OutboundStatus> statuses = outBoundStatusRepository.findAll();
		statuses.forEach(status -> outboundStatusMap.put(status.getCode(), status));
	}

	/**
	 * 
	 * @param providerList  - List of providers
	 * @param statusCode    - http status code values
	 * @param transactionId - transaction id
	 * @return
	 */
	public boolean saveOutBoundMessages(List<Provider> providerList, String statusCode, String transactionId, String conversationId) {
		boolean returnFlag = false;

		if (CollectionUtils.isEmpty(providerList) || StringUtils.isEmpty(statusCode)
				|| StringUtils.isEmpty(transactionId) || StringUtils.isEmpty(conversationId)) {
			return false;
		}
		LOG.info("status code : "+statusCode);
		String httpStatusId = String.valueOf(outboundStatusMap.get(statusCode).getId());
		
		long start = new Date().getTime();
		EntityManager entityManager = emf.createEntityManager();
		List<String> providerIdList = new ArrayList<String>();
		List<String> insList = new ArrayList<String>();
		if (!CollectionUtils.isEmpty(providerList) && statusCode != null) {
			for (Provider provider : providerList) {
				providerIdList.add(provider.getProviderId());
			}
		}
		
		@SuppressWarnings("unchecked")
		List<String> updatesList = entityManager.createNativeQuery(
				"Select convert(varchar(10), Provider_Id_FK) as id from App."+CommonConstants.DB_ENV+"PPMS_OutboundMsg_V where Provider_Id_FK in ( :values )")
				.setParameter("values", providerIdList).getResultList();
		for (String id : providerIdList) {
			if (!updatesList.contains(id.trim())) {
				insList.add(id);
			}
		}
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		String uPPmsOutboundMsg = OutBoundMessagesServiceHelper.createUpdatePPMSOutboundMsgVQuery(updatesList, httpStatusId,
				transactionId, conversationId);

		if (!StringUtils.isEmpty(uPPmsOutboundMsg)) {
			runProcedure(entityManager,uPPmsOutboundMsg);

		}

		if (!CollectionUtils.isEmpty(insList)) {

			int count = 0;
			List<String> inSt100Limit = new ArrayList<String>();
			StringBuffer insertValues = new StringBuffer();
			for (String id : insList) {
				count++;
				insertValues.append("(").append(id + ",").append(httpStatusId + ",").append("1,").append("getDate(),")
						.append("'system',").append("getDate(),").append("null,").append("null,")
						.append("'" + transactionId + "'").append(",'"+conversationId +"'), ");

				if (count == 1000) {
					inSt100Limit.add(insertValues.toString());
					count = 0;
					insertValues = new StringBuffer();
				}

			}

			if (!CollectionUtils.isEmpty(inSt100Limit)) {
				for (String tmpInclause : inSt100Limit) {
					String iOutboundMessageData = OutBoundMessagesServiceHelper
							.createInsertPPMSOutboundMsgVQuery(tmpInclause);
					if (!StringUtils.isEmpty(iOutboundMessageData)) {
						runProcedure(entityManager,iOutboundMessageData);
					}

				}
			}
			if (!StringUtils.isEmpty(insertValues.toString())) {
				String iOutboundMessageData = OutBoundMessagesServiceHelper
						.createInsertPPMSOutboundMsgVQuery(insertValues.toString());
				if (!StringUtils.isEmpty(iOutboundMessageData)) {
						runProcedure(entityManager,iOutboundMessageData);
				}
			}
		}
		
		 int isProcessed = 0;
		 if (statusCode != null && (statusCode.equals(PieUtils.HTTP_STATUS_OK) || 
			 statusCode.equals(PieUtils.HTTP_STATUS_OK_CREATED)))
			 isProcessed = 1 ;

		if (!CollectionUtils.isEmpty(providerIdList)) {
			String uProviderData = OutBoundMessagesServiceHelper.updateProvidersQuery(providerIdList, isProcessed);
			runProcedure(entityManager,uProviderData);
		}
		transaction.commit();
		LOG.info(" OutBoundMessagesService time : " + ((new Date().getTime() - start) / 1000) + " seconds ");
		returnFlag = true;
		return returnFlag;

	}
	
	private void runProcedure(EntityManager entityManager, String param) throws PersistenceException {
		try {
			StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(PROC);
			storedProcedureQuery.registerStoredProcedureParameter("insert", String.class, ParameterMode.IN);
			storedProcedureQuery.setParameter("insert", param);
			storedProcedureQuery.execute();
		} catch (PersistenceException e) {
			LOG.error(e.getMessage());
			pieErrorService.captureError(e, App.PIE);
		}
	}

}