package gov.va.pie.splitter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.integration.splitter.AbstractMessageSplitter;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import gov.va.pie.cdw.model.VAProvider;
import gov.va.pie.utils.PieUtils;

/**
 * 
 * @author AbleVets
 * Splits the message in to equal sized lists. List can hold 1000 {@link  gov.va.pie.cdw.model.VAProvider}
 *
 */

@Component("vaProvidersSplitter")
public class VAProvidersSplitter extends AbstractMessageSplitter{

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<Message<List<VAProvider>>> splitMessage(Message<?> message) {
		
		List<Message<List<VAProvider>>> messages = new ArrayList<Message<List<VAProvider>>>();
		@SuppressWarnings("unchecked")
		List<VAProvider> vaProvidersList = (List<VAProvider>) message.getPayload();
		if(CollectionUtils.isNotEmpty(vaProvidersList)){
			List<List<VAProvider>> listOfVAProviderLists = Lists.partition(vaProvidersList, PieUtils.PPMS_MESSAGAE_LIMIT);
			for (List<VAProvider> vaProvidersSubList : listOfVAProviderLists) {
				Message<List<VAProvider>> msg = MessageBuilder.withPayload(vaProvidersSubList).build();
				messages.add(msg);
			}
		}
		return messages;
	}
	
}
