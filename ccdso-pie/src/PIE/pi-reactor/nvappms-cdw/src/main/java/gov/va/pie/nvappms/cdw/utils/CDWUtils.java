package gov.va.pie.nvappms.cdw.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.persistence.EntityManager;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import gov.va.pie.common.entities.PPMSNonVAInboundResponse;
import gov.va.pie.common.utils.CommonConstants;
import gov.va.pie.common.utils.CommonUtils;
import gov.va.pie.nvappms.cdw.model.CDWAgreement;
import gov.va.pie.nvappms.cdw.model.CDWCareSite;
import gov.va.pie.nvappms.cdw.model.CDWProvider;
import gov.va.pie.nvappms.cdw.model.CDWProviderService;


public class CDWUtils {
	
	public static final String PROVIDERS_DIR = "providers";
	
	public static final String PROVIDER_SERVICE_DIR = "providerservices";
	
	public static final String AGREEMENTS_DIR = "agreements";
	
	public static final String CARE_SITES_DIR = "caresites";
	
	public static final String INITIALIZATION_FAILURE = "Initialization Failure: Initialization of PPMSNonVAInboundResponse failed. Can't Call PPMS to get Data";
	
	public static final String SPACE_COMMA_SPACE_SINGLEQUOTE = " , '";
	
	public static final String SPACE_COMMA_SPACE = " , ";
	
	public static final String SINGLEQUOTE  = "'";
	
	public static final String CURRENT_DATE = " getDate()";
	
	public static final String SINGLEQUOTE_COMMA_SPACE = "', ";
		
	public static final String STAGING_NON_VA_PROVIDER_TABLE_NAME = "NonVAProvider_Stg_V";
	
	public static final String STAGING_CARE_SITE_TABLE_NAME = "CareSite_Stg_V";
	
	public static final String STAGING_PROVIDER_SERVICE_CARE_SITE_TABLE_NAME = "ProviderServiceCareSite_Stg_V";
	
	public static final String STAGING_PROVIDER_AGREEMENT_TABLE_NAME = "ProviderAgreement_Stg_V";
	
	public static final String VALUES = "VALUES (";
	
	public static final String BULK_LOAD_MECHANISM = " OPTION (RECOMPILE)"; 
	
	static final Logger LOG = LogManager.getLogger(CDWUtils.class);
	
	public static boolean isPPMSNonVAInboundResponseFailureRecord(PPMSNonVAInboundResponse cdwPPMSNonVAInboundResponse) {
		if (cdwPPMSNonVAInboundResponse == null)
			return true;
		else
			return cdwPPMSNonVAInboundResponse.getIsProviderFail() || cdwPPMSNonVAInboundResponse.getIsProviderServiceFail()
					|| cdwPPMSNonVAInboundResponse.getIsProviderAgreementFail() || cdwPPMSNonVAInboundResponse.getIsCareSiteFail();
	}
	
	public static HashMap<Long, Long> generateNonVAProviderNPIToIdMap(EntityManager entityManager) {
		HashMap<Long, Long> nonVAProviderNPIToIdMap = new HashMap<Long, Long>();
			
			// get NonVAProvider_Id for ProviderNpi
			@SuppressWarnings("unchecked")
			List<Object[]> nonVAProvdersList = entityManager
					.createNativeQuery("Select cast(NonVAProvider_Id as varchar(10)) NonVAProvider_Id, cast(ProviderNpi as varchar(10)) ProviderNpi from "
							+ "App." + CommonConstants.DB_ENV + "NonVAProvider_V").getResultList();
			for (Object[] nonVAProvder : nonVAProvdersList) {
				nonVAProviderNPIToIdMap.put(Long.valueOf(nonVAProvder[1].toString()), Long.valueOf(nonVAProvder[0].toString()));
			}
		return nonVAProviderNPIToIdMap;	
	}
	
	public static final String AGREEMENT_COLUMNS = "ProviderNpi, ProviderName, ProviderType, AgreementGuid, AgreementId, AgreementType, AssignedFacility, EffectiveDate, "
			+ "ExpiratiOn_Date, MedicalDirectorSignatureDate, QualificationExpirationDate, QualificationReviewDate, SpecialtyCoverage, "
			+ "ProviderAgreementStatus, ProviderAgreementStatusReason, CalcAgreementExpirationDate, CalcExpirationTimeFrameYears, "
			+ "DocumentLocation, ImportStatusField, Owner, OwningBusinessUnit, OwningTeam, OwningUser, PendingDateSet, CancelDateSet, "
			+ "RequiredAttachment, IsProviderCapacityLackOfAvailability, IsPreviousExperienceWithChoiceViaTW, IsDeclinedPreviousExpWithProvAgreement, "
			+ "IsDeclinedRefusalToCommitAtLowerLevel, IsDeclinedRefusalToCompleteOtherReqTraining, IsDeclinedRefusalToCompleteReqOpiodTraining, "
			+ "IsDeclinedRefusedCompleteQualificationDocument, IsDeclinedPaymentRateOrMethodology, IsDeclinedPreviousExperienceWithChoice, "
			+ "IsDeclinedTimelyReimbursements, IsStatusSwitch, StatusSwitchDate, PPMSCreated_By, PPMSCreatedOn_Date, PPMSModified_By, "
			+ "PPMSModifiedOn_Date) VALUES ";
	
