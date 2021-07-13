package gov.va.pie.serviceactivator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

import gov.va.pie.ppms.model.Providers;
import gov.va.pie.service.OutBoundMessagesService;
import gov.va.pie.utils.PieUtils;

@Component
public class HttpStatusErrorLogger {
	private static final String HTTP_STATUS_CODE = "503";

	@Autowired
	private OutBoundMessagesService outBoundMessagesService;

	/**
	 * Logs error message when http outbound gateway throws an exception
	 * 
	 * @param responseMessage
	 *            - {@link org.springframework.messaging.MessagingException}
	 */
	@ServiceActivator
	public void handleError(Message<MessagingException> responseMessage){

		if (responseMessage.getPayload().getCause() instanceof ResourceAccessException) {			
			MessagingException payload = responseMessage.getPayload();
			Providers providers = (Providers) payload.getFailedMessage().getHeaders().get(PieUtils.ORIGINAL_PAYLOAD);
			String transactionId = (String) payload.getFailedMessage().getHeaders().get(PieUtils.TRANSACTION_ID);
			String conversationId = (String) payload.getFailedMessage().getHeaders().get(PieUtils.CONVERSATION_NUMBER);
			outBoundMessagesService.saveOutBoundMessages(providers.getProvider(), HTTP_STATUS_CODE, transactionId, conversationId);
		}
	}
}
