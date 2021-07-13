package gov.va.pie.nvacdwvista.model;

import java.util.ArrayList;
import java.util.List;

import gov.va.pie.nvacdwvista.hl7v3.AD;
import gov.va.pie.nvacdwvista.hl7v3.CE;
import gov.va.pie.nvacdwvista.hl7v3.COCTMT150002UV01Organization;
import gov.va.pie.nvacdwvista.hl7v3.CS;
import gov.va.pie.nvacdwvista.hl7v3.II;
import gov.va.pie.nvacdwvista.hl7v3.MFMIMT700701UV01Custodian;
import gov.va.pie.nvacdwvista.hl7v3.PN;
import gov.va.pie.nvacdwvista.hl7v3.PRPAMT201301UV02OtherIDs;
import gov.va.pie.nvacdwvista.hl7v3.PRPAMT201302UV02OtherIDs;
import gov.va.pie.nvacdwvista.hl7v3.TEL;

public class SoapBody {
	protected II patientNullFlavor;
	protected II regNullFlavor;
	protected CS regEventStatusCode;
	protected CS statusCode;
	protected List<AD> addressList;
	protected PN patientName;
	protected CE gender;

	protected List<TEL> telephoneList;

	protected MFMIMT700701UV01Custodian custodian;
	protected COCTMT150002UV01Organization titleScopOrg;

	private List<PRPAMT201301UV02OtherIDs> asOtherIds;

	private List<PRPAMT201302UV02OtherIDs> asOtherUpdateIds;

	public II getPatientNullFlavor() {
		return patientNullFlavor;
	}

	public void setPatientNullFlavor(II patientNullFlavor) {
		this.patientNullFlavor = patientNullFlavor;
	}

	public II getRegNullFlavor() {
		return regNullFlavor;
	}

	public void setRegNullFlavor(II regNullFlavor) {
		this.regNullFlavor = regNullFlavor;
	}

	public CS getRegEventStatusCode() {
		return regEventStatusCode;
	}

	public void setRegEventStatusCode(CS regEventStatusCode) {
		this.regEventStatusCode = regEventStatusCode;
	}

	public CS getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(CS statusCode) {
		this.statusCode = statusCode;
	}

	public List<AD> getAddressList() {
		if (addressList == null) {
			addressList = new ArrayList<>();
		}
		return addressList;
	}

	public void setAddressList(List<AD> addr) {
		this.addressList = addr;
	}

	public PN getPatientName() {
		return patientName;
	}

	public void setPatientName(PN patientName) {
		this.patientName = patientName;
	}

	public MFMIMT700701UV01Custodian getCustodian() {
		return custodian;
	}

	public void setCustodian(MFMIMT700701UV01Custodian custodian) {
		this.custodian = custodian;
	}

	public COCTMT150002UV01Organization getTitleScopOrg() {
		return titleScopOrg;
	}

	public void setTitleScopOrg(COCTMT150002UV01Organization titleScopOrg) {
		this.titleScopOrg = titleScopOrg;
	}

	public List<PRPAMT201301UV02OtherIDs> getAsOtherIds() {
		if (asOtherIds == null) {
			asOtherIds = new ArrayList<>();
		}

		return asOtherIds;
	}

	public void setAsOtherIds(List<PRPAMT201301UV02OtherIDs> otherIds) {
		this.asOtherIds = otherIds;
	}

	public List<TEL> getTelephoneList() {
		if (telephoneList == null) {
			telephoneList = new ArrayList<>();
		}
		return telephoneList;
	}

	public void setTelephoneList(List<TEL> telephoneList) {
		this.telephoneList = telephoneList;
	}

	public CE getGender() {
		return gender;
	}

	public void setGender(CE gender) {
		this.gender = gender;
	}

	public List<PRPAMT201302UV02OtherIDs> getAsOtherUpdateIds() {
		if (asOtherUpdateIds == null)
			asOtherUpdateIds = new ArrayList<>();
		return asOtherUpdateIds;
	}

	public void setAsOtherUpdateIds(List<PRPAMT201302UV02OtherIDs> asOtherUpdateIds) {
		this.asOtherUpdateIds = asOtherUpdateIds;
	}

}
