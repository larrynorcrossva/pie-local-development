package gov.va.pie.common.entities;

import java.io.Serializable;
import javax.persistence.*;

import gov.va.pie.common.utils.CommonConstants;

import java.sql.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the PPMSNonVAInboundResponse database table.
 * 
 */
@Entity
@Table(name=CommonConstants.DB_ENV+"PPMSNonVAInboundResponse_V")
public class PPMSNonVAInboundResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PPMSNonVAInboundResponse_Id")
	private Integer ppmsNonVAInboundResponseId;
	
	@Column(name="CareSiteLastSuccess_Date")
	private Date careSiteLastSuccessDate;

	@Column(name="Created_By")
	private String createdBy;

	@Column(name="Created_Date")
	private Timestamp createdDate;

	@Column(name="IsCareSiteFail")
	private boolean isCareSiteFail;

	@Column(name="IsProviderAgreementFail")
	private boolean isProviderAgreementFail;

	@Column(name="IsProviderDEAFail")
	private boolean isProviderDEAFail;

	@Column(name="IsProviderFail")
	private boolean isProviderFail;

	@Column(name="IsProviderMedicalEducationFail")
	private boolean isProviderMedicalEducationFail;

	@Column(name="IsProviderOtherIdentifierFail")
	private boolean isProviderOtherIdentifierFail;

	@Column(name="IsProviderServiceFail")
	private boolean isProviderServiceFail;

	@Column(name="Modified_By")
	private String modifiedBy;

	@Column(name="Modified_Date")
	private Timestamp modifiedDate;
	
	@Column(name="ProviderLastSuccess_Date")
	private Date providerLastSuccessDate;
	
	@Column(name="ProviderServiceLastSuccess_Date")
	private Date providerServiceLastSuccessDate;
	
	@Column(name="ProviderDEALastSuccess_Date")
	private Date providerDEALastSuccessDate;
	
	@Column(name="ProviderOtherIdentifierLastSuccess_Date")
	private Date providerOtherIdentifierLastSuccessDate;
	
	@Column(name="ProviderMedicalEducationLastSuccess_Date")
	private Date providerMedicalEducationLastSuccessDate;
	
	@Column(name="ProviderAgreementLastSuccess_Date")
	private Date providerAgreementLastSuccessDate;
	
	@Column(name="Response_Message_Text")
	private String responseMessageText;

	@Column(name="Transaction_Count")
	private int transactionCount;

	@Column(name="Transaction_Number")
	private String transactionNumber;

	@Column(name="Transaction_Type")
	private String transactionType;

	public PPMSNonVAInboundResponse() {
	}

	public boolean getIsCareSiteFail() {
		return this.isCareSiteFail;
	}

	public void setIsCareSiteFail(boolean isCareSiteFail) {
		this.isCareSiteFail = isCareSiteFail;
	}

	public boolean getIsProviderAgreementFail() {
		return this.isProviderAgreementFail;
	}

	public void setIsProviderAgreementFail(boolean isProviderAgreementFail) {
		this.isProviderAgreementFail = isProviderAgreementFail;
	}

	public boolean getIsProviderDEAFail() {
		return this.isProviderDEAFail;
	}

	public void setIsProviderDEAFail(boolean isProviderDEAFail) {
		this.isProviderDEAFail = isProviderDEAFail;
	}

	public boolean getIsProviderFail() {
		return this.isProviderFail;
	}

	public void setIsProviderFail(boolean isProviderFail) {
		this.isProviderFail = isProviderFail;
	}

	public boolean getIsProviderMedicalEducationFail() {
		return this.isProviderMedicalEducationFail;
	}

	public void setIsProviderMedicalEducationFail(boolean isProviderMedicalEducationFail) {
		this.isProviderMedicalEducationFail = isProviderMedicalEducationFail;
	}

	public boolean getIsProviderOtherIdentifierFail() {
		return this.isProviderOtherIdentifierFail;
	}

	public void setIsProviderOtherIdentifierFail(boolean isProviderOtherIdentifierFail) {
		this.isProviderOtherIdentifierFail = isProviderOtherIdentifierFail;
	}

	public boolean getIsProviderServiceFail() {
		return this.isProviderServiceFail;
	}

	public void setIsProviderServiceFail(boolean isProviderServiceFail) {
		this.isProviderServiceFail = isProviderServiceFail;
	}

	public Integer getPPMSNonVAInboundResponseId() {
		return ppmsNonVAInboundResponseId;
	}

	public void setPPMSNonVAInboundResponseId(Integer ppmsNonVAInboundResponseId) {
		this.ppmsNonVAInboundResponseId = ppmsNonVAInboundResponseId;
	}

	public Date getCareSiteLastSuccessDate() {
		return careSiteLastSuccessDate;
	}

	public void setCareSiteLastSuccess_Date(Date careSiteLastSuccessDate) {
		this.careSiteLastSuccessDate = careSiteLastSuccessDate;
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

	public Date getProviderLastSuccessDate() {
		return providerLastSuccessDate;
	}

	public void setProviderLastSuccessDate(Date providerLastSuccessDate) {
		this.providerLastSuccessDate = providerLastSuccessDate;
	}

	public Date getProviderServiceLastSuccessDate() {
		return providerServiceLastSuccessDate;
	}

	public void setProviderServiceLastSuccessDate(Date providerServiceLastSuccessDate) {
		this.providerServiceLastSuccessDate = providerServiceLastSuccessDate;
	}

	public Date getProviderDEALastSuccessDate() {
		return providerDEALastSuccessDate;
	}

	public void setProviderDEALastSuccessDate(Date providerDEALastSuccessDate) {
		this.providerDEALastSuccessDate = providerDEALastSuccessDate;
	}

	public Date getProviderOtherIdentifierLastSuccessDate() {
		return providerOtherIdentifierLastSuccessDate;
	}

	public void setProviderOtherIdentifierLastSuccessDate(Date providerOtherIdentifierLastSuccessDate) {
		this.providerOtherIdentifierLastSuccessDate = providerOtherIdentifierLastSuccessDate;
	}

	public Date getProviderMedicalEducationLastSuccessDate() {
		return providerMedicalEducationLastSuccessDate;
	}

	public void setProviderMedicalEducationLastSuccessDate(Date providerMedicalEducationLastSuccessDate) {
		this.providerMedicalEducationLastSuccessDate = providerMedicalEducationLastSuccessDate;
	}

	public String getResponseMessageText() {
		return responseMessageText;
	}

	public void setResponseMessageText(String responseMessageText) {
		this.responseMessageText = responseMessageText;
	}

	public int getTransactionCount() {
		return transactionCount;
	}

	public void setTransactionCount(int transactionCount) {
		this.transactionCount = transactionCount;
	}

	public String getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public void setCareSiteFail(boolean isCareSiteFail) {
		this.isCareSiteFail = isCareSiteFail;
	}

	public Date getProviderAgreementLastSuccessDate() {
		return providerAgreementLastSuccessDate;
	}

	public void setProviderAgreementLastSuccessDate(Date providerAgreementLastSuccessDate) {
		this.providerAgreementLastSuccessDate = providerAgreementLastSuccessDate;
	}

	
}