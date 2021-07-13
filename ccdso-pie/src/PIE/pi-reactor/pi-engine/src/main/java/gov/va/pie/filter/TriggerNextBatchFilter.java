package gov.va.pie.filter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.core.MessageSelector;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import gov.va.pie.utils.PieUtils;

/**
 * 
 * @author Ablevets Filter class to filter invalid VA providers
 *
 */

@Component("triggerNextBatchFilter")
public class TriggerNextBatchFilter implements MessageSelector {

	/**
	 * @param message - Message object
	 * 
	 * @return boolean - Return true when all the status is 200 or 201
	 * 
	 */
	public boolean accept(Message<?> message) {
		boolean flag = false;
		HttpStatus statusCode = ((ResponseEntity<String>) message.getPayload()).getStatusCode();
		if (statusCode.value() == Integer.valueOf(PieUtils.HTTP_STATUS_OK).intValue()
				|| statusCode.value() == Integer.valueOf(PieUtils.HTTP_STATUS_OK_CREATED).intValue()) {
			flag = true;
		}

		return flag;
	}

}