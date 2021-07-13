package gov.va.pie.serviceactivator;

import java.io.IOException;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;

/**
 * Used by activator to process response other than 200
 * @author AbleVets
 *
 */

@Component
public class CustomResponseErrorHandler extends DefaultResponseErrorHandler {

	
	@Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
		 return true;
    }

	
    @Override
    public void handleError(ClientHttpResponse response) throws IOException { 
    	return;
    }
}
