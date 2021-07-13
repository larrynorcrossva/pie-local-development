package gov.va.pie.serviceactivator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import gov.va.pie.ppms.model.ProviderResponses;

@Component
public class SendAck {

	static Logger LOG = LogManager.getLogger(SendAck.class);

	/**
	 * 
	 * @param responseMessage
	 *            - {@link org.springframework.messaging.Message} object with
	 *            {@link gov.va.pie.ppms.model.ProviderResponses} as payload.
	 *            Message and payload cannot be null.
	 * @return - returns data received message (status 200 to caller)
	 */
	@ServiceActivator
	public HttpStatus sendAck(Message<ProviderResponses> responseMessage) {

		if (responseMessage == null || responseMessage.getPayload() == null
				|| CollectionUtils.isEmpty(responseMessage.getPayload().getProviderResponse())) {
			return HttpStatus.BAD_REQUEST;
		}
		return HttpStatus.OK;
	}
}
