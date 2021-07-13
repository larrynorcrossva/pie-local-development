package gov.va.pie.common.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import gov.va.pie.common.service.PieLogService;

@Service
public class ScheduledTask {
	
	@Autowired
	private PieLogService pieLogService;
	
	
	@Scheduled(fixedRateString = "${cron.fixed.rate}")
	public void pingLogger() {
		pieLogService.recordMessage("***  Pinging Logger ***");
	}

}
