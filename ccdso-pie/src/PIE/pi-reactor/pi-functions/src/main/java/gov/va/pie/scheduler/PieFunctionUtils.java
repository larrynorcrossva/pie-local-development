package gov.va.pie.scheduler;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.microsoft.azure.functions.ExecutionContext;

import gov.va.pie.scheduler.AadToken;

public class PieFunctionUtils {

	public static final String SERVER_URL = "https://pie.ppms.va.gov";
	private CloseableHttpClient httpClient;
	private static final String TOKEN_TYPE = "Bearer";
	public static final String VA_PROVIDER_ENDPOINT_URL = SERVER_URL + "/pie/ppms/triggers/outbound";
	public static final String VAST_ENDPOINT_URL = SERVER_URL + "/pie/ppms/triggers/vast/outbound";

	public RestTemplate getRestTemplateAcceptAllCets() {
		try {
			TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
				@Override
				public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
					return true;
				}
			};
			SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
					.loadTrustMaterial(null, acceptingTrustStrategy).build();
			SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
			httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
			HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
			requestFactory.setHttpClient(httpClient);
			RestTemplate restTemplate = new RestTemplate(requestFactory);
			return restTemplate;
		} catch (KeyManagementException | KeyStoreException | NoSuchAlgorithmException e1) {
			return null;
		}
	}

	public String triggerEndPoint(ExecutionContext context, String serverUrl, String endPoint) throws IOException {
	
		context.getLogger().info("Java HTTP trigger processed a request.");

		String msiEndPoint = System.getenv("MSI_ENDPOINT");

		String secret = System.getenv("MSI_SECRET");
		if (secret == null || secret.equals("")) {
			context.getLogger().warning("Pie function reading msi secret fails");
			return "Read configuration failed";

		} else {
			context.getLogger().info("msi secret read successes.");

			RestTemplate restTemplate = new RestTemplate();

			// Request from AAD secured server
			// Set content type
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Secret", secret);
			headers.add("Ocp-APIM-Subscription-key", ppmsTestKey);

			HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

			// Request AAD access token
			Map<String, String> params = new HashMap<String, String>();
			params.put("resource", SERVER_URL);
			params.put("api-version", "2017-09-01");

			MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
			converter.setSupportedMediaTypes(
					Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM }));

			restTemplate.setMessageConverters(Arrays.asList(converter, new FormHttpMessageConverter()));

			ResponseEntity<AadToken> aadTokenResponse = restTemplate.exchange(
					msiEndPoint + "?resource={resource}&api-version={api-version}",
					org.springframework.http.HttpMethod.GET, entity, AadToken.class, params);

			org.springframework.http.HttpStatus httpStatus = aadTokenResponse.getStatusCode();
			context.getLogger().info("AAD token http status: " + httpStatus.value());
			AadToken accessToken = aadTokenResponse.getBody();
			AadToken validatedAccessToken = getValidatedToken(context, accessToken);
			if (StringUtils.isEmpty(validatedAccessToken))
				return "Azure AD token validation failed.";

			if (httpStatus == org.springframework.http.HttpStatus.OK && !StringUtils.isEmpty(validatedAccessToken)) {
				RestTemplate serviceRestTemplate;
				serviceRestTemplate = getRestTemplateAcceptAllCets();

				// Prepare to load AAD access token
				HttpHeaders serviceHeaders = new HttpHeaders();
				serviceHeaders.add("Content-Type", MediaType.TEXT_XML_VALUE);
				serviceHeaders.add("Authorization", "Bearer " + validatedAccessToken.getAccess_token());
				serviceHeaders.add("Ocp-APIM-Subscription-key", ppmsTestKey);
				HttpEntity<String> serviceEntity = new HttpEntity<>("parameters", serviceHeaders);

				ResponseEntity<String> serviceResponse = serviceRestTemplate.exchange(
					endPoint, 
					org.springframework.http.HttpMethod.POST, serviceEntity,
					String.class);

				org.springframework.http.HttpStatus serviceHttpStatus = serviceResponse.getStatusCode();
				context.getLogger().info("Service http status: " + serviceHttpStatus.value());
				context.getLogger().info("Pie function is get called!");

				if (serviceHttpStatus == org.springframework.http.HttpStatus.OK) {
					httpClient.close();
					return "success";
				} else {
					httpClient.close();
					return serviceResponse.getBody();
				}

			} else {

				return "Azure AD token request failed.";
			}
		}
	}

	private AadToken getValidatedToken(ExecutionContext context, AadToken token) {

		if (context == null || token == null)
			return null;

		if (!TOKEN_TYPE.equals(token.getToken_type())) {
			context.getLogger().info(" Token Type is invalid ");
			return null;
		} else if (!SERVER_URL.equals(token.getResource())) {
			context.getLogger().info(" Resource is invalid ");
			return null;
		} else {
			SimpleDateFormat sdFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a XXX");
			Date expiresOnDate;
			try {
				expiresOnDate = sdFormat.parse(token.getExpires_on());
			} catch (ParseException e) {
				context.getLogger().info(" Date parse exception ");
				return null;
			}
			Date now = new Date();
			if (now.after(expiresOnDate)) {
				context.getLogger().info(" Token expired");
				return null;
			}
		}

		List<String> invalidSubStrings = new ArrayList<String>();
		invalidSubStrings.add("\n");
		invalidSubStrings.add("\r");
		invalidSubStrings.add("%0d");
		invalidSubStrings.add("%0a");
		String access_token = token.getAccess_token();
		for (String invalidSubString : invalidSubStrings) {
			if (access_token.contains(invalidSubString)) {
				context.getLogger().info(" Token contains invalid chars, validation failed ");
				return null;
			}	
		}
		return token;
	}
}
