package gov.va.pie.nva.scheduler;

import java.io.IOException;
import java.time.LocalDateTime;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.TimerTrigger;

/**
 * Azure Functions with Timer trigger.
 */
public class PPMSDailyTrigger {

	
	
	/**
	 * This function will be invoked periodically according to the specified
	 * schedule.
	 * @throws IOException 
	 */
	@FunctionName("VistaPPMSDailyTrigger")
	public void run(@TimerTrigger(name = "timerInfo", schedule = "0 30 14 * * *") String timerInfo,
			final ExecutionContext context) throws IOException {
		context.getLogger().info(" Timer trigger function executed at: " + LocalDateTime.now());
		PieFunctionUtils pieFunctionUtils = new PieFunctionUtils();
		pieFunctionUtils.triggerEndPoint(context,PieFunctionUtils.SERVER_URL, PieFunctionUtils.PPMS_ENDPOINT_URL);
	}
	
	


}
