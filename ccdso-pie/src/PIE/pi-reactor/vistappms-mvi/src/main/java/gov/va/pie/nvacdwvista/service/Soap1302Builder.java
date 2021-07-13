package gov.va.pie.nvacdwvista.service;

import java.util.Date;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.apache.commons.lang3.StringUtils;

import gov.va.pie.nvacdwvista.hl7v3.AD;
import gov.va.pie.nvacdwvista.hl7v3.ActClassControlAct;
import gov.va.pie.nvacdwvista.hl7v3.CS;
import gov.va.pie.nvacdwvista.hl7v3.ObjectFactory;
import gov.va.pie.nvacdwvista.hl7v3.PRPAIN201302UV02;
import gov.va.pie.nvacdwvista.hl7v3.PRPAIN201302UV02MFMIMT700701UV01ControlActProcess;
import gov.va.pie.nvacdwvista.hl7v3.PRPAIN201302UV02MFMIMT700701UV01RegistrationEvent;
import gov.va.pie.nvacdwvista.hl7v3.PRPAIN201302UV02MFMIMT700701UV01Subject1;
import gov.va.pie.nvacdwvista.hl7v3.PRPAIN201302UV02MFMIMT700701UV01Subject2;
import gov.va.pie.nvacdwvista.hl7v3.PRPAMT201302UV02OtherIDs;
import gov.va.pie.nvacdwvista.hl7v3.PRPAMT201302UV02Patient;
import gov.va.pie.nvacdwvista.hl7v3.PRPAMT201302UV02PatientId;
import gov.va.pie.nvacdwvista.hl7v3.PRPAMT201302UV02PatientPatientPerson;
import gov.va.pie.nvacdwvista.hl7v3.PRPAMT201302UV02PatientStatusCode;
import gov.va.pie.nvacdwvista.hl7v3.ParticipationTargetSubject;
import gov.va.pie.nvacdwvista.hl7v3.TEL;
import gov.va.pie.nvacdwvista.hl7v3.XActMoodIntentEvent;
import gov.va.pie.nvacdwvista.model.MviModel;
import gov.va.pie.nvacdwvista.model.SoapBody;
import gov.va.pie.nvacdwvista.util.Soap1302Assembler;
import gov.va.pie.nvacdwvista.util.SoapCommonUtil;
import gov.va.pie.nvacdwvista.util.SoapConstants;

public class Soap1302Builder {

	private static ObjectFactory factory = new ObjectFactory();

	public PRPAIN201302UV02 build1302UpdateMessage(MviModel mviModel, boolean deleteNpi, String processingCode) {
		PRPAIN201302UV02 msg = SoapCommonUtil.get1302PRPAIN201302UV02Header(factory, new Date(), processingCode);
		PRPAIN201302UV02MFMIMT700701UV01ControlActProcess cap = getNpiDeleteControlProcess(factory, mviModel,
				deleteNpi);
		msg.setControlActProcess(cap);
		return msg;

	}

	public PRPAIN201302UV02MFMIMT700701UV01ControlActProcess getNpiDeleteControlProcess(ObjectFactory factory,
			MviModel mviModel, boolean deleteNpi) {
		PRPAIN201302UV02MFMIMT700701UV01ControlActProcess cap = new PRPAIN201302UV02MFMIMT700701UV01ControlActProcess();

		SoapBody bodyHelper = Soap1302Assembler.setupUpdateNPIHl7v3Body(mviModel, deleteNpi);
		cap.setMoodCode(XActMoodIntentEvent.EVN);
		cap.setClassCode(ActClassControlAct.CACT);

		PRPAIN201302UV02MFMIMT700701UV01Subject1 subj = new PRPAIN201302UV02MFMIMT700701UV01Subject1();
		PRPAIN201302UV02MFMIMT700701UV01RegistrationEvent regEvent = new PRPAIN201302UV02MFMIMT700701UV01RegistrationEvent();
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
		PRPAIN201302UV02MFMIMT700701UV01Subject2 subj2 = new PRPAIN201302UV02MFMIMT700701UV01Subject2();
		subj2.setTypeCode(ParticipationTargetSubject.SBJ);
		regEvent.setSubject1(subj2);

		// <patient classCode="PAT">
		PRPAMT201302UV02Patient patient = new PRPAMT201302UV02Patient();

		patient.getClassCode().add("PAT");

		PRPAMT201302UV02PatientId id = new PRPAMT201302UV02PatientId();
		id.setRoot(SoapConstants.ROOT_ID);
		id.setExtension("NO_ICN");
		patient.getId().add(id);

		subj2.setPatient(patient);

		PRPAMT201302UV02PatientStatusCode statusCode = new PRPAMT201302UV02PatientStatusCode();
		statusCode.setCode("active");
		patient.setStatusCode(statusCode);

		// <patientPerson>
		// <!--NAME-->
		PRPAMT201302UV02PatientPatientPerson patientPerson = new PRPAMT201302UV02PatientPatientPerson();

		QName _PRPAMT201303UV02PatientPatientPerson_QNAME = new QName("urn:hl7-org:v3", "patientPerson");
		JAXBElement<PRPAMT201302UV02PatientPatientPerson> patPerson = new JAXBElement<PRPAMT201302UV02PatientPatientPerson>(
				_PRPAMT201303UV02PatientPatientPerson_QNAME, PRPAMT201302UV02PatientPatientPerson.class,
				PRPAMT201302UV02Patient.class, null);

		// Name
		patientPerson.getName().add(bodyHelper.getPatientName());
		patient.setStatusCode(statusCode);
		
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

		for (PRPAMT201302UV02OtherIDs updateId : bodyHelper.getAsOtherUpdateIds()) {
			patientPerson.getAsOtherIDs().add(updateId);
		}

		// end of 1302
		patPerson.setValue(patientPerson);
		patient.setPatientPerson(patPerson);

		// Custodian
		regEvent.setCustodian(bodyHelper.getCustodian());
		subj.setRegistrationEvent(regEvent);
		cap.getSubject().add(subj);

		return cap;

	}

}
