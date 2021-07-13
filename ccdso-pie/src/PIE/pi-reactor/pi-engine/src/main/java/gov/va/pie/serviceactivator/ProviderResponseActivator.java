package gov.va.pie.serviceactivator;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import gov.va.pie.ppms.model.ProviderResponses;
import gov.va.pie.service.ProviderResponseService;
import gov.va.pie.utils.PieUtils;

@Component("providerResponseActivator")
public class ProviderResponseActivator {
	static final Logger LOG = LogManager.getLogger(ProviderResponseActivator.class);

	@Autowired
	private ProviderResponseService providerResponseService;

	/**
	 * 
	 * @param responseMessage
	 *            - {@link org.springframework.messaging.Message} object with
	 *            {@link gov.va.pie.ppms.model.ProviderResponses}
	 *            as payload. Message and payload cannot be null.
	 */
	@ServiceActivator
	public void processProviderResponses(Message<ProviderResponses> responseMessage) {

		if (responseMessage == null || responseMessage.getPayload() == null || CollectionUtils.isEmpty(responseMessage.getPayload().getProviderResponse())) {
			return;
		}
		MessageHeaders msgHeaders = responseMessage.getHeaders();
		String conversationNumber = (String)msgHeaders.get(PieUtils.CONVERSATION_NUMBER.toLowerCase());
		LOG.info("Received response with conversation Number: " + conversationNumber);
		if (StringUtils.isEmpty(conversationNumber)) {
			LOG.info("There is no conversation number for the response");
			return;
		}
		providerResponseService.processProviderResponses(responseMessage.getPayload(), conversationNumber);
	}
}
