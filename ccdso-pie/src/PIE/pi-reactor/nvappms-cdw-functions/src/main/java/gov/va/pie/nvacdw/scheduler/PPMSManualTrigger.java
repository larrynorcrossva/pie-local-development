package gov.va.pie.nvacdw.scheduler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Optional;
import java.util.Properties;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

/**
 * Azure Functions with HTTP Trigger.
 */
public class PPMSManualTrigger {

	/**
	 * Fucntion app to manually trigger pie's outbound route
	 * 
	 * @param request
	 * @param context
	 * @return
	 * @throws IOException
	 */
	@FunctionName("VistaPpmsCdwManualTrigger")
	public HttpResponseMessage run(@HttpTrigger(name = "req", methods = {
			HttpMethod.GET }, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
			final ExecutionContext context) throws IOException {

		PieFunctionUtils pieFunctionUtils = new PieFunctionUtils();
		String status = pieFunctionUtils.triggerEndPoint(context,PieFunctionUtils.SERVER_URL,PieFunctionUtils.PPMS_ENDPOINT_URL);
		if ("success".equals(status)) {
			return request.createResponseBuilder(HttpStatus.OK).body("Azure AD token request failed.").build();

		} else {
			return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).body(status).build();

		}
	}

	
}
