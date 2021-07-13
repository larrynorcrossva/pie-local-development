package gov.va.pie.nvacdwvista.util;

import java.util.Date;

import javax.xml.bind.JAXBElement;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import gov.va.pie.nvacdwvista.hl7v3.AD;
import gov.va.pie.nvacdwvista.hl7v3.CE;
import gov.va.pie.nvacdwvista.hl7v3.COCTMT090003UV01AssignedEntity;
import gov.va.pie.nvacdwvista.hl7v3.COCTMT090003UV01Organization;
import gov.va.pie.nvacdwvista.hl7v3.CS;
import gov.va.pie.nvacdwvista.hl7v3.CommunicationFunctionType;
import gov.va.pie.nvacdwvista.hl7v3.EN;
import gov.va.pie.nvacdwvista.hl7v3.EntityClassDevice;
import gov.va.pie.nvacdwvista.hl7v3.II;
import gov.va.pie.nvacdwvista.hl7v3.IVLTS;
import gov.va.pie.nvacdwvista.hl7v3.MCCIMT000100UV01Device;
import gov.va.pie.nvacdwvista.hl7v3.MCCIMT000100UV01Receiver;
import gov.va.pie.nvacdwvista.hl7v3.MCCIMT000100UV01Sender;
import gov.va.pie.nvacdwvista.hl7v3.MFMIMT700701UV01Custodian;
import gov.va.pie.nvacdwvista.hl7v3.ObjectFactory;
import gov.va.pie.nvacdwvista.hl7v3.PN;
import gov.va.pie.nvacdwvista.hl7v3.PRPAMT201301UV02OtherIDs;
import gov.va.pie.nvacdwvista.hl7v3.TEL;
import gov.va.pie.nvacdwvista.hl7v3.TS;
import gov.va.pie.nvacdwvista.model.DeaModel;
import gov.va.pie.nvacdwvista.model.MviModel;
import gov.va.pie.nvacdwvista.model.SoapBody;
import gov.va.pie.nvacdwvista.model.SoapHeader;
import gov.va.pie.nvacdwvista.model.SpecialityModel;

public class Soap1301Assembler {

	private static ObjectFactory factory = new ObjectFactory();

