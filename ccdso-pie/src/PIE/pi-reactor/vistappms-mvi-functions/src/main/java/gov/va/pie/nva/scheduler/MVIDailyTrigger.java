package gov.va.pie.nva.scheduler;

import java.io.IOException;
import java.time.LocalDateTime;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.TimerTrigger;

/**
 * Azure Functions with Timer trigger.
 */
public class MVIDailyTrigger {

	
	
	/**
	 * This function will be invoked periodically according to the specified
	 * schedule.
	 * @throws IOException 
	 */
	@FunctionName("VistaCdwMviDailyTrigger")
	public void run(@TimerTrigger(name = "timerInfo", schedule = "0 0 2 * * *") String timerInfo,
			final ExecutionContext context) throws IOException {
		context.getLogger().info(" Timer trigger function executed at: " + LocalDateTime.now());
		PieFunctionUtils pieFunctionUtils = new PieFunctionUtils();
		pieFunctionUtils.triggerEndPoint(context,PieFunctionUtils.SERVER_URL, PieFunctionUtils.MVI_ENDPOINT_URL);
	}
	
	


}
