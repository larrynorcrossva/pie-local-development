package gov.va.pie.common.entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import gov.va.pie.common.utils.CommonConstants;

/**
 * The persistent class for the NonVAProvider database table.
 * 
 */
@Entity
@Table(name = CommonConstants.DB_ENV + "NonVAProvider_V")
public class NonVAProvider implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NonVAProvider_Id")
	private long nonVAProviderId;

	@Column(name = "AgreementCount")
	private String agreementCount;

	@Column(name = "AgreementCountLastUpdatedOn")
	private Timestamp agreementCountLastUpdatedOn;

	@Column(name = "AgreementCountState")
	private String agreementCountState;

	@Column(name = "BillingAddress1")
	private String billingAddress1;

	@Column(name = "BillingAddress1City")
	private String billingAddress1City;

	@Column(name = "BillingAddress1CountryRegion")
	private String billingAddress1CountryRegion;

	@Column(name = "BillingAddress1County")
	private String billingAddress1County;

	@Column(name = "BillingAddress1Fax")
	private String billingAddress1Fax;

	@Column(name = "BillingAddress1ID")
	private String billingAddress1ID;

	@Column(name = "BillingAddress1Latitude")
	private Double billingAddress1Latitude;

	@Column(name = "BillingAddress1Longitude")
	private Double billingAddress1Longitude;

	@Column(name = "BillingAddress1Name")
	private String billingAddress1Name;

	@Column(name = "BillingAddress1PostOfficeBox")
	private String billingAddress1PostOfficeBox;

	@Column(name = "BillingAddress1StateProvince")
	private String billingAddress1StateProvince;

	@Column(name = "BillingAddress1Street1")
	private String billingAddress1Street1;

	@Column(name = "BillingAddress1Street2")
	private String billingAddress1Street2;

	@Column(name = "BillingAddress1Street3")
	private String billingAddress1Street3;

	@Column(name = "BillingAddress1Telephone2")
	private String billingAddress1Telephone2;

	@Column(name = "BillingAddress1Telephone3")
	private String billingAddress1Telephone3;

	@Column(name = "BillingAddress1ZipPostalCode")
	private String billingAddress1ZipPostalCode;

	@Column(name = "BillingAddressPhone")
	private String billingAddressPhone;

	@Column(name = "Created_By")
	private String createdBy;

	@Column(name = "Created_Date")
	private Timestamp createdDate;

	@Column(name = "Email")
	private String email;

	@Column(name = "EmailAddress2")
	private String emailAddress2;

	@Column(name = "EmailAddress3")
	private String emailAddress3;

	@Column(name = "EMRSystem")
	private String emrSystem;
	
	@Column(name = "EMRSystemOther")
	private String emrSystemOther;
	
	@Column(name = "Ethnicity")
	private String ethnicity;

	@Column(name = "ExternalHealthProviderType")
	private String externalHealthProviderType;

	@Column(name = "ExternalInstitutionDEANumber")
	private String externalInstitutionDEANumber;

	@Column(name = "ExternalLeieCheckDate")
	private Timestamp externalLeieCheckDate;

	@Column(name = "ExternalValidationSource")
	private String externalValidationSource;

	@Column(name = "Facility")
	private String facility;

	@Column(name = "Fax")
	private String fax;

	@Column(name = "FeeSchedule")
	private String feeSchedule;

	@Column(name = "Gender")
	private String gender;

	@Column(name = "InactiveDate")
	private Date inactiveDate;

	@Column(name = "InactiveFlag")
	private boolean inactiveFlag;

	@Column(name = "IndivProviderDateOfBirth")
	private Timestamp indivProviderDateOfBirth;

	@Column(name = "InternalAppointmentStatus")
	private String internalAppointmentStatus;

	@Column(name = "InternalLicensingJurisdiction")
	private String internalLicensingJurisdiction;

	@Column(name = "InternalType")
	private String internalType;

	@Column(name = "IsAccountableCareOrganization")
	private String isAccountableCareOrganization;

	@Column(name = "IsAddressValidated")
	private String isAddressValidated;

	@Column(name = "IsContactPrefEmail")
	private String isContactPrefEmail;
	
	@Column(name = "IsContactPrefFax")
	private String isContactPrefFax;


	@Column(name = "IsDirectMessagingVHIE")
	private String isDirectMessagingVHIE;

	@Column(name = "IsDoNotAllowBulkEmails")
	private String isDoNotAllowBulkEmails;

	@Column(name = "IsDoNotAllowBulkMails")
	private String isDoNotAllowBulkMails;

	@Column(name = "IsDoNotAllowEmails")
	private String isDoNotAllowEmails;

	@Column(name = "IsDoNotAllowFaxes")
	private String isDoNotAllowFaxes;

	@Column(name = "IsDoNotAllowMails")
	private String isDoNotAllowMails;

	@Column(name = "IsDoNotAllowPhoneCalls")
	private String isDoNotAllowPhoneCalls;
	
	@Column(name = "IsDoNotSendMarketingMaterials")
	private String isDoNotSendMarketingMaterials;

	@Column(name = "IsExternal")
	private String isExternal;

	@Column(name = "IsGeocoded")
	private String isGeocoded;

	@Column(name = "IsHSRM")
	private String isHSRM;

	@Column(name = "IsIndividualAcceptingNewPatients")
	private String isIndividualAcceptingNewPatients;

	@Column(name = "IsIndividualPrimaryCareProviderAcceptingVA")
	private String isIndividualPrimaryCareProviderAcceptingVA;

	@Column(name = "IsInternalCanCreateHealthCareOrders")
	private String isInternalCanCreateHealthCareOrders;

	@Column(name = "IsLeieValidated")
	private String isLeieValidated;

	@Column(name = "IsLicenseValidated")
	private String isLicenseValidated;

	@Column(name = "IsMail")
	private String isMail;

	@Column(name = "IsNppesNPIValidated")
	private String isNppesNPIValidated;

	@Column(name = "IsOnLeie")
	private String isOnLeie;

	@Column(name = "IsPhone")
	private String isPhone;

	@Column(name = "IsPrimaryCarePhysician")
	private String isPrimaryCarePhysician;

	@Column(name = "IsProcessed")
	private boolean isProcessed;

	@Column(name = "IsProviderContactValidated")
	private String isProviderContactValidated;

	@Column(name = "IsRefDoc")
	private String isRefDoc;

	@Column(name = "IsSAMValidated")
	private String isSAMValidated;

	@Column(name = "IsSecuredEmail")
	private String isSecuredEmail;

	@Column(name = "IsSendMarketingMaterials")
	private String isSendMarketingMaterials;

	@Column(name = "IsValidationReset")
	private String isValidationReset;

	@Column(name = "LastValidated")
	private Timestamp lastValidated;

	@Column(name = "MainPhone")
	private String mainPhone;

	@Column(name = "Modified_By")
	private String modifiedBy;

	@Column(name = "Modified_Date")
	private Timestamp modifiedDate;

	@Column(name = "OrganizationId")
	private String organizationId;

	@Column(name = "OrganizationStatus")
	private String organizationStatus;

	@Column(name = "OtherPhone")
	private String otherPhone;

	@Column(name = "OwnedCareSite")
	private String ownedCareSite;

	@Column(name = "Owner")
	private String owner;

	@Column(name = "Ownership")
	private String ownership;

	@Column(name = "OwningBusinessunit")
	private String owningBusinessunit;

	@Column(name = "OwningTeam")
	private String owningTeam;

	@Column(name = "OwningUser")
	private String owningUser;

	private String PAMigrationKey;

	@Column(name = "ParentProvider")
	private String parentProvider;

	@Column(name = "Phone")
	private String phone;
	
	@Column(name="PPMSCreated_By")
	private String ppmsCreatedBy;

	@Column(name="PPMSCreatedOn_Date")
	private Timestamp ppmsCreatedOnDate;

	@Column(name = "PPMSLastUpdatedDate")
	private Date ppmsLastUpdatedDate;
	
	@Column(name = "PPMSLastValidatedDate")
	private Date ppmsLastValidatedDate;
	
	@Column(name="PPMSModified_By")
	private String ppmsModifiedBy;

	@Column(name = "PPMSModifiedOn_Date")
	private Date ppmsModifiedOnDate;
	
	
	@Column(name = "PreferredDay")
	private String preferredDay;

	@Column(name = "PreferredMethodOfContact")
	private String preferredMethodOfContact;

	@Column(name = "PreferredTime")
	private String preferredTime;

	@Column(name = "PrimaryDirectMessagingAddress")
	private String primaryDirectMessagingAddress;

	@Column(name = "ProviderFirstName")
	private String providerFirstName;

	@Column(name = "ProviderGuid")
	private String providerGuid;

	@Column(name = "ProviderLastName")
	private String providerLastName;

	@Column(name = "ProviderMiddleName")
	private String providerMiddleName;

	@Column(name = "ProviderName")
	private String providerName;

	@Column(name = "ProviderNpi")
	private long providerNpi;

	@Column(name = "ProviderStatus")
	private String providerStatus;

	@Column(name = "ProviderStatusReason")
	private String providerStatusReason;

	@Column(name = "ProviderType")
	private String providerType;

	@Column(name = "RecordSource")
	private String recordSource;

	@Column(name = "RelationshipType")
	private String relationshipType;

	@Column(name = "Religion")
	private String religion;

	@Column(name = "SecondaryDirectMessagingAddress")
	private String secondaryDirectMessagingAddress;

	@Column(name = "ServiceProviderType")
	private String serviceProviderType;

	@Column(name = "SpecialInstruction")
	private String specialInstruction;

	@Column(name = "Telephone2")
	private String telephone2;
	
	@Column(name = "Telephone3")
	private String telephone3;

	@Column(name = "ValidationResetDate")
	private Timestamp validationResetDate;

	@Column(name = "WebSite")
	private String webSite;
	
	@Column(name = "Process")
	private String process;
	
	@Column(name = "BillingAddress1Composite")
	private String billingAddress1Composite;
	

	// bi-directional many-to-one association to ProviderAgreement
	@OneToMany(mappedBy = "nonVaprovider")
	private List<ProviderAgreement> providerAgreements;

	// bi-directional many-to-one association to ProviderDEASchedulePrivilege
	@OneToMany(mappedBy = "nonVaprovider", fetch = FetchType.LAZY)
	private List<ProviderDEASchedulePrivilege> providerDeaschedulePrivileges;

	// bi-directional many-to-one association to ProviderMedicalEducation
	@OneToMany(mappedBy = "nonVaprovider", fetch = FetchType.LAZY)
	private List<ProviderMedicalEducation> providerMedicalEducations;

	// bi-directional many-to-one association to ProviderOtherIdentifier
	@OneToMany(mappedBy = "nonVaprovider", fetch = FetchType.LAZY)
	private List<ProviderOtherIdentifier> providerOtherIdentifiers;

	// bi-directional many-to-one association to VistaOutResponse
	@OneToMany(mappedBy = "nonVaprovider", fetch = FetchType.LAZY)
	private List<VistaOutResponse> vistaOutResponses;

	public NonVAProvider() {
	}

	public long getNonVAProviderId() {
		return this.nonVAProviderId;
	}

	public void setNonVAProvider_Id(long nonVAProviderId) {
		this.nonVAProviderId = nonVAProviderId;
	}

	public String getAgreementCount() {
		return this.agreementCount;
	}

	public void setAgreementCount(String agreementCount) {
		this.agreementCount = agreementCount;
	}

	public Timestamp getAgreementCountLastUpdatedOn() {
		return this.agreementCountLastUpdatedOn;
	}

	public void setAgreementCountLastUpdatedOn(Timestamp agreementCountLastUpdatedOn) {
		this.agreementCountLastUpdatedOn = agreementCountLastUpdatedOn;
	}

	public String getAgreementCountState() {
		return this.agreementCountState;
	}

	public void setAgreementCountState(String agreementCountState) {
		this.agreementCountState = agreementCountState;
	}

	public String getBillingAddress1() {
		return this.billingAddress1;
	}

	public void setBillingAddress1(String billingAddress1) {
		this.billingAddress1 = billingAddress1;
	}

	public String getBillingAddress1City() {
		return this.billingAddress1City;
	}

	public void setBillingAddress1City(String billingAddress1City) {
		this.billingAddress1City = billingAddress1City;
	}

	public String getBillingAddress1CountryRegion() {
		return this.billingAddress1CountryRegion;
	}

	public void setBillingAddress1CountryRegion(String billingAddress1CountryRegion) {
		this.billingAddress1CountryRegion = billingAddress1CountryRegion;
	}

	public String getBillingAddress1County() {
		return this.billingAddress1County;
	}

	public void setBillingAddress1County(String billingAddress1County) {
		this.billingAddress1County = billingAddress1County;
	}

	public String getBillingAddress1Fax() {
		return this.billingAddress1Fax;
	}

	public void setBillingAddress1Fax(String billingAddress1Fax) {
		this.billingAddress1Fax = billingAddress1Fax;
	}

	public String getBillingAddress1ID() {
		return this.billingAddress1ID;
	}

	public void setBillingAddress1ID(String billingAddress1ID) {
		this.billingAddress1ID = billingAddress1ID;
	}

	public Double getBillingAddress1Latitude() {
		return this.billingAddress1Latitude;
	}

	public void setBillingAddress1Latitude(Double billingAddress1Latitude) {
		this.billingAddress1Latitude = billingAddress1Latitude;
	}

	public Double getBillingAddress1Longitude() {
		return this.billingAddress1Longitude;
	}

	public void setBillingAddress1Longitude(Double billingAddress1Longitude) {
		this.billingAddress1Longitude = billingAddress1Longitude;
	}

	public String getBillingAddress1Name() {
		return this.billingAddress1Name;
	}

	public void setBillingAddress1Name(String billingAddress1Name) {
		this.billingAddress1Name = billingAddress1Name;
	}

	public String getBillingAddress1PostOfficeBox() {
		return this.billingAddress1PostOfficeBox;
	}

	public void setBillingAddress1PostOfficeBox(String billingAddress1PostOfficeBox) {
		this.billingAddress1PostOfficeBox = billingAddress1PostOfficeBox;
	}

	public String getBillingAddress1StateProvince() {
		return this.billingAddress1StateProvince;
	}

	public void setBillingAddress1StateProvince(String billingAddress1StateProvince) {
		this.billingAddress1StateProvince = billingAddress1StateProvince;
	}

	public String getBillingAddress1Street1() {
		return this.billingAddress1Street1;
	}

	public void setBillingAddress1Street1(String billingAddress1Street1) {
		this.billingAddress1Street1 = billingAddress1Street1;
	}

	public String getBillingAddress1Street2() {
		return this.billingAddress1Street2;
	}

	public void setBillingAddress1Street2(String billingAddress1Street2) {
		this.billingAddress1Street2 = billingAddress1Street2;
	}

	public String getBillingAddress1Street3() {
		return this.billingAddress1Street3;
	}

	public void setBillingAddress1Street3(String billingAddress1Street3) {
		this.billingAddress1Street3 = billingAddress1Street3;
	}

	public String getBillingAddress1Telephone2() {
		return this.billingAddress1Telephone2;
	}

	public void setBillingAddress1Telephone2(String billingAddress1Telephone2) {
		this.billingAddress1Telephone2 = billingAddress1Telephone2;
	}

	public String getBillingAddress1Telephone3() {
		return this.billingAddress1Telephone3;
	}

	public void setBillingAddress1Telephone3(String billingAddress1Telephone3) {
		this.billingAddress1Telephone3 = billingAddress1Telephone3;
	}

	public String getBillingAddress1ZipPostalCode() {
		return this.billingAddress1ZipPostalCode;
	}

	public void setBillingAddress1ZipPostalCode(String billingAddress1ZipPostalCode) {
		this.billingAddress1ZipPostalCode = billingAddress1ZipPostalCode;
	}

	public String getBillingAddressPhone() {
		return this.billingAddressPhone;
	}

	public void setBillingAddressPhone(String billingAddressPhone) {
		this.billingAddressPhone = billingAddressPhone;
	}

	public String getIsContactPrefEmail() {
		return this.isContactPrefEmail;
	}

	public void setIsContactPrefEmail(String isContactPrefEmail) {
		this.isContactPrefEmail = isContactPrefEmail;
	}

	public String getIsContactPrefFax() {
		return this.isContactPrefFax;
	}

	public void setIsContactPrefFax(String isContactPrefFax) {
		this.isContactPrefFax = isContactPrefFax;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailAddress2() {
		return this.emailAddress2;
	}

	public void setEmailAddress2(String emailAddress2) {
		this.emailAddress2 = emailAddress2;
	}

	public String getEmailAddress3() {
		return this.emailAddress3;
	}

	public void setEmailAddress3(String emailAddress3) {
		this.emailAddress3 = emailAddress3;
	}

	public String getEmrSystemOther() {
		return this.emrSystemOther;
	}

	public void setEmrSystemOther(String emrSystemOther) {
		this.emrSystemOther = emrSystemOther;
	}

	public String getEthnicity() {
		return this.ethnicity;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	public String getExternalHealthProviderType() {
		return this.externalHealthProviderType;
	}

	public void setExternalHealthProviderType(String externalHealthProviderType) {
		this.externalHealthProviderType = externalHealthProviderType;
	}

	public String getExternalInstitutionDEANumber() {
		return this.externalInstitutionDEANumber;
	}

	public void setExternalInstitutionDEANumber(String externalInstitutionDEANumber) {
		this.externalInstitutionDEANumber = externalInstitutionDEANumber;
	}

	public Timestamp getExternalLeieCheckDate() {
		return this.externalLeieCheckDate;
	}

	public void setExternalLeieCheckDate(Timestamp externalLeieCheckDate) {
		this.externalLeieCheckDate = externalLeieCheckDate;
	}

	public String getExternalValidationSource() {
		return this.externalValidationSource;
	}

	public void setExternalValidationSource(String externalValidationSource) {
		this.externalValidationSource = externalValidationSource;
	}

	public String getFacility() {
		return this.facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFeeSchedule() {
		return this.feeSchedule;
	}

	public void setFeeSchedule(String feeSchedule) {
		this.feeSchedule = feeSchedule;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getInactiveDate() {
		return inactiveDate;
	}

	public void setInactiveDate(Date inactiveDate) {
		this.inactiveDate = inactiveDate;
	}

	public boolean isInactiveFlag() {
		return inactiveFlag;
	}

	public void setInactiveFlag(boolean inactiveFlag) {
		this.inactiveFlag = inactiveFlag;
	}

	public Timestamp getIndivProviderDateOfBirth() {
		return this.indivProviderDateOfBirth;
	}

	public void setIndivProviderDateOfBirth(Timestamp indivProviderDateOfBirth) {
		this.indivProviderDateOfBirth = indivProviderDateOfBirth;
	}

	public String getInternalAppointmentStatus() {
		return this.internalAppointmentStatus;
	}

	public void setInternalAppointmentStatus(String internalAppointmentStatus) {
		this.internalAppointmentStatus = internalAppointmentStatus;
	}

	public String getInternalLicensingJurisdiction() {
		return this.internalLicensingJurisdiction;
	}

	public void setInternalLicensingJurisdiction(String internalLicensingJurisdiction) {
		this.internalLicensingJurisdiction = internalLicensingJurisdiction;
	}

	public String getInternalType() {
		return this.internalType;
	}

	public void setInternalType(String internalType) {
		this.internalType = internalType;
	}

	public String getIsAccountableCareOrganization() {
		return this.isAccountableCareOrganization;
	}

	public void setIsAccountableCareOrganization(String isAccountableCareOrganization) {
		this.isAccountableCareOrganization = isAccountableCareOrganization;
	}

	public String getIsAddressValidated() {
		return this.isAddressValidated;
	}

	public void setIsAddressValidated(String isAddressValidated) {
		this.isAddressValidated = isAddressValidated;
	}

	public String getIsDirectMessagingVHIE() {
		return this.isDirectMessagingVHIE;
	}

	public void setIsDirectMessagingVHIE(String isDirectMessagingVHIE) {
		this.isDirectMessagingVHIE = isDirectMessagingVHIE;
	}

	public String getIsDoNotAllowBulkEmails() {
		return this.isDoNotAllowBulkEmails;
	}

	public void setIsDoNotAllowBulkEmails(String isDoNotAllowBulkEmails) {
		this.isDoNotAllowBulkEmails = isDoNotAllowBulkEmails;
	}

	public String getIsDoNotAllowBulkMails() {
		return this.isDoNotAllowBulkMails;
	}

	public void setIsDoNotAllowBulkMails(String isDoNotAllowBulkMails) {
		this.isDoNotAllowBulkMails = isDoNotAllowBulkMails;
	}

	public String getIsDoNotAllowEmails() {
		return this.isDoNotAllowEmails;
	}

	public void setIsDoNotAllowEmails(String isDoNotAllowEmails) {
		this.isDoNotAllowEmails = isDoNotAllowEmails;
	}

	public String getIsDoNotAllowFaxes() {
		return this.isDoNotAllowFaxes;
	}

	public void setIsDoNotAllowFaxes(String isDoNotAllowFaxes) {
		this.isDoNotAllowFaxes = isDoNotAllowFaxes;
	}

	public String getIsDoNotAllowMails() {
		return this.isDoNotAllowMails;
	}

	public void setIsDoNotAllowMails(String isDoNotAllowMails) {
		this.isDoNotAllowMails = isDoNotAllowMails;
	}

	public String getIsDoNotAllowPhoneCalls() {
		return this.isDoNotAllowPhoneCalls;
	}

	public void setIsDoNotAllowPhoneCalls(String isDoNotAllowPhoneCalls) {
		this.isDoNotAllowPhoneCalls = isDoNotAllowPhoneCalls;
	}

	public String getIsExternal() {
		return this.isExternal;
	}

	public void setIsExternal(String isExternal) {
		this.isExternal = isExternal;
	}

	public String getIsGeocoded() {
		return this.isGeocoded;
	}

	public void setIsGeocoded(String isGeocoded) {
		this.isGeocoded = isGeocoded;
	}

	public String getIsHSRM() {
		return this.isHSRM;
	}

	public void setIsHSRM(String isHSRM) {
		this.isHSRM = isHSRM;
	}
	
	public String getProviderMiddleName() {
		return providerMiddleName;
	}

	public void setNonVAProviderId(int nonVAProviderId) {
		this.nonVAProviderId = nonVAProviderId;
	}

	public void setProviderMiddleName(String providerMiddleName) {
		this.providerMiddleName = providerMiddleName;
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

	public void setProcessed(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}

	public String getIsIndividualAcceptingNewPatients() {
		return this.isIndividualAcceptingNewPatients;
	}

	public void setIsIndividualAcceptingNewPatients(String isIndividualAcceptingNewPatients) {
		this.isIndividualAcceptingNewPatients = isIndividualAcceptingNewPatients;
	}

	public String getIsIndividualPrimaryCareProviderAcceptingVA() {
		return this.isIndividualPrimaryCareProviderAcceptingVA;
	}

	public void setIsIndividualPrimaryCareProviderAcceptingVA(String isIndividualPrimaryCareProviderAcceptingVA) {
		this.isIndividualPrimaryCareProviderAcceptingVA = isIndividualPrimaryCareProviderAcceptingVA;
	}

	public String getIsInternalCanCreateHealthCareOrders() {
		return this.isInternalCanCreateHealthCareOrders;
	}

	public void setIsInternalCanCreateHealthCareOrders(String isInternalCanCreateHealthCareOrders) {
		this.isInternalCanCreateHealthCareOrders = isInternalCanCreateHealthCareOrders;
	}

	public String getIsLeieValidated() {
		return this.isLeieValidated;
	}

	public void setIsLeieValidated(String isLeieValidated) {
		this.isLeieValidated = isLeieValidated;
	}

	public String getIsLicenseValidated() {
		return this.isLicenseValidated;
	}

	public void setIsLicenseValidated(String isLicenseValidated) {
		this.isLicenseValidated = isLicenseValidated;
	}

	public String getIsMail() {
		return this.isMail;
	}

	public void setIsMail(String isMail) {
		this.isMail = isMail;
	}

	public String getIsNppesNPIValidated() {
		return this.isNppesNPIValidated;
	}

	public void setIsNppesNPIValidated(String isNppesNPIValidated) {
		this.isNppesNPIValidated = isNppesNPIValidated;
	}

	public String getIsOnLeie() {
		return this.isOnLeie;
	}

	public void setIsOnLeie(String isOnLeie) {
		this.isOnLeie = isOnLeie;
	}

	public String getIsPhone() {
		return this.isPhone;
	}

	public void setIsPhone(String isPhone) {
		this.isPhone = isPhone;
	}

	public String getIsPrimaryCarePhysician() {
		return this.isPrimaryCarePhysician;
	}

	public void setIsPrimaryCarePhysician(String isPrimaryCarePhysician) {
		this.isPrimaryCarePhysician = isPrimaryCarePhysician;
	}

	public boolean getIsProcessed() {
		return this.isProcessed;
	}

	public void setIsProcessed(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}

	public String getIsProviderContactValidated() {
		return this.isProviderContactValidated;
	}

	public void setIsProviderContactValidated(String isProviderContactValidated) {
		this.isProviderContactValidated = isProviderContactValidated;
	}

	public String getIsRefDoc() {
		return this.isRefDoc;
	}

	public void setIsRefDoc(String isRefDoc) {
		this.isRefDoc = isRefDoc;
	}

	public String getIsSAMValidated() {
		return this.isSAMValidated;
	}

	public void setIsSAMValidated(String isSAMValidated) {
		this.isSAMValidated = isSAMValidated;
	}

	public String getIsSecuredEmail() {
		return this.isSecuredEmail;
	}

	public void setIsSecuredEmail(String isSecuredEmail) {
		this.isSecuredEmail = isSecuredEmail;
	}

	public String getIsSendMarketingMaterials() {
		return this.isSendMarketingMaterials;
	}

	public void setIsSendMarketingMaterials(String isSendMarketingMaterials) {
		this.isSendMarketingMaterials = isSendMarketingMaterials;
	}

	public String getIsValidationReset() {
		return this.isValidationReset;
	}

	public void setIsValidationReset(String isValidationReset) {
		this.isValidationReset = isValidationReset;
	}

	public Timestamp getLastValidated() {
		return this.lastValidated;
	}

	public void setLastValidated(Timestamp lastValidated) {
		this.lastValidated = lastValidated;
	}

	public String getMainPhone() {
		return this.mainPhone;
	}

	public void setMainPhone(String mainPhone) {
		this.mainPhone = mainPhone;
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

	public String getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationStatus() {
		return this.organizationStatus;
	}

	public void setOrganizationStatus(String organizationStatus) {
		this.organizationStatus = organizationStatus;
	}

	public String getOtherPhone() {
		return this.otherPhone;
	}

	public void setOtherPhone(String otherPhone) {
		this.otherPhone = otherPhone;
	}

	public String getOwnedCareSite() {
		return this.ownedCareSite;
	}

	public void setOwnedCareSite(String ownedCareSite) {
		this.ownedCareSite = ownedCareSite;
	}

	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getOwnership() {
		return this.ownership;
	}

	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}

	public String getOwningBusinessunit() {
		return this.owningBusinessunit;
	}

	public void setOwningBusinessunit(String owningBusinessunit) {
		this.owningBusinessunit = owningBusinessunit;
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

	public String getPAMigrationKey() {
		return this.PAMigrationKey;
	}

	public void setPAMigrationKey(String PAMigrationKey) {
		this.PAMigrationKey = PAMigrationKey;
	}

	public String getParentProvider() {
		return this.parentProvider;
	}

	public void setParentProvider(String parentProvider) {
		this.parentProvider = parentProvider;
	}

	public Date getPpmsModifiedOnDate() {
		return ppmsModifiedOnDate;
	}

	public void setPpmsModifiedOnDate(Date ppmsModifiedOnDate) {
		this.ppmsModifiedOnDate = ppmsModifiedOnDate;
	}

	public String getPreferredDay() {
		return this.preferredDay;
	}

	public void setPreferredDay(String preferredDay) {
		this.preferredDay = preferredDay;
	}

	public String getPreferredMethodOfContact() {
		return this.preferredMethodOfContact;
	}

	public void setPreferredMethodOfContact(String preferredMethodOfContact) {
		this.preferredMethodOfContact = preferredMethodOfContact;
	}

	public String getPreferredTime() {
		return this.preferredTime;
	}

	public void setPreferredTime(String preferredTime) {
		this.preferredTime = preferredTime;
	}

	public String getPrimaryDirectMessagingAddress() {
		return this.primaryDirectMessagingAddress;
	}

	public void setPrimaryDirectMessagingAddress(String primaryDirectMessagingAddress) {
		this.primaryDirectMessagingAddress = primaryDirectMessagingAddress;
	}

	public String getProviderFirstName() {
		return this.providerFirstName;
	}

	public void setProviderFirstName(String providerFirstName) {
		this.providerFirstName = providerFirstName;
	}

	public String getProviderGuid() {
		return this.providerGuid;
	}

	public void setProviderGuid(String providerGuid) {
		this.providerGuid = providerGuid;
	}

	public String getProviderLastName() {
		return this.providerLastName;
	}

	public void setProviderLastName(String providerLastName) {
		this.providerLastName = providerLastName;
	}

	/*
	 * public String getProviderMiddleName() { return this.providerMiddleName; }
	 * 
	 * public void setProviderMiddleName(String providerMiddleName) {
	 * this.providerMiddleName = providerMiddleName; }
	 */

	public String getProviderName() {
		return this.providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public long getProviderNpi() {
		return this.providerNpi;
	}

	public void setProviderNpi(long providerNpi) {
		this.providerNpi = providerNpi;
	}

	public String getProviderType() {
		return this.providerType;
	}

	public void setProviderType(String providerType) {
		this.providerType = providerType;
	}

	public String getRecordSource() {
		return this.recordSource;
	}

	public void setRecordSource(String recordSource) {
		this.recordSource = recordSource;
	}

	public String getRelationshipType() {
		return this.relationshipType;
	}

	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}

	public String getReligion() {
		return this.religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getSecondaryDirectMessagingAddress() {
		return this.secondaryDirectMessagingAddress;
	}

	public void setSecondaryDirectMessagingAddress(String secondaryDirectMessagingAddress) {
		this.secondaryDirectMessagingAddress = secondaryDirectMessagingAddress;
	}

	public String getServiceProviderType() {
		return this.serviceProviderType;
	}

	public void setServiceProviderType(String serviceProviderType) {
		this.serviceProviderType = serviceProviderType;
	}

	public String getSpecialInstruction() {
		return this.specialInstruction;
	}

	public void setSpecialInstruction(String specialInstruction) {
		this.specialInstruction = specialInstruction;
	}

	public String getTelephone3() {
		return this.telephone3;
	}

	public void setTelephone3(String telephone3) {
		this.telephone3 = telephone3;
	}

	public Timestamp getValidationResetDate() {
		return this.validationResetDate;
	}

	public void setValidationResetDate(Timestamp validationResetDate) {
		this.validationResetDate = validationResetDate;
	}

	public String getWebSite() {
		return this.webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	
	public String getEmrSystem() {
		return emrSystem;
	}

	public void setEmrSystem(String emrSystem) {
		this.emrSystem = emrSystem;
	}

	public String getIsDoNotSendMarketingMaterials() {
		return isDoNotSendMarketingMaterials;
	}

	public void setIsDoNotSendMarketingMaterials(String isDoNotSendMarketingMaterials) {
		this.isDoNotSendMarketingMaterials = isDoNotSendMarketingMaterials;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public Date getPpmsLastUpdatedDate() {
		return ppmsLastUpdatedDate;
	}

	public void setPpmsLastUpdatedDate(Date ppmsLastUpdatedDate) {
		this.ppmsLastUpdatedDate = ppmsLastUpdatedDate;
	}

	public Date getPpmsLastValidatedDate() {
		return ppmsLastValidatedDate;
	}

	public void setPpmsLastValidatedDate(Date ppmsLastValidatedDate) {
		this.ppmsLastValidatedDate = ppmsLastValidatedDate;
	}

	public String getPpmsModifiedBy() {
		return ppmsModifiedBy;
	}

	public void setPpmsModifiedBy(String ppmsModifiedBy) {
		this.ppmsModifiedBy = ppmsModifiedBy;
	}

	public String getTelephone2() {
		return telephone2;
	}

	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}

	public List<ProviderAgreement> getProviderAgreements() {
		return this.providerAgreements;
	}

	public void setProviderAgreements(List<ProviderAgreement> providerAgreements) {
		this.providerAgreements = providerAgreements;
	}

	public ProviderAgreement addProviderAgreement(ProviderAgreement providerAgreement) {
		getProviderAgreements().add(providerAgreement);
		providerAgreement.setNonVaprovider(this);

		return providerAgreement;
	}

	public ProviderAgreement removeProviderAgreement(ProviderAgreement providerAgreement) {
		getProviderAgreements().remove(providerAgreement);
		providerAgreement.setNonVaprovider(null);

		return providerAgreement;
	}

	public List<ProviderDEASchedulePrivilege> getProviderDeaschedulePrivileges() {
		return this.providerDeaschedulePrivileges;
	}

	public void setProviderDeaschedulePrivileges(List<ProviderDEASchedulePrivilege> providerDeaschedulePrivileges) {
		this.providerDeaschedulePrivileges = providerDeaschedulePrivileges;
	}

	public ProviderDEASchedulePrivilege addProviderDeaschedulePrivilege(
			ProviderDEASchedulePrivilege providerDeaschedulePrivilege) {
		getProviderDeaschedulePrivileges().add(providerDeaschedulePrivilege);
		providerDeaschedulePrivilege.setNonVaprovider(this);

		return providerDeaschedulePrivilege;
	}

	public ProviderDEASchedulePrivilege removeProviderDeaschedulePrivilege(
			ProviderDEASchedulePrivilege providerDeaschedulePrivilege) {
		getProviderDeaschedulePrivileges().remove(providerDeaschedulePrivilege);
		providerDeaschedulePrivilege.setNonVaprovider(null);

		return providerDeaschedulePrivilege;
	}

	public List<ProviderMedicalEducation> getProviderMedicalEducations() {
		return this.providerMedicalEducations;
	}

	public void setProviderMedicalEducations(List<ProviderMedicalEducation> providerMedicalEducations) {
		this.providerMedicalEducations = providerMedicalEducations;
	}

	public ProviderMedicalEducation addProviderMedicalEducation(ProviderMedicalEducation providerMedicalEducation) {
		getProviderMedicalEducations().add(providerMedicalEducation);
		providerMedicalEducation.setNonVaprovider(this);

		return providerMedicalEducation;
	}

	public ProviderMedicalEducation removeProviderMedicalEducation(ProviderMedicalEducation providerMedicalEducation) {
		getProviderMedicalEducations().remove(providerMedicalEducation);
		providerMedicalEducation.setNonVaprovider(null);

		return providerMedicalEducation;
	}

	public List<ProviderOtherIdentifier> getProviderOtherIdentifiers() {
		return this.providerOtherIdentifiers;
	}

	public void setProviderOtherIdentifiers(List<ProviderOtherIdentifier> providerOtherIdentifiers) {
		this.providerOtherIdentifiers = providerOtherIdentifiers;
	}

	public ProviderOtherIdentifier addProviderOtherIdentifier(ProviderOtherIdentifier providerOtherIdentifier) {
		getProviderOtherIdentifiers().add(providerOtherIdentifier);
		providerOtherIdentifier.setNonVaprovider(this);

		return providerOtherIdentifier;
	}

	public ProviderOtherIdentifier removeProviderOtherIdentifier(ProviderOtherIdentifier providerOtherIdentifier) {
		getProviderOtherIdentifiers().remove(providerOtherIdentifier);
		providerOtherIdentifier.setNonVaprovider(null);

		return providerOtherIdentifier;
	}

	public List<VistaOutResponse> getVistaOutResponses() {
		return this.vistaOutResponses;
	}

	public void setVistaOutResponses(List<VistaOutResponse> vistaOutResponses) {
		this.vistaOutResponses = vistaOutResponses;
	}

	public VistaOutResponse addVistaOutboundMsg(VistaOutResponse vistaOutResponse) {
		getVistaOutResponses().add(vistaOutResponse);
		vistaOutResponse.setNonVaprovider(this);
		return vistaOutResponse;
	}

	public VistaOutResponse removeVistaOutResponse(VistaOutResponse vistaOutResponse) {
		getVistaOutResponses().remove(vistaOutResponse);
		vistaOutResponse.setNonVaprovider(null);
		return vistaOutResponse;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getBillingAddress1Composite() {
		return billingAddress1Composite;
	}

	public void setBillingAddress1Composite(String billingAddress1Composite) {
		this.billingAddress1Composite = billingAddress1Composite;
	}
	
	
}