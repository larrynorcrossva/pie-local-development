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
 * The persistent class for the ProviderOtherIdentifier database table.
 * 
 */
@Entity
@Table(name=CommonConstants.DB_ENV+"ProviderOtherIdentifier_V")
public class ProviderOtherIdentifier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ProviderOtherIdentifier_Id")
	private int providerOtherIdentifierId;

	@Column(name="Created_By")
	private String createdBy;

	@Column(name="Created_Date")
	private Timestamp createdDate;

	@Column(name="IdentifierIssuer")
	private String identifierIssuer;

	@Column(name="IdentifierState")
	private String identifierState;

	@Column(name="IdentifierTypeCode")
	private String identifierTypeCode;

	@Column(name="InactiveDate")
	private Date inactiveDate;

	@Column(name="InactiveFlag")
	private boolean inactiveFlag;

	@Column(name="Modified_By")
	private String modifiedBy;

	@Column(name="Modified_Date")
	private Timestamp modifiedDate;

	@Column(name="OtherIdentifierName")
	private String otherIdentifierName;
	
	@Column(name = "PPMSModifiedOn_Date")
	private Date ppmsModifiedOnDate;
	
	@Column(name="ProviderIdentifierStatus")
	private String providerIdentifierStatus;
	
	@Column(name="ProviderIdentifierStatusReason")
	private String providerIdentifierStatusReason;

	//bi-directional many-to-one association to NonVAProvider
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="NonVAProvider_Id_FK")
	private NonVAProvider nonVaprovider;

	public ProviderOtherIdentifier() {
	}

	public int getProviderOtherIdentifierId() {
		return this.providerOtherIdentifierId;
	}

	public void setProviderOtherIdentifierId(int providerOtherIdentifierId) {
		this.providerOtherIdentifierId = providerOtherIdentifierId;
	}

	public String getIdentifierIssuer() {
		return this.identifierIssuer;
	}

	public void setIdentifierIssuer(String identifierIssuer) {
		this.identifierIssuer = identifierIssuer;
	}

	public String getIdentifierState() {
		return this.identifierState;
	}

	public void setIdentifierState(String identifierState) {
		this.identifierState = identifierState;
	}

	public String getIdentifierTypeCode() {
		return this.identifierTypeCode;
	}

	public void setIdentifierTypeCode(String identifierTypeCode) {
		this.identifierTypeCode = identifierTypeCode;
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

	public String getOtherIdentifierName() {
		return this.otherIdentifierName;
	}

	public void setOtherIdentifierName(String otherIdentifierName) {
		this.otherIdentifierName = otherIdentifierName;
	}

	public NonVAProvider getNonVaprovider() {
		return this.nonVaprovider;
	}

	public void setNonVaprovider(NonVAProvider nonVaprovider) {
		this.nonVaprovider = nonVaprovider;
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

	public void setPpmsModifiedOnDate(Date ppmsModifiedOnDate) {
		this.ppmsModifiedOnDate = ppmsModifiedOnDate;
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

}