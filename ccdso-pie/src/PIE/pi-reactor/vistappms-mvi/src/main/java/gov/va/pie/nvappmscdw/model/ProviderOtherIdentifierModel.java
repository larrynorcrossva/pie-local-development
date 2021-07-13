package gov.va.pie.nvappmscdw.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model object for ProviderIdentifier
 * 
 * @author Ablevets
 *
 */
public class ProviderOtherIdentifierModel {

	@JsonProperty("ProviderIdentifier")
	private String providerIdentifier;

	@JsonProperty("ProviderIdentifierType")
	private String providerIdentifierType;

	@JsonProperty("PrimaryProviderNPI")
	private Long primaryProviderNPI;

	@JsonProperty("ProviderIdentifierStatus")
	private String providerIdentifierStatus;

	@JsonProperty("ProviderIdentifierStatusReason")
	private String providerIdentifierStatusReason;

	@JsonProperty("ModifiedOnDate")
	private String ppmsModifiedOnDate;

	//@JsonProperty("IdentifierIssuer") - removed by ppms
	private String identifierIssuer;

	@JsonProperty("IdentifierState")
	private String identifierState;

	public String getIdentifierIssuer() {
		return identifierIssuer;
	}

	public void setIdentifierIssuer(String identifierIssuer) {
		this.identifierIssuer = identifierIssuer;
	}

	public String getIdentifierState() {
		return identifierState;
	}

	public void setIdentifierState(String identifierState) {
		this.identifierState = identifierState;
	}

	public String getProviderIdentifier() {
		return providerIdentifier;
	}

	public void setProviderIdentifier(String providerIdentifier) {
		this.providerIdentifier = providerIdentifier;
	}

	public String getProviderIdentifierType() {
		return providerIdentifierType;
	}

	public void setProviderIdentifierType(String providerIdentifierType) {
		this.providerIdentifierType = providerIdentifierType;
	}

	public String getProviderIdentifierStatus() {
		return providerIdentifierStatus;
	}

	public void setProviderIdentifierStatus(String providerIdentifierStatus) {
		this.providerIdentifierStatus = providerIdentifierStatus;
	}

	public String getProviderIdentifierStatusReason() {
		return providerIdentifierStatusReason;
	}

	public void setProviderIdentifierStatusReason(String providerIdentifierStatusReason) {
		this.providerIdentifierStatusReason = providerIdentifierStatusReason;
	}

	public Long getPrimaryProviderNPI() {
		return primaryProviderNPI;
	}

	public void setPrimaryProviderNPI(Long primaryProviderNPI) {
		this.primaryProviderNPI = primaryProviderNPI;
	}

	public String getPpmsModifiedOnDate() {
		return ppmsModifiedOnDate;
	}

	public void setPpmsModifiedOnDate(String ppmsModifiedOnDate) {
		this.ppmsModifiedOnDate = ppmsModifiedOnDate;
	}

}
