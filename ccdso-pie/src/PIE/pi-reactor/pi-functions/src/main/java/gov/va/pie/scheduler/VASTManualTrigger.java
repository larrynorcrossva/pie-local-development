package gov.va.pie.scheduler;

import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import java.io.IOException;
import java.util.Optional;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;

public class VASTManualTrigger {
    @FunctionName("VASTManualTrigger")
	public HttpResponseMessage run(@HttpTrigger(name = "req", methods = {
			HttpMethod.GET }, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
			final ExecutionContext context) throws IOException {

		PieFunctionUtils pieFunctionUtils = new PieFunctionUtils();
		String status = pieFunctionUtils.triggerEndPoint(context, PieFunctionUtils.SERVER_URL, PieFunctionUtils.VAST_ENDPOINT_URL);



		if ("success".equals(status)) {
			return request.createResponseBuilder(HttpStatus.OK).body("VAST endpoint is triggered.").build();

		} else {
			return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).body(status).build();

		}
	}

}
