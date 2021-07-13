package gov.va.pie.nvappmscdw.model;

import java.sql.Date;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import gov.va.pie.nvappmscdw.utils.NvaUtils;

/**
 * Model object of non va provider data.
 * 
 * @author Ablevets
 * 
 */

public class NvaProvider {

	@JsonProperty("FirstName")
	private String providerFirstName;

	@JsonProperty("MiddleName")
	private String providerMiddleName;

	@JsonProperty("LastName")
	private String providerLastName;

	@JsonProperty("NPI")
	private Long npi;

	@JsonProperty("MainPhone")
	private String mainPhone;

	@JsonProperty("Fax")
	private String fax;

	@JsonProperty("ProviderType")
	private String providerType;

	@JsonProperty("Gender")
	private String gender;

	@JsonProperty("ProviderStatus")
	private String providerStatus;

	@JsonProperty("ProviderStatusReason")
	private String providerStatusReason;

	@JsonProperty("ModifiedOnDate")
	private String ppmsModifiedOnDate;

	public String getProviderFirstName() {
		if(!StringUtils.isEmpty(providerFirstName)) {
			providerFirstName=NvaUtils.stripSpecialCharsForSql(providerFirstName);
		}
		return providerFirstName;
	}

	public void setProviderFirstName(String providerFirstName) {
		this.providerFirstName = providerFirstName;
	}

	public String getProviderMiddleName() {
		if(!StringUtils.isEmpty(providerMiddleName)) {
			providerMiddleName=NvaUtils.stripSpecialCharsForSql(providerMiddleName);
		}
		return providerMiddleName;
	}

	public void setProviderMiddleName(String providerMiddleName) {
		this.providerMiddleName = providerMiddleName;
	}

	public String getProviderLastName() {
		if(!StringUtils.isEmpty(providerLastName)) {
			providerLastName=NvaUtils.stripSpecialCharsForSql(providerLastName);
		}
		return providerLastName;
	}

	public void setProviderLastName(String providerLastName) {
		this.providerLastName = providerLastName;
	}

	public Long getNpi() {
		return npi;
	}

	public void setNpi(Long npi) {
		this.npi = npi;
	}

	public String getMainPhone() {
		if(!StringUtils.isEmpty(mainPhone)) {
			int index = mainPhone.length()>=20?19:mainPhone.length();
			mainPhone= mainPhone.substring(0,index);
		}
		return mainPhone;
	}

	public void setMainPhone(String mainPhone) {
		this.mainPhone = mainPhone;
	}

	public String getFax() {
		if(!StringUtils.isEmpty(fax)) {
			int index = fax.length()>=20?19:fax.length();
			fax= fax.substring(0,index);
		}
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getProviderType() {
		return providerType;
	}

	public void setProviderType(String providerType) {
		this.providerType = providerType;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getProviderStatus() {
		return providerStatus;
	}

	public void setProviderStatus(String providerStatus) {
		this.providerStatus = providerStatus;
	}

	public String getProviderStatusReason() {
		return providerStatusReason;
	}

	public void setProviderStatusReason(String providerStatusReason) {
		this.providerStatusReason = providerStatusReason;
	}

	public String getPpmsModifiedOnDate() {
		return ppmsModifiedOnDate;
	}

	public void setPpmsModifiedOnDate(String ppmsModifiedOnDate) {
		this.ppmsModifiedOnDate = ppmsModifiedOnDate;
	}
}
