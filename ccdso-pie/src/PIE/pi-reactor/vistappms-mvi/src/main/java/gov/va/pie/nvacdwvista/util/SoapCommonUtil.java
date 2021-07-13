package gov.va.pie.nvacdwvista.util;

import java.util.Date;

import javax.xml.bind.JAXBElement;

import org.apache.commons.lang3.StringUtils;

import gov.va.pie.nvacdwvista.hl7v3.AD;
import gov.va.pie.nvacdwvista.hl7v3.COCTMT090003UV01AssignedEntity;
import gov.va.pie.nvacdwvista.hl7v3.COCTMT090003UV01Organization;
import gov.va.pie.nvacdwvista.hl7v3.COCTMT150002UV01Organization;
import gov.va.pie.nvacdwvista.hl7v3.EN;
import gov.va.pie.nvacdwvista.hl7v3.II;
import gov.va.pie.nvacdwvista.hl7v3.MFMIMT700701UV01Custodian;
import gov.va.pie.nvacdwvista.hl7v3.ObjectFactory;
import gov.va.pie.nvacdwvista.hl7v3.PN;
import gov.va.pie.nvacdwvista.hl7v3.PRPAIN201302UV02;
import gov.va.pie.nvacdwvista.hl7v3.PRPAMT201301UV02OtherIDs;
import gov.va.pie.nvacdwvista.hl7v3.PRPAMT201302UV02OtherIDs;
import gov.va.pie.nvacdwvista.hl7v3.PRPAMT201302UV02OtherIDsId;
import gov.va.pie.nvacdwvista.model.MviModel;
import gov.va.pie.nvacdwvista.model.SoapHeader;

public class SoapCommonUtil {
	
	public static PRPAIN201302UV02 get1302PRPAIN201302UV02Header(ObjectFactory factory, Date timeStamp, String processingCode) {

		PRPAIN201302UV02 msg = factory.createPRPAIN201302UV02();
		msg.setITSVersion("XML_1.0");
		SoapHeader mviHeaderInfo = Soap1302Assembler.setupHl7v3Header(SoapConstants.EXTENSION, timeStamp, processingCode);
		msg.setId(mviHeaderInfo.getId());
		msg.setCreationTime(mviHeaderInfo.getCreationTime());
		msg.setVersionCode(mviHeaderInfo.getVersionCode());
		msg.setInteractionId(mviHeaderInfo.getInteractionId());
		msg.setProcessingCode(mviHeaderInfo.getProcessingCode());
		msg.setProcessingModeCode(mviHeaderInfo.getProcessingModeCode());
		msg.setAcceptAckCode(mviHeaderInfo.getAcceptAckCode());
		msg.getReceiver().add(mviHeaderInfo.getReceiver());
		msg.setSender(mviHeaderInfo.getSender());
		return msg;
	}

	public static PN getProviderName(ObjectFactory factory, MviModel mviModel) {
		PN providerName = new PN();
		providerName.getUse().add("L");
		providerName.getContent().add(factory.createENGiven(mviModel.getFirstName()));
		if (!StringUtils.isEmpty(mviModel.getMiddleName())) {
			providerName.getContent().add(factory.createENGiven(mviModel.getMiddleName()));
		}
		if (!StringUtils.isEmpty(mviModel.getLastName())) {
			providerName.getContent().add(factory.createENFamily(mviModel.getLastName()));
		}
		return providerName;
	}

	public static MFMIMT700701UV01Custodian getCustodian(ObjectFactory factory) {
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
		org.setDeterminerCode("INSTANCE");
		EN assigOrgName = new EN();
		assigOrgName.getContent().add("200ESR");
		org.getName().add(assigOrgName);
		orgvalue.setValue(org);
		assignedEntity.setAssignedOrganization(orgvalue);
		custodian.setAssignedEntity(assignedEntity);
		return custodian;
	}

	public static PRPAMT201301UV02OtherIDs getPRPAMT201301UV02OtherIDs(String classCode, String idOID, String value,
			String scopOid) {
		PRPAMT201301UV02OtherIDs otherId = new PRPAMT201301UV02OtherIDs();
		otherId.getClassCode().add(classCode);
		otherId.getId().add(getII(idOID, value));
		COCTMT150002UV01Organization otherIdScopeScopOrg = getCOCTMT150002UV01Organization("ORG",
				SoapConstants.DETERMINER_CODE_INSTANCE);
		otherIdScopeScopOrg.getId().add(getII(scopOid, null));
		otherId.setScopingOrganization(otherIdScopeScopOrg);
		return otherId;

	}

	public static AD getProviderAddress(ObjectFactory factory, MviModel mviModel) {
		AD providerAddrs = new AD();
		providerAddrs.getUse().add("WP");
		// patientAddr.getContent().add(factory.createADAdditionalLocator(aa));
		providerAddrs.getContent().add(factory.createADStreetAddressLine(mviModel.getStreeAddress1()));
		// patientAddr.getContent().add(factory.createADHouseNumber("123"));
		providerAddrs.getContent().add(factory.createADCity(mviModel.getCity()));
		providerAddrs.getContent().add(factory.createADState(mviModel.getState()));
		providerAddrs.getContent().add(factory.createADPostalCode(mviModel.getZip()));
		return providerAddrs;
	}

	public static PRPAMT201302UV02OtherIDs getPRPAMT201302UV02OtherIDs(String classCode, String idOID, String value,
			String scopOid) {
		PRPAMT201302UV02OtherIDs otherId = new PRPAMT201302UV02OtherIDs();
		PRPAMT201302UV02OtherIDsId queryId = new PRPAMT201302UV02OtherIDsId();
		otherId.getClassCode().add(classCode);
		queryId.setRoot(idOID);
		queryId.setExtension(value);
		otherId.getId().add(queryId);

		COCTMT150002UV01Organization scopOrg = new COCTMT150002UV01Organization();
		scopOrg.setClassCode("ORG");
		scopOrg.setDeterminerCode(SoapConstants.DETERMINER_CODE_INSTANCE);
		II scopId = new II();
		scopId.setRoot(scopOid);
		scopOrg.getId().add(scopId);
		otherId.setScopingOrganization(scopOrg);

		return otherId;
	}

	public static COCTMT150002UV01Organization getCOCTMT150002UV01Organization(String classCode,
			String determinerCode) {
		COCTMT150002UV01Organization tmp = new COCTMT150002UV01Organization();
		tmp.setClassCode(classCode);
		tmp.setDeterminerCode(determinerCode);
		return tmp;
	}

	public static II getII(String root, String extension) {
		II ii = new II();
		if (!StringUtils.isEmpty(root))
			ii.setRoot(root);
		if (!StringUtils.isEmpty(extension))
			ii.setExtension(extension);
		return ii;
	}

}