	public static String createInsertStatementForAgreements(CDWAgreement providerAgreement) {
		
		if (providerAgreement == null) {
			return null;
		}
		
		StringBuffer insertString = new StringBuffer("(");
		insertString.append(providerAgreement.getProviderNpi());
		insertString.append(assembleStringForSql(providerAgreement.getProviderName()));
		insertString.append(assembleStringForSql(providerAgreement.getProviderType()));
		insertString.append(assembleStringForSql(providerAgreement.getAgreementGuid()));
		insertString.append(assembleStringForSql(providerAgreement.getAgreementId()));
		insertString.append(assembleStringForSql(providerAgreement.getAgreementType()));
		insertString.append(assembleStringForSql(providerAgreement.getAssignedFacility()));
		insertString.append(assembleStringForSql(providerAgreement.getEffectiveDate()));
		insertString.append(assembleStringForSql(providerAgreement.getExpirationDate()));
		insertString.append(assembleStringForSql(providerAgreement.getMedicalDirectorSignatureDate()));
		insertString.append(assembleStringForSql(providerAgreement.getQualificationExpirationDate()));
		insertString.append(assembleStringForSql(providerAgreement.getQualificationReviewDate()));
		insertString.append(assembleStringForSql(providerAgreement.getSpecialtyCoverage()));
		insertString.append(assembleStringForSql(providerAgreement.getAgreementStatus()));
		insertString.append(assembleStringForSql(providerAgreement.getAgreementStatusReason()));
		insertString.append(assembleStringForSql(providerAgreement.getCalcAgreementExpirationDate()));
		insertString.append(assembleIntForSql(providerAgreement.getCalcExpirationTimeFrameYears()));
		insertString.append(assembleStringForSql(providerAgreement.getDocumentLocation()));
		insertString.append(assembleStringForSql(providerAgreement.getImportStatusField()));
		insertString.append(assembleStringForSql(providerAgreement.getOwner()));
		insertString.append(assembleStringForSql(providerAgreement.getOwningBusinessUnit()));
		insertString.append(assembleStringForSql(providerAgreement.getOwningTeam()));
		insertString.append(assembleStringForSql(providerAgreement.getOwningUser()));
		insertString.append(assembleStringForSql(providerAgreement.getPendingDateSet()));
		insertString.append(assembleStringForSql(providerAgreement.getCancelDateSet()));
		insertString.append(assembleStringForSql(providerAgreement.getRequiredAttachment()));
		insertString.append(assembleBooleanForSql(providerAgreement.getDeclinedProviderCapacityLackOfAvailability()));
		insertString.append(assembleBooleanForSql(providerAgreement.getPreviousExperienceWithChoiceViaTW()));
		insertString.append(assembleBooleanForSql(providerAgreement.getDeclinedPreviousExpWithProvAgreement()));
		insertString.append(assembleBooleanForSql(providerAgreement.getDeclinedRefusalToCommitAtLowerLevel()));
		insertString.append(assembleBooleanForSql(providerAgreement.getDeclinedRefusalToCompleteOtherReqTraining()));
		insertString.append(assembleBooleanForSql(providerAgreement.getDeclinedRefusalToCompleteReqOpiodTraining()));
		insertString.append(assembleBooleanForSql(providerAgreement.getDeclinedRefusedCompleteQualificationDocument()));
		insertString.append(assembleBooleanForSql(providerAgreement.getDeclinedPaymentRateorMethodology()));
		insertString.append(assembleBooleanForSql(providerAgreement.getDeclinedPreviousExperienceWithChoice()));
		insertString.append(assembleBooleanForSql(providerAgreement.getDeclinedTimelyReimbursements()));
		insertString.append(assembleBooleanForSql(providerAgreement.getStatusSwitch()));
		insertString.append(assembleStringForSql(providerAgreement.getStatusSwitchDate()));
		insertString.append(assembleStringForSql(providerAgreement.getCreatedBy()));
		insertString.append(assembleStringForSql(providerAgreement.getCreatedOn()));
		insertString.append(assembleStringForSql(providerAgreement.getModifiedBy()));
		insertString.append(assembleStringForSql(providerAgreement.getModifiedOnDate()));
		
		insertString.append("),");
		return insertString.toString();
	}
	
