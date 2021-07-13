package gov.va.pie.serviceactivator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import gov.va.pie.ppms.model.ProviderResponse;
import gov.va.pie.ppms.model.ProviderResponses;
import gov.va.pie.service.ProviderService;

@Component("correlationIdActivator")
public class CorrelationIdActivator {

	@Autowired
	private ProviderService providerService;
	
	private static final Logger LOG = LogManager.getLogger(CorrelationIdActivator.class);


	
	/**
	 * 
	 * @param responseMessage
	 *            - {@link org.springframework.messaging.Message} object with
	 *            {@link gov.va.pie.ppms.model.ProviderResponses}
	 *            as payload. Message and payload cannot be null.
	 */
	@ServiceActivator
	public boolean updateCorrelationId(Message<ProviderResponses> responseMessage)  {
		LOG.info("Inside updateCorrelationId  ");
		if (responseMessage == null || responseMessage.getPayload() == null) {
			return false;
		}
		ProviderResponses payload = responseMessage.getPayload();
		if (!CollectionUtils.isEmpty(payload.getProviderResponse())) {
			for (ProviderResponse providerResponse : payload.getProviderResponse()) {
				providerService.updateCorrlationId(Integer.valueOf(providerResponse.getProviderId()),
						providerResponse.getCorrelationId());
			}
			
		}else {
			LOG.info("Payload.getProviderResponse() is empty ");
			
			return false;
		}
		return true;
	}
}
