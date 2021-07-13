package gov.va.pie.nvacdwvista.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gov.va.pie.nvacdwvista.hl7v3.AD;
import gov.va.pie.nvacdwvista.hl7v3.CE;
import gov.va.pie.nvacdwvista.hl7v3.CS;
import gov.va.pie.nvacdwvista.hl7v3.CommunicationFunctionType;
import gov.va.pie.nvacdwvista.hl7v3.EntityClassDevice;
import gov.va.pie.nvacdwvista.hl7v3.II;
import gov.va.pie.nvacdwvista.hl7v3.IVLTS;
import gov.va.pie.nvacdwvista.hl7v3.MCCIMT000100UV01Device;
import gov.va.pie.nvacdwvista.hl7v3.MCCIMT000100UV01Receiver;
import gov.va.pie.nvacdwvista.hl7v3.MCCIMT000100UV01Sender;
import gov.va.pie.nvacdwvista.hl7v3.MFMIMT700701UV01Custodian;
import gov.va.pie.nvacdwvista.hl7v3.ObjectFactory;
import gov.va.pie.nvacdwvista.hl7v3.PN;
import gov.va.pie.nvacdwvista.hl7v3.PRPAMT201302UV02OtherIDs;
import gov.va.pie.nvacdwvista.hl7v3.TEL;
import gov.va.pie.nvacdwvista.hl7v3.TS;
import gov.va.pie.nvacdwvista.model.DeaModel;
import gov.va.pie.nvacdwvista.model.MviModel;
import gov.va.pie.nvacdwvista.model.SoapBody;
import gov.va.pie.nvacdwvista.model.SoapHeader;
import gov.va.pie.nvacdwvista.model.SpecialityModel;
import gov.va.pie.nvacdwvista.service.Soap1301ClientService;

public class Soap1302Assembler {

	private static ObjectFactory factory = new ObjectFactory();
	
	static final Logger LOG = LogManager.getLogger(Soap1302Assembler.class);