	public static final String CARE_SITE_COLUMNS = "CareSiteGuid, CareSiteType, CareSiteName, AddressComposite, AddressStreet1, AddressStreet2, City, State, ZipCode, Country, "
			+ "Latitude, Longitude, MainSitePhone, DEANumber, CareSiteEmail, CareSiteFax, VersionNumber, CareSiteStatus, CareSiteStatusReason, "
			+ "OtherName, Owner, OwningUser, OwningTeam, OwningBusinessUnit, VISN, ParentStationNumber, SiteContact, IsExternal, IsHandicapAccessible, "
			+ "IsUseGeocodingService, IsUseAddressValidationService, ProviderGroup, StationName, Facility, FacilityType, PPMSInactive_Date, "
			+ "CenterOfExcellence, DeliveryPointValidation, BingConfidenceLevel, BingSuggestedPostalAddress, AddressValidationConfidenceScore,"
			+ "IsAddressValidated, IsVACareSite, IsGeocoded, ActiveDate, TimeZoneRuleVersionNumber, UseBingGeocodingService, UTCConversionTimeZoneCode, "
			+ "PPMSCreated_By, PPMSCreatedOn_Date, PPMSModified_By, PPMSModifiedOn_Date"
			+ ") VALUES ";
	
	public static String createInsertStatementForCareSite(CDWCareSite careSite) {
		
		if (careSite == null || StringUtils.isEmpty(careSite.getCareSiteGuid())) {
			return null;
		}
	
		StringBuffer insertString = new StringBuffer("(");
		insertString.append(SINGLEQUOTE).append(escapeForSql(careSite.getCareSiteGuid())).append(SINGLEQUOTE);
		insertString.append(assembleStringForSql(careSite.getCareSiteType()));
		insertString.append(assembleStringForSql(careSite.getName()));
		insertString.append(assembleStringForSql(careSite.getAddressComposite()));
		insertString.append(assembleStringForSql(careSite.getStreet()));
		insertString.append(assembleStringForSql(careSite.getStreet2()));
		insertString.append(assembleStringForSql(careSite.getCity()));
		insertString.append(assembleStringForSql(careSite.getState()));
		insertString.append(assembleStringForSql(careSite.getZipCode()));
		insertString.append(assembleStringForSql(careSite.getCountry()));
		insertString.append(assembleDoubleForSql(careSite.getLatitude()));
		insertString.append(assembleDoubleForSql(careSite.getLongitude()));
		insertString.append(assembleStringForSql(careSite.getMainSitePhone()));
		insertString.append(assembleStringForSql(careSite.getDEANumber()));
		insertString.append(assembleStringForSql(careSite.getCareSiteEmail()));
		insertString.append(assembleStringForSql(careSite.getCareSiteFax()));
		insertString.append(assembleIntForSql(careSite.getVersionNumber()));
		insertString.append(assembleStringForSql(careSite.getCareSiteStatus()));
		insertString.append(assembleStringForSql(careSite.getCareSiteStatusReason()));
		insertString.append(assembleStringForSql(careSite.getOtherName()));
		insertString.append(assembleStringForSql(careSite.getOwner()));
		insertString.append(assembleStringForSql(careSite.getOwningUser()));
		insertString.append(assembleStringForSql(careSite.getOwningTeam()));
		insertString.append(assembleStringForSql(careSite.getOwningBusinessUnit()));
		insertString.append(assembleStringForSql(careSite.getVISN()));
		insertString.append(assembleStringForSql(careSite.getParentStationNumber()));
		insertString.append(assembleStringForSql(careSite.getSiteContact()));
		insertString.append(assembleBooleanForSql(careSite.getIsExternal()));
		insertString.append(assembleBooleanForSql(careSite.getIsHandicapAccessible()));
		insertString.append(assembleBooleanForSql(careSite.getUseBingGeocodingService()));
		insertString.append(assembleBooleanForSql(careSite.getUseAddressValidationService()));
		insertString.append(assembleStringForSql(careSite.getProviderGroup()));
		insertString.append(assembleStringForSql(careSite.getStationName()));
		insertString.append(assembleStringForSql(careSite.getFacility()));
		insertString.append(assembleStringForSql(careSite.getFacilityType()));
		insertString.append(assembleStringForSql(careSite.getInactiveDate()));
		insertString.append(assembleStringForSql(careSite.getCenterOfExcellence()));
		insertString.append(assembleStringForSql(careSite.getDeliveryPointValidation()));
		insertString.append(assembleStringForSql(careSite.getBingConfidenceLevel()));
		insertString.append(assembleStringForSql(careSite.getBingSuggestedPostalAddress()));
		insertString.append(assembleIntForSql(careSite.getAddressValidationConfidenceScore()));
		insertString.append(assembleBooleanForSql(careSite.getAddressValidated()));
		insertString.append(assembleBooleanForSql(careSite.getVACareSite()));
		insertString.append(assembleBooleanForSql(careSite.getGeocoded()));
		insertString.append(assembleStringForSql(careSite.getActiveDate()));
		insertString.append(assembleIntForSql(careSite.getTimeZoneRuleVersionNumber()));
		insertString.append(assembleBooleanForSql(careSite.getUseBingGeocodingService()));
		insertString.append(assembleIntForSql(careSite.getUTCConversionTimeZoneCode()));
		insertString.append(assembleStringForSql(careSite.getCreatedBy()));
		insertString.append(assembleStringForSql(careSite.getCreatedOn()));
		insertString.append(assembleStringForSql(careSite.getModifiedBy()));
		insertString.append(assembleStringForSql(careSite.getModifiedOnDate()));
	
		insertString.append("),");
		return insertString.toString();
	}
	
