package gov.va.pie.nvappms.cdw.serviceactivator;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import gov.va.pie.common.entities.CernerResponse;
import gov.va.pie.common.repositories.CernerResponseRepository;
@Component("integrationActivator")
public class IntegrationActivator {
	
	static final Logger LOG = LogManager.getLogger(IntegrationActivator.class);
	
	@Autowired
	private SFTPActivator sftp;
	
	@Autowired
	private CernerResponseRepository cernerResponses;
	
	@ServiceActivator
	@Async
	public void weeklyTask(Message<String> message) {
		
		CernerResponse lastSuccess = cernerResponses.findLastSuccess();
		CernerResponse currentRun = new CernerResponse();
		currentRun.setFromDate(lastSuccess.getToDate());
		currentRun.setToDate(new java.sql.Date(new java.util.Date().getTime()));
		currentRun.setStatus("Started");
		cernerResponses.save(currentRun);
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
			String strFrom = sdf.format(currentRun.getFromDate());
			String strTo = sdf.format(currentRun.getToDate());
			LOG.info(strFrom + " " + strTo);
			Message<String> sftpParams = new GenericMessage<String>(strFrom + " " + strTo);
			sftp.sftpTask(sftpParams);
			currentRun.setStatus("Success");
			cernerResponses.save(currentRun);
		} catch(IOException ex) {
			currentRun.setStatus("Error");
			currentRun.setErrorMsg(ex.getMessage());
			cernerResponses.save(currentRun);
		} catch (IllegalArgumentException e) {
			currentRun.setStatus("Error");
			currentRun.setErrorMsg(e.getMessage());
			cernerResponses.save(currentRun);
		}
		
	}
}