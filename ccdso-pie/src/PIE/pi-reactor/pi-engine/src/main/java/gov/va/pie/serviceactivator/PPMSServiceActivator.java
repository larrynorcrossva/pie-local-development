package gov.va.pie.serviceactivator;

import java.io.IOException;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.xml.transform.StringResult;




@Component("ppmsServiceActivator")
public class PPMSServiceActivator {
	static Logger LOG = LogManager.getLogger(PPMSServiceActivator.class);
	

	
	
	/**
	 *Passes data to next channel
	 * 
	 * @param ppmsProvidersMsg
	 *            - {@link org.springframework.messaging.Message} object with
	 *            {@link org.springframework.xml.transform,StringResult} as pay
	 *     	       load
	 * @throws IOException
	 *             - throws IOException
	 */
	
	public Message<String> postPPMSProviders(Message<StringResult> ppmsProvidersMsg){
		StringResult providersXML = ppmsProvidersMsg.getPayload();
		LOG.info(providersXML.toString());
		String providersXMLString=providersXML.toString();
		
		Message<String> message = MessageBuilder.withPayload(providersXMLString).build();
		return message;
	}
	
	public String getTransactionId() {
		UUID uuId = UUID.randomUUID();
		return uuId.toString();
	}
	
	
}