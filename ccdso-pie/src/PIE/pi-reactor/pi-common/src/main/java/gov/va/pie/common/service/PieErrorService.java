package gov.va.pie.common.service;

import java.sql.Timestamp;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.va.pie.common.entities.App;
import gov.va.pie.common.entities.PieError;
import gov.va.pie.common.repositories.PieErrorRepository;

@Service
public class PieErrorService {
	
	@Autowired
	private PieErrorRepository pieErrorRepository;
	
	
	public void captureError(Exception exception, App app) {
		
		PieError pieError = populatePieError(exception, app);
		pieErrorRepository.save(pieError);
	}
	
	public void capturePPMSUpdateError (Exception exception, App app, Integer page) {
		
		PieError pieError = populatePieError(exception, app);
		pieError.setPage(page);
		pieErrorRepository.save(pieError);
	}
	
	private PieError populatePieError(Exception exception, App app) {
		
		PieError pieError = new PieError();
		String message = exception.getMessage();
		String stackTrace = ExceptionUtils.getStackTrace(exception);
		pieError.setApp(app);
		pieError.setMessage(message);
		pieError.setStacktrace(stackTrace);
		pieError.setTime(new Timestamp(System.currentTimeMillis()));
		return pieError;
	}

}
