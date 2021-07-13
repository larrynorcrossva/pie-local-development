package gov.va.pie.nvappms.cdw.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * CDWAgreement
 * <p>
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CDWAgreement {
	/**
    *
    * (Required)
    *
    */
   @JsonProperty("AgreementId")
   String agreementId;
  /**
   *
   * 
   *
   */
   @JsonProperty("AgreementType")
   String agreementType;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("AssignedFacility")
   String assignedFacility;
   /**
    *
    *
    *
    */
   @JsonProperty("DaysTillExpiration")
   Integer daysTillExpiration;
   /**
    *
    *
    *
    */
   @JsonProperty("EffectiveDate")
   String effectiveDate;
   /**
    *
    *
    *
    */
   @JsonProperty("ExpirationDate")
   String expirationDate;
   /**
    *
    *
    *
    */
   @JsonProperty("MedicalDirectorSignatureDate")
   String medicalDirectorSignatureDate;
   /**
    *
    *
    *
    */
   @JsonProperty("ModifiedOnDate")
   String modifiedOnDate;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("Owner")
   String owner;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("ProviderName")
   String providerName;
   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderType")
   String providerType;
   /**
    *
    *
    *
    */
   @JsonProperty("QualificationExpirationDate")
   String qualificationExpirationDate;
   /**
    *
    *
    *
    */
   @JsonProperty("QualificationReviewDate")
   String qualificationReviewDate;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("SpecialtyCoverage")
   String specialtyCoverage;
   /**
    *
    * 
    *
    */
   @JsonProperty("AgreementStatus")
   String agreementStatus;
   /**
    *
    * 
    *
    */
   @JsonProperty("AgreementStatusReason")
   String agreementStatusReason;
  /**
    *
    *
    *
    */
   @JsonProperty("AgreementGuid")
   String agreementGuid;
   /**
    *
    *
    *
    */
   @JsonProperty("CalcAgreementExpirationDate")
   String calcAgreementExpirationDate;
   /**
    *
    *
    *
    */
   @JsonProperty("CalcExpirationTimeFrameYears")
   Integer calcExpirationTimeFrameYears;
   /**
    *
    *
    *
    */
   @JsonProperty("CancelDateSet")
   String cancelDateSet;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("CreatedBy")
   String createdBy;
   /**
    *
    *
    *
    */
   @JsonProperty("CreatedOn")
   String createdOn;
   /**
    *
    *
    *
    */
   @JsonProperty("DeclinedPreviousExpWithProvAgreement")
   Boolean declinedPreviousExpWithProvAgreement;
   /**
    *
    *
    *
    */
   @JsonProperty("PreviousExperienceWithChoiceViaTW")
   Boolean previousExperienceWithChoiceViaTW;
   /**
    *
    *
    *
    */
   @JsonProperty("DeclinedProviderCapacityLackOfAvailability")
   Boolean declinedProviderCapacityLackOfAvailability;
   /**
    *
    *
    *
    */
   @JsonProperty("DeclinedRefusalToCommitAtLowerLevel")
   Boolean declinedRefusalToCommitAtLowerLevel;
   /**
    *
    *
    *
    */
   @JsonProperty("DeclinedRefusalToCompleteOtherReqTraining")
   Boolean declinedRefusalToCompleteOtherReqTraining;
   /**
    *
    *
    *
    */
   @JsonProperty("DeclinedRefusalToCompleteReqOpiodTraining")
   Boolean declinedRefusalToCompleteReqOpiodTraining;
   /**
    *
    *
    *
    */
   @JsonProperty("DeclinedRefusedCompleteQualificationDocument")
   Boolean declinedRefusedCompleteQualificationDocument;
   /**
    *
    *
    *
    */
   @JsonProperty("DeclinedPaymentRateorMethodology")
   Boolean declinedPaymentRateorMethodology;
   /**
    *
    *
    *
    */
   @JsonProperty("DeclinedPreviousExperienceWithChoice")
   Boolean declinedPreviousExperienceWithChoice;
   /**
    *
    *
    *
    */
   @JsonProperty("DeclinedTimelyReimbursements")
   Boolean declinedTimelyReimbursements;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("DocumentLocation")
   String documentLocation;
   /**
    *
    *
    *
    */
   @JsonProperty("ExpirationReminderDate15Days")
   String expirationReminderDate15Days;
   /**
    *
    *
    *
    */
   @JsonProperty("ExpirationReminderDate30Days")
   String expirationReminderDate30Days;
   /**
    *
    *
    *
    */
   @JsonProperty("ExpirationReminderDate45Days")
   String expirationReminderDate45Days;
   /**
    *
    *
    *
    */
   @JsonProperty("ExpirationReminderDate5Days")
   String expirationReminderDate5Days;
   /**
    *
    *
    *
    */
   @JsonProperty("ExpirationReminderDate60Days")
   String expirationReminderDate60Days;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("ImportStatusField")
   String importStatusField;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("ModifiedBy")
   String modifiedBy;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("OwningBusinessUnit")
   String owningBusinessUnit;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("OwningTeam")
   String owningTeam;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("OwningUser")
   String owningUser;
   /**
    *
    *
    *
    */
   @JsonProperty("PendingDateSet")
   String pendingDateSet;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("RequiredAttachment")
   String requiredAttachment;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("StatusSwitch")
   Boolean statusSwitch;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("StatusSwitchDate")
   String statusSwitchDate;
   /**
    *
    *
    *
    */
   @JsonProperty("ProviderNpi")
   long providerNpi;

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("AgreementId")
   public String getAgreementId() {
       return agreementId;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("AgreementId")
   public void setAgreementId(String agreementId) {
       this.agreementId = agreementId;
   }
   
  /**
   *
   * 
   *
   */
  @JsonProperty("AgreementType")
  public String getAgreementType() {
      return agreementType;
  }

  /**
   *
   * 
   *
   */
  @JsonProperty("AgreementType")
  public void setAgreementType(String agreementType) {
      this.agreementType = agreementType;
  }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("AssignedFacility")
   public String getAssignedFacility() {
       return assignedFacility;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("AssignedFacility")
   public void setAssignedFacility(String assignedFacility) {
       this.assignedFacility = assignedFacility;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("DaysTillExpiration")
   public Integer getDaysTillExpiration() {
       return daysTillExpiration;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("DaysTillExpiration")
   public void setDaysTillExpiration(Integer daysTillExpiration) {
       this.daysTillExpiration = daysTillExpiration;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("EffectiveDate")
   public String getEffectiveDate() {
       return effectiveDate;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("EffectiveDate")
   public void setEffectiveDate(String effectiveDate) {
       this.effectiveDate = effectiveDate;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ExpirationDate")
   public String getExpirationDate() {
       return expirationDate;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ExpirationDate")
   public void setExpirationDate(String expirationDate) {
       this.expirationDate = expirationDate;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("MedicalDirectorSignatureDate")
   public String getMedicalDirectorSignatureDate() {
       return medicalDirectorSignatureDate;
   }

   /**
    *
    *
    *
    */
   @JsonProperty("MedicalDirectorSignatureDate")
   public void setMedicalDirectorSignatureDate(String medicalDirectorSignatureDate) {
       this.medicalDirectorSignatureDate = medicalDirectorSignatureDate;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ModifiedOnDate")
   public String getModifiedOnDate() {
       return modifiedOnDate;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ModifiedOnDate")
   public void setModifiedOnDate(String modifiedOnDate) {
       this.modifiedOnDate = modifiedOnDate;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("Owner")
   public String getOwner() {
       return owner;
   }

   /**
    *
    * (Required)  
    *
    */
   @JsonProperty("Owner")
   public void setOwner(String owner) {
       this.owner = owner;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("ProviderName")
   public String getProviderName() {
       return providerName;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("ProviderName")
   public void setProviderName(String providerName) {
       this.providerName = providerName;
   }
   
  /**
   *
   * 
   *
   */
  @JsonProperty("ProviderType")
  public String getProviderType() {
      return providerType;
  }

  /**
   *
   * 
   *
   */
  @JsonProperty("ProviderType")
  public void setProviderType(String providerType) {
      this.providerType = providerType;
  }

   /**
    *
    * 
    *
    */
   @JsonProperty("QualificationExpirationDate")
   public String getQualificationExpirationDate() {
       return qualificationExpirationDate;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("QualificationExpirationDate")
   public void setQualificationExpirationDate(String qualificationExpirationDate) {
       this.qualificationExpirationDate = qualificationExpirationDate;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("QualificationReviewDate")
   public String getQualificationReviewDate() {
       return qualificationReviewDate;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("QualificationReviewDate")
   public void setQualificationReviewDate(String qualificationReviewDate) {
       this.qualificationReviewDate = qualificationReviewDate;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("SpecialtyCoverage")
   public String getSpecialtyCoverage() {
       return specialtyCoverage;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("SpecialtyCoverage")
   public void setSpecialtyCoverage(String specialtyCoverage) {
       this.specialtyCoverage = specialtyCoverage;
   }
   
   /**
    *
    * 
    *
    */
   @JsonProperty("AgreementStatus")
   public String getAgreementStatus() {
       return agreementStatus;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("AgreementStatus")
   public void setAgreementStatus(String agreementStatus) {
       this.agreementStatus = agreementStatus;
   }
   
   /**
    *
    * 
    *
    */
   @JsonProperty("AgreementStatusReason")
   public String getAgreementStatusReason() {
       return agreementStatusReason;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("AgreementStatusReason")
   public void setAgreementStatusReason(String agreementStatusReason) {
       this.agreementStatusReason = agreementStatusReason;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("AgreementGuid")
   public String getAgreementGuid() {
       return agreementGuid;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("AgreementGuid")
   public void setAgreementGuid(String agreementGuid) {
       this.agreementGuid = agreementGuid;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("CalcAgreementExpirationDate")
   public String getCalcAgreementExpirationDate() {
       return calcAgreementExpirationDate;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("CalcAgreementExpirationDate")
   public void setCalcAgreementExpirationDate(String calcAgreementExpirationDate) {
       this.calcAgreementExpirationDate = calcAgreementExpirationDate;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("CalcExpirationTimeFrameYears")
   public Integer getCalcExpirationTimeFrameYears() {
       return calcExpirationTimeFrameYears;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("CalcExpirationTimeFrameYears")
   public void setCalcExpirationTimeFrameYears(Integer calcExpirationTimeFrameYears) {
       this.calcExpirationTimeFrameYears = calcExpirationTimeFrameYears;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("CancelDateSet")
   public String getCancelDateSet() {
       return cancelDateSet;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("CancelDateSet")
   public void setCancelDateSet(String cancelDateSet) {
       this.cancelDateSet = cancelDateSet;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("CreatedBy")
   public String getCreatedBy() {
       return createdBy;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("CreatedBy")
   public void setCreatedBy(String createdBy) {
       this.createdBy = createdBy;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("CreatedOn")
   public String getCreatedOn() {
       return createdOn;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("CreatedOn")
   public void setCreatedOn(String createdOn) {
       this.createdOn = createdOn;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("DeclinedPreviousExpWithProvAgreement")
   public Boolean getDeclinedPreviousExpWithProvAgreement() {
       return declinedPreviousExpWithProvAgreement;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("DeclinedPreviousExpWithProvAgreement")
   public void setDeclinedPreviousExpWithProvAgreement(Boolean declinedPreviousExpWithProvAgreement) {
       this.declinedPreviousExpWithProvAgreement = declinedPreviousExpWithProvAgreement;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("PreviousExperienceWithChoiceViaTW")
   public Boolean getPreviousExperienceWithChoiceViaTW() {
       return previousExperienceWithChoiceViaTW;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("PreviousExperienceWithChoiceViaTW")
   public void setPreviousExperienceWithChoiceViaTW(Boolean previousExperienceWithChoiceViaTW) {
       this.previousExperienceWithChoiceViaTW = previousExperienceWithChoiceViaTW;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("DeclinedProviderCapacityLackOfAvailability")
   public Boolean getDeclinedProviderCapacityLackOfAvailability() {
       return declinedProviderCapacityLackOfAvailability;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("DeclinedProviderCapacityLackOfAvailability")
   public void setDeclinedProviderCapacityLackOfAvailability(Boolean declinedProviderCapacityLackOfAvailability) {
       this.declinedProviderCapacityLackOfAvailability = declinedProviderCapacityLackOfAvailability;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("DeclinedRefusalToCommitAtLowerLevel")
   public Boolean getDeclinedRefusalToCommitAtLowerLevel() {
       return declinedRefusalToCommitAtLowerLevel;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("DeclinedRefusalToCommitAtLowerLevel")
   public void setDeclinedRefusalToCommitAtLowerLevel(Boolean declinedRefusalToCommitAtLowerLevel) {
       this.declinedRefusalToCommitAtLowerLevel = declinedRefusalToCommitAtLowerLevel;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("DeclinedRefusalToCompleteOtherReqTraining")
   public Boolean getDeclinedRefusalToCompleteOtherReqTraining() {
       return declinedRefusalToCompleteOtherReqTraining;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("DeclinedRefusalToCompleteOtherReqTraining")
   public void setDeclinedRefusalToCompleteOtherReqTraining(Boolean declinedRefusalToCompleteOtherReqTraining) {
       this.declinedRefusalToCompleteOtherReqTraining = declinedRefusalToCompleteOtherReqTraining;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("DeclinedRefusalToCompleteReqOpiodTraining")
   public Boolean getDeclinedRefusalToCompleteReqOpiodTraining() {
       return declinedRefusalToCompleteReqOpiodTraining;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("DeclinedRefusalToCompleteReqOpiodTraining")
   public void setDeclinedRefusalToCompleteReqOpiodTraining(Boolean declinedRefusalToCompleteReqOpiodTraining) {
       this.declinedRefusalToCompleteReqOpiodTraining = declinedRefusalToCompleteReqOpiodTraining;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("DeclinedRefusedCompleteQualificationDocument")
   public Boolean getDeclinedRefusedCompleteQualificationDocument() {
       return declinedRefusedCompleteQualificationDocument;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("DeclinedRefusedCompleteQualificationDocument")
   public void setDeclinedRefusedCompleteQualificationDocument(Boolean declinedRefusedCompleteQualificationDocument) {
       this.declinedRefusedCompleteQualificationDocument = declinedRefusedCompleteQualificationDocument;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("DeclinedPaymentRateorMethodology")
   public Boolean getDeclinedPaymentRateorMethodology() {
       return declinedPaymentRateorMethodology;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("DeclinedPaymentRateorMethodology")
   public void setDeclinedPaymentRateorMethodology(Boolean declinedPaymentRateorMethodology) {
       this.declinedPaymentRateorMethodology = declinedPaymentRateorMethodology;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("DeclinedPreviousExperienceWithChoice")
   public Boolean getDeclinedPreviousExperienceWithChoice() {
       return declinedPreviousExperienceWithChoice;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("DeclinedPreviousExperienceWithChoice")
   public void setDeclinedPreviousExperienceWithChoice(Boolean declinedPreviousExperienceWithChoice) {
       this.declinedPreviousExperienceWithChoice = declinedPreviousExperienceWithChoice;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("DeclinedTimelyReimbursements")
   public Boolean getDeclinedTimelyReimbursements() {
       return declinedTimelyReimbursements;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("DeclinedTimelyReimbursements")
   public void setDeclinedTimelyReimbursements(Boolean declinedTimelyReimbursements) {
       this.declinedTimelyReimbursements = declinedTimelyReimbursements;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("DocumentLocation")
   public String getDocumentLocation() {
       return documentLocation;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("DocumentLocation")
   public void setDocumentLocation(String documentLocation) {
       this.documentLocation = documentLocation;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ExpirationReminderDate15Days")
   public String getExpirationReminderDate15Days() {
       return expirationReminderDate15Days;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ExpirationReminderDate15Days")
   public void setExpirationReminderDate15Days(String expirationReminderDate15Days) {
       this.expirationReminderDate15Days = expirationReminderDate15Days;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ExpirationReminderDate30Days")
   public String getExpirationReminderDate30Days() {
       return expirationReminderDate30Days;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ExpirationReminderDate30Days")
   public void setExpirationReminderDate30Days(String expirationReminderDate30Days) {
       this.expirationReminderDate30Days = expirationReminderDate30Days;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ExpirationReminderDate45Days")
   public String getExpirationReminderDate45Days() {
       return expirationReminderDate45Days;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ExpirationReminderDate45Days")
   public void setExpirationReminderDate45Days(String expirationReminderDate45Days) {
       this.expirationReminderDate45Days = expirationReminderDate45Days;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ExpirationReminderDate5Days")
   public String getExpirationReminderDate5Days() {
       return expirationReminderDate5Days;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ExpirationReminderDate5Days")
   public void setExpirationReminderDate5Days(String expirationReminderDate5Days) {
       this.expirationReminderDate5Days = expirationReminderDate5Days;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ExpirationReminderDate60Days")
   public String getExpirationReminderDate60Days() {
       return expirationReminderDate60Days;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ExpirationReminderDate60Days")
   public void setExpirationReminderDate60Days(String expirationReminderDate60Days) {
       this.expirationReminderDate60Days = expirationReminderDate60Days;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("ImportStatusField")
   public String getImportStatusField() {
       return importStatusField;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("ImportStatusField")
   public void setImportStatusField(String importStatusField) {
       this.importStatusField = importStatusField;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("ModifiedBy")
   public String getModifiedBy() {
       return modifiedBy;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("ModifiedBy")
   public void setModifiedBy(String modifiedBy) {
       this.modifiedBy = modifiedBy;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("OwningBusinessUnit")
   public String getOwningBusinessUnit() {
       return owningBusinessUnit;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("OwningBusinessUnit")
   public void setOwningBusinessUnit(String owningBusinessUnit) {
       this.owningBusinessUnit = owningBusinessUnit;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("OwningTeam")
   public String getOwningTeam() {
       return owningTeam;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("OwningTeam")
   public void setOwningTeam(String owningTeam) {
       this.owningTeam = owningTeam;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("OwningUser")
   public String getOwningUser() {
       return owningUser;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("OwningUser")
   public void setOwningUser(String owningUser) {
       this.owningUser = owningUser;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("PendingDateSet")
   public String getPendingDateSet() {
       return pendingDateSet;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("PendingDateSet")
   public void setPendingDateSet(String pendingDateSet) {
       this.pendingDateSet = pendingDateSet;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("RequiredAttachment")
   public String getRequiredAttachment() {
       return requiredAttachment;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("RequiredAttachment")
   public void setRequiredAttachment(String requiredAttachment) {
       this.requiredAttachment = requiredAttachment;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("StatusSwitch")
   public Boolean getStatusSwitch() {
       return statusSwitch;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("StatusSwitch")
   public void setStatusSwitch(Boolean statusSwitch) {
       this.statusSwitch = statusSwitch;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("StatusSwitchDate")
   public String getStatusSwitchDate() {
       return statusSwitchDate;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("StatusSwitchDate")
   public void setStatusSwitchDate(String statusSwitchDate) {
       this.statusSwitchDate = statusSwitchDate;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderNpi")
   public long getProviderNpi() {
       return providerNpi;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderNpi")
   public void setProviderNpi(long providerNpi) {
       this.providerNpi = providerNpi;
   }
   

  @Override
  public String toString() {
     return new ToStringBuilder(this)
   		  .append("agreementId", agreementId)
   		  .append("agreementType", agreementType)
   		  .append("assignedFacility", assignedFacility)
   		  .append("daysTillExpiration", daysTillExpiration)
   		  .append("effectiveDate", effectiveDate)
   		  .append("expirationDate", expirationDate)
   		  .append("medicalDirectorSignatureDate", medicalDirectorSignatureDate)
   		  .append("modifiedOnDate", modifiedOnDate)
   		  .append("owner", owner)
   		  .append("providerName", providerName)
   		  .append("providerType", providerType)
   		  .append("qualificationExpirationDate", qualificationExpirationDate)
   		  .append("qualificationReviewDate", qualificationReviewDate)
   		  .append("specialtyCoverage", specialtyCoverage)
   		  .append("agreementStatus", agreementStatus)
   		  .append("agreementStatusReason", agreementStatusReason)
   		  .append("agreementGuid", agreementGuid)
   		  .append("calcAgreementExpirationDate", calcAgreementExpirationDate)
   		  .append("calcExpirationTimeFrameYears", calcExpirationTimeFrameYears)
   		  .append("cancelDateSet", cancelDateSet)
   		  .append("createdBy", createdBy)
   		  .append("createdOn", createdOn)
   		  .append("declinedPreviousExpWithProvAgreement", declinedPreviousExpWithProvAgreement)
   		  .append("previousExperienceWithChoiceViaTW", previousExperienceWithChoiceViaTW)
   		  .append("declinedProviderCapacityLackOfAvailability", declinedProviderCapacityLackOfAvailability)
   		  .append("declinedRefusalToCommitAtLowerLevel", declinedRefusalToCommitAtLowerLevel)
   		  .append("declinedRefusalToCompleteOtherReqTraining", declinedRefusalToCompleteOtherReqTraining)
   		  .append("declinedRefusalToCompleteReqOpiodTraining", declinedRefusalToCompleteReqOpiodTraining)
   		  .append("declinedRefusedCompleteQualificationDocument", declinedRefusedCompleteQualificationDocument)
   		  .append("declinedPaymentRateorMethodology", declinedPaymentRateorMethodology)
   		  .append("declinedPreviousExperienceWithChoice", declinedPreviousExperienceWithChoice)
   		  .append("declinedTimelyReimbursements", declinedTimelyReimbursements)
   		  .append("documentLocation", documentLocation)
   		  .append("expirationReminderDate15Days", expirationReminderDate15Days)
   		  .append("expirationReminderDate30Days", expirationReminderDate30Days)
   		  .append("expirationReminderDate45Days", expirationReminderDate45Days)
   		  .append("expirationReminderDate5Days", expirationReminderDate5Days)
   		  .append("expirationReminderDate60Days", expirationReminderDate60Days)
   		  .append("importStatusField", importStatusField)
   		  .append("modifiedBy", modifiedBy)
   		  .append("owningBusinessUnit", owningBusinessUnit)
   		  .append("owningTeam", owningTeam)
   		  .append("owningUser", owningUser)
   		  .append("pendingDateSet", pendingDateSet)
   		  .append("requiredAttachment", requiredAttachment)
   		  .append("statusSwitch", statusSwitch)
   		  .append("statusSwitchDate", statusSwitchDate)
   		  .append("providerNpi", providerNpi)
   		  .toString();
  }
}
