package gov.va.pie.serviceactivator;

import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import gov.va.pie.common.model.MessageType;

import gov.va.pie.ppms.model.Provider;
import gov.va.pie.ppms.model.Providers;
import gov.va.pie.service.OutBoundMessagesService;
import gov.va.pie.utils.PieUtils;

@Component("ppmsAckToDatabaseActivator")
public class PPMSAckToDatabaseActivator {

	@Autowired
	private OutBoundMessagesService outBoundMessagesService;


	

	/**
	 * Calls the service to save the out bound messages.
	 * 
	 * @param responseMessage ResponseEntity wrapped in Messsage
	 * @throws JAXBException
	 */
	public Message<ResponseEntity<String>> postToDb(Message<ResponseEntity<String>> responseMessage)
			throws JAXBException {
		MessageHeaders headers = responseMessage.getHeaders();
		Providers providers = (Providers) headers.get(PieUtils.ORIGINAL_PAYLOAD);
		List<Provider> providerList = providers.getProvider();
		String transactionId = (String) headers.get(PieUtils.TRANSACTION_ID);
		String conversationId = (String) headers.get(PieUtils.CONVERSATION_NUMBER);
		HttpStatus statusCode = responseMessage.getPayload().getStatusCode();
		JAXBContext jaxbContext = JAXBContext.newInstance(Providers.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		StringWriter sw = new StringWriter();
		jaxbMarshaller.marshal(providers, sw);
		

		if (!CollectionUtils.isEmpty(providerList) && !StringUtils.isEmpty(statusCode)
				&& !StringUtils.isEmpty(transactionId)) {
			boolean success = outBoundMessagesService.saveOutBoundMessages(providerList, statusCode.toString(),
					transactionId, conversationId);
			if (success) {
				
			} else {
				
			}

		}
		return responseMessage;
	}

}
