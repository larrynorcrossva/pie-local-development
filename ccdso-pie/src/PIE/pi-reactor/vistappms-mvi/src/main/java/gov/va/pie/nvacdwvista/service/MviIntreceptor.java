package gov.va.pie.nvacdwvista.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;

import gov.va.pie.common.exceptions.PieException;
import gov.va.pie.common.service.PieLogService;
import gov.va.pie.common.utils.CommonConstants;
import gov.va.pie.nvacdwvista.hl7v3.MCCIIN000002UV01;
import gov.va.pie.nvacdwvista.hl7v3.MCCIMT000200UV01Acknowledgement;
import gov.va.pie.nvacdwvista.hl7v3.MCCIMT000200UV01AcknowledgementDetail;
import gov.va.pie.nvacdwvista.model.MviResponse;
import gov.va.pie.nvacdwvista.util.BeanUtil;

public class MviIntreceptor implements ClientInterceptor {

	@Autowired
	private PieLogService pieLogService;

	private MviResponse mviResponse;

	private boolean logPayload;

	private Unmarshaller unmarshaller;

	String payload = null;
	
	private static final Logger LOG = LogManager.getLogger(MviIntreceptor.class);

	public MviIntreceptor(boolean logPayload) {
		this.logPayload = logPayload;
	}

	@Override
	public void afterCompletion(MessageContext arg0, Exception arg1) throws WebServiceClientException {
		// No-op
	}

	@Override
	public boolean handleFault(MessageContext messageContext) throws WebServiceClientException {
		// No-op

		return true;
	}

	@Override
	public boolean handleRequest(MessageContext messageContext) throws WebServiceClientException {
		if (logPayload) {
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			
			try {

				messageContext.getRequest().writeTo(buffer);
				payload = buffer.toString(java.nio.charset.StandardCharsets.UTF_8.name());
			} catch (UnsupportedEncodingException e) {
				LOG.info("Exception in handling request : ");
				pieLogService.recordDetails(e, CommonConstants.LOG_ERROR);
			} catch (IOException e) {
				LOG.info("Exception in handling request : ");
				pieLogService.recordDetails(e, CommonConstants.LOG_ERROR);
			}
			LOG.info(payload);
		}
		return true;
	}

	@Override
	public boolean handleResponse(MessageContext messageContext) throws WebServiceClientException {
		LOG.info("  Received response  ");
		ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
		try {
			messageContext.getResponse().writeTo(responseStream);

			String bodyXSD = StringUtils.substringBetween(responseStream.toString("UTF-8"), "<env:Body>",
					"</env:Body>");
			String body = bodyXSD.replaceAll(
					"xsi:schemaLocation=\"urn:hl7-org:v3 ../../schema/HL7V3/NE2008/multicacheschemas/MCCI_IN000002UV01.xsd\"",
					"");

			InputStream inputStream = IOUtils.toInputStream(body);
			XMLInputFactory xif = XMLInputFactory.newInstance();
			xif.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
			xif.setProperty(XMLInputFactory.SUPPORT_DTD, false);
			XMLStreamReader xsr = xif.createXMLStreamReader(new StreamSource(inputStream));
			;
			LOG.info(Thread.currentThread().getName() + " running    " );

			JAXBElement<MCCIIN000002UV01> jaxResponse = (JAXBElement<MCCIIN000002UV01>) unmarshaller.unmarshal(xsr,
					MCCIIN000002UV01.class);
			List<MCCIMT000200UV01Acknowledgement> acknowledgement = jaxResponse.getValue().getAcknowledgement();
			MviResponseProcessor mviResponseProcessor = BeanUtil.getBean(MviResponseProcessor.class);

			if (CollectionUtils.isEmpty(acknowledgement)) {
				throw new PieException();
			} else {

				for (MCCIMT000200UV01Acknowledgement mccimt000200uv01Acknowledgement : acknowledgement) {
					MCCIMT000200UV01AcknowledgementDetail acknowledgementDetail = mccimt000200uv01Acknowledgement
							.getAcknowledgementDetail().get(0);
					mviResponseProcessor.populateMessageCode(acknowledgementDetail.getText(),
							mccimt000200uv01Acknowledgement.getTypeCode().getCode());
					mviResponse.setMessage(acknowledgementDetail.getText());
					mviResponse.setVistaCode(mccimt000200uv01Acknowledgement.getTypeCode().getCode());
				}

			}

		} catch (PieException | JAXBException | IOException | XMLStreamException e) {
			LOG.error(payload);
			LOG.error("Exception in handling request : ");
			pieLogService.recordDetails(e, CommonConstants.LOG_ERROR);
		} 

		return true;
	}

	public MviResponse getMviResponse() {
		return mviResponse;
	}

	public void setMviResponse(MviResponse mviResponse) {
		this.mviResponse = mviResponse;
	}

	public Unmarshaller getUnmarshaller() {
		return unmarshaller;
	}

	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}

}