	public static final String PROVIDER_SERVICE_CARE_SITE_COLUMNS = "ProviderNpi, CareSiteGuid, SpecialityCode, ProviderServiceStatus, ProviderServiceStatusReason, "
			+ "WorkHours, SpecialtyName, ProviderNetwork, ProviderAgreement, DescriptionOfService, VAProviderRelationship, QualityRankingTotalScore, "
			+ "IsPrimaryCare, IsProviderAcceptingVA, IsProviderAcceptingNewPatients, "
			+ "ProviderServiceGuid, CareSiteName, AddressValidated, Facility, Geocoded, HPP, HSRM, Latitude, Longitude, Name, " 
			+ "OrganizationGroup, Owner, ProviderName, DupCheck, Limitation, OwningBusinessUnit, OwningTeam, OwningUser, ProviderGender, TimeZoneRuleVersionNumber, UTCConversionTimeZoneCode, VersionNumber, "
			+ "PPMSCreated_By, PPMSCreatedOn_Date, PPMSModified_By, PPMSModifiedOn_Date) VALUES ";
	
	public static String createInsertStatementForProviderServiceCareSite(CDWProviderService providerService) {
		
		if (providerService ==null) {
			return null;
		}
		StringBuffer insertString = new StringBuffer("(");
		insertString.append(providerService.getProviderNpi());
		insertString.append(assembleStringForSql(providerService.getCareSiteGuid()));
		insertString.append(assembleStringForSql(providerService.getSpecialtyCode()));
		
		//"IsPrimaryTaxonomy" is not exist !!!!
		/*if (StringUtils.isEmpty(providerService.getIsPrimaryTaxonomy())) {
			insertString.append(SPACE_COMMA_SPACE).append("null");
		} else {
			insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(escapeForSql(providerService.getIsPrimaryTaxonomy())).append(SINGLEQUOTE );
		}*/
		insertString.append(assembleStringForSql(providerService.getProviderServiceStatus()));
		insertString.append(assembleStringForSql(providerService.getProviderServiceStatusReason()));
		insertString.append(assembleStringForSql(providerService.getWorkHours()));
		insertString.append(assembleStringForSql(providerService.getSpecialtyName()));
		insertString.append(assembleStringForSql(providerService.getProviderNetwork()));
		insertString.append(assembleStringForSql(providerService.getProviderAgreement()));
		insertString.append(assembleStringForSql(providerService.getDescriptionOfService()));
		insertString.append(assembleStringForSql(providerService.getVAProviderRelationship()));
		insertString.append(assembleIntForSql(providerService.getQualityRankingTotalScore()));
		insertString.append(assembleBooleanForSql(providerService.getProviderIsPrimaryCare()));
		insertString.append(assembleBooleanForSql(providerService.getProviderAcceptingVA()));
		insertString.append(assembleBooleanForSql(providerService.getProviderAcceptingNewPatients()));
		insertString.append(assembleStringForSql(providerService.getProviderServiceGuid()));
		insertString.append(assembleStringForSql(providerService.getCareSiteName()));
		insertString.append(assembleBooleanForSql(providerService.getAddressValidated()));
		insertString.append(assembleStringForSql(providerService.getFacility()));
		insertString.append(assembleBooleanForSql(providerService.getGeocoded()));
		insertString.append(assembleStringForSql(providerService.getHPP()));
		insertString.append(assembleBooleanForSql(providerService.getHSRM()));
		insertString.append(assembleDoubleForSql(providerService.getLatitude()));
		insertString.append(assembleDoubleForSql(providerService.getLongitude()));
		insertString.append(assembleStringForSql(providerService.getName()));
		insertString.append(assembleStringForSql(providerService.getOrganizationGroup()));
		insertString.append(assembleStringForSql(providerService.getOwner()));
		insertString.append(assembleStringForSql(providerService.getProviderName()));
		insertString.append(assembleStringForSql(providerService.getDupCheck()));
		insertString.append(assembleStringForSql(providerService.getLimitation()));
		insertString.append(assembleStringForSql(providerService.getOwningBusinessUnit()));
		insertString.append(assembleStringForSql(providerService.getOwningTeam()));
		insertString.append(assembleStringForSql(providerService.getOwningUser()));
		insertString.append(assembleStringForSql(providerService.getProviderGender()));
		insertString.append(assembleIntForSql(providerService.getTimeZoneRuleVersionNumber()));
		insertString.append(assembleIntForSql(providerService.getUTCConversionTimeZoneCode()));
		insertString.append(assembleStringForSql(providerService.getVersionNumber()));
		insertString.append(assembleStringForSql(providerService.getCreatedBy()));
		insertString.append(assembleStringForSql(providerService.getCreatedOn()));
		insertString.append(assembleStringForSql(providerService.getModifiedBy()));
		insertString.append(assembleStringForSql(providerService.getModifiedOnDate()));
		
		insertString.append("),");
		return insertString.toString();
	}
	
