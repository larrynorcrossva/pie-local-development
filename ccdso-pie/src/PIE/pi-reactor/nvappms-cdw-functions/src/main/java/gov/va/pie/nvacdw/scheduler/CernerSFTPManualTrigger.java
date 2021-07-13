package gov.va.pie.nvacdw.scheduler;

import java.io.IOException;
import java.util.Optional;

import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.ExecutionContext;

public class CernerSFTPManualTrigger {
    @FunctionName("CernerSFTPManualTrigger")
	public HttpResponseMessage run(@HttpTrigger(name = "req", methods = {
			HttpMethod.GET }, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
			final ExecutionContext context) throws IOException {
        context.getLogger().info("Cerner SFTP trigger received a request.");

        PieFunctionUtils pieFunctionUtils = new PieFunctionUtils();
		String status = pieFunctionUtils.triggerEndPoint(context,PieFunctionUtils.SERVER_URL,PieFunctionUtils.CERNER_ENDPOINT_URL);
		if ("success".equals(status)) {
			return request.createResponseBuilder(HttpStatus.OK).body("Cerner SFTP trigger succeed.").build();

		} else {
			return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).body(status).build();

		}
	}
}
