package gov.va.pie.nvacdwvista.service;

import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

import gov.va.pie.common.service.PieLogService;
import gov.va.pie.common.utils.CommonConstants;
import gov.va.pie.nvacdwvista.hl7v3.MCCIIN000002UV01;
import gov.va.pie.nvacdwvista.hl7v3.PRPAIN201301UV02;
import gov.va.pie.nvacdwvista.model.MviModel;
import gov.va.pie.nvacdwvista.model.MviResponse;
import gov.va.pie.nvacdwvista.util.BeanUtil;
import gov.va.pie.nvacdwvista.util.SoapConstants;

@Component
@Scope("prototype")
public class Soap1301ClientService {

	static final Logger LOG = LogManager.getLogger(Soap1301ClientService.class);

	

	private MviModel mviModel;

	private List<Object[]> results;

	@Value("${cdw.mvi.url}")
	private String url;

	@Value("${cdw.mvi.processingCode}")
	private String processingCode;

	@Value("${cdw.nva.logpayload}")
	private String logPayload;

	@Value("${cdw.nva.maxcalls}")
	private int maxcalls;

	private List<MviModel> payload;

	private int count;
	
	@Autowired
	private PieLogService pieLogService;

	public int run() {
		LOG.info(" Processing batch in Soap1301ClientService " + results.size());
		MviResponseProcessor mviResponseProcessor = (MviResponseProcessor) BeanUtil.getBean(MviResponseProcessor.class);
		mviResponseProcessor.setMode(SoapConstants.NEW);
		mviResponseProcessor.getResponseList().clear();
		Soap1301Builder soap1301CreateProvider = new Soap1301Builder();
		MviModelBuilder mviModelBuilder = new MviModelBuilder();
		payload = mviModelBuilder.getNonVaProviders(results);
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("gov.va.pie.nvacdwvista.hl7v3");
		JAXBContext jc;
		Unmarshaller unmarshaller = null;
		try {
			jc = JAXBContext.newInstance(MCCIIN000002UV01.class);
			unmarshaller = jc.createUnmarshaller();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			LOG.info("Exception in Soap1301ClientService... ");
			pieLogService.recordDetails(e, CommonConstants.LOG_ERROR);

		}

		if (!CollectionUtils.isEmpty(payload)) {
			for (MviModel tmpMviModel : payload) {
				mviModel = tmpMviModel;

				if (tmpMviModel != null && !tmpMviModel.hasAcitveSpecialites()) {
					continue;
				}
				MviResponse mviResponse = null;
				if (count < maxcalls) {
					PRPAIN201301UV02 soapMessage = soap1301CreateProvider.buildMessage(mviModel, processingCode);
					mviResponse = new MviResponse();
					if (mviModel != null && mviResponse != null) {
						mviResponse.setNvaProviderId(mviModel.getNonVaproviderId()); // Will be used in MviIntreceptor
						mviResponse.setStation(mviModel.getStationNumber()); // Will be used in MviIntreceptor
					}
					if (mviResponse == null) {
						continue;
					}
						
					mviResponse.setActionCode(SoapConstants.NEW);
					if (tmpMviModel != null) {
						mviResponse.setProviderServicesCareSiteId(tmpMviModel.getProviderSerivicesCareSiteId());
						mviResponse.setCareSiteId(tmpMviModel.getCareSiteId());
					}
					MviIntreceptor mviIntreceptor = new MviIntreceptor(Boolean.valueOf(logPayload));
					mviIntreceptor.setMviResponse(mviResponse);
					mviIntreceptor.setUnmarshaller(unmarshaller);
					SOAPConnector soapConnector = new SOAPConnector();
					soapConnector.setMarshaller(marshaller);
					soapConnector.setUnmarshaller(marshaller);
					soapConnector.setMviIntreceptor(mviIntreceptor);
					count++;
					LOG.info(" calling MVI endpoint for 1301 , call count " + count + " max allowd calls " + maxcalls);
					soapConnector.callWebService(url, soapMessage, SoapConstants.MESSAGE_TYPE_1301);
				} else {
					mviResponseProcessor.saveBulkResponse();
					LOG.info("Max number of call limt reached  (in 1301 client service) ( call count " + count
							+ " max calls " + maxcalls);
					return count;

				}

				// mviResponse.setVistaCode(mviResponseProcessor.getMviMessageCode().getCode());
				// mviResponse.setMessage(mviResponseProcessor.getMviMessageCode().getMessage());
				// mviResponse.setVistaOutResponseId(tmpMviModel.getVistaOutResponseId());
				if (mviResponse != null)
					mviResponseProcessor.getResponseList().add(mviResponse);
			}
		}
		mviResponseProcessor.saveBulkResponse();
		return count;
	}

	public List<MviModel> getPayload() {
		return payload;
	}

	public void setPayload(List<MviModel> payload) {
		this.payload = payload;
	}

	public List<Object[]> getResults() {
		return results;
	}

	public void setResults(List<Object[]> results) {
		this.results = results;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