	public static final String NON_VA_PROVIDER_COLUMNS = "ProviderNpi, ProviderGuid, ProviderType, ProviderName, ProviderFirstName, ProviderMiddleName, ProviderLastName, Phone, "
			+ "MainPhone, OtherPhone, Telephone2, Telephone3, WebSite, Email, EmailAddress2, EmailAddress3, Fax, PrimaryDirectMessagingAddress, "
			+ "SecondaryDirectMessagingAddress, Ethnicity, Gender, Religion, IndivProviderDateOfBirth, OrganizationId, OrganizationStatus, "
			+ "BillingAddress1Name, BillingAddress1ID, BillingAddress1Composite, BillingAddress1Street1, BillingAddress1Street2, BillingAddress1Street3, "
			+ "BillingAddress1City, BillingAddress1County, BillingAddress1PostOfficeBox, BillingAddress1StateProvince, BillingAddress1ZipPostalCode, "
			+ "BillingAddress1CountryRegion, BillingAddress1Latitude, BillingAddress1Longitude, BillingAddressPhone, BillingAddress1Telephone2, "
			+ "BillingAddress1Telephone3, BillingAddress1Fax, AgreementCount, AgreementCountLastUpdatedOn, AgreementCountState, ExternalHealthProviderType, "
			+ "ExternalInstitutionDEANumber, ExternalLeieCheckDate, ExternalValidationSource, Facility, FeeSchedule, ProviderStatus, "
			+ "ProviderStatusReason, ParentProvider, Process, EMRSystem, EMRSystemOther, Owner, OwnedCareSite, Ownership, OwningBusinessunit, "
			+ "OwningTeam, OwningUser, PreferredDay, PreferredMethodOfContact, PreferredTime, PAMigrationKey, RecordSource, RelationshipType, "
			+ "ServiceProviderType, InternalType, InternalAppointmentStatus, InternalLicensingJurisdiction, LastValidated, SpecialInstruction, "
			+ "ValidationResetDate, IsValidationReset, IsIndividualAcceptingNewPatients, IsInternalCanCreateHealthCareOrders, IsDirectMessagingVHIE, "
			+ "IsHSRM, IsExternal, IsRefDoc, IsOnLeie, IsLeieValidated, IsLicenseValidated, IsProviderContactValidated, IsAddressValidated, "
			+ "IsNppesNPIValidated, IsSAMValidated, IsGeocoded, IsPhone, IsMail, IsSecuredEmail, IsPrimaryCarePhysician, "
			+ "IsIndividualPrimaryCareProviderAcceptingVA, IsAccountableCareOrganization, IsDoNotAllowPhoneCalls, "
			+ "IsDoNotAllowFaxes, IsDoNotAllowEmails, IsDoNotAllowBulkEmails, IsDoNotAllowMails, IsDoNotAllowBulkMails, IsContactPrefEmail, "
			+ "IsContactPrefFax, IsDoNotSendMarketingMaterials, PPMSLastUpdatedDate, PPMSLastValidatedDate, PPMSCreated_By, "
			+ "PPMSCreatedOn_Date, PPMSModified_By, PPMSModifiedOn_Date "
			+ ") VALUES ";
	
