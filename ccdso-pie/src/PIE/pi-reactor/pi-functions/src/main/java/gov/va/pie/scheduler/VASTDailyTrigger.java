package gov.va.pie.scheduler;

import java.io.IOException;
import java.time.LocalDateTime;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.TimerTrigger;

public class VASTDailyTrigger {
    /**
	 * This function will be invoked periodically according to the specified
	 * schedule.
	 * @throws IOException 
	 */
	@FunctionName("VASTDailyTrigger")
	public void run(@TimerTrigger(name = "timerInfo", schedule = "0 0 0 1 * *") String timerInfo,
			final ExecutionContext context) throws IOException {
		context.getLogger().info("VAST Timer trigger function executed at: " + LocalDateTime.now());
		PieFunctionUtils pieFunctionUtils = new PieFunctionUtils();
        String status = pieFunctionUtils.triggerEndPoint(context, PieFunctionUtils.SERVER_URL, PieFunctionUtils.VAST_ENDPOINT_URL);        
        context.getLogger().info(status);
	}
}
