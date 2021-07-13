package gov.va.pie.nvappmscdw.exception;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.ResponseErrorHandler;

@Component
public class VistaPharmacyResponseErrorHandler implements ResponseErrorHandler {

	static final Logger log = LogManager.getLogger(VistaPharmacyResponseErrorHandler.class);

	@Override
	public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
		return (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
				|| httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
	}

	/**
	 * Handle client and server errors
	 */
	@Override
	public void handleError(ClientHttpResponse httpResponse) throws IOException {

		if (httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
			String errorTxt = "PPMS Server response status code:" + httpResponse.getStatusCode() 
					+ " Response body: " + StreamUtils.copyToString(httpResponse.getBody(), Charset.defaultCharset());
			log.info(errorTxt);
			throw new IOException(errorTxt);
		} else if (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {

			// handle CLIENT_ERROR
			if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
				log.info("PPMS Data not found for the matching criteria");
				//throw new IOException("PPMS Data not found for the matching criteria");
			} else {
				log.error("PPMS Client error!");
				throw new IOException("PPMS Client error!");
			}

		}
	}
}