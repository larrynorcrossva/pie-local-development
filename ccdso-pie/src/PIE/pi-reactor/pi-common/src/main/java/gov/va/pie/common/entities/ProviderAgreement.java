package gov.va.pie.common.entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import gov.va.pie.common.utils.CommonConstants;


/**
 * The persistent class for the ProviderAgreement database table.
 * 
 */
@Entity
@Table(name=CommonConstants.DB_ENV+"ProviderAgreement_V")
public class ProviderAgreement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ProviderAgreement_Id")
	private int providerAgreementId;

	@Column(name="AgreementGuid")
	private String agreementGuid;

	@Column(name="AgreementId")
	private String agreementId;

	@Column(name="AgreementType")
	private String agreementType;

	@Column(name="AssignedFacility")
	private String assignedFacility;

	@Column(name="CalcAgreementExpirationDate")
	private Timestamp calcAgreementExpirationDate;

	@Column(name="CalcExpirationTimeFrameYears")
	private String calcExpirationTimeFrameYears;

	@Column(name="CancelDateSet")
	private Timestamp cancelDateSet;

	@Column(name="Created_By")
	private String createdBy;

	@Column(name="Created_Date")
	private Timestamp createdDate;

	@Column(name="DocumentLocation")
	private String documentLocation;

	@Column(name="EffectiveDate")
	private Timestamp effectiveDate;

	@Column(name="ExpirationDate")
	private Timestamp expirationDate;

	@Column(name="ImportStatusField")
	private String importStatusField;

	@Column(name="InactiveDate")
	private Date inactiveDate;

	@Column(name="InactiveFlag")
	private boolean inactiveFlag;

	@Column(name="IsDeclinedPaymentRateOrMethodology")
	private String isDeclinedPaymentRateOrMethodology;

	@Column(name="IsDeclinedPreviousExperienceWithChoice")
	private String isDeclinedPreviousExperienceWithChoice;

	@Column(name="IsDeclinedPreviousExpWithProvAgreement")
	private String isDeclinedPreviousExpWithProvAgreement;

	@Column(name="IsDeclinedRefusalToCommitAtLowerLevel")
	private String isDeclinedRefusalToCommitAtLowerLevel;

	@Column(name="IsDeclinedRefusalToCompleteOtherReqTraining")
	private String isDeclinedRefusalToCompleteOtherReqTraining;

	@Column(name="IsDeclinedRefusalToCompleteReqOpiodTraining")
	private String isDeclinedRefusalToCompleteReqOpiodTraining;

	@Column(name="IsDeclinedRefusedCompleteQualificationDocument")
	private String isDeclinedRefusedCompleteQualificationDocument;

	@Column(name="IsDeclinedTimelyReimbursements")
	private String isDeclinedTimelyReimbursements;

	@Column(name="IsPreviousExperienceWithChoiceViaTW")
	private String isPreviousExperienceWithChoiceViaTW;

	@Column(name="IsProviderCapacityLackOfAvailability")
	private String isProviderCapacityLackOfAvailability;

	@Column(name="IsStatusSwitch")
	private String isStatusSwitch;

	@Column(name="MedicalDirectorSignatureDate")
	private Timestamp medicalDirectorSignatureDate;

	@Column(name="Modified_By")
	private String modifiedBy;

	@Column(name="Modified_Date")
	private Timestamp modifiedDate;

	@Column(name="Owner")
	private String owner;

	@Column(name="OwningBusinessUnit")
	private String owningBusinessUnit;

	@Column(name="OwningTeam")
	private String owningTeam;

	@Column(name="OwningUser")
	private String owningUser;

	@Column(name="PendingDateSet")
	private Timestamp pendingDateSet;
	
	@Column(name="PPMSCreated_By")
	private String ppmsCreatedBy;

	@Column(name="PPMSCreatedOn_Date")
	private Timestamp ppmsCreatedOnDate;

	@Column(name="PPMSModified_By")
	private String ppmsModifiedBy;

	@Column(name = "PPMSModifiedOn_Date")
	private Date ppmsModifiedOnDate;
	
	@Column(name="ProviderName")
	private String providerName;

	@Column(name="ProviderType")
	private String providerType;

	@Column(name="QualificationExpirationDate")
	private Timestamp qualificationExpirationDate;

	@Column(name="QualificationReviewDate")
	private Timestamp qualificationReviewDate;

	@Column(name="RequiredAttachment")
	private String requiredAttachment;

	@Column(name="SpecialtyCoverage")
	private String specialtyCoverage;

	@Column(name="ProviderAgreementStatus")
	private String providerAgreementStatus;

	@Column(name="ProviderAgreementStatusReason")
	private String providerAgreementStatusReason;

	@Column(name="StatusSwitchDate")
	private Timestamp statusSwitchDate;
	
	 

	//bi-directional many-to-one association to NonVAProvider
	@ManyToOne
	@JoinColumn(name="NonVAProvider_Id_FK")
	private NonVAProvider nonVaprovider;

	public ProviderAgreement() {
	}

	public int getProviderAgreementId() {
		return this.providerAgreementId;
	}

	public void setProviderAgreementId(int providerAgreementId) {
		this.providerAgreementId = providerAgreementId;
	}

	public String getAgreementGuid() {
		return this.agreementGuid;
	}

	public void setAgreementGuid(String agreementGuid) {
		this.agreementGuid = agreementGuid;
	}

	public String getAgreementId() {
		return this.agreementId;
	}

	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}

	public String getAgreementType() {
		return this.agreementType;
	}

	public void setAgreementType(String agreementType) {
		this.agreementType = agreementType;
	}

	public String getAssignedFacility() {
		return this.assignedFacility;
	}

	public void setAssignedFacility(String assignedFacility) {
		this.assignedFacility = assignedFacility;
	}

	public Timestamp getCalcAgreementExpirationDate() {
		return this.calcAgreementExpirationDate;
	}

	public void setCalcAgreementExpirationDate(Timestamp calcAgreementExpirationDate) {
		this.calcAgreementExpirationDate = calcAgreementExpirationDate;
	}

	public String getCalcExpirationTimeFrameYears() {
		return this.calcExpirationTimeFrameYears;
	}

	public void setCalcExpirationTimeFrameYears(String calcExpirationTimeFrameYears) {
		this.calcExpirationTimeFrameYears = calcExpirationTimeFrameYears;
	}

	public Timestamp getCancelDateSet() {
		return this.cancelDateSet;
	}

	public void setCancelDateSet(Timestamp cancelDateSet) {
		this.cancelDateSet = cancelDateSet;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreated_Date() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getDocumentLocation() {
		return this.documentLocation;
	}

	public void setDocumentLocation(String documentLocation) {
		this.documentLocation = documentLocation;
	}

	public Timestamp getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Timestamp effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Timestamp getExpirationDate() {
		return this.expirationDate;
	}

	public void setExpirationDate(Timestamp expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getImportStatusField() {
		return this.importStatusField;
	}

	public void setImportStatusField(String importStatusField) {
		this.importStatusField = importStatusField;
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

	public String getIsDeclinedPaymentRateOrMethodology() {
		return this.isDeclinedPaymentRateOrMethodology;
	}

	public void setIsDeclinedPaymentRateOrMethodology(String isDeclinedPaymentRateOrMethodology) {
		this.isDeclinedPaymentRateOrMethodology = isDeclinedPaymentRateOrMethodology;
	}

	public String getIsDeclinedPreviousExperienceWithChoice() {
		return this.isDeclinedPreviousExperienceWithChoice;
	}

	public void setIsDeclinedPreviousExperienceWithChoice(String isDeclinedPreviousExperienceWithChoice) {
		this.isDeclinedPreviousExperienceWithChoice = isDeclinedPreviousExperienceWithChoice;
	}

	public String getIsDeclinedPreviousExpWithProvAgreement() {
		return this.isDeclinedPreviousExpWithProvAgreement;
	}

	public void setIsDeclinedPreviousExpWithProvAgreement(String isDeclinedPreviousExpWithProvAgreement) {
		this.isDeclinedPreviousExpWithProvAgreement = isDeclinedPreviousExpWithProvAgreement;
	}

	public String getIsDeclinedRefusalToCommitAtLowerLevel() {
		return this.isDeclinedRefusalToCommitAtLowerLevel;
	}

	public void setIsDeclinedRefusalToCommitAtLowerLevel(String isDeclinedRefusalToCommitAtLowerLevel) {
		this.isDeclinedRefusalToCommitAtLowerLevel = isDeclinedRefusalToCommitAtLowerLevel;
	}

	public String getIsDeclinedRefusalToCompleteOtherReqTraining() {
		return this.isDeclinedRefusalToCompleteOtherReqTraining;
	}

	public void setIsDeclinedRefusalToCompleteOtherReqTraining(String isDeclinedRefusalToCompleteOtherReqTraining) {
		this.isDeclinedRefusalToCompleteOtherReqTraining = isDeclinedRefusalToCompleteOtherReqTraining;
	}

	public String getIsDeclinedRefusalToCompleteReqOpiodTraining() {
		return this.isDeclinedRefusalToCompleteReqOpiodTraining;
	}

	public void setIsDeclinedRefusalToCompleteReqOpiodTraining(String isDeclinedRefusalToCompleteReqOpiodTraining) {
		this.isDeclinedRefusalToCompleteReqOpiodTraining = isDeclinedRefusalToCompleteReqOpiodTraining;
	}

	public String getIsDeclinedRefusedCompleteQualificationDocument() {
		return this.isDeclinedRefusedCompleteQualificationDocument;
	}

	public void setIsDeclinedRefusedCompleteQualificationDocument(String isDeclinedRefusedCompleteQualificationDocument) {
		this.isDeclinedRefusedCompleteQualificationDocument = isDeclinedRefusedCompleteQualificationDocument;
	}

	public String getIsDeclinedTimelyReimbursements() {
		return this.isDeclinedTimelyReimbursements;
	}

	public void setIsDeclinedTimelyReimbursements(String isDeclinedTimelyReimbursements) {
		this.isDeclinedTimelyReimbursements = isDeclinedTimelyReimbursements;
	}

	public String getIsPreviousExperienceWithChoiceViaTW() {
		return this.isPreviousExperienceWithChoiceViaTW;
	}

	public void setIsPreviousExperienceWithChoiceViaTW(String isPreviousExperienceWithChoiceViaTW) {
		this.isPreviousExperienceWithChoiceViaTW = isPreviousExperienceWithChoiceViaTW;
	}

	public String getIsProviderCapacityLackOfAvailability() {
		return this.isProviderCapacityLackOfAvailability;
	}

	public void setIsProviderCapacityLackOfAvailability(String isProviderCapacityLackOfAvailability) {
		this.isProviderCapacityLackOfAvailability = isProviderCapacityLackOfAvailability;
	}

	public String getIsStatusSwitch() {
		return this.isStatusSwitch;
	}

	public void setIsStatusSwitch(String isStatusSwitch) {
		this.isStatusSwitch = isStatusSwitch;
	}

	public Timestamp getMedicalDirectorSignatureDate() {
		return this.medicalDirectorSignatureDate;
	}

	public void setMedicalDirectorSignatureDate(Timestamp medicalDirectorSignatureDate) {
		this.medicalDirectorSignatureDate = medicalDirectorSignatureDate;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getOwningBusinessUnit() {
		return this.owningBusinessUnit;
	}

	public void setOwningBusinessUnit(String owningBusinessUnit) {
		this.owningBusinessUnit = owningBusinessUnit;
	}

	public String getOwningTeam() {
		return this.owningTeam;
	}

	public void setOwningTeam(String owningTeam) {
		this.owningTeam = owningTeam;
	}

	public String getOwningUser() {
		return this.owningUser;
	}

	public void setOwningUser(String owningUser) {
		this.owningUser = owningUser;
	}

	public Timestamp getPendingDateSet() {
		return this.pendingDateSet;
	}

	public void setPendingDateSet(Timestamp pendingDateSet) {
		this.pendingDateSet = pendingDateSet;
	}
	
	public String getPpmsCreatedBy() {
		return ppmsCreatedBy;
	}

	public void setPpmsCreatedBy(String ppmsCreatedBy) {
		this.ppmsCreatedBy = ppmsCreatedBy;
	}

	public Timestamp getPpmsCreatedOnDate() {
		return ppmsCreatedOnDate;
	}

	public void setPpmsCreatedOnDate(Timestamp ppmsCreatedOnDate) {
		this.ppmsCreatedOnDate = ppmsCreatedOnDate;
	}

	public String getPpmsModifiedBy() {
		return ppmsModifiedBy;
	}

	public void setPpmsModifiedBy(String ppmsModifiedBy) {
		this.ppmsModifiedBy = ppmsModifiedBy;
	}

	public Date getPpmsModifiedOnDate() {
		return ppmsModifiedOnDate;
	}

	public void setPpmsModifiedOnDate(Date ppmsModifiedOnDate) {
		this.ppmsModifiedOnDate = ppmsModifiedOnDate;
	}

	public String getProviderAgreementStatus() {
		return providerAgreementStatus;
	}

	public void setProviderAgreementStatus(String providerAgreementStatus) {
		this.providerAgreementStatus = providerAgreementStatus;
	}

	public String getProviderAgreementStatusReason() {
		return providerAgreementStatusReason;
	}

	public void setProviderAgreementStatusReason(String providerAgreementStatusReason) {
		this.providerAgreementStatusReason = providerAgreementStatusReason;
	}

	public String getProviderName() {
		return this.providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getProviderType() {
		return this.providerType;
	}

	public void setProviderType(String providerType) {
		this.providerType = providerType;
	}

	public Timestamp getQualificationExpirationDate() {
		return this.qualificationExpirationDate;
	}

	public void setQualificationExpirationDate(Timestamp qualificationExpirationDate) {
		this.qualificationExpirationDate = qualificationExpirationDate;
	}

	public Timestamp getQualificationReviewDate() {
		return this.qualificationReviewDate;
	}

	public void setQualificationReviewDate(Timestamp qualificationReviewDate) {
		this.qualificationReviewDate = qualificationReviewDate;
	}

	public String getRequiredAttachment() {
		return this.requiredAttachment;
	}

	public void setRequiredAttachment(String requiredAttachment) {
		this.requiredAttachment = requiredAttachment;
	}

	public String getSpecialtyCoverage() {
		return this.specialtyCoverage;
	}

	public void setSpecialtyCoverage(String specialtyCoverage) {
		this.specialtyCoverage = specialtyCoverage;
	}

	public Timestamp getStatusSwitchDate() {
		return this.statusSwitchDate;
	}

	public void setStatusSwitchDate(Timestamp statusSwitchDate) {
		this.statusSwitchDate = statusSwitchDate;
	}

	public NonVAProvider getNonVaprovider() {
		return this.nonVaprovider;
	}

	public void setNonVaprovider(NonVAProvider nonVaprovider) {
		this.nonVaprovider = nonVaprovider;
	}
	
	
}