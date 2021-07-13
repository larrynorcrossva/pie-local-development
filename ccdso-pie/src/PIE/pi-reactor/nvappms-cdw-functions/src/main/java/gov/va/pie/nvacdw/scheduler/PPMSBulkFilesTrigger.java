package gov.va.pie.nvacdw.scheduler;

import java.io.IOException;
import java.util.Optional;

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
public class PPMSBulkFilesTrigger {

	/**
	 * Fucntion app to manually trigger pie's outbound route
	 * 
	 * @param request
	 * @param context
	 * @return
	 * @throws IOException
	 */
	@FunctionName("CDWPPMSBulkFilesTrigger")
	public HttpResponseMessage run(@HttpTrigger(name = "req", methods = {
			HttpMethod.GET }, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
			final ExecutionContext context) throws IOException {
        context.getLogger().info("PPMS Bulk files trigger received a request.");

        String folderName = request.getQueryParameters().get("directory");
        String bulkProcessURL = PieFunctionUtils.PPMS_ENDPOINT_URL + "?mode=manual&directory=" + folderName;
        context.getLogger().info("PPMS Bulk files URL:" + bulkProcessURL);

        PieFunctionUtils pieFunctionUtils = new PieFunctionUtils();

		String status = pieFunctionUtils.triggerEndPoint(
            context, 
            PieFunctionUtils.SERVER_URL,
            bulkProcessURL
            );
		if ("success".equals(status)) {
			return request.createResponseBuilder(HttpStatus.OK).body("PPMS CDW bulk process is done.").build();

		} else {
			return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).body(status).build();

		}
	}

	
}
