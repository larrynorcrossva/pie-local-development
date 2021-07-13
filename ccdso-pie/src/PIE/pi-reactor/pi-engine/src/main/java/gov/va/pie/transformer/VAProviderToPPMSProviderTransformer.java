
package gov.va.pie.transformer;

import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import gov.va.pie.cdw.model.VAProvider;
import gov.va.pie.common.service.PieLogService;
import gov.va.pie.common.utils.CommonConstants;
import gov.va.pie.ppms.model.Provider;
import gov.va.pie.ppms.model.Providers;
import gov.va.pie.utils.PieUtils;

@Component("vaProviderToPPMSProviderTransformer")
public class VAProviderToPPMSProviderTransformer {

	static Logger LOG = LogManager.getLogger(VAProviderToPPMSProviderTransformer.class);
	
	@Autowired
	private PieLogService pieLogService;

	/**
	 * Transforms providers list to PPMS providers message list
	 * 
	 * @param providerListMsg -{@link org.springframework.messaging.Message} object
	 *                        with a list of {@link gov.va.pie.cdw.model.VAProvider}
	 *                        as pay load
	 * @return {@link org.springframework.messaging.Message} object with a list of
	 *         {@link gov.va.pie.ppms.model.Providers} as pay load
	 */
	@Transformer
	public Message<Providers> toPPMSProviders(Message<List<VAProvider>> providerListMsg)
			throws DatatypeConfigurationException {
		Providers providers = new Providers();
		List<VAProvider> vaProvidersList = (List<VAProvider>) providerListMsg.getPayload();
		if (CollectionUtils.isNotEmpty(vaProvidersList)) {
			for (VAProvider vaProdiver : vaProvidersList) {
				Provider ppmsProvider = PieUtils.convertVAProviderToPPMSProvider(vaProdiver);
				providers.getProvider().add(ppmsProvider);
			}
		}
		Message<Providers> message = null;
		if (providerListMsg.getHeaders().get("manual") != null) {
			message = MessageBuilder.withPayload(providers)
					.setHeader("manual", providerListMsg.getHeaders().get("manual")).build();

		} else {
			message = MessageBuilder.withPayload(providers).build();
		}
		return message;
	}

	/**
	 * Fall back method for toPPMSProviders.
	 * 
	 * @param providerListMsg -{@link org.springframework.messaging.Message} object
	 *                        with a list of {@link gov.va.pie.cdw.model.VAProvider}
	 *                        as pay load
	 * @param e               Exception occurred in toPPMSProviders
	 * @return {@link org.springframework.messaging.Message} object with a list of
	 *         {@link gov.va.pie.ppms.model.Providers} as pay load
	 * 
	 * 
	 */
	public Message<Providers> toPPMSProvidersFallBack(Message<List<VAProvider>> providerListMsg, Throwable e) {
		LOG.error("Calling fall back method : ");
		pieLogService.recordDetails(new Exception(e), CommonConstants.LOG_ERROR);
		Providers providers = new Providers();
		return MessageBuilder.withPayload(providers).build();
	}
}
