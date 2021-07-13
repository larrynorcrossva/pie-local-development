package gov.va.pie.common.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import gov.va.pie.common.utils.CommonConstants;


/**
 * The persistent class for the Providers database table.
 * 
 */
@Entity
@Table(name=CommonConstants.DB_ENV+"Cerner_Providers")

public class CernerProvider implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="ProviderNpi")
	private String npi;

	@Column(name="ProviderLastName")
	private String lastName;
	
	@Column(name="ProviderFirstName")
	private String firstName;
	

	@Column(name="AddressStreet1")
	private String streetAddress1;

	@Column(name="AddressStreet2")
	private String streetAddress2;
	
	@Column(name="City")
	private String city;

	@Column(name="State")
	private String state;

	@Column(name="ZipCode")
	private String zip;

	@Column(name="MainSitePhone")
	private String officePhone;
	
	@Column(name="CareSiteFax")
	private String faxNumber;
	
	@Column(name="ProviderStatusReason")
	private String providerStatusReason;
	
	@Column(name="SpecialityCode")
	private String SpecialtyCode;

	@Column(name="ProviderNetwork")
	private String ProviderNetwork;

	@Column(name="ProviderServiceStatusReason")
	private String ProviderServiceStatusReason;

	@Column(name="DeaNumber")
	private String dea;

	@Column(name="ExpirationDate")
	private String expirationDate;

	@Column(name="HasScheduleII")
	private Boolean hasScheduleII;

	@Column(name="HasScheduleIII")
	private Boolean hasScheduleIII;

	@Column(name="HasScheduleIV")
	private Boolean hasScheduleIV;

	@Column(name="HasScheduleV")
	private Boolean hasScheduleV;

	@Column(name="HasScheduleIINonNarcotic")
	private Boolean hasScheduleIINonNarcotic;

	@Column(name="HasScheduleIIINonNarcotic")
	private Boolean hasScheduleIIINonNarcotic;

	@Column(name="DEAStatusReason")
	private String deaStatusReason;
	
	
	public List<String> toFieldList(){
		List<String> l = new ArrayList<String>();
		l.add(removeCommas(lastName));
		l.add(removeCommas(firstName));
		l.add(npi);
		l.add(removeCommas(streetAddress1));
		l.add(removeCommas(streetAddress2));
		l.add(removeCommas(city));
		l.add(state);
		l.add(zip);
		l.add(removeSpecialCharacters(officePhone));
		l.add(removeSpecialCharacters(faxNumber));
		l.add(providerStatusReason);
		l.add(SpecialtyCode);
		l.add(ProviderNetwork);
		l.add(ProviderServiceStatusReason);
		l.add(dea);
		l.add(expirationDate);
		l.add(hasScheduleII == null ? null : hasScheduleII.toString());
		l.add(hasScheduleIII == null ? null : hasScheduleIII.toString());
		l.add(hasScheduleIV == null ? null : hasScheduleIV.toString());
		l.add(hasScheduleV == null ? null : hasScheduleV.toString());
		l.add(hasScheduleIINonNarcotic == null ? null : hasScheduleIINonNarcotic.toString());
		l.add(hasScheduleIIINonNarcotic == null ? null : hasScheduleIIINonNarcotic.toString());
		l.add(deaStatusReason);
		return l;
	}
	
	private String removeSpecialCharacters(String s) {
		if(s==null) { return null; }
		String pattern = "[^a-zA-Z0-9]";
		return s.replaceAll(pattern, "");
	}
	
	private String removeCommas(String s) {
		if(s==null) { return null; }
		String pattern = ",";
		return s.replaceAll(pattern, "");
	}
	
	public CernerProvider() {
	}


	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}


	public String getDea() {
		return this.dea;
	}

	public void setDea(String dea) {
		this.dea = dea;
	}

	public String getFaxNumber() {
		return this.faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getOfficePhone() {
		return this.officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreetAddress1() {
		return this.streetAddress1;
	}

	public void setStreetAddress1(String streetAddress1) {
		this.streetAddress1 = streetAddress1;
	}

	public String getStreetAddress2() {
		return this.streetAddress2;
	}

	public void setStreetAddress2(String streetAddress2) {
		this.streetAddress2 = streetAddress2;
	}
	
	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}



	/*private String getDeaStatusReason() {
		return deaStatusReason;
	}



	private void setDeaStatusReason(String deaStatusReason) {
		this.deaStatusReason = deaStatusReason;
	}*/



	public String getProviderStatusReason() {
		return providerStatusReason;
	}



	public void setProviderStatusReason(String providerStatusReason) {
		this.providerStatusReason = providerStatusReason;
	}



	public String getSpecialtyCode() {
		return SpecialtyCode;
	}



	public void setSpecialtyCode(String specialtyCode) {
		SpecialtyCode = specialtyCode;
	}



	public String getProviderNetwork() {
		return ProviderNetwork;
	}



	public void setProviderNetwork(String providerNetwork) {
		ProviderNetwork = providerNetwork;
	}



	public String getProviderServiceStatusReason() {
		return ProviderServiceStatusReason;
	}



	public void setProviderServiceStatusReason(String providerServiceStatusReason) {
		ProviderServiceStatusReason = providerServiceStatusReason;
	}



	public String getExpirationDate() {
		return expirationDate;
	}



	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}



	public boolean isHasScheduleII() {
		return hasScheduleII;
	}



	public void setHasScheduleII(boolean hasScheduleII) {
		this.hasScheduleII = hasScheduleII;
	}



	public boolean isHasScheduleIII() {
		return hasScheduleIII;
	}



	public void setHasScheduleIII(boolean hasScheduleIII) {
		this.hasScheduleIII = hasScheduleIII;
	}



	public boolean isHasScheduleIV() {
		return hasScheduleIV;
	}



	public void setHasScheduleIV(boolean hasScheduleIV) {
		this.hasScheduleIV = hasScheduleIV;
	}



	public boolean isHasScheduleV() {
		return hasScheduleV;
	}



	public void setHasScheduleV(boolean hasScheduleV) {
		this.hasScheduleV = hasScheduleV;
	}



	public boolean isHasScheduleIINonNarcotic() {
		return hasScheduleIINonNarcotic;
	}



	public void setHasScheduleIINonNarcotic(boolean hasScheduleIINonNarcotic) {
		this.hasScheduleIINonNarcotic = hasScheduleIINonNarcotic;
	}



	public boolean isHasScheduleIIINonNarcotic() {
		return hasScheduleIIINonNarcotic;
	}



	public void setHasScheduleIIINonNarcotic(boolean hasScheduleIIINonNarcotic) {
		this.hasScheduleIIINonNarcotic = hasScheduleIIINonNarcotic;
	}



	/*private int getId() {
		return id;
	}



	private void setId(int id) {
		this.id = id;
	}*/

}