package gov.va.pie.nvappmscdw.model;

import java.sql.Date;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import gov.va.pie.nvappmscdw.utils.NvaUtils;

/**
 * Model object for DEA
 * 
 * @author Ablevets
 *
 */
public class DeaModel {

	@JsonProperty("DeaNumber")
	private String deaNumber;

	@JsonProperty("ExpirationDate")
	private String expirationDate;

	@JsonProperty("VerificationDate")
	private String verificationDate;

	@JsonProperty("Verifier")
	private String verifier;

	@JsonProperty("AssociatedLocationName")
	private String associatedLocationName;

	@JsonProperty("HasSchedule2")
	private String hasScheduleII;

	@JsonProperty("HasSchedule2NonNarcotic")
	private String hasScheduleIINonNarcotic;

	@JsonProperty("HasSchedule3")
	private String hasScheduleIII;

	@JsonProperty("HasSchedule3NonNarcotic")
	private String hasScheduleIIINonNarcotic;

	@JsonProperty("HasSchedule4")
	private String hasScheduleIV;

	@JsonProperty("HasSchedule5")
	private String hasScheduleV;

	@JsonProperty("ProviderNPI")
	private Long providerNPI;

	@JsonProperty("DEAStatus")
	private String deaStatus;

	@JsonProperty("DEAStatusReason")
	private String deaStatusReason;

	@JsonProperty("ModifiedOnDate")
	private String ppmsModifiedOnDate;

	public String getDeaNumber() {
		
		return deaNumber;
	}

	public void setDeaNumber(String deaNumber) {
		this.deaNumber = deaNumber;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getVerificationDate() {
		return verificationDate;
	}

	public void setVerificationDate(String verificationDate) {
		this.verificationDate = verificationDate;
	}

	public String getVerifier() {
		return verifier;
	}

	public void setVerifier(String verifier) {
		this.verifier = verifier;
	}

	public String getAssociatedLocationName() {
		return associatedLocationName;
	}

	public void setAssociatedLocationName(String associatedLocationName) {
		this.associatedLocationName = associatedLocationName;
	}

	public String getHasScheduleII() {
		return hasScheduleII;
	}

	public void setHasScheduleII(String hasScheduleII) {
		this.hasScheduleII = hasScheduleII;
	}

	public String getHasScheduleIINonNarcotic() {
		return hasScheduleIINonNarcotic;
	}

	public void setHasScheduleIINonNarcotic(String hasScheduleIINonNarcotic) {
		this.hasScheduleIINonNarcotic = hasScheduleIINonNarcotic;
	}

	public String getHasScheduleIII() {
		return hasScheduleIII;
	}

	public void setHasScheduleIII(String hasScheduleIII) {
		this.hasScheduleIII = hasScheduleIII;
	}

	public String getHasScheduleIIINonNarcotic() {
		return hasScheduleIIINonNarcotic;
	}

	public void setHasScheduleIIINonNarcotic(String hasScheduleIIINonNarcotic) {
		this.hasScheduleIIINonNarcotic = hasScheduleIIINonNarcotic;
	}

	public String getHasScheduleIV() {
		return hasScheduleIV;
	}

	public void setHasScheduleIV(String hasScheduleIV) {
		this.hasScheduleIV = hasScheduleIV;
	}

	public String getHasScheduleV() {
		return hasScheduleV;
	}

	public void setHasScheduleV(String hasScheduleV) {
		this.hasScheduleV = hasScheduleV;
	}

	public Long getProviderNPI() {
		return providerNPI;
	}

	public void setProviderNPI(Long providerNPI) {
		this.providerNPI = providerNPI;
	}

	public String getPpmsModifiedOnDate() {
		return ppmsModifiedOnDate;
	}

	public void setPpmsModifiedOnDate(String ppmsModifiedOnDate) {
		this.ppmsModifiedOnDate = ppmsModifiedOnDate;
	}

	public String getDeaStatus() {
		return deaStatus;
	}

	public void setDeaStatus(String deaStatus) {
		this.deaStatus = deaStatus;
	}

	public String getDeaStatusReason() {
		return deaStatusReason;
	}

	public void setDeaStatusReason(String deaStatusReason) {
		this.deaStatusReason = deaStatusReason;
	}

}
