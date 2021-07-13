package gov.va.pie.common.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class PieLogService {
	
	public void recordDetails(Exception e, String level) {
		String details = extractDetails(e);
		switch (level) {
			case "info": 
				record.info(details, e);
			case "error":
				record.error(details, e);
			case "warn":
				record.warn(details, e);
			default:
				record.debug(details, e);
		}
		
	}
	
	public void recordMessage(String message) {
		record.info(message);
	}
	
	private String extractDetails(Exception e) {
		String details = e.getMessage();
		return details;
	}
}
