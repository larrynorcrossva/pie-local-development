package gov.va.pie.nvacdwvista.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import gov.va.pie.nvacdwvista.hl7v3.PRPAIN201302UV02;
import gov.va.pie.nvacdwvista.model.MviModel;
import gov.va.pie.nvacdwvista.model.MviResponse;
import gov.va.pie.nvacdwvista.util.BeanUtil;
import gov.va.pie.nvacdwvista.util.SoapConstants;

@Component
@Scope("prototype")
public class Soap1302ClientService {

	private static final Logger LOG = LogManager.getLogger(Soap1302ClientService.class);

	private Set<String> npi;

	@Value("${cdw.mvi.url}")
	private String url;

	@Value("${cdw.mvi.processingCode}")
	private String processingCode;

	@Value("${cdw.nva.logpayload}")
	private String logPayload;

	private List<Object[]> results;

	private List<MviModel> payload;

	@Value("${cdw.nva.maxcalls}")
	private int maxcalls;

	private int count;

	@Value("${mvi.without.sr2350}")
	private boolean mviWithoutSR2350;
	
	@Autowired
	private PieLogService pieLogService;

	public int run() {
		MviResponseProcessor mviResponseProcessor = (MviResponseProcessor) BeanUtil.getBean(MviResponseProcessor.class);
		mviResponseProcessor.setMode(SoapConstants.UPDATE);
		mviResponseProcessor.getResponseList().clear();

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
			pieLogService.recordDetails(e, CommonConstants.LOG_ERROR);
		}

		npi = new HashSet<>();
		HashSet<String> updatedProviders = new HashSet<>();
		if (!CollectionUtils.isEmpty(payload)) {
			for (MviModel tmpMviModel : payload) {
				MviResponse mviResponse = new MviResponse();
				mviResponse.setNvaProviderId(tmpMviModel.getNonVaproviderId()); // Will be used in MviIntreceptor
				mviResponse.setStation(tmpMviModel.getStationNumber());// Will be used in MviIntreceptor
				mviResponse.setActionCode(SoapConstants.NEW);
				mviResponse.setProviderServicesCareSiteId(tmpMviModel.getProviderSerivicesCareSiteId());
				mviResponse.setCareSiteId(tmpMviModel.getCareSiteId());
				mviResponse.setVistaOutResponseId(tmpMviModel.getVistaOutResponseId());
				mviResponse.setCareSiteId(tmpMviModel.getCareSiteId());
				MviIntreceptor mviIntreceptor = new MviIntreceptor(Boolean.valueOf(logPayload));
				mviIntreceptor.setMviResponse(mviResponse);
				mviIntreceptor.setUnmarshaller(unmarshaller);

				if ((!tmpMviModel.isActiveProvider() || !tmpMviModel.hasAcitveSpecialites()) && (!npi.contains(tmpMviModel.getNpi()) || !mviWithoutSR2350))
				{
					// Do soap Update call to remove npi
					Soap1302Builder saop1302DeleteProvider = new Soap1302Builder();
					PRPAIN201302UV02 soapMessage = saop1302DeleteProvider.build1302UpdateMessage(tmpMviModel, true,
							processingCode);
					npi.add(tmpMviModel.getNpi());
					mviResponse.setActionCode(SoapConstants.DELETE);
					SOAPConnector soapConnector = new SOAPConnector();
					soapConnector.setMarshaller(marshaller);
					soapConnector.setUnmarshaller(marshaller);
					soapConnector.setMviIntreceptor(mviIntreceptor);
					if (count <= maxcalls) {
						LOG.info(" calling MVI endpoint for 1302 , call count " + count +" max allowd calls "+maxcalls);
						soapConnector.callWebService(url, soapMessage, SoapConstants.MESSAGE_TYPE_1302);
						count++;
					}else {
						LOG.info("Max number of call limt reached  (in 1302 client service) ( call count " + count + " max calls " + maxcalls);
						return count;

					}
					// MviIntreceptor will populate vista response code and message
					mviResponseProcessor.getResponseList().add(mviResponse);

				}
				if (tmpMviModel.hasAcitveSpecialites() && tmpMviModel.isActiveProvider()) {
					if (!mviWithoutSR2350 && npi.contains(tmpMviModel.getNpi())) {
						// Do a 1301 call
						Soap1301Builder soap1301CreateProvider = new Soap1301Builder();
						PRPAIN201301UV02 soapMessage = soap1301CreateProvider.buildMessage(tmpMviModel, processingCode);
						mviResponse.setActionCode(SoapConstants.NEW);
						SOAPConnector soapConnector = new SOAPConnector();
						soapConnector.setMarshaller(marshaller);
						soapConnector.setUnmarshaller(marshaller);
						soapConnector.setMviIntreceptor(mviIntreceptor);
						if (count <= maxcalls) {
							LOG.info(" calling MVI endpoint for 1301 (in 1302 client service) , call count " + count);
							soapConnector.callWebService(url, soapMessage, SoapConstants.MESSAGE_TYPE_1301);
							count++;
						}else {
							LOG.info("Max number of call limt reached  (in 1302 client service) ( call count " + count + " max calls " + maxcalls);
							return count;
						}
						// MviIntreceptor will populate vista response code and message
						mviResponseProcessor.getResponseList().add(mviResponse);

					} else {
						// Do a 1302 call
						if (!mviWithoutSR2350 || !updatedProviders.contains(tmpMviModel.getNpi())){

							Soap1302Builder soap1302CreateProvider = new Soap1302Builder();
							PRPAIN201302UV02 soapMessage = soap1302CreateProvider.build1302UpdateMessage(tmpMviModel, false,
									processingCode);
							mviResponse.setActionCode(SoapConstants.UPDATE);
							SOAPConnector soapConnector = new SOAPConnector();
							soapConnector.setMarshaller(marshaller);
							soapConnector.setUnmarshaller(marshaller);
							soapConnector.setMviIntreceptor(mviIntreceptor);
							if (count <= maxcalls) {
								LOG.info(" calling MVI endpoint for 1302 (in 1302 client service) , call count " + count);
								soapConnector.callWebService(url, soapMessage, SoapConstants.MESSAGE_TYPE_1302);
								count++;
							}else {
								mviResponseProcessor.saveBulkResponse();
								LOG.info("Max number of call limt reached  (in 1302 client service) ( call count " + count + " max calls " + maxcalls);
								return count;

							}
							// MviIntreceptor will populate vista response code and message
							mviResponseProcessor.getResponseList().add(mviResponse);
							if (mviWithoutSR2350)
								updatedProviders.add(tmpMviModel.getNpi());
						}
					}
				}
			}
		}
		mviResponseProcessor.saveBulkResponse();
		return count;
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