	public static String createInsertStatementForNonVAProvider(CDWProvider nonVAProvider) {
		if (nonVAProvider == null || nonVAProvider.getProviderNpi() < 1) {
			return null;
		}
		StringBuffer insertString = new StringBuffer("(");
		insertString.append(nonVAProvider.getProviderNpi());
		insertString.append(assembleStringForSql(nonVAProvider.getProviderGuid()));
		insertString.append(assembleStringForSql(nonVAProvider.getProviderType()));
		insertString.append(assembleStringForSql(nonVAProvider.getProviderName()));
		insertString.append(assembleStringForSql(nonVAProvider.getProviderFirstName()));
		insertString.append(assembleStringForSql(nonVAProvider.getProviderMiddleName()));
		insertString.append(assembleStringForSql(nonVAProvider.getProviderLastName()));
		insertString.append(assembleBooleanForSql(nonVAProvider.getPhone()));
		insertString.append(assembleStringForSql(nonVAProvider.getMainPhone()));
		insertString.append(assembleStringForSql(nonVAProvider.getOtherPhone()));
		insertString.append(assembleStringForSql(nonVAProvider.getTelephone2()));
		insertString.append(assembleStringForSql(nonVAProvider.getTelephone3()));
		insertString.append(assembleStringForSql(nonVAProvider.getWebSite()));
		insertString.append(assembleStringForSql(nonVAProvider.getEmail()));
		insertString.append(assembleStringForSql(nonVAProvider.getEmailAddress2()));
		insertString.append(assembleStringForSql(nonVAProvider.getEmailAddress3()));
		insertString.append(assembleStringForSql(nonVAProvider.getFax()));
		insertString.append(assembleStringForSql(nonVAProvider.getPrimaryDirectMessagingAddress()));
		insertString.append(assembleStringForSql(nonVAProvider.getSecondaryDirectMessagingAddress()));
		insertString.append(assembleStringForSql(nonVAProvider.getProviderEthnicity()));
		insertString.append(assembleStringForSql(nonVAProvider.getGender()));
		insertString.append(assembleStringForSql(nonVAProvider.getReligion()));
		insertString.append(assembleStringForSql(nonVAProvider.getIndivProviderDateOfBirth()));
		insertString.append(assembleStringForSql(nonVAProvider.getOrganizationId()));
		insertString.append(assembleStringForSql(nonVAProvider.getOrganizationStatus()));
		insertString.append(assembleStringForSql(nonVAProvider.getBillingAddress1Name()));
		insertString.append(assembleStringForSql(nonVAProvider.getBillingAddress1ID()));
		insertString.append(assembleStringForSql(nonVAProvider.getBillingAddress1Composite()));
		insertString.append(assembleStringForSql(nonVAProvider.getBillingAddress1Street1()));
		insertString.append(assembleStringForSql(nonVAProvider.getBillingAddress1Street2()));
		insertString.append(assembleStringForSql(nonVAProvider.getBillingAddress1Street3()));
		insertString.append(assembleStringForSql(nonVAProvider.getBillingAddress1City()));
		insertString.append(assembleStringForSql(nonVAProvider.getBillingAddress1County()));
		insertString.append(assembleStringForSql(nonVAProvider.getBillingAddress1PostOfficeBox()));
		insertString.append(assembleStringForSql(nonVAProvider.getBillingAddress1StateProvince()));
		insertString.append(assembleStringForSql(nonVAProvider.getBillingAddress1ZipPostalCode()));
		insertString.append(assembleStringForSql(nonVAProvider.getBillingAddress1CountryRegion()));
		insertString.append(assembleDoubleForSql(nonVAProvider.getBillingAddress1Latitude()));
		insertString.append(assembleDoubleForSql(nonVAProvider.getBillingAddress1Longitude()));
		insertString.append(assembleStringForSql(nonVAProvider.getBillingAddressPhone()));
		insertString.append(assembleStringForSql(nonVAProvider.getBillingAddress1Telephone2()));
		insertString.append(assembleStringForSql(nonVAProvider.getBillingAddress1Telephone3()));
		insertString.append(assembleStringForSql(nonVAProvider.getBillingAddress1Fax()));
		insertString.append(assembleIntForSql(nonVAProvider.getAgreementCount()));
		insertString.append(assembleStringForSql(nonVAProvider.getAgreementCountLastUpdatedOn()));
		insertString.append(assembleIntForSql(nonVAProvider.getAgreementCountState()));
		insertString.append(assembleStringForSql(nonVAProvider.getExternalHealthProviderType()));
		insertString.append(assembleStringForSql(nonVAProvider.getExternalInstitutionDEANumber()));
		insertString.append(assembleStringForSql(nonVAProvider.getExternalLeieCheckDate()));
		insertString.append(assembleStringForSql(nonVAProvider.getExternalValidationSource()));
		insertString.append(assembleStringForSql(nonVAProvider.getFacility()));
		insertString.append(assembleStringForSql(nonVAProvider.getFeeSchedule()));
		insertString.append(assembleStringForSql(nonVAProvider.getProviderStatus()));
		insertString.append(assembleStringForSql(nonVAProvider.getProviderStatusReason()));
		insertString.append(assembleStringForSql(nonVAProvider.getParentProvider()));
		insertString.append(assembleStringForSql(nonVAProvider.getProcess()));
		insertString.append(assembleStringForSql(nonVAProvider.getEMRSystem()));
		insertString.append(assembleStringForSql(nonVAProvider.getEMRSystemOther()));
		insertString.append(assembleStringForSql(nonVAProvider.getOwner()));
		insertString.append(assembleStringForSql(nonVAProvider.getOwnedCareSite()));
		insertString.append(assembleStringForSql(nonVAProvider.getOwnership()));
		insertString.append(assembleStringForSql(nonVAProvider.getOwningBusinessunit()));
		insertString.append(assembleStringForSql(nonVAProvider.getOwningTeam()));
		insertString.append(assembleStringForSql(nonVAProvider.getOwningUser()));
		insertString.append(assembleStringForSql(nonVAProvider.getPreferredDay()));
		insertString.append(assembleStringForSql(nonVAProvider.getPreferredMethodOfContact()));
		insertString.append(assembleStringForSql(nonVAProvider.getPreferredTime()));
		insertString.append(assembleStringForSql(nonVAProvider.getPAMigrationKey()));
		insertString.append(assembleStringForSql(nonVAProvider.getRecordSource()));
		insertString.append(assembleStringForSql(nonVAProvider.getRelationshipType()));
		insertString.append(assembleStringForSql(nonVAProvider.getServiceProviderType()));
		insertString.append(assembleStringForSql(nonVAProvider.getInternalType()));
		insertString.append(assembleStringForSql(nonVAProvider.getInternalAppointmentStatus()));
		insertString.append(assembleStringForSql(nonVAProvider.getInternalLicensingJurisdiction()));
		insertString.append(assembleStringForSql(nonVAProvider.getLastValidated()));
		insertString.append(assembleStringForSql(nonVAProvider.getSpecialInstruction()));
		insertString.append(assembleStringForSql(nonVAProvider.getValidationResetDate()));		
		insertString.append(assembleBooleanForSql(nonVAProvider.getValidationReset()));
		insertString.append(assembleBooleanForSql(nonVAProvider.getIndividualIsAcceptingNewPatients()));
		insertString.append(assembleBooleanForSql(nonVAProvider.getInternalCanCreateHealtCareOrders()));
		insertString.append(assembleBooleanForSql(nonVAProvider.getDirectMessagingVHIE()));
		insertString.append(assembleBooleanForSql(nonVAProvider.getHSRM()));
		insertString.append(assembleBooleanForSql(nonVAProvider.getIsExternal()));
		insertString.append(assembleBooleanForSql(nonVAProvider.getRefDoc()));
		insertString.append(assembleBooleanForSql(nonVAProvider.getOnLeie()));
		insertString.append(assembleBooleanForSql(nonVAProvider.getLeieValidated()));
		insertString.append(assembleBooleanForSql(nonVAProvider.getLicenseValidated()));
		insertString.append(assembleBooleanForSql(nonVAProvider.getProviderContactValidated()));
		insertString.append(assembleBooleanForSql(nonVAProvider.getAddressValidated()));
		insertString.append(assembleBooleanForSql(nonVAProvider.getNppesNPIValidated()));
		insertString.append(assembleBooleanForSql(nonVAProvider.getSAMValidated()));
		insertString.append(assembleBooleanForSql(nonVAProvider.getGeocoded()));
		insertString.append(assembleBooleanForSql(nonVAProvider.getPhone()));
		insertString.append(assembleBooleanForSql(nonVAProvider.getMail()));
		insertString.append(assembleBooleanForSql(nonVAProvider.getSecuredEmail()));
		insertString.append(assembleBooleanForSql(nonVAProvider.getPrimaryCarePhysician()));
		insertString.append(assembleBooleanForSql(nonVAProvider.getIndividualIsPrimaryCareProviderAcceptingVA()));
		insertString.append(assembleBooleanForSql(nonVAProvider.getAccounatbleCareOrganization()));
		insertString.append(assembleBooleanForSql(nonVAProvider.getDoNotAllowPhoneCalls()));
		insertString.append(assembleBooleanForSql(nonVAProvider.getDoNotAllowFaxes()));
		insertString.append(assembleBooleanForSql(nonVAProvider.getDoNotAllowEmails()));
		insertString.append(assembleBooleanForSql(nonVAProvider.getDoNotAllowBulkEmails()));
		insertString.append(assembleBooleanForSql(nonVAProvider.getDoNotAllowMails()));
		insertString.append(assembleBooleanForSql(nonVAProvider.getDoNotAllowBulkMails()));
		insertString.append(assembleBooleanForSql(nonVAProvider.getContactPrefEmail()));
		insertString.append(assembleBooleanForSql(nonVAProvider.getContactPrefFax()));
		insertString.append(assembleBooleanForSql(nonVAProvider.getDoNotSendMarketingMaterials()));
		insertString.append(assembleStringForSql(nonVAProvider.getLastUpdatedDate()));
		insertString.append(assembleStringForSql(nonVAProvider.getLastValidated()));
		insertString.append(assembleStringForSql(nonVAProvider.getCreatedBy()));
		insertString.append(assembleStringForSql(nonVAProvider.getCreatedOn()));
		insertString.append(assembleStringForSql(nonVAProvider.getModifiedBy()));
		insertString.append(assembleStringForSql(nonVAProvider.getModifiedOnDate()));		
		insertString.append("),");
		return insertString.toString();
	}
	
