package gov.va.pie.nvappms.cdw.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * CDWProvider
 * <p>
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CDWProvider {

	/**
    *
    * (Required)
    *
    */
   @JsonProperty("ProviderNpi")
   long providerNpi;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1Composite")
   String billingAddress1Composite;
   /**
    *
    * (Required)
    *
    */
  
   @JsonProperty("BillingAddress1City")
   String billingAddress1City;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1CountryRegion")
   String billingAddress1CountryRegion;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1County")
   String billingAddress1County;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1StateProvince")
   String billingAddress1StateProvince;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1Street1")
   String billingAddress1Street1;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1Street2")
   String billingAddress1Street2;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1Street3")
   String billingAddress1Street3;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1ZipPostalCode")
   String billingAddress1ZipPostalCode;
   /**
    *
    * 
    *
    */
   @JsonProperty("DirectMessagingVHIE")
   Boolean directMessagingVHIE;
   /**
    *
    * 
    *
    */
   @JsonProperty("EMRSystem")
   String eMRSystem;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("EMRSystemOther")
   String eMRSystemOther;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("Email")
   String email;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("Fax")
   String fax;
   /**
    *
    * 
    *
    */
   @JsonProperty("HSRM")
   Boolean hSRM;
   /**
    *
    * 
    *
    */
   @JsonProperty("IndividualIsPrimaryCareProviderAcceptingVA")
   Boolean individualIsPrimaryCareProviderAcceptingVA;
   /**
    *
    * 
    *
    */
   @JsonProperty("IsExternal")
   Boolean isExternal;
   /**
    *
    * 
    *
    */
   @JsonProperty("LeieValidated")
   Boolean leieValidated;
   /**
    *
    * 
    *
    */
   @JsonProperty("LicenseValidated")
   Boolean licenseValidated;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("MainPhone")
   String mainPhone;
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
    * 
    *
    */
   @JsonProperty("Phone")
   Boolean phone;
   /**
    *
    * 
    *
    */
   @JsonProperty("PrimaryCarePhysician")
   Boolean primaryCarePhysician;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("PrimaryDirectMessagingAddress")
   String primaryDirectMessagingAddress;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("ProviderFirstName")
   String providerFirstName;
   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderMiddleName")
   String providerMiddleName;
   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderIdentifierType")
   String providerIdentifierType;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("ProviderLastName")
   String providerLastName;
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
   @JsonProperty("RefDoc")
   Boolean refDoc;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("SecondaryDirectMessagingAddress")
   String secondaryDirectMessagingAddress;
   /**
    *
    * 
    *
    */
   @JsonProperty("SecuredEmail")
   Boolean securedEmail;
   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderStatus")
   String providerStatus;
   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderStatusReason")
   String providerStatusReason;
   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderGuid")
   String providerGuid;
   /**
    *
    * 
    *
    */
   @JsonProperty("AccounatbleCareOrganization")
   Boolean accounatbleCareOrganization;
   /**
    *
    * 
    *
    */
   @JsonProperty("AddressValidated")
   Boolean addressValidated;
   /**
    *
    * 
    *
    */
   @JsonProperty("AgreementCount")
   Integer agreementCount;
   /**
    *
    * 
    *
    */
   @JsonProperty("AgreementCountLastUpdatedOn")
   String agreementCountLastUpdatedOn;
   /**
    *
    * 
    *
    */
   @JsonProperty("AgreementCountState")
   Integer agreementCountState;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1Fax")
   String billingAddress1Fax;
   /**
    *
    * 
    *
    */
   @JsonProperty("BillingAddress1ID")
   String billingAddress1ID;
   /**
    *
    * 
    *
    */
   @JsonProperty("BillingAddress1Latitude")
   Double billingAddress1Latitude;
   /**
    *
    * 
    *
    */
   @JsonProperty("BillingAddress1Longitude")
   Double billingAddress1Longitude;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1Name")
   String billingAddress1Name;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1PostOfficeBox")
   String billingAddress1PostOfficeBox;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1Telephone2")
   String billingAddress1Telephone2;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1Telephone3")
   String billingAddress1Telephone3;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddressPhone")
   String billingAddressPhone;
   /**
    *
    * 
    *
    */
   @JsonProperty("ContactPrefEmail")
   Boolean contactPrefEmail;
   /**
    *
    * 
    *
    */
   @JsonProperty("ContactPrefFax")
   Boolean contactPrefFax;
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
   @JsonProperty("DoNotAllowBulkEmails")
   Boolean doNotAllowBulkEmails;
   /**
    *
    * 
    *
    */
   @JsonProperty("DoNotAllowBulkMails")
   Boolean doNotAllowBulkMails;
   /**
    *
    * 
    *
    */
   @JsonProperty("DoNotAllowEmails")
   Boolean doNotAllowEmails;
   /**
    *
    * 
    *
    */
   @JsonProperty("DoNotAllowFaxes")
   Boolean doNotAllowFaxes;
   /**
    *
    * 
    *
    */
   @JsonProperty("DoNotAllowMails")
   Boolean doNotAllowMails;
   /**
    *
    * 
    *
    */
   @JsonProperty("DoNotAllowPhoneCalls")
   Boolean doNotAllowPhoneCalls;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("EmailAddress2")
   String emailAddress2;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("EmailAddress3")
   String emailAddress3;
   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderEthnicity")
   String providerEthnicity;
   /**
    *
    * 
    *
    */
   @JsonProperty("ExternalHealthProviderType")
   String externalHealthProviderType;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("ExternalInstitutionDEANumber")
   String externalInstitutionDEANumber;
   /**
    *
    * 
    *
    */
   @JsonProperty("ExternalLeieCheckDate")
   String externalLeieCheckDate;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("ExternalValidationSource")
   String externalValidationSource;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("Facility")
   String facility;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("FeeSchedule")
   String feeSchedule;
   /**
    *
    * 
    *
    */
   @JsonProperty("Gender")
   String gender;
   /**
    *
    * 
    *
    */
   @JsonProperty("Geocoded")
   Boolean geocoded;
   /**
    *
    * 
    *
    */
   @JsonProperty("IndivProviderDateOfBirth")
   String indivProviderDateOfBirth;
   /**
    *
    * 
    *
    */
   @JsonProperty("IndividualIsAcceptingNewPatients")
   Boolean individualIsAcceptingNewPatients;
   /**
    *
    * 
    *
    */
   @JsonProperty("InternalAppointmentStatus")
   String internalAppointmentStatus;
   /**
    *
    * 
    *
    */
   @JsonProperty("InternalCanCreateHealthCareOrders")
   Boolean internalCanCreateHealthCareOrders;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("InternalLicensingJurisdiction")
   String internalLicensingJurisdiction;
   /**
    *
    *
    *
    */
   @JsonProperty("InternalType")
   String internalType;
   /**
    *
    * 
    *
    */
   @JsonProperty("LastUpdatedDate")
   String lastUpdatedDate;
   /**
    *
    * 
    *
    */
   @JsonProperty("LastValidated")
   String lastValidated;
   /**
    *
    * 
    *
    */
   @JsonProperty("Mail")
   Boolean mail;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("ModifiedBy")
   String modifiedBy;
   /**
    *
    * 
    *
    */
   @JsonProperty("NppesNPIValidated")
   Boolean nppesNPIValidated;
   /**
    *
    * 
    *
    */
   @JsonProperty("OnLeie")
   Boolean onLeie;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("OrganizationId")
   String organizationId;
   /**
    *
    *
    *
    */
   @JsonProperty("OrganizationStatus")
   String organizationStatus;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("OtherPhone")
   String otherPhone;
   /**
    *
    *
    *
    */
   @JsonProperty("Ownership")
   String ownership;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("OwnedCareSite")
   String ownedCareSite;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("OwningBusinessunit")
   String owningBusinessunit;
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
    * (Required)
    *
    */
   @JsonProperty("PAMigrationKey")
   String pAMigrationKey;
   /**
    *
    *
    *
    */
   @JsonProperty("PreferredDay")
   String preferredDay;
   /**
    *
    *
    *
    */
   @JsonProperty("PreferredMethodOfContact")
   String preferredMethodOfContact;
  /**
   *
   *
   *
   */
   @JsonProperty("PreferredTime")
   String preferredTime;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("ParentProvider")
   String parentProvider;
   /**
    *
    * 
    *
    */
   @JsonProperty("Process")
   String process;
   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderContactValidated")
   Boolean providerContactValidated;
  /**
   *
   * 
   *
   */
   @JsonProperty("RecordSource")
   String recordSource;
  /**
   *
   * 
   *
   */
   @JsonProperty("RelationshipType")
   String relationshipType;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("Religion")
   String religion;
   /**
    *
    * 
    *
    */
   @JsonProperty("SAMValidated")
   Boolean sAMValidated;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("DoNotSendMarketingMaterials")
   Boolean doNotSendMarketingMaterials;
   /**
    *
    *
    *
    */
   @JsonProperty("ServiceProviderType")
   String serviceProviderType;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("SpecialInstruction")
   String specialInstruction;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("Telephone2")
   String telephone2;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("Telephone3")
   String telephone3;
   /**
    *
    * 
    *
    */
   @JsonProperty("ValidationReset")
   Boolean validationReset;
   /**
    *
    * 
    *
    */
   @JsonProperty("ValidationResetDate")
   String validationResetDate;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("WebSite")
   String webSite;

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("ProviderNpi")
   public long getProviderNpi() {
       return providerNpi;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("ProviderNpi")
   public void setProviderNpi(long providerNpi) {
       this.providerNpi = providerNpi;
   }
   
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1Composite")
   public String getBillingAddress1Composite() {
       return billingAddress1Composite;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1Composite")
   public void setBillingAddress1Composite(String billingAddress1Composite) {
       this.billingAddress1Composite = billingAddress1Composite;
   }

   /**
    *
    * (Required)
    *
    */
  
   @JsonProperty("BillingAddress1City")
   public String getBillingAddress1City() {
       return billingAddress1City;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1City")
   public void setBillingAddress1City(String billingAddress1City) {
       this.billingAddress1City = billingAddress1City;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1CountryRegion")
   public String getBillingAddress1CountryRegion() {
       return billingAddress1CountryRegion;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1CountryRegion")
   public void setBillingAddress1CountryRegion(String billingAddress1CountryRegion) {
       this.billingAddress1CountryRegion = billingAddress1CountryRegion;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1County")
   public String getBillingAddress1County() {
       return billingAddress1County;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1County")
   public void setBillingAddress1County(String billingAddress1County) {
       this.billingAddress1County = billingAddress1County;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1StateProvince")
   public String getBillingAddress1StateProvince() {
       return billingAddress1StateProvince;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1StateProvince")
   public void setBillingAddress1StateProvince(String billingAddress1StateProvince) {
       this.billingAddress1StateProvince = billingAddress1StateProvince;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1Street1")
   public String getBillingAddress1Street1() {
       return billingAddress1Street1;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1Street1")
   public void setBillingAddress1Street1(String billingAddress1Street1) {
       this.billingAddress1Street1 = billingAddress1Street1;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1Street2")
   public String getBillingAddress1Street2() {
       return billingAddress1Street2;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1Street2")
   public void setBillingAddress1Street2(String billingAddress1Street2) {
       this.billingAddress1Street2 = billingAddress1Street2;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1Street3")
   public String getBillingAddress1Street3() {
       return billingAddress1Street3;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1Street3")
   public void setBillingAddress1Street3(String billingAddress1Street3) {
       this.billingAddress1Street3 = billingAddress1Street3;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1ZipPostalCode")
   public String getBillingAddress1ZipPostalCode() {
       return billingAddress1ZipPostalCode;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1ZipPostalCode")
   public void setBillingAddress1ZipPostalCode(String billingAddress1ZipPostalCode) {
       this.billingAddress1ZipPostalCode = billingAddress1ZipPostalCode;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("DirectMessagingVHIE")
   public Boolean getDirectMessagingVHIE() {
       return directMessagingVHIE;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("DirectMessagingVHIE")
   public void setDirectMessagingVHIE(Boolean directMessagingVHIE) {
       this.directMessagingVHIE = directMessagingVHIE;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("EMRSystem")
   public String getEMRSystem() {
       return eMRSystem;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("EMRSystem")
   public void setEMRSystem(String eMRSystem) {
       this.eMRSystem = eMRSystem;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("EMRSystemOther")
   public String getEMRSystemOther() {
       return eMRSystemOther;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("EMRSystemOther")
   public void setEMRSystemOther(String eMRSystemOther) {
       this.eMRSystemOther = eMRSystemOther;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("Email")
   public String getEmail() {
       return email;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("Email")
   public void setEmail(String email) {
       this.email = email;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("Fax")
   public String getFax() {
       return fax;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("Fax")
   public void setFax(String fax) {
       this.fax = fax;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("HSRM")
   public Boolean getHSRM() {
       return hSRM;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("HSRM")
   public void setHSRM(Boolean hSRM) {
       this.hSRM = hSRM;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("IndividualIsPrimaryCareProviderAcceptingVA")
   public Boolean getIndividualIsPrimaryCareProviderAcceptingVA() {
       return individualIsPrimaryCareProviderAcceptingVA;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("IndividualIsPrimaryCareProviderAcceptingVA")
   public void setIndividualIsPrimaryCareProviderAcceptingVA(Boolean individualIsPrimaryCareProviderAcceptingVA) {
       this.individualIsPrimaryCareProviderAcceptingVA = individualIsPrimaryCareProviderAcceptingVA;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("IsExternal")
   public Boolean getIsExternal() {
       return isExternal;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("IsExternal")
   public void setIsExternal(Boolean isExternal) {
       this.isExternal = isExternal;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("LeieValidated")
   public Boolean getLeieValidated() {
       return leieValidated;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("LeieValidated")
   public void setLeieValidated(Boolean leieValidated) {
       this.leieValidated = leieValidated;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("LicenseValidated")
   public Boolean getLicenseValidated() {
       return licenseValidated;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("LicenseValidated")
   public void setLicenseValidated(Boolean licenseValidated) {
       this.licenseValidated = licenseValidated;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("MainPhone")
   public String getMainPhone() {
       return mainPhone;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("MainPhone")
   public void setMainPhone(String mainPhone) {
       this.mainPhone = mainPhone;
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
    * 
    *
    */
   @JsonProperty("Phone")
   public Boolean getPhone() {
       return phone;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("Phone")
   public void setPhone(Boolean phone) {
       this.phone = phone;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("PrimaryCarePhysician")
   public Boolean getPrimaryCarePhysician() {
       return primaryCarePhysician;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("PrimaryCarePhysician")
   public void setPrimaryCarePhysician(Boolean primaryCarePhysician) {
       this.primaryCarePhysician = primaryCarePhysician;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("PrimaryDirectMessagingAddress")
   public String getPrimaryDirectMessagingAddress() {
       return primaryDirectMessagingAddress;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("PrimaryDirectMessagingAddress")
   public void setPrimaryDirectMessagingAddress(String primaryDirectMessagingAddress) {
       this.primaryDirectMessagingAddress = primaryDirectMessagingAddress;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("ProviderFirstName")
   public String getProviderFirstName() {
       return providerFirstName;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("ProviderFirstName")
   public void setProviderFirstName(String providerFirstName) {
       this.providerFirstName = providerFirstName;
   }
   
   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderMiddleName")
   public String getProviderMiddleName() {
       return providerMiddleName;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderMiddleName")
   public void setProviderMiddleName(String providerMiddleName) {
       this.providerMiddleName = providerMiddleName;
   }
   
   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderIdentifierType")
   public String getProviderIdentifierType() {
       return providerIdentifierType;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderIdentifierType")
   public void setProviderIdentifierType(String providerIdentifierType) {
       this.providerIdentifierType = providerIdentifierType;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("ProviderLastName")
   public String getProviderLastName() {
       return providerLastName;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("ProviderLastName")
   public void setProviderLastName(String providerLastName) {
       this.providerLastName = providerLastName;
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
   @JsonProperty("RefDoc")
   public Boolean getRefDoc() {
       return refDoc;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("RefDoc")
   public void setRefDoc(Boolean refDoc) {
       this.refDoc = refDoc;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("SecondaryDirectMessagingAddress")
   public String getSecondaryDirectMessagingAddress() {
       return secondaryDirectMessagingAddress;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("SecondaryDirectMessagingAddress")
   public void setSecondaryDirectMessagingAddress(String secondaryDirectMessagingAddress) {
       this.secondaryDirectMessagingAddress = secondaryDirectMessagingAddress;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("SecuredEmail")
   public Boolean getSecuredEmail() {
       return securedEmail;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("SecuredEmail")
   public void setSecuredEmail(Boolean securedEmail) {
       this.securedEmail = securedEmail;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderStatus")
   public String getProviderStatus() {
       return providerStatus;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderStatus")
   public void setProviderStatus(String providerStatus) {
       this.providerStatus = providerStatus;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderStatusReason")
   public String getProviderStatusReason() {
       return providerStatusReason;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderStatusReason")
   public void setProviderStatusReason(String providerStatusReason) {
       this.providerStatusReason = providerStatusReason;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderGuid")
   public String getProviderGuid() {
       return providerGuid;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderGuid")
   public void setProviderGuid(String providerGuid) {
       this.providerGuid = providerGuid;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("AccounatbleCareOrganization")
   public Boolean getAccounatbleCareOrganization() {
       return accounatbleCareOrganization;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("AccounatbleCareOrganization")
   public void setAccounatbleCareOrganization(Boolean accounatbleCareOrganization) {
       this.accounatbleCareOrganization = accounatbleCareOrganization;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("AddressValidated")
   public Boolean getAddressValidated() {
       return addressValidated;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("AddressValidated")
   public void setAddressValidated(Boolean addressValidated) {
       this.addressValidated = addressValidated;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("AgreementCount")
   public Integer getAgreementCount() {
       return agreementCount;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("AgreementCount")
   public void setAgreementCount(Integer agreementCount) {
       this.agreementCount = agreementCount;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("AgreementCountLastUpdatedOn")
   public String getAgreementCountLastUpdatedOn() {
       return agreementCountLastUpdatedOn;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("AgreementCountLastUpdatedOn")
   public void setAgreementCountLastUpdatedOn(String agreementCountLastUpdatedOn) {
       this.agreementCountLastUpdatedOn = agreementCountLastUpdatedOn;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("AgreementCountState")
   public Integer getAgreementCountState() {
       return agreementCountState;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("AgreementCountState")
   public void setAgreementCountState(Integer agreementCountState) {
       this.agreementCountState = agreementCountState;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1Fax")
   public String getBillingAddress1Fax() {
       return billingAddress1Fax;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1Fax")
   public void setBillingAddress1Fax(String billingAddress1Fax) {
       this.billingAddress1Fax = billingAddress1Fax;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("BillingAddress1ID")
   public String getBillingAddress1ID() {
       return billingAddress1ID;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("BillingAddress1ID")
   public void setBillingAddress1ID(String billingAddress1ID) {
       this.billingAddress1ID = billingAddress1ID;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("BillingAddress1Latitude")
   public Double getBillingAddress1Latitude() {
       return billingAddress1Latitude;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("BillingAddress1Latitude")
   public void setBillingAddress1Latitude(Double billingAddress1Latitude) {
       this.billingAddress1Latitude = billingAddress1Latitude;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("BillingAddress1Longitude")
   public Double getBillingAddress1Longitude() {
       return billingAddress1Longitude;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("BillingAddress1Longitude")
   public void setBillingAddress1Longitude(Double billingAddress1Longitude) {
       this.billingAddress1Longitude = billingAddress1Longitude;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1Name")
   public String getBillingAddress1Name() {
       return billingAddress1Name;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1Name")
   public void setBillingAddress1Name(String billingAddress1Name) {
       this.billingAddress1Name = billingAddress1Name;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1PostOfficeBox")
   public String getBillingAddress1PostOfficeBox() {
       return billingAddress1PostOfficeBox;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1PostOfficeBox")
   public void setBillingAddress1PostOfficeBox(String billingAddress1PostOfficeBox) {
       this.billingAddress1PostOfficeBox = billingAddress1PostOfficeBox;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1Telephone2")
   public String getBillingAddress1Telephone2() {
       return billingAddress1Telephone2;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1Telephone2")
   public void setBillingAddress1Telephone2(String billingAddress1Telephone2) {
       this.billingAddress1Telephone2 = billingAddress1Telephone2;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1Telephone3")
   public String getBillingAddress1Telephone3() {
       return billingAddress1Telephone3;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddress1Telephone3")
   public void setBillingAddress1Telephone3(String billingAddress1Telephone3) {
       this.billingAddress1Telephone3 = billingAddress1Telephone3;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddressPhone")
   public String getBillingAddressPhone() {
       return billingAddressPhone;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("BillingAddressPhone")
   public void setBillingAddressPhone(String billingAddressPhone) {
       this.billingAddressPhone = billingAddressPhone;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ContactPrefEmail")
   public Boolean getContactPrefEmail() {
       return contactPrefEmail;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ContactPrefEmail")
   public void setContactPrefEmail(Boolean contactPrefEmail) {
       this.contactPrefEmail = contactPrefEmail;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ContactPrefFax")
   public Boolean getContactPrefFax() {
       return contactPrefFax;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ContactPrefFax")
   public void setContactPrefFax(Boolean contactPrefFax) {
       this.contactPrefFax = contactPrefFax;
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
   @JsonProperty("DoNotAllowBulkEmails")
   public Boolean getDoNotAllowBulkEmails() {
       return doNotAllowBulkEmails;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("DoNotAllowBulkEmails")
   public void setDoNotAllowBulkEmails(Boolean doNotAllowBulkEmails) {
       this.doNotAllowBulkEmails = doNotAllowBulkEmails;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("DoNotAllowBulkMails")
   public Boolean getDoNotAllowBulkMails() {
       return doNotAllowBulkMails;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("DoNotAllowBulkMails")
   public void setDoNotAllowBulkMails(Boolean doNotAllowBulkMails) {
       this.doNotAllowBulkMails = doNotAllowBulkMails;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("DoNotAllowEmails")
   public Boolean getDoNotAllowEmails() {
       return doNotAllowEmails;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("DoNotAllowEmails")
   public void setDoNotAllowEmails(Boolean doNotAllowEmails) {
       this.doNotAllowEmails = doNotAllowEmails;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("DoNotAllowFaxes")
   public Boolean getDoNotAllowFaxes() {
       return doNotAllowFaxes;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("DoNotAllowFaxes")
   public void setDoNotAllowFaxes(Boolean doNotAllowFaxes) {
       this.doNotAllowFaxes = doNotAllowFaxes;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("DoNotAllowMails")
   public Boolean getDoNotAllowMails() {
       return doNotAllowMails;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("DoNotAllowMails")
   public void setDoNotAllowMails(Boolean doNotAllowMails) {
       this.doNotAllowMails = doNotAllowMails;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("DoNotAllowPhoneCalls")
   public Boolean getDoNotAllowPhoneCalls() {
       return doNotAllowPhoneCalls;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("DoNotAllowPhoneCalls")
   public void setDoNotAllowPhoneCalls(Boolean doNotAllowPhoneCalls) {
       this.doNotAllowPhoneCalls = doNotAllowPhoneCalls;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("EmailAddress2")
   public String getEmailAddress2() {
       return emailAddress2;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("EmailAddress2")
   public void setEmailAddress2(String emailAddress2) {
       this.emailAddress2 = emailAddress2;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("EmailAddress3")
   public String getEmailAddress3() {
       return emailAddress3;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("EmailAddress3")
   public void setEmailAddress3(String emailAddress3) {
       this.emailAddress3 = emailAddress3;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderEthnicity")
   public String getProviderEthnicity() {
       return providerEthnicity;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderEthnicity")
   public void setProviderEthnicity(String providerEthnicity) {
       this.providerEthnicity = providerEthnicity;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ExternalHealthProviderType")
   public String getExternalHealthProviderType() {
       return externalHealthProviderType;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ExternalHealthProviderType")
   public void setExternalHealthProviderType(String externalHealthProviderType) {
       this.externalHealthProviderType = externalHealthProviderType;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("ExternalInstitutionDEANumber")
   public String getExternalInstitutionDEANumber() {
       return externalInstitutionDEANumber;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("ExternalInstitutionDEANumber")
   public void setExternalInstitutionDEANumber(String externalInstitutionDEANumber) {
       this.externalInstitutionDEANumber = externalInstitutionDEANumber;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ExternalLeieCheckDate")
   public String getExternalLeieCheckDate() {
       return externalLeieCheckDate;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ExternalLeieCheckDate")
   public void setExternalLeieCheckDate(String externalLeieCheckDate) {
       this.externalLeieCheckDate = externalLeieCheckDate;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("ExternalValidationSource")
   public String getExternalValidationSource() {
       return externalValidationSource;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("ExternalValidationSource")
   public void setExternalValidationSource(String externalValidationSource) {
       this.externalValidationSource = externalValidationSource;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("Facility")
   public String getFacility() {
       return facility;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("Facility")
   public void setFacility(String facility) {
       this.facility = facility;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("FeeSchedule")
   public String getFeeSchedule() {
       return feeSchedule;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("FeeSchedule")
   public void setFeeSchedule(String feeSchedule) {
       this.feeSchedule = feeSchedule;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("Gender")
   public String getGender() {
       return gender;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("Gender")
   public void setGender(String gender) {
       this.gender = gender;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("Geocoded")
   public Boolean getGeocoded() {
       return geocoded;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("Geocoded")
   public void setGeocoded(Boolean geocoded) {
       this.geocoded = geocoded;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("IndivProviderDateOfBirth")
   public String getIndivProviderDateOfBirth() {
       return indivProviderDateOfBirth;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("IndivProviderDateOfBirth")
   public void setIndivProviderDateOfBirth(String indivProviderDateOfBirth) {
       this.indivProviderDateOfBirth = indivProviderDateOfBirth;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("IndividualIsAcceptingNewPatients")
   public Boolean getIndividualIsAcceptingNewPatients() {
       return individualIsAcceptingNewPatients;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("IndividualIsAcceptingNewPatients")
   public void setIndividualIsAcceptingNewPatients(Boolean individualIsAcceptingNewPatients) {
       this.individualIsAcceptingNewPatients = individualIsAcceptingNewPatients;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("InternalAppointmentStatus")
   public String getInternalAppointmentStatus() {
       return internalAppointmentStatus;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("InternalAppointmentStatus")
   public void setInternalAppointmentStatus(String internalAppointmentStatus) {
       this.internalAppointmentStatus = internalAppointmentStatus;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("InternalCanCreateHealthCareOrders")
   public Boolean getInternalCanCreateHealtCareOrders() {
       return internalCanCreateHealthCareOrders;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("InternalCanCreateHealthCareOrders")
   public void setInternalCanCreateHealtCareOrders(Boolean internalCanCreateHealthCareOrders) {
       this.internalCanCreateHealthCareOrders = internalCanCreateHealthCareOrders;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("InternalLicensingJurisdiction")
   public String getInternalLicensingJurisdiction() {
       return internalLicensingJurisdiction;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("InternalLicensingJurisdiction")
   public void setInternalLicensingJurisdiction(String internalLicensingJurisdiction) {
       this.internalLicensingJurisdiction = internalLicensingJurisdiction;
   }

   /**
    *
    *
    *
    */
   @JsonProperty("InternalType")
   public String getInternalType() {
       return internalType;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("InternalType")
   public void setInternalType(String internalType) {
       this.internalType = internalType;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("LastUpdatedDate")
   public String getLastUpdatedDate() {
       return lastUpdatedDate;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("LastUpdatedDate")
   public void setLastUpdatedDate(String lastUpdatedDate) {
       this.lastUpdatedDate = lastUpdatedDate;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("LastValidated")
   public String getLastValidated() {
       return lastValidated;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("LastValidated")
   public void setLastValidated(String lastValidated) {
       this.lastValidated = lastValidated;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("Mail")
   public Boolean getMail() {
       return mail;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("Mail")
   public void setMail(Boolean mail) {
       this.mail = mail;
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
    * 
    *
    */
   @JsonProperty("NppesNPIValidated")
   public Boolean getNppesNPIValidated() {
       return nppesNPIValidated;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("NppesNPIValidated")
   public void setNppesNPIValidated(Boolean nppesNPIValidated) {
       this.nppesNPIValidated = nppesNPIValidated;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("OnLeie")
   public Boolean getOnLeie() {
       return onLeie;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("OnLeie")
   public void setOnLeie(Boolean onLeie) {
       this.onLeie = onLeie;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("OrganizationId")
   public String getOrganizationId() {
       return organizationId;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("OrganizationId")
   public void setOrganizationId(String organizationId) {
       this.organizationId = organizationId;
   }
   
   /**
    *
    *
    *
    */
   @JsonProperty("OrganizationStatus")
   public String getOrganizationStatus() {
       return organizationStatus;
   }

   /**
    *
    *
    *
    */
   @JsonProperty("OrganizationStatus")
   public void setOrganizationStatus(String organizationStatus) {
       this.organizationStatus = organizationStatus;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("OtherPhone")
   public String getOtherPhone() {
       return otherPhone;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("OtherPhone")
   public void setOtherPhone(String otherPhone) {
       this.otherPhone = otherPhone;
   }
   
   /**
    *
    *
    *
    */
   @JsonProperty("Ownership")
   public String getOwnership() {
       return ownership;
   }

   /**
    *
    *
    *
    */
   @JsonProperty("Ownership")
   public void setOwnership(String ownership) {
       this.ownership = ownership;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("OwnedCareSite")
   public String getOwnedCareSite() {
       return ownedCareSite;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("OwnedCareSite")
   public void setOwnedCareSite(String ownedCareSite) {
       this.ownedCareSite = ownedCareSite;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("OwningBusinessunit")
   public String getOwningBusinessunit() {
       return owningBusinessunit;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("OwningBusinessunit")
   public void setOwningBusinessunit(String owningBusinessunit) {
       this.owningBusinessunit = owningBusinessunit;
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
    * (Required)
    *
    */
   @JsonProperty("PAMigrationKey")
   public String getPAMigrationKey() {
       return pAMigrationKey;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("PAMigrationKey")
   public void setPAMigrationKey(String pAMigrationKey) {
       this.pAMigrationKey = pAMigrationKey;
   }
   
   /**
    *
    *
    *
    */
   @JsonProperty("PreferredDay")
   public String getPreferredDay() {
       return preferredDay;
   }

   /**
    *
    *
    *
    */
   @JsonProperty("PreferredDay")
   public void setPreferredDay(String preferredDay) {
       this.preferredDay = preferredDay;
   }
       
   /**
    *
    *
    *
    */
   @JsonProperty("PreferredMethodOfContact")
   public String getPreferredMethodOfContact() {
       return preferredMethodOfContact;
   }

   /**
    *
    *
    *
    */
   @JsonProperty("PreferredMethodOfContact")
   public void setPreferredMethodOfContact(String preferredMethodOfContact) {
       this.preferredMethodOfContact = preferredMethodOfContact;
   }
   
   /**
    *
    *
    *
    */
   @JsonProperty("PreferredTime")
   public String getPreferredTime() {
       return preferredTime;
   }

   /**
    *
    *
    *
    */
   @JsonProperty("PreferredTime")
   public void setPreferredTime(String preferredTime) {
       this.preferredTime = preferredTime;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("ParentProvider")
   public String getParentProvider() {
       return parentProvider;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("ParentProvider")
   public void setParentProvider(String parentProvider) {
       this.parentProvider = parentProvider;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("Process")
   public String getProcess() {
       return process;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("Process")
   public void setProcess(String process) {
       this.process = process;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderContactValidated")
   public Boolean getProviderContactValidated() {
       return providerContactValidated;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderContactValidated")
   public void setProviderContactValidated(Boolean providerContactValidated) {
       this.providerContactValidated = providerContactValidated;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("RecordSource")
   public String getRecordSource() {
       return recordSource;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("RecordSource")
   public void setRecordSource(String recordSource) {
       this.recordSource = recordSource;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("RelationshipType")
   public String getRelationshipType() {
       return relationshipType;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("RelationshipType")
   public void setRelationshipType(String relationshipType) {
       this.relationshipType = relationshipType;
   }
   
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("Religion")
   public String getReligion() {
       return religion;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("Religion")
   public void setReligion(String religion) {
       this.religion = religion;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("SAMValidated")
   public Boolean getSAMValidated() {
       return sAMValidated;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("SAMValidated")
   public void setSAMValidated(Boolean sAMValidated) {
       this.sAMValidated = sAMValidated;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("DoNotSendMarketingMaterials")
   public Boolean getDoNotSendMarketingMaterials() {
       return doNotSendMarketingMaterials;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("DoNotSendMarketingMaterials")
   public void setDoNotSendMarketingMaterials(Boolean doNotSendMarketingMaterials) {
       this.doNotSendMarketingMaterials = doNotSendMarketingMaterials;
   }

   /**
    *
    *
    *
    */
   @JsonProperty("ServiceProviderType")
   public String getServiceProviderType() {
       return serviceProviderType;
   }

   /**
    *
    *
    *
    */
   @JsonProperty("ServiceProviderType")
   public void setServiceProviderType(String serviceProviderType) {
       this.serviceProviderType = serviceProviderType;
   }
   
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("SpecialInstruction")
   public String getSpecialInstruction() {
       return specialInstruction;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("SpecialInstruction")
   public void setSpecialInstruction(String specialInstruction) {
       this.specialInstruction = specialInstruction;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("Telephone2")
   public String getTelephone2() {
       return telephone2;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("Telephone2")
   public void setTelephone2(String telephone2) {
       this.telephone2 = telephone2;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("Telephone3")
   public String getTelephone3() {
       return telephone3;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("Telephone3")
   public void setTelephone3(String telephone3) {
       this.telephone3 = telephone3;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ValidationReset")
   public Boolean getValidationReset() {
       return validationReset;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ValidationReset")
   public void setValidationReset(Boolean validationReset) {
       this.validationReset = validationReset;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ValidationResetDate")
   public String getValidationResetDate() {
       return validationResetDate;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ValidationResetDate")
   public void setValidationResetDate(String validationResetDate) {
       this.validationResetDate = validationResetDate;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("WebSite")
   public String getWebSite() {
       return webSite;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("WebSite")
   public void setWebSite(String webSite) {
       this.webSite = webSite;
   }
   
   @Override
   public String toString() {
   return new ToStringBuilder(this)
   		.append("providerNpi", providerNpi)
   		.append("billingAddress1Composite", billingAddress1Composite)
   		.append("billingAddress1City", billingAddress1City)
   		.append("billingAddress1CountryRegion", billingAddress1CountryRegion)
   		.append("billingAddress1County", billingAddress1County)
   		.append("billingAddress1StateProvince", billingAddress1StateProvince)
   		.append("billingAddress1Street1", billingAddress1Street1)
   		.append("billingAddress1Street2", billingAddress1Street2)
   		.append("billingAddress1Street3", billingAddress1Street3)
   		.append("billingAddress1ZipPostalCode", billingAddress1ZipPostalCode)
   		.append("directMessagingVHIE", directMessagingVHIE)
   		.append("eMRSystem", eMRSystem)
   		.append("eMRSystemOther", eMRSystemOther)
   		.append("email", email)
   		.append("fax", fax)
   		.append("hSRM", hSRM)
   		.append("individualIsPrimaryCareProviderAcceptingVA", individualIsPrimaryCareProviderAcceptingVA)
   		.append("isExternal", isExternal)
   		.append("leieValidated", leieValidated)
   		.append("licenseValidated", licenseValidated)
   		.append("mainPhone", mainPhone)
   		.append("modifiedOnDate", modifiedOnDate)
   		.append("owner", owner)
   		.append("phone", phone)
   		.append("primaryCarePhysician", primaryCarePhysician)
   		.append("primaryDirectMessagingAddress", primaryDirectMessagingAddress)
   		.append("providerFirstName", providerFirstName)
   		.append("providerMiddleName", providerMiddleName)
   		.append("providerIdentifierType", providerIdentifierType)
   		.append("providerLastName", providerLastName)
   		.append("providerName", providerName)
   		.append("providerType", providerType)
   		.append("refDoc", refDoc)
   		.append("secondaryDirectMessagingAddress", secondaryDirectMessagingAddress)
   		.append("securedEmail", securedEmail)
   		.append("providerStatus", providerStatus)
   		.append("providerStatusReason", providerStatusReason)
   		.append("providerGuid", providerGuid)
   		.append("accounatbleCareOrganization", accounatbleCareOrganization)
   		.append("addressValidated", addressValidated)
   		.append("agreementCount", agreementCount)
   		.append("agreementCountLastUpdatedOn", agreementCountLastUpdatedOn)
   		.append("agreementCountState", agreementCountState)
   		.append("billingAddress1Fax", billingAddress1Fax)
   		.append("billingAddress1ID", billingAddress1ID)
   		.append("billingAddress1Latitude", billingAddress1Latitude)
   		.append("billingAddress1Longitude", billingAddress1Longitude)
   		.append("billingAddress1Name", billingAddress1Name)
   		.append("billingAddress1PostOfficeBox", billingAddress1PostOfficeBox)
   		.append("billingAddress1Telephone2", billingAddress1Telephone2)
   		.append("billingAddress1Telephone3", billingAddress1Telephone3)
   		.append("billingAddressPhone", billingAddressPhone)
   		.append("contactPrefEmail", contactPrefEmail)
   		.append("contactPrefFax", contactPrefFax)
   		.append("createdBy", createdBy)
   		.append("createdOn", createdOn)
   		.append("doNotAllowBulkEmails", doNotAllowBulkEmails)
   		.append("doNotAllowBulkMails", doNotAllowBulkMails)
   		.append("doNotAllowEmails", doNotAllowEmails)
   		.append("doNotAllowFaxes", doNotAllowFaxes)
   		.append("doNotAllowMails", doNotAllowMails)
   		.append("doNotAllowPhoneCalls", doNotAllowPhoneCalls)
   		.append("emailAddress2", emailAddress2)
   		.append("emailAddress3", emailAddress3)
   		.append("providerEthnicity", providerEthnicity)
   		.append("externalHealthProviderType", externalHealthProviderType)
   		.append("externalInstitutionDEANumber", externalInstitutionDEANumber)
   		.append("externalLeieCheckDate", externalLeieCheckDate)
   		.append("externalValidationSource", externalValidationSource)
   		.append("facility", facility)
   		.append("feeSchedule", feeSchedule)
   		.append("gender", gender)
   		.append("geocoded", geocoded)
   		.append("indivProviderDateOfBirth", indivProviderDateOfBirth)
   		.append("individualIsAcceptingNewPatients", individualIsAcceptingNewPatients)
   		.append("internalAppointmentStatus", internalAppointmentStatus)
   		.append("internalCanCreateHealtCareOrders", internalCanCreateHealthCareOrders)
   		.append("internalLicensingJurisdiction", internalLicensingJurisdiction)
   		.append("internalType", internalType)
   		.append("lastUpdatedDate", lastUpdatedDate)
   		.append("lastValidated", lastValidated)
   		.append("mail", mail)
   		.append("modifiedBy", modifiedBy)
   		.append("nppesNPIValidated", nppesNPIValidated)
   		.append("onLeie", onLeie)
   		.append("organizationId", organizationId)
   		.append("organizationStatus", organizationStatus)
   		.append("otherPhone", otherPhone)
   		.append("ownership", ownership)
   		.append("ownedCareSite", ownedCareSite)
   		.append("owningBusinessunit", owningBusinessunit)
   		.append("owningTeam", owningTeam)
   		.append("owningUser", owningUser)
   		.append("pAMigrationKey", pAMigrationKey)
   		.append("preferredDay", preferredDay)
   		.append("preferredMethodOfContact", preferredMethodOfContact)
   		.append("preferredTime", preferredTime)
   		.append("parentProvider", parentProvider)
   		.append("process", process)
   		.append("providerContactValidated", providerContactValidated)
   		.append("recordSource", recordSource)
   		.append("relationshipType", relationshipType)
   		.append("religion", religion)
   		.append("sAMValidated", sAMValidated)
   		.append("doNotSendMarketingMaterials", doNotSendMarketingMaterials)
   		.append("serviceProviderType", serviceProviderType)
   		.append("specialInstruction", specialInstruction)
   		.append("telephone2", telephone2)
   		.append("telephone3", telephone3)
   		.append("validationReset", validationReset)
   		.append("validationResetDate", validationResetDate)
   		.append("webSite", webSite)
   		.toString();
   }

}