package gov.va.pie.common.entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import gov.va.pie.common.utils.CommonConstants;


/**
 * The persistent class for the ProviderDEASchedulePrivilege database table.
 * 
 */
@Entity
@Table(name=CommonConstants.DB_ENV+"ProviderDEASchedulePrivilege_V")
public class ProviderDEASchedulePrivilege implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ProviderDEASchedulePrivilege_Id")
	private int providerDEASchedulePrivilegeId;

	@Column(name="Created_By")
	private String createdBy;

	@Column(name="Created_Date")
	private Timestamp createdDate;

	@Column(name="DeaNumber")
	private String deaNumber;

	@Column(name="ExpirationDate")
	private String expirationDate;

	@Column(name="HasScheduleII")
	private String hasScheduleII;

	@Column(name="HasScheduleIII")
	private String hasScheduleIII;

	@Column(name="HasScheduleIIINonNarcotic")
	private String hasScheduleIIINonNarcotic;

	@Column(name="HasScheduleIINonNarcotic")
	private String hasScheduleIINonNarcotic;

	@Column(name="HasScheduleIV")
	private String hasScheduleIV;

	@Column(name="HasScheduleV")
	private String hasScheduleV;

	@Column(name="InactiveDate")
	private Date inactiveDate;

	@Column(name="InactiveFlag")
	private boolean inactiveFlag;

	@Column(name="Modified_By")
	private String modifiedBy;

	@Column(name="Modified_Date")
	private Timestamp modifiedDate;
	
	@Column(name = "PPMSModifiedOn_Date")
	private Date ppmsModifiedOnDate;

	@Column(name="VerificationDate")
	private String verificationDate;

	@Column(name="Verifier")
	private String verifier;
	
	@Column(name="AssociatedLocationName")
	private String associatedLocationName;
	
	@Column(name="DeaStatus")
	private String deaStatus;
	
	@Column(name="DeaStatusReason")
	private String deaStatusReason;

	//bi-directional many-to-one association to NonVAProvider
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="NonVAProvider_Id_FK")
	private NonVAProvider nonVaprovider;

	public ProviderDEASchedulePrivilege() {
	}

	public int getProviderDEASchedulePrivilegeId() {
		return this.providerDEASchedulePrivilegeId;
	}

	public void setProviderDEASchedulePrivilegeId(int providerDEASchedulePrivilegeId) {
		this.providerDEASchedulePrivilegeId = providerDEASchedulePrivilegeId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getDeaNumber() {
		return this.deaNumber;
	}

	public void setDeaNumber(String deaNumber) {
		this.deaNumber = deaNumber;
	}

	public String getExpirationDate() {
		return this.expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getHasScheduleII() {
		return this.hasScheduleII;
	}

	public void setHasScheduleII(String hasScheduleII) {
		this.hasScheduleII = hasScheduleII;
	}

	public String getHasScheduleIII() {
		return this.hasScheduleIII;
	}

	public void setHasScheduleIII(String hasScheduleIII) {
		this.hasScheduleIII = hasScheduleIII;
	}

	public String getHasScheduleIIINonNarcotic() {
		return this.hasScheduleIIINonNarcotic;
	}

	public void setHasScheduleIIINonNarcotic(String hasScheduleIIINonNarcotic) {
		this.hasScheduleIIINonNarcotic = hasScheduleIIINonNarcotic;
	}

	public String getHasScheduleIINonNarcotic() {
		return this.hasScheduleIINonNarcotic;
	}

	public void setHasScheduleIINonNarcotic(String hasScheduleIINonNarcotic) {
		this.hasScheduleIINonNarcotic = hasScheduleIINonNarcotic;
	}

	public String getHasScheduleIV() {
		return this.hasScheduleIV;
	}

	public void setHasScheduleIV(String hasScheduleIV) {
		this.hasScheduleIV = hasScheduleIV;
	}

	public String getHasScheduleV() {
		return this.hasScheduleV;
	}

	public void setHasScheduleV(String hasScheduleV) {
		this.hasScheduleV = hasScheduleV;
	}

	public Date getInactiveDate() {
		return this.inactiveDate;
	}

	public void setInactiveDate(Date inactiveDate) {
		this.inactiveDate = inactiveDate;
	}

	public boolean getInactiveFlag() {
		return this.inactiveFlag;
	}

	public void setInactiveFlag(boolean inactiveFlag) {
		this.inactiveFlag = inactiveFlag;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Date getPpmsModifiedOnDate() {
		return ppmsModifiedOnDate;
	}

	public void setPpmsModifiedOnDate(Date date) {
		this.ppmsModifiedOnDate = date;
	}

	public String getVerificationDate() {
		return this.verificationDate;
	}

	public void setVerificationDate(String verificationDate) {
		this.verificationDate = verificationDate;
	}

	public String getVerifier() {
		return this.verifier;
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

	public NonVAProvider getNonVaprovider() {
		return this.nonVaprovider;
	}

	public void setNonVaprovider(NonVAProvider nonVaprovider) {
		this.nonVaprovider = nonVaprovider;
	}

}