	public static SoapHeader setupHl7v3Header(final String messageControlId, Date createdAt, String processingCode) {

		String timeStamp = DateFormatUtils.format(createdAt, "yyyy-MM-dd-HHmmSS");
		String createTime = DateFormatUtils.format(createdAt, "yyyyMMddHHmmSS");
		
		SoapHeader hl7v3Header = new SoapHeader();
		// <id root="2.16.840.1.113883.4.349" extension="MCID-12345"/>
		II mcid = new II();
		mcid.setRoot(SoapConstants.ROOT_ID);
		mcid.setExtension(messageControlId + "_" + timeStamp);
		hl7v3Header.setId(mcid);

		// <creationTime value="20070810140900"/>
		TS creatTime = new TS();
		creatTime.setValue(createTime);
		hl7v3Header.setCreationTime(creatTime);
		
		// <verionCode="3.5"/>
		CS versionCode = new CS();
		versionCode.setCode(SoapConstants.VERSION_CODE);
		hl7v3Header.setVersionCode(versionCode);

		// <interactionId root="2.16.840.1.113883.1.6"/>
		II intId = new II();
		intId.setRoot(SoapConstants.INTERACTION_ID_ROOT);
		intId.setExtension(SoapConstants.INTERACTION_ID_EXTENSION_1301);
		hl7v3Header.setInteractionId(intId);

		// <processingCode code="T"/>
		CS procCode = new CS();
		procCode.setCode(processingCode);
		hl7v3Header.setProcessingCode(procCode);

		// <processingModeCode code="T"/>
		CS procMode = new CS();
		procMode.setCode(processingCode);
		hl7v3Header.setProcessingModeCode(procMode);

		// <acceptAckCode code="AL"/>
		CS ackCode = new CS();
		ackCode.setCode("AL");
		hl7v3Header.setAcceptAckCode(ackCode);

		// Receiver
//	    <receiver typeCode="RCV">
//        <device classCode="DEV" determinerCode="INSTANCE">
//            <id root="2.16.840.1.113883.4.349"/>
//        </device>
//	    </receiver>
		MCCIMT000100UV01Receiver receiver = new MCCIMT000100UV01Receiver();
		receiver.setTypeCode(CommunicationFunctionType.RCV);
		//TEL tel = new TEL();
		//tel.setValue(SoapConstants.EXTENSION);
		//receiver.setTelecom(tel);
		MCCIMT000100UV01Device rcvDevice = new MCCIMT000100UV01Device();
		rcvDevice.setClassCode(EntityClassDevice.DEV);
		rcvDevice.setDeterminerCode("INSTANCE");
		II rcvDeviceId = new II();
		rcvDeviceId.setRoot(SoapConstants.ROOT_ID);
		rcvDevice.getId().add(rcvDeviceId);
		receiver.setDevice(rcvDevice);
		hl7v3Header.setReceiver(receiver);

		// <sender typeCode="SND">
		// <device classCode="DEV" determinerCode="INSTANCE">
		// <id extension="200ESR" root="1.2.840.114350.1.13.99997.2.7788"/>
		// </device>
		// </sender>
		// Sender

		MCCIMT000100UV01Sender sender = new MCCIMT000100UV01Sender();
		sender.setTypeCode(CommunicationFunctionType.SND);

		MCCIMT000100UV01Device sndDevice = new MCCIMT000100UV01Device();
		sndDevice.setClassCode(EntityClassDevice.DEV);
		sndDevice.setDeterminerCode(SoapConstants.DETERMINER_CODE_INSTANCE);

		II sndDeviceId = new II();
		sndDeviceId.setRoot(SoapConstants.ROOT_ID);
		sndDeviceId.setExtension("200PIEV");
		sndDevice.getId().add(sndDeviceId);

		sender.setDevice(sndDevice);
		hl7v3Header.setSender(sender);

		return hl7v3Header;
	}