	public static SoapHeader setupHl7v3Header(final String messageControlId, Date createdAt, String processingCode) {
		SoapHeader hl7v3Header = new SoapHeader();

		String timeStamp = DateFormatUtils.format(createdAt, "yyyy-MM-dd-HHmmSS");
		String createTime = DateFormatUtils.format(createdAt, "yyyyMMddHHmmSS");

		// <id root="2.16.840.1.113883.4.349" extension="MCID-12345"/>
		II mcid = new II();
		mcid.setRoot(SoapConstants.UPDATE_ROOT_OID);
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

		// <interactionId extension="PRPA_IN201302UV02" root="2.16.840.1.113883.1.6" />
		II intId = new II();
		intId.setRoot("2.16.840.1.113883.1.6");
		intId.setExtension(SoapConstants.INTERACTION_ID_EXTENSION_1302);
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
		// TEL tel = new TEL();
		// tel.setValue(SoapConstants.EXTENSION);
		// receiver.setTelecom(tel);
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

	public static SoapBody setupUpdateNPIHl7v3Body(MviModel mviModel, boolean deleteNpi) {
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
		PN patientName = SoapCommonUtil.getProviderName(factory, mviModel);
		hl7v3Body.setPatientName(patientName);

		// Address
		AD patientAddr = SoapCommonUtil.getProviderAddress(factory, mviModel);
		hl7v3Body.getAddressList().add(patientAddr);

		// Phone Number
		if (!StringUtils.isEmpty(mviModel.getOfficePhone())) {
			TEL tel = new TEL();
			tel.getUse().add("WP");
			tel.setValue(mviModel.getOfficePhone());
			hl7v3Body.getTelephoneList().add(tel);
		}

		// Station Number
		PRPAMT201302UV02OtherIDs stationId = SoapCommonUtil.getPRPAMT201302UV02OtherIDs("PAT", SoapConstants.ROOT_ID,
				"PROXY_VISTA^PN^" + mviModel.getStationNumber() + "^USDVA", SoapConstants.ROOT_ID);
		hl7v3Body.getAsOtherUpdateIds().add(stationId);

		// NPI
		PRPAMT201302UV02OtherIDs npiId = SoapCommonUtil.getPRPAMT201302UV02OtherIDs("NPI", SoapConstants.NPI_OID,
				mviModel.getNpi() + "^NI^200ENPI^USDVA", SoapConstants.ROOT_ID);
		CS npiStatus = new CS();
		npiStatus.setCode(deleteNpi ? "Inactive" : "Active");
		npiId.setStatusCode(npiStatus);

		if (deleteNpi) {
			IVLTS effectiveTime = new IVLTS();
			String timeStamp = DateFormatUtils.format(new Date(), "yyyyMMdd");
			effectiveTime.setValue(timeStamp);
			npiId.setEffectiveTime(effectiveTime);
		}

		hl7v3Body.getAsOtherUpdateIds().add(npiId);

		PRPAMT201302UV02OtherIDs schedculeNarcotics = null;
		if (!deleteNpi) {

			// Dea Numbers
			if (CollectionUtils.isNotEmpty(mviModel.getDeaModelList())) {
				for (DeaModel deaModel : mviModel.getDeaModelList()) {

					PRPAMT201302UV02OtherIDs deaOtherId = SoapCommonUtil.getPRPAMT201302UV02OtherIDs("DEA_NUMBER",
							SoapConstants.DEA_OID, deaModel.getDeaNumber(), SoapConstants.ROOT_ID);
					CS deaStatus = new CS();
					deaStatus.setCode(deaModel.getDeaStatusReason());
					IVLTS deaEffectiveStatus = new IVLTS();
					if (deaModel.getInactiveFlag() && deaModel.getInactiveDate() != null) {
						deaEffectiveStatus.setValue(deaModel.getInactiveDate());
						deaOtherId.setEffectiveTime(deaEffectiveStatus);
					}
					else if (deaModel.getExpirationDate() != null){
						deaEffectiveStatus.setValue(deaModel.getExpirationDate());
						deaOtherId.setEffectiveTime(deaEffectiveStatus);
					}
					deaOtherId.setStatusCode(deaStatus);
					hl7v3Body.getAsOtherUpdateIds().add(deaOtherId);

					// Scheduled Narcotics
					if (StringUtils.isNotEmpty(deaModel.getHasSchedule())) {
						schedculeNarcotics = SoapCommonUtil.getPRPAMT201302UV02OtherIDs("Sched_Narcotic",
								SoapConstants.ROOT_ID, deaModel.getHasSchedule(), SoapConstants.ROOT_ID);
						hl7v3Body.getAsOtherUpdateIds().add(schedculeNarcotics);
					}

					//Detox Number
					if (StringUtils.isNotEmpty(deaModel.getDetoxNumber())) {
						PRPAMT201302UV02OtherIDs detoxNumber = SoapCommonUtil.getPRPAMT201302UV02OtherIDs("DetoxMaintNumber",
								SoapConstants.ROOT_ID, deaModel.getDetoxNumber(), SoapConstants.ROOT_ID);
						hl7v3Body.getAsOtherUpdateIds().add(detoxNumber);
					}
				}

			}

			// Remarks
			PRPAMT201302UV02OtherIDs remarks = SoapCommonUtil.getPRPAMT201302UV02OtherIDs("Remarks",
					SoapConstants.ROOT_ID, "NON-VA PROVIDER", SoapConstants.ROOT_ID);
			hl7v3Body.getAsOtherUpdateIds().add(remarks);

			// Title
			PRPAMT201302UV02OtherIDs title = SoapCommonUtil.getPRPAMT201302UV02OtherIDs("Title", SoapConstants.ROOT_ID,
					"NON-VA PROVIDER", SoapConstants.ROOT_ID);
			hl7v3Body.getAsOtherUpdateIds().add(title);

			// Non-VA Prescriber
			PRPAMT201302UV02OtherIDs nonVaPrescriber = SoapCommonUtil.getPRPAMT201302UV02OtherIDs("NonVAPrescriber",
					SoapConstants.ROOT_ID, "Yes", SoapConstants.ROOT_ID);
			hl7v3Body.getAsOtherUpdateIds().add(nonVaPrescriber);

			Map<String, List<SpecialityModel>> specialityMap = new HashMap<>();
			SpecialityModel finalSpecialityModel = null;
			
			// PersonClass
			if (CollectionUtils.isNotEmpty(mviModel.getSpecialitiesList())) {
				for (SpecialityModel specialityModel : mviModel.getSpecialitiesList()) {
					if (specialityMap.get(specialityModel.getSpecialityCode()) == null) {
						List<SpecialityModel> tmp = new ArrayList<>();
						tmp.add(specialityModel);
						specialityMap.put(specialityModel.getSpecialityCode(), tmp);
					} else {
						specialityMap.get(specialityModel.getSpecialityCode()).add(specialityModel);
					}
				}
			}
			if (CollectionUtils.isNotEmpty(specialityMap.keySet())) {
				for (String key : specialityMap.keySet()) {
					List<SpecialityModel> specialityList = specialityMap.get(key);
					boolean allInactive = true;
					for (SpecialityModel specialityModel : specialityList) {
						if (specialityModel.getSpecialityActive()) {

							finalSpecialityModel = specialityModel;
							allInactive = false;
							break;
						} else {
							allInactive = true;

						}
					}
					if (allInactive) {
						specialityList.sort((p1, p2) -> {

							try {
								return DateUtils.parseDate(p1.getSpecialityinActiveDate(), "yyyyMMdd")
										.compareTo(DateUtils.parseDate(p2.getSpecialityinActiveDate(), "yyyyMMdd"));
							} catch (ParseException e) {
								//e.printStackTrace();
								LOG.error(e.getMessage());
							}

							return 0;
						});
						finalSpecialityModel = specialityList.get(0);
					}
					if (finalSpecialityModel != null) {
						PRPAMT201302UV02OtherIDs personClassID = SoapCommonUtil.getPRPAMT201302UV02OtherIDs(
								"PersonClass", SoapConstants.ROOT_ID, finalSpecialityModel.getSpecialityCode(),
								SoapConstants.ROOT_ID);
						CS speciality = new CS();
						speciality.setCode((finalSpecialityModel.getSpecialityActive()) ? "Active" : "Inactive");
						IVLTS specialityEffectiveStatus = new IVLTS();
						specialityEffectiveStatus.setValue(finalSpecialityModel.getSpecialityinActiveDate());
						personClassID.setEffectiveTime(specialityEffectiveStatus);
						personClassID.setStatusCode(speciality);
						hl7v3Body.getAsOtherUpdateIds().add(personClassID);
					}
				}

			}

		}

		// Custodian
		MFMIMT700701UV01Custodian custodian = SoapCommonUtil.getCustodian(factory);
		hl7v3Body.setCustodian(custodian);

		// gender
		if (!StringUtils.isEmpty(mviModel.getGender())) {
			CE gender = new CE();
			if (mviModel.getGender().equalsIgnoreCase("M"))
				gender.setCode("M");
			else if (mviModel.getGender().equalsIgnoreCase("F"))
				gender.setCode("F");

			hl7v3Body.setGender(gender);
		}

		return hl7v3Body;

	}

}