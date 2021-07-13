package gov.va.pie.nvacdwvista.service;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.oxm.XmlMappingException;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;

import gov.va.pie.nvacdwvista.hl7v3.PRPAIN201301UV02;
import gov.va.pie.nvacdwvista.hl7v3.PRPAIN201302UV02;
import gov.va.pie.nvacdwvista.util.SoapConstants;

public class SOAPConnector extends WebServiceGatewaySupport {

	private static final Logger LOG = LogManager.getLogger(SOAPConnector.class);
	private MviIntreceptor mviIntreceptor;

	public void callWebService(String url, Object request, String messageType) {
		try {
			ClientInterceptor[] interceptors = new ClientInterceptor[] { mviIntreceptor };
			setInterceptors(interceptors);
			getWebServiceTemplate().marshalSendAndReceive(url, request);
		} catch ( XmlMappingException | WebServiceClientException e) {
			JAXBContext jc;
			Marshaller soapConnectorMarshaller = null;
			try {
				if (SoapConstants.MESSAGE_TYPE_1301.equalsIgnoreCase(messageType))
					jc = JAXBContext.newInstance(PRPAIN201301UV02.class);
				else 
					jc = JAXBContext.newInstance(PRPAIN201302UV02.class);
				soapConnectorMarshaller = jc.createMarshaller();
				soapConnectorMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				LOG.error(e.getMessage(), e);
				StringWriter sw = new StringWriter();
				soapConnectorMarshaller.marshal(request, sw);
				LOG.error(sw.toString());
			} catch (JAXBException ex) {
				LOG.error("Error in SOAPConnector exception handling unmarshalling ", ex);
			}
		}
	}

	public MviIntreceptor getMviIntreceptor() {
		return mviIntreceptor;
	}

	public void setMviIntreceptor(MviIntreceptor mviIntreceptor) {
		this.mviIntreceptor = mviIntreceptor;
	}

}