	public static SoapBody setupHl7v3Body(MviModel mviModel) {
		SoapBody hl7v3Body = new SoapBody();

		// Reg
		II regNullFlavor = new II();
		regNullFlavor.getNullFlavor().add("NA");
		CS regStatus = new CS();
		regStatus.setCode("ACTIVE");
		hl7v3Body.setRegEventStatusCode(regStatus);
		hl7v3Body.setRegNullFlavor(regNullFlavor);

		// Patient Null Flavor UNK/
		II patientNullFlavor = new II();
		patientNullFlavor.getNullFlavor().add("UNK");
		hl7v3Body.setPatientNullFlavor(patientNullFlavor);

		// Name
		PN patientName = new PN();
		patientName.getUse().add("L");

		if (!StringUtils.isEmpty(mviModel.getFirstName())) {
			patientName.getContent().add(factory.createENGiven(mviModel.getFirstName()));
		}
		if (!StringUtils.isEmpty(mviModel.getMiddleName())) {
			patientName.getContent().add(factory.createENGiven(mviModel.getMiddleName()));
		}
		if (!StringUtils.isEmpty(mviModel.getLastName())) {
			patientName.getContent().add(factory.createENFamily(mviModel.getLastName()));
		}
		hl7v3Body.setPatientName(patientName);

		// Address
		AD patientAddr = new AD();
		patientAddr.getUse().add("WP");
		// patientAddr.getContent().add(factory.createADAdditionalLocator(aa));
		if (!StringUtils.isEmpty(mviModel.getStreeAddress1())) {
			patientAddr.getContent().add(factory.createADStreetAddressLine(mviModel.getStreeAddress1()));
		}

		// patientAddr.getContent().add(factory.createADHouseNumber("123"));
		if (!StringUtils.isEmpty(mviModel.getCity())) {
			patientAddr.getContent().add(factory.createADCity(mviModel.getCity()));
		}
		if (!StringUtils.isEmpty(mviModel.getState())) {
			patientAddr.getContent().add(factory.createADState(mviModel.getState()));
		}
		if (!StringUtils.isEmpty(mviModel.getZip())) {
			patientAddr.getContent().add(factory.createADPostalCode(mviModel.getZip()));
		}

		// patientAddr.getContent().add(factory.createADCountry("Country"));
		hl7v3Body.getAddressList().add(patientAddr);

		if (!StringUtils.isEmpty(mviModel.getOfficePhone())) {
			TEL tel = new TEL();
			tel.getUse().add("WP");
			tel.setValue(mviModel.getOfficePhone());
			hl7v3Body.getTelephoneList().add(tel);
		}

		// gender
		if (!StringUtils.isEmpty(mviModel.getGender())) {
			CE gender = new CE();
			if (mviModel.getGender().equalsIgnoreCase("M"))
				gender.setCode("M");
			else if (mviModel.getGender().equalsIgnoreCase("F"))
				gender.setCode("F");

			hl7v3Body.setGender(gender);
		}

		// Other Id Status Code - Link/Unlink/4/5/2/
		CS idStatus = new CS();
		hl7v3Body.setStatusCode(idStatus);

		// Station Number
		PRPAMT201301UV02OtherIDs stationId = SoapCommonUtil.getPRPAMT201301UV02OtherIDs("PAT", SoapConstants.ROOT_ID,
				"PROXY_VISTA^PN^" + mviModel.getStationNumber() + "^USDVA", SoapConstants.ROOT_ID);
		hl7v3Body.getAsOtherIds().add(stationId);

		// NPI
		PRPAMT201301UV02OtherIDs npiId = SoapCommonUtil.getPRPAMT201301UV02OtherIDs("NPI", SoapConstants.NPI_OID,
				mviModel.getNpi() + "^NI^200ENPI^USDVA", SoapConstants.ROOT_ID);
		CS npiStatus = new CS();
		npiStatus.setCode("Active");
		npiId.setStatusCode(npiStatus);
		hl7v3Body.getAsOtherIds().add(npiId);

		PRPAMT201301UV02OtherIDs schedculeNarcotics = null;
		// Dea Numbers
		if (CollectionUtils.isNotEmpty(mviModel.getDeaModelList())) {
			for (DeaModel deaModel : mviModel.getDeaModelList()) {

				PRPAMT201301UV02OtherIDs deaOtherId = SoapCommonUtil.getPRPAMT201301UV02OtherIDs("DEA_NUMBER",
						SoapConstants.DEA_OID, deaModel.getDeaNumber(), SoapConstants.ROOT_ID);
				CS deaStatus = new CS();
				deaStatus.setCode(deaModel.getDeaStatusReason());
				IVLTS deaEffectiveStatus = new IVLTS();
				if(deaModel.getInactiveFlag() && deaModel.getInactiveDate() != null) {
					deaEffectiveStatus.setValue(deaModel.getInactiveDate());
					deaOtherId.setEffectiveTime(deaEffectiveStatus);
				}
				else if (deaModel.getExpirationDate() != null){
					deaEffectiveStatus.setValue(deaModel.getExpirationDate());
					deaOtherId.setEffectiveTime(deaEffectiveStatus);
				}
				deaOtherId.setStatusCode(deaStatus);
				hl7v3Body.getAsOtherIds().add(deaOtherId);

				// Scheduled Narcotics
				if (StringUtils.isNotEmpty(deaModel.getHasSchedule())) {
					schedculeNarcotics = SoapCommonUtil.getPRPAMT201301UV02OtherIDs("Sched_Narcotic",
							SoapConstants.ROOT_ID, deaModel.getHasSchedule(), SoapConstants.ROOT_ID);
					hl7v3Body.getAsOtherIds().add(schedculeNarcotics);
				}
				
				//Detox Number
				if (StringUtils.isNotEmpty(deaModel.getDetoxNumber())) {
					PRPAMT201301UV02OtherIDs detoxNumber = SoapCommonUtil.getPRPAMT201301UV02OtherIDs("DetoxMaintNumber",
							SoapConstants.ROOT_ID, deaModel.getDetoxNumber(), SoapConstants.ROOT_ID);
					hl7v3Body.getAsOtherIds().add(detoxNumber);
				}
				
			}

		}


		// Remarks
		PRPAMT201301UV02OtherIDs remarks = SoapCommonUtil.getPRPAMT201301UV02OtherIDs("Remarks", SoapConstants.ROOT_ID,
				"NON-VA PROVIDER", SoapConstants.ROOT_ID);
		hl7v3Body.getAsOtherIds().add(remarks);

		// Title
		PRPAMT201301UV02OtherIDs title = SoapCommonUtil.getPRPAMT201301UV02OtherIDs("Title", SoapConstants.ROOT_ID,
				"NON-VA PROVIDER", SoapConstants.ROOT_ID);
		hl7v3Body.getAsOtherIds().add(title);

		// Non-VA Prescriber
		PRPAMT201301UV02OtherIDs nonVaPrescriber = SoapCommonUtil.getPRPAMT201301UV02OtherIDs("NonVAPrescriber",
				SoapConstants.ROOT_ID, "Yes", SoapConstants.ROOT_ID);
		hl7v3Body.getAsOtherIds().add(nonVaPrescriber);

		// ProviderType
		PRPAMT201301UV02OtherIDs providerType = SoapCommonUtil.getPRPAMT201301UV02OtherIDs("ProviderType",
				SoapConstants.ROOT_ID, "4^FEE BASIS", SoapConstants.ROOT_ID);
		hl7v3Body.getAsOtherIds().add(providerType);

		// PersonClass
		if (CollectionUtils.isNotEmpty(mviModel.getSpecialitiesList())) {
			for (SpecialityModel specialityModel : mviModel.getSpecialitiesList()) {
				PRPAMT201301UV02OtherIDs personClassID = SoapCommonUtil.getPRPAMT201301UV02OtherIDs("PersonClass",
						SoapConstants.ROOT_ID, specialityModel.getSpecialityCode(), SoapConstants.ROOT_ID);
				CS speciality = new CS();
				speciality.setCode((specialityModel.getSpecialityActive()) ? "Active" : "Inactive");
				IVLTS specialityEffectiveStatus = new IVLTS();
				specialityEffectiveStatus.setValue(specialityModel.getSpecialityinActiveDate());
				personClassID.setEffectiveTime(specialityEffectiveStatus);
				personClassID.setStatusCode(speciality);
				hl7v3Body.getAsOtherIds().add(personClassID);
			}
		}

		// Custodian
		MFMIMT700701UV01Custodian custodian = new MFMIMT700701UV01Custodian();
		custodian.getTypeCode().add("CST");
		II assignId = new II();
		assignId.setRoot(SoapConstants.ROOT_ID);
		COCTMT090003UV01AssignedEntity assignedEntity = new COCTMT090003UV01AssignedEntity();
		assignedEntity.getId().add(assignId);
		assignedEntity.setClassCode("ASSIGNED");
		
		// Assigned Organization
		JAXBElement<COCTMT090003UV01Organization> orgvalue = factory
				.createCOCTMT090003UV01AssignedEntityAssignedOrganization(null);
		COCTMT090003UV01Organization org = new COCTMT090003UV01Organization();
		org.setClassCode("ORG");
		org.setDeterminerCode(SoapConstants.DETERMINER_CODE_INSTANCE);
		EN assigOrgName = new EN();
		assigOrgName.getContent().add("200ESR");
		org.getName().add(assigOrgName);
		orgvalue.setValue(org);
		assignedEntity.setAssignedOrganization(orgvalue);	
		custodian.setAssignedEntity(assignedEntity);
		hl7v3Body.setCustodian(custodian);

		return hl7v3Body;
	}

}
