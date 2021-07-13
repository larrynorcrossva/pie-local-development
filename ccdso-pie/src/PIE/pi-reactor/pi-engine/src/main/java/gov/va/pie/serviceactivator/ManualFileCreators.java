package gov.va.pie.serviceactivator;

import java.io.FileWriter;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.xml.transform.StringResult;

import gov.va.pie.common.entities.App;
import gov.va.pie.common.service.PieErrorService;
import gov.va.pie.ppms.model.Providers;
import gov.va.pie.utils.PieUtils;

@Component("manualFileCreators")
public class ManualFileCreators {
	static Logger LOG = LogManager.getLogger(ManualFileCreators.class);

	@Autowired
	private PieErrorService pieErrorService;	
	

	/**
	 * Writes data to file
	 * 
	 * @param ppmsProvidersMsg - {@link org.springframework.messaging.Message}
	 *                         object with
	 *                         {@link org.springframework.xml.transform,StringResult}
	 *                         as pay load
	 * @throws IOException - throws IOException
	 */

	public Message<ResponseEntity<String>> postPPMSProviders(Message<StringResult> ppmsProvidersMsg) {
		StringResult providersXML = ppmsProvidersMsg.getPayload();
		String conversationId = (String) ppmsProvidersMsg.getHeaders().get("X-ConversationID");
		Providers providers = (Providers) ppmsProvidersMsg.getHeaders().get(PieUtils.ORIGINAL_PAYLOAD);
		String transactionId = (String) ppmsProvidersMsg.getHeaders().get(PieUtils.TRANSACTION_ID);
		Message<ResponseEntity<String>> message = null;
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(conversationId + ".xml");
			fileWriter.write(providersXML.toString());
			ResponseEntity<String> responseEntity = new ResponseEntity<String>(HttpStatus.OK);
			message = MessageBuilder.withPayload(responseEntity).setHeader(PieUtils.ORIGINAL_PAYLOAD, providers)
					.setHeader(PieUtils.TRANSACTION_ID, transactionId).build();
			LOG.info("done writing file  " + conversationId + ".xml");
		} catch (IOException e) {
			LOG.error("Error in writing the file with conversatin id " + conversationId);
			pieErrorService.captureError(e, App.PIE);
		} finally {
			if (fileWriter != null)
				try {
					fileWriter.close();
				} catch (IOException e) {
					LOG.error("Error in closing the file with conversatin id " + conversationId);
				}
		}
		return message;
	}

}