package gov.va.pie.service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import gov.va.pie.cdw.vast.model.Address;
import gov.va.pie.cdw.vast.model.Facilities;
import gov.va.pie.cdw.vast.model.Facility;
import gov.va.pie.cdw.vast.model.FacilityType;
import gov.va.pie.cdw.vast.model.RuralityType;
import gov.va.pie.common.entities.App;
import gov.va.pie.common.exceptions.PieException;
import gov.va.pie.common.service.PieErrorService;
import gov.va.pie.common.service.PieLogService;
import gov.va.pie.common.utils.CommonConstants;

@Component("vastActivator")
public class VastService {

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private PieErrorService pieErrorService;
	
	@Autowired
	private PieLogService pieLogService;

	private static final Logger LOG = LogManager.getLogger(VastService.class);

	@Value("${pie.vast.ppmsurl}")
	private String ppmsUrl;
	@Value("${pie.vast.ppmsKey}")
	private String ppmsKey;
	
	private static int STATION_NUMBER_INDEX = 0;
	private static int STATION_NAME_INDEX = 1;
	private static int FACILITY_INDEX = 2;
	private static int FACILITY_TYPE_INDEX = 3;
	private static int VISN_INDEX = 4;
	private static int ADDRESS1_INDEX = 5;
	private static int ADDRESS2_INDEX = 6;
	private static int ADDRESS3_INDEX = 7;
	private static int CITY_INDEX = 8;
	private static int STATE_INDEX = 9;
	private static int POSTAL_CODE_INDEX = 10;
	private static int FAX_INDEX = 11;
	private static int PHONE_INDEX = 12;
	private static int RURALITY_INDEX = 13;
	private static int PARENT_STATION_NUMBER_INDEX = 14;
	private static int LATITUDE_INDEX = 15;
	private static int LONGITUDE_INDEX = 16;

	@ServiceActivator
	@Async
	public void sendVastData() throws IOException, JAXBException {

		String whereClause = "ORDER BY parentrank, parentstationnumber";

		@SuppressWarnings("unchecked")
		List<Object[]> vastSites = entityManager
				.createNativeQuery(
						" Select distinct * From APP." + CommonConstants.DB_ENV + "PPMS_Vast_Sites_V " + whereClause)
				.getResultList();

		Facilities facilities = new Facilities();
		for (Object[] vast : vastSites) {
			Facility facility = new Facility();

			facility.setStationNumber(vast[STATION_NUMBER_INDEX].toString());
			facility.setStationName(vast[STATION_NAME_INDEX].toString());
			facility.setFacility(vast[FACILITY_INDEX].toString());

			switch (vast[FACILITY_TYPE_INDEX].toString()) {

			case "VAMC":
				facility.setFacilityType(FacilityType.VAMC);
				break;
			case "PC CBOC":
				facility.setFacilityType(FacilityType.PC_CBOC);
				break;
			case "OOS":
				facility.setFacilityType(FacilityType.OOS);
				break;
			case "MS CBOC":
				facility.setFacilityType(FacilityType.MS_CBOC);
				break;
			case "HCC":
				facility.setFacilityType(FacilityType.HCC);
				break;
			case "CLC":
				facility.setFacilityType(FacilityType.CLC);
				break;
			case "DRRTP":
				facility.setFacilityType(FacilityType.MH_RRTP_DRRTP);
				break;

			}

			facility.setVisn(vast[VISN_INDEX].toString());

			Address address = new Address();
			address.setAddress1(vast[ADDRESS1_INDEX].toString());
			address.setAddress2(vast[ADDRESS2_INDEX].toString());
			address.setAddress3(vast[ADDRESS3_INDEX].toString());
			address.setCity(vast[CITY_INDEX].toString());
			address.setState(vast[STATE_INDEX].toString());
			address.setPostalCode(vast[POSTAL_CODE_INDEX].toString());
			facility.setFax(vast[FAX_INDEX].toString());
			facility.setPhone(vast[PHONE_INDEX].toString());

			facility.setAddress(address);

			switch (vast[RURALITY_INDEX].toString()) {
			case "U":
				facility.setRurality(RuralityType.U);
				break;
			case "R":
				facility.setRurality(RuralityType.R);
				break;
			case "H":
				facility.setRurality(RuralityType.H);
				break;
			case "I":
				facility.setRurality(RuralityType.I);
				break;

			}

			facility.setParentStationNumber(vast[PARENT_STATION_NUMBER_INDEX].toString());

			try {
				if (vast[LATITUDE_INDEX] != null && vast[LATITUDE_INDEX].toString() != null)
					facility.setLatitude(Float.parseFloat(vast[LATITUDE_INDEX].toString()));
				if (vast[LONGITUDE_INDEX] != null && vast[LONGITUDE_INDEX].toString() != null)
					facility.setLongitude(Float.parseFloat(vast[LONGITUDE_INDEX].toString()));
			} catch(NumberFormatException e)
			{
				pieLogService.recordDetails(e, CommonConstants.LOG_ERROR);
				pieErrorService.captureError(e, App.PIE);
			}

			facilities.getFacility().add(facility);

		}
		
		try {
			callPPMSEndPoint(facilities);
		} 
		catch (RestClientException e) {
			pieErrorService.captureError(e, App.PIE);
			pieLogService.recordDetails(e, CommonConstants.LOG_ERROR);
		} 
		catch (PieException e) {
			pieLogService.recordDetails(e, CommonConstants.LOG_ERROR);
			pieErrorService.captureError(e, App.PIE);
		}

	}

	public void callPPMSEndPoint(Facilities facilities) throws PieException, RestClientException {

		if (StringUtils.isEmpty(ppmsUrl)) {
			throw new PieException("Invalid Vast URl");
		}

		JAXBContext jaxbContext = null;
		try {
			jaxbContext = JAXBContext.newInstance(Facilities.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			StringWriter sw = new StringWriter();
			jaxbMarshaller.marshal(facilities, sw);
			String xmlContent = sw.toString();
			
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_XML);
			if (pieMode == 'P' && ppmsProdKey != NULL) {
				// Will contain the Subscription Key for Production
				headers.add("Ocp-APIM-Subscription-key", ppmsProdKey);
			}
			else
			{
				headers.add("Ocp-APIM-Subscription-key", ppmsTestKey);
			}
			headers.add("Ocp-APIM-Subscription-key", ppmsTestKey);
			headers.add("X-ConversationID", UUID.randomUUID().toString());
			headers.add("X-RoutingSenderID", "VA_Network");
			headers.add("X-RoutingReceiverIDs", "VA_PPMS");
			headers.add("X-TransactionID", UUID.randomUUID().toString());
			headers.add("Expect", "100-continue");
			
			HttpEntity<String> facilityRequest = new HttpEntity<String>(xmlContent, headers);
			ResponseEntity<String> response = restTemplate.postForEntity(ppmsUrl, facilityRequest, String.class);
			if (response.getStatusCode() != HttpStatus.OK && response.getStatusCode() != HttpStatus.CREATED) {
				LOG.error(" Error not able to post the data,  Status Code  :" + response.getStatusCode());
				throw new PieException("PPMS IWS VAST Post failed with msg:" + xmlContent);
			}
			else {
				LOG.info("VAST service succeeded with status code: " + response.getStatusCode());
			}
		} catch (JAXBException e) {
			pieErrorService.captureError(e, App.PIE);
			pieLogService.recordDetails(e, CommonConstants.LOG_ERROR);
		}

	}

}
