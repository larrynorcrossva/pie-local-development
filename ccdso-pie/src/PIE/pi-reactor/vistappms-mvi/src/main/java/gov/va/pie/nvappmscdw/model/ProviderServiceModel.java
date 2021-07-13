package gov.va.pie.nvappmscdw.model;

import java.sql.Date;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import gov.va.pie.nvappmscdw.utils.NvaUtils;

/**
 * Model object for CareSite
 * 
 * @author Ablevets
 *
 */
public class ProviderServiceModel {

	@JsonProperty("ProviderNPI")
	private long providerNpi;
	
	@JsonProperty("ProviderNetwork")
	private String providerNetwork;

	@JsonProperty("CareSiteName")
	private String careSiteName;

	@JsonProperty("CareSiteId")
	private String careSiteGuid;

	@JsonProperty("CareSiteType")
	private String careSiteType;

	@JsonProperty("Address")
	private String addressComposite;

	@JsonProperty("AddressStreet1")
	private String addressStreet1;

	@JsonProperty("AddressStreet2")
	private String addressStreet2;

	@JsonProperty("AddressCity")
	private String addressCity;

	@JsonProperty("AddressState")
	private String addressState;

	@JsonProperty("AddressZip")
	private String addressZip;

	@JsonProperty("SpecialtyCode")
	private String specialtyCode;

	@JsonProperty("IsPrimarySpecialty")
	private String isPrimaryTaxonomy;

	@JsonProperty("Latitude")
	private double lat;

	@JsonProperty("Longitude")
	private double lon;

	@JsonProperty("OfficePhone")
	private String officePhone;

	@JsonProperty("ModifiedOnDate")
	private String ppmsModifiedOnDate;

	@JsonProperty("ProviderServiceStatus")
	private String providerServiceStatus;

	@JsonProperty("ProviderServiceStatusReason")
	private String providerServiceStatusReason;

	public long getProviderNpi() {
		return providerNpi;
	}

	public void setProviderNpi(long providerNpi) {
		this.providerNpi = providerNpi;
	}

	public String getCareSiteName() {
		if (!StringUtils.isEmpty(careSiteName)) {
			careSiteName = NvaUtils.stripSpecialCharsForSql(careSiteName);
		}
		return careSiteName;
	}

	public void setCareSiteName(String careSiteName) {
		this.careSiteName = careSiteName;
	}

	public String getCareSiteGuid() {
		return careSiteGuid;
	}

	public void setCareSiteGuid(String careSiteGuid) {
		this.careSiteGuid = careSiteGuid;
	}

	public String getCareSiteType() {
		return careSiteType;
	}

	public void setCareSiteType(String careSiteType) {
		this.careSiteType = careSiteType;
	}

	public String getAddressComposite() {
		if (!StringUtils.isEmpty(addressComposite)) {
			addressComposite = NvaUtils.stripSpecialCharsForSql(addressComposite);
		}
		return addressComposite;
	}

	public void setAddressComposite(String addressComposite) {
		this.addressComposite = addressComposite;
	}

	public String getAddressStreet1() {
		if (!StringUtils.isEmpty(addressStreet1)) {
			addressStreet1 = NvaUtils.stripSpecialCharsForSql(addressStreet1);
		}
		return addressStreet1;
	}

	public void setAddressStreet1(String addressStreet1) {
		this.addressStreet1 = addressStreet1;
	}

	public String getAddressStreet2() {
		if (!StringUtils.isEmpty(addressStreet2)) {
			addressStreet2 = NvaUtils.stripSpecialCharsForSql(addressStreet2);
		}
		return addressStreet2;
	}

	public void setAddressStreet2(String addressStreet2) {
		this.addressStreet2 = addressStreet2;
	}

	public String getAddressCity() {
		if (!StringUtils.isEmpty(addressCity)) {
			addressCity = NvaUtils.stripSpecialCharsForSql(addressCity);
		}
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public String getAddressState() {
		return addressState;
	}

	public void setAddressState(String addressState) {
		this.addressState = addressState;
	}

	public String getAddressZip() {
		return addressZip;
	}

	public void setAddressZip(String addressZip) {
		this.addressZip = addressZip;
	}

	public String getSpecialtyCode() {
		return specialtyCode;
	}

	public void setSpecialtyCode(String specialtyCode) {
		this.specialtyCode = specialtyCode;
	}

	public String getIsPrimaryTaxonomy() {
		return isPrimaryTaxonomy;
	}

	public void setIsPrimaryTaxonomy(String isPrimaryTaxonomy) {
		this.isPrimaryTaxonomy = isPrimaryTaxonomy;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public String getOfficePhone() {
		if(!StringUtils.isEmpty(officePhone)) {
			int index = officePhone.length()>=20?19:officePhone.length();
			officePhone= officePhone.substring(0,index);
		}
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getPpmsModifiedOnDate() {
		return ppmsModifiedOnDate;
	}

	public void setPpmsModifiedOnDate(String ppmsModifiedOnDate) {
		this.ppmsModifiedOnDate = ppmsModifiedOnDate;
	}

	public String getProviderServiceStatus() {
		return providerServiceStatus;
	}

	public void setProviderServiceStatus(String providerServiceStatus) {
		this.providerServiceStatus = providerServiceStatus;
	}

	public String getProviderServiceStatusReason() {
		return providerServiceStatusReason;
	}

	public void setProviderServiceStatusReason(String providerServiceStatusReason) {
		this.providerServiceStatusReason = providerServiceStatusReason;
	}

	public String getProviderNetwork() {
		return providerNetwork;
	}

	public void setProviderNetwork(String providerNetwork) {
		this.providerNetwork = providerNetwork;
	}
	
	

}
