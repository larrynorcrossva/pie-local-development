package gov.va.pie.filter;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.integration.core.MessageSelector;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import gov.va.pie.cdw.model.VAProvider;

/**
 * 
 * @author Ablevets Filter class to filter invalid VA providers
 *
 */

@Component("manualAutoFilter")
public class ManualAutoFilter implements MessageSelector {

	/**
	 * @param message - Message object
	 *               
	 * @return boolean - Return true when all the header values is empty else false
	 * 	
	 */
	public boolean accept(Message<?> message) {
		if (message.getHeaders().get("manual") != null
				&& "yes".equalsIgnoreCase(message.getHeaders().get("manual").toString()))
			return false;
		else
			return true;
	}

}