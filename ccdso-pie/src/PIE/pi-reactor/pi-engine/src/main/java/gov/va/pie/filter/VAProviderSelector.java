package gov.va.pie.filter;


import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.integration.core.MessageSelector;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import gov.va.pie.cdw.model.VAProvider;

/**
 * 
 * @author Ablevets 
 * Filter class to filter invalid VA providers
 *
 */

@Component("vaProviderSelector")
public class VAProviderSelector implements MessageSelector {

	/**
	 * @param message
	 *            - Message object with a list of
	 *            {@link gov.va.pie.cdw.model.VAProvider}
	 *            VAProider in the payload
	 * @return boolean - Return true when all the providers in the message has a
	 *         valid npi( not null and not equal to -1)
	 */
	public boolean accept(Message<?> message) {
		@SuppressWarnings("unchecked")
		List<VAProvider> vaProvidersList = (List<VAProvider>) message.getPayload();
		if (CollectionUtils.isNotEmpty(vaProvidersList)) {
			for (VAProvider vaProvider : vaProvidersList) {
				if (vaProvider.getNpi() == null || vaProvider.getNpi().equals("-1"))
					return false;
			}
		}
		return true;
	}

}