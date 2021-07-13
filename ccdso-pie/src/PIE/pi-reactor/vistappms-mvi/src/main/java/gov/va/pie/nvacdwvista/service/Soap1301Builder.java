package gov.va.pie.nvacdwvista.service;

import java.util.Date;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.apache.commons.lang3.StringUtils;

import gov.va.pie.nvacdwvista.hl7v3.AD;
import gov.va.pie.nvacdwvista.hl7v3.ActClassControlAct;
import gov.va.pie.nvacdwvista.hl7v3.CD;
import gov.va.pie.nvacdwvista.hl7v3.COCTMT090100UV01AssignedPerson;
import gov.va.pie.nvacdwvista.hl7v3.COCTMT090100UV01Person;
import gov.va.pie.nvacdwvista.hl7v3.CS;
import gov.va.pie.nvacdwvista.hl7v3.EN;
import gov.va.pie.nvacdwvista.hl7v3.MFMIMT700701UV01DataEnterer;
import gov.va.pie.nvacdwvista.hl7v3.ObjectFactory;
import gov.va.pie.nvacdwvista.hl7v3.PRPAIN201301UV02;
import gov.va.pie.nvacdwvista.hl7v3.PRPAIN201301UV02MFMIMT700701UV01ControlActProcess;
import gov.va.pie.nvacdwvista.hl7v3.PRPAIN201301UV02MFMIMT700701UV01RegistrationEvent;
import gov.va.pie.nvacdwvista.hl7v3.PRPAIN201301UV02MFMIMT700701UV01Subject1;
import gov.va.pie.nvacdwvista.hl7v3.PRPAIN201301UV02MFMIMT700701UV01Subject2;
import gov.va.pie.nvacdwvista.hl7v3.PRPAMT201301UV02OtherIDs;
import gov.va.pie.nvacdwvista.hl7v3.PRPAMT201301UV02Patient;
import gov.va.pie.nvacdwvista.hl7v3.PRPAMT201301UV02Person;
import gov.va.pie.nvacdwvista.hl7v3.ParticipationTargetSubject;
import gov.va.pie.nvacdwvista.hl7v3.TEL;
import gov.va.pie.nvacdwvista.hl7v3.XActMoodIntentEvent;
import gov.va.pie.nvacdwvista.model.MviModel;
import gov.va.pie.nvacdwvista.model.SoapBody;
import gov.va.pie.nvacdwvista.model.SoapHeader;
import gov.va.pie.nvacdwvista.util.Soap1301Assembler;
import gov.va.pie.nvacdwvista.util.SoapConstants;

public class Soap1301Builder {
	
	public PRPAIN201301UV02 buildMessage(MviModel mviModel, String processingCode) {

		ObjectFactory factory = new ObjectFactory();
		PRPAIN201301UV02 msg = factory.createPRPAIN201301UV02();
		msg.setITSVersion("XML_1.0");
		SoapHeader mviHeaderInfo = Soap1301Assembler.setupHl7v3Header(SoapConstants.EXTENSION, new Date(), processingCode);
		msg.setId(mviHeaderInfo.getId());
		msg.setCreationTime(mviHeaderInfo.getCreationTime());
		msg.setVersionCode(mviHeaderInfo.getVersionCode());
		msg.setInteractionId(mviHeaderInfo.getInteractionId());
		msg.setProcessingCode(mviHeaderInfo.getProcessingCode());
		msg.setProcessingModeCode(mviHeaderInfo.getProcessingModeCode());
		msg.setAcceptAckCode(mviHeaderInfo.getAcceptAckCode());
		msg.getReceiver().add(mviHeaderInfo.getReceiver());
		msg.setSender(mviHeaderInfo.getSender());

		PRPAIN201301UV02MFMIMT700701UV01ControlActProcess cap = getControlProcess(factory, mviModel);
		msg.setControlActProcess(cap);

		return msg;
	}