	public static void runProcedureInTransaction(EntityManager em, String statementToExecute, DataSource ds) {
		
		CommonUtils.runProcedure(em, statementToExecute, ds);
	}
	
	public static String updateNonVAProvidersQuery(List<Integer> providerIdList, int isProcessedStatus) {
		if (CollectionUtils.isEmpty(providerIdList) || (isProcessedStatus != 1 && isProcessedStatus != 0) ) {
			return null;
		}
		String inclause = createInClause(providerIdList);
		return "Update " + CommonConstants.DB_ENV + CommonConstants.PIE + "NonVAProvider_V set isProcessed = " + 
				isProcessedStatus + ", Modified_by = 'PIE', Modified_Date = getdate() " +
				" where NONVAPROVIDER_ID in (" + inclause + ")";
	}
	
	public static HashMap<String, Integer> getExistingCareSiteGuidIdMap(EntityManager entityManager) {
		HashMap<String, Integer> alreadyExistingCareSiteGuidToIdMap = new HashMap<String, Integer>();
		@SuppressWarnings("unchecked")
		List<Object[]> existingCareSiteList = entityManager.createNativeQuery(
				"Select cast(CareSite_id as varchar(10))    as caresiteid, convert(varchar(50), CareSiteGuid) as CareSiteGuid from "
						+ "App."+ CommonConstants.DB_ENV + "CareSite_V").getResultList();
		existingCareSiteList.forEach(updateCareSite -> {
			alreadyExistingCareSiteGuidToIdMap.put((String) updateCareSite[1], Integer.valueOf((updateCareSite[0].toString())));
		});
		return alreadyExistingCareSiteGuidToIdMap;
	}
	
