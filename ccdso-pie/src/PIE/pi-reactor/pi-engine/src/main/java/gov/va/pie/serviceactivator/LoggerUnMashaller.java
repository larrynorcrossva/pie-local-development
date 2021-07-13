package gov.va.pie.serviceactivator;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import gov.va.pie.common.entities.App;
import gov.va.pie.common.exceptions.PieException;
import gov.va.pie.common.service.PieErrorService;
import gov.va.pie.common.service.PieLogService;
import gov.va.pie.common.utils.CommonConstants;
import gov.va.pie.ppms.model.ProviderResponses;


@Component("loggerUnMashaller")
public class LoggerUnMashaller {
	static Logger LOG = LogManager.getLogger(LoggerUnMashaller.class);

	@Autowired
	private PieErrorService pieErrorService;
	
	@Autowired
	private PieLogService pieLogService;
	

	/**
	 * 
	 * @param responseMessage - {@link org.springframework.messaging.Message} object
	 *                        with String as payload. Message and payload cannot be
	 *                        null.
	 * @return - {@link org.springframework.messaging.Message} object with
	 *         {@link gov.va.pie.ppms.model.ProviderResponses} as payload. Message
	 *         and payload cannot be null.
	 * @throws JAXBException
	 */

	public Message<ProviderResponses> logMessageUnmarshall(Message<String> responseMessage) {
		try {
			if (responseMessage == null || responseMessage.getPayload() == null
					|| StringUtils.isEmpty(responseMessage.getPayload())) {
				throw new PieException("Empty message");
			}

			LOG.info(responseMessage.getPayload());
			
 
			JAXBContext context = JAXBContext.newInstance(ProviderResponses.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			XMLInputFactory xif = XMLInputFactory.newFactory(); 
            xif.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false); 
            xif.setProperty(XMLInputFactory.SUPPORT_DTD, false); 
            XMLStreamReader xsr = xif.createXMLStreamReader(new StringReader(responseMessage.getPayload()));
        	ProviderResponses providerResponses = (ProviderResponses) unmarshaller.unmarshal(xsr);
			MessageBuilder.withPayload(providerResponses).build();
			return MessageBuilder.withPayload(providerResponses).build();
		}
		catch (JAXBException ex) {
			Throwable linkedException = ex.getLinkedException();
			Exception e = new Exception(linkedException);
			LOG.error("Inside exception ");
			pieLogService.recordDetails(e, CommonConstants.LOG_ERROR);
			pieErrorService.captureError(e, App.PIE);
			return createProviderResponsesMessage();
			
		} catch (XMLStreamException | PieException ex) {
			LOG.error("Inside exception ");
			pieLogService.recordDetails(ex, CommonConstants.LOG_ERROR);
			pieErrorService.captureError(ex, App.PIE);
			return createProviderResponsesMessage();
		}
	}
	
	private Message<ProviderResponses> createProviderResponsesMessage() {
		ProviderResponses providerResponses = new ProviderResponses();
		return MessageBuilder.withPayload(providerResponses).build();
	}

}