	private PRPAIN201301UV02MFMIMT700701UV01ControlActProcess getControlProcess(ObjectFactory factory,
			MviModel mviModel) {
		PRPAIN201301UV02MFMIMT700701UV01ControlActProcess cap = new PRPAIN201301UV02MFMIMT700701UV01ControlActProcess();

		SoapBody bodyHelper = Soap1301Assembler.setupHl7v3Body(mviModel);
		cap.setMoodCode(XActMoodIntentEvent.EVN);
		cap.setClassCode(ActClassControlAct.CACT);

		CD cntrlCode = new CD();
		cntrlCode.setCode("PRPA_TE201301UV02");
		cntrlCode.setCodeSystem("2.16.840.1.113883.1.6");
		cap.setCode(cntrlCode);
		
		//DataEnterer
		MFMIMT700701UV01DataEnterer dataEnterer = factory.createMFMIMT700701UV01DataEnterer();
		dataEnterer.getTypeCode().add("ENT");
		dataEnterer.setContextControlCode("AP");
		
		COCTMT090100UV01AssignedPerson assignedPerson = factory.createCOCTMT090100UV01AssignedPerson();
		assignedPerson.setClassCode("ASSIGNED");
		
		final QName _COCTMT090100UV01Person_QNAME = new QName("urn:hl7-org:v3", "assignedPerson");
		JAXBElement<COCTMT090100UV01Person> personJaxB = new JAXBElement<COCTMT090100UV01Person>(
				_COCTMT090100UV01Person_QNAME, COCTMT090100UV01Person.class,
				PRPAMT201301UV02Patient.class, null);
		COCTMT090100UV01Person person  = factory.createCOCTMT090100UV01Person();
		person.setDeterminerCode("INSTANCE");
		person.getClassCode().add("PSN");
		EN personName = new EN();
		personName.getContent().add("PPMS PIE");
		person.getName().add(personName);
		personJaxB.setValue(person);
		assignedPerson.setAssignedPerson(personJaxB);
		dataEnterer.setAssignedPerson(assignedPerson);
		cap.getDataEnterer().add(dataEnterer);
		
		
		PRPAIN201301UV02MFMIMT700701UV01Subject1 subj = new PRPAIN201301UV02MFMIMT700701UV01Subject1();
		PRPAIN201301UV02MFMIMT700701UV01RegistrationEvent regEvent = new PRPAIN201301UV02MFMIMT700701UV01RegistrationEvent();
		subj.getTypeCode().add("SUBJ");
		regEvent.getMoodCode().add("EVN");
		regEvent.getClassCode().add("REG");
		// <id nullFlavor="NA"/>
		regEvent.getId().add(bodyHelper.getRegNullFlavor());

		// <statusCode code="active"/>
		CS regEventStatusCode = new CS();
		regEventStatusCode.setCode("active");
		regEvent.setStatusCode(regEventStatusCode);

		// <subject1 typeCode="SBJ">
		PRPAIN201301UV02MFMIMT700701UV01Subject2 subj2 = new PRPAIN201301UV02MFMIMT700701UV01Subject2();
		subj2.setTypeCode(ParticipationTargetSubject.SBJ);
		regEvent.setSubject1(subj2);

		// <patient classCode="PAT">
		PRPAMT201301UV02Patient patient = new PRPAMT201301UV02Patient();
		patient.getClassCode().add("PAT");

 		// <id nullFlavor="ASKU"/>
		patient.getId().add(bodyHelper.getPatientNullFlavor());

		subj2.setPatient(patient);
		CS patientStatusCode = new CS();
		patientStatusCode.setCode("active");
		// <statusCode code="active"/>
		patient.setStatusCode(patientStatusCode);

		// <patientPerson>
		// <!--NAME-->
		final QName _PRPAMT201303UV02PatientPatientPerson_QNAME = new QName("urn:hl7-org:v3", "patientPerson");
		JAXBElement<PRPAMT201301UV02Person> patPerson = new JAXBElement<PRPAMT201301UV02Person>(
				_PRPAMT201303UV02PatientPatientPerson_QNAME, PRPAMT201301UV02Person.class,
				PRPAMT201301UV02Patient.class, null);
		PRPAMT201301UV02Person patientPerson = factory.createPRPAMT201301UV02Person();

		// Name
		patientPerson.getName().add(bodyHelper.getPatientName());

		// Phone

		for (TEL phone : bodyHelper.getTelephoneList()) {
			patientPerson.getTelecom().add(phone);
		}

		// Gender
		if(bodyHelper.getGender()!= null){
			if (!StringUtils.isEmpty((bodyHelper.getGender().getCode())))
				patientPerson.setAdministrativeGenderCode(bodyHelper.getGender());
		}
		
		
		// address
		for (AD address : bodyHelper.getAddressList()) {
			patientPerson.getAddr().add(address);
		}

		for (PRPAMT201301UV02OtherIDs id : bodyHelper.getAsOtherIds()) {
			patientPerson.getAsOtherIDs().add(id);
		}

		// end of 1301
		patPerson.setValue(patientPerson);
		patient.setPatientPerson(patPerson);

		// Custodian
		regEvent.setCustodian(bodyHelper.getCustodian());
		subj.setRegistrationEvent(regEvent);
		cap.getSubject().add(subj);

		return cap;
	}
	
}