	public static String createInClause(List<Integer> list) {
		if (CollectionUtils.isEmpty(list))
			return "";
		StringBuffer strBuffer = new StringBuffer();
		for (Integer id : list) {
			strBuffer.append(id.intValue() + " , ");
		}
		return strBuffer.substring(0, strBuffer.toString().lastIndexOf(","));
	}
	
	public static StringBuffer determineTableToInsertEntity(String tableName,  String tableColumns) {
		
		String insertTableStatement = "INSERT INTO " + "App." + CommonConstants.DB_ENV + tableName + " (";
		StringBuffer insertTableStatementBuffer = new StringBuffer(insertTableStatement);
		insertTableStatementBuffer.append(tableColumns);
		return insertTableStatementBuffer;
	}
	
	public static String convertToString(Boolean object) {
		String msSqlServerBoolean = "0";
		if (object != null && object) {
			msSqlServerBoolean = "1";
		}
		return msSqlServerBoolean;
	}
	
	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
	    Map<Object, Boolean> seen = new ConcurrentHashMap<>();
	    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

	public static String escapeForSql (String string) {
	    if (string != null) {
	    	return string.trim().replace("'", "''");
	    }
	    return null;
	}
	
	public static String assembleBooleanForSql (Boolean object) {
		StringBuilder ret = new StringBuilder();
		if (object != null)
		{
			ret.append(SPACE_COMMA_SPACE_SINGLEQUOTE)
			.append(escapeForSql(convertToString(object)))
			.append(SINGLEQUOTE);
		}
		else
		{
			ret.append(SPACE_COMMA_SPACE)
			.append("null");
		}
		
		return ret.toString();
	}
	
	public static String assembleStringForSql(String object) {
		StringBuilder ret = new StringBuilder();
		if (object != null)
		{
			ret.append(SPACE_COMMA_SPACE_SINGLEQUOTE)
			.append(escapeForSql(object))
			.append(SINGLEQUOTE);
		}
		else
		{
			ret.append(SPACE_COMMA_SPACE)
			.append("null");
		}
		
		return ret.toString();
	}
	
	/*public static String assembleTimestampForSql(String object) {
		StringBuilder ret = new StringBuilder();
		ZonedDateTime zonedDateTime = null;
		if (object != null) {
			try {
			zonedDateTime = ZonedDateTime.parse(object);
			}
			catch (DateTimeParseException e)
			{
				LOG.error("error in date time convertion: " + object + " exception: " + e.getMessage());
			}
		}

		if (zonedDateTime != null) {
			ZonedDateTime systemDefaultDateTime = zonedDateTime.withZoneSameInstant(ZoneId.systemDefault());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String formattedString = systemDefaultDateTime.format(formatter);
			ret.append(SPACE_COMMA_SPACE_SINGLEQUOTE);
			ret.append(formattedString).append(SINGLEQUOTE);
		}
		else
		{
			ret.append(SPACE_COMMA_SPACE).append("null");
		}
		
		return ret.toString();
	}*/
	
	public static String assembleDoubleForSql(Double object) {
		StringBuilder ret = new StringBuilder();
		if (object != null) {
			ret.append(SPACE_COMMA_SPACE_SINGLEQUOTE)
			.append(object)
			.append(SINGLEQUOTE );
		} else {
			ret.append(SPACE_COMMA_SPACE)
			.append("null");
		}
		
		return ret.toString();
	}
	
	public static String assembleIntForSql(Integer object) {
		StringBuilder ret = new StringBuilder();
		if (object != null) {
			ret.append(SPACE_COMMA_SPACE_SINGLEQUOTE)
			.append(object)
			.append(SINGLEQUOTE );
		} else {
			ret.append(SPACE_COMMA_SPACE)
			.append("null");
		}
		
		return ret.toString();
	}

}
