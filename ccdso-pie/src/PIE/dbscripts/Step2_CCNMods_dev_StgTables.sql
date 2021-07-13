create table [dev_pie].[NonVAProvider_stg](
	[NonVAProvider_stg_Id] [int] identity (1,1) not null,
	[ProviderGuid] [varchar](500) null,
	[ProviderNpi] [bigint] not null,
	[ProviderType] [varchar](500) null,
	[ProviderName] [varchar](500) null,
	[ProviderFirstName] [varchar](500) null,
	[ProviderMiddleName] [varchar](500) null,
	[ProviderLastName] [varchar](500) null,
	[Phone] [varchar](500) null,
	[MainPhone] [varchar](500) null,
	[OtherPhone] [varchar](500) null,
	[Telephone2] [varchar](500) null,
	[Telephone3] [varchar](500) null,
	[WebSite] [varchar](500) null,
	[Email] [varchar](500) null,
	[EmailAddress2] [varchar](500) null,
	[EmailAddress3] [varchar](500) null,
	[Fax] [varchar](500) null,
	[PrimaryDirectMessagingAddress] [varchar](500) null,
	[SecondaryDirectMessagingAddress] [varchar](500) null,
	[Ethnicity] [varchar](500) null,
	[Gender] [varchar](50) null,
	[Religion] [varchar](500) null,
	[IndivProviderDateOfBirth] [datetime]  null,
	[OrganizationId] [varchar](500) null,
	[OrganizationStatus] [varchar](500) null,
	[BillingAddress1Name] [varchar](500) null,
	[BillingAddress1ID] [varchar](50) null,
	[BillingAddress1Composite] [varchar](500) null,
	[BillingAddress1Street1] [varchar](500) null,
	[BillingAddress1Street2] [varchar](500) null,
	[BillingAddress1Street3] [varchar](500) null,
	[BillingAddress1City] [varchar](500) null,
	[BillingAddress1County] [varchar](500) null,
	[BillingAddress1PostOfficeBox] [varchar](500) null,
	[BillingAddress1StateProvince] [varchar](500) null,
	[BillingAddress1ZipPostalCode] [varchar](50) null,
	[BillingAddress1CountryRegion] [varchar](500) null,
	[BillingAddress1Latitude] [float] null,
	[BillingAddress1Longitude] [float] null,
	[BillingAddressPhone] [varchar](500) null,
	[BillingAddress1Telephone2] [varchar](500) null,
	[BillingAddress1Telephone3] [varchar](500) null,
	[BillingAddress1Fax] [varchar](500) null,
	[AgreementCount] [varchar](50) null,
	[AgreementCountLastUpdatedOn] [datetime]  null,
	[AgreementCountState] [varchar](500) null,
	[ExternalHealthProviderType] [varchar](50) null,
	[ExternalInstitutionDEANumber] [varchar](50) null,
	[ExternalLeieCheckDate] [datetime]  null,
	[ExternalValidationSource] [varchar](500) null,
	[Facility] [varchar](500) null,
	[FeeSchedule] [varchar](500) null,
	[ProviderStatus] [varchar](500) null,
	[ProviderStatusReason] [varchar](500) null,
	[ParentProvider] [varchar](500) null,
	[Process] [varchar](500) null,
	[EMRSystem] [varchar](500) null,
	[EMRSystemOther] [varchar](500) null,
	[Owner] [varchar](500) null,
	[OwnedCareSite] [varchar](500) null,
	[Ownership] [varchar](500) null,
	[OwningBusinessunit] [varchar](500) null,
	[OwningTeam] [varchar](500) null,
	[OwningUser] [varchar](500) null,
	[PreferredDay] [varchar](500) null,
	[PreferredMethodOfContact] [varchar](500) null,
	[PreferredTime] [varchar](500) null,
	[PAMigrationKey] [varchar](500) null,
	[RecordSource] [varchar](500) null,
	[RelationshipType] [varchar](500) null,
	[ServiceProviderType] [varchar](500) null,
	[InternalType] [varchar](500) null,
	[InternalAppointmentStatus] [varchar](500) null,
	[InternalLicensingJurisdiction] [varchar](500) null,
	[LastValidated] [datetime]  null,
	[SpecialInstruction] [varchar](500) null,
	[ValidationResetDate] [datetime]  null,
	[IsValidationReset] [varchar](10) null,
	[IsIndividualAcceptingNewPatients] [varchar](10) null,
	[IsInternalCanCreateHealthCareOrders] [varchar](10) null,
	[IsDirectMessagingVHIE] [varchar](10) null,
	[IsHSRM] [varchar](10) null,
	[IsExternal] [varchar](10) null,
	[IsRefDoc] [varchar](10) null,
	[IsOnLeie] [varchar](10) null,
	[IsLeieValidated] [varchar](10) null,
	[IsLicenseValidated] [varchar](10) null,
	[IsProviderContactValidated] [varchar](10) null,
	[IsAddressValidated] [varchar](10) null,
	[IsNppesNPIValidated] [varchar](10) null,
	[IsSAMValidated] [varchar](10) null,
	[IsGeocoded] [varchar](10) null,
	[IsPhone] [varchar](10) null,
	[IsMail] [varchar](10) null,
	[IsSecuredEmail] [varchar](10) null,
	[IsPrimaryCarePhysician] [varchar](10) null,
	[IsIndividualPrimaryCareProviderAcceptingVA] [varchar](10) null,
	[IsAccountableCareOrganization] [varchar](10) null,
	[IsSendMarketingMaterials] [varchar](10) null,
	[IsDoNotAllowPhoneCalls] [varchar](10) null,
	[IsDoNotAllowFaxes] [varchar](10) null,
	[IsDoNotAllowEmails] [varchar](10) null,
	[IsDoNotAllowBulkEmails] [varchar](10) null,
	[IsDoNotAllowMails] [varchar](10) null,
	[IsDoNotAllowBulkMails] [varchar](10) null,
	[IsContactPrefEmail] [varchar](10) null,
	[IsContactPrefFax] [varchar](10) null,
	[IsDoNotSendMarketingMaterials] [varchar](10) null,
	[IsProcessed] [bit] not null constraint [df_NonVAProvider_stg_IsProcessed]  default 0,
	[PPMSLastUpdatedDate] [date] null,
	[PPMSLastValidatedDate] [date] null,
	[PPMSCreated_By] [varchar](500) null,
	[PPMSCreatedOn_Date] [date] null,
	[PPMSModified_By] [varchar](500) null,
	[PPMSModifiedOn_Date] [date] null,
	[IsRetrieved] [bit] not null CONSTRAINT [df_NonVAProvider_stg_IsRetrieved] DEFAULT 0,
	[InactiveFlag] [bit] not null constraint [df_NonVAProvider_stg_InactiveFlag]  default (0),
	[InactiveDate] [date] null,
	[Created_By] varchar(30) not null constraint [df_NonVAProvider_stg_Created_By]  default ('DBA'),
	[Created_Date] datetime not null constraint [df_NonVAProvider_stg_Created_Date]  default (getdate()),
	[Modified_By] varchar(30) not null constraint [df_NonVAProvider_stg_Modified_By]  default ('DBA'),
	[Modified_Date] datetime not null constraint [df_NonVA_stg_Modified_Date]  default (getdate())
	constraint [pk_NonVAProvider_stg] primary key([NonVAProvider_stg_Id]) with (fillfactor=80) on [Deffg]
) on [Deffg]
GO

--
create table [dev_pie].[ProviderService_stg](
	[ProviderService_stg_Id] [int] identity (1,1) not null,
NVAProvider_Id_FK [int] null,
CareSite_Id_FK [int] null,
	[ProviderServiceGuid] [varchar] (100) null, 
	[AddressValidated] [varchar] (500) null,
	[CareSiteGuid] [varchar] (500) null,
	[CareSiteName] [varchar] (500) null,
	[Facility] [varchar] (500) null,
	[Geocoded] [varchar] (100) null,
	[HPP] [varchar] (100) null,
	[HSRM] [varchar] (100) null,
	[Latitude] [float] null,
	[Longitude] [float] null,
	[Name] [varchar] (500) null,
	[OrganizationGroup] [varchar] (500) null,
	[Owner] [varchar] (500) null,
	[ProviderName] [varchar] (500) null,
	[ProviderAgreement] [varchar] (500) null,
	[ProviderNpi] [BigInt] null,
[CareSiteType] [varchar] (500) null,
[AddressComposite] [varchar] (500) null,
[AddressStreet1] [varchar] (500) null,
[AddressStreet2] [varchar] (500) null,
[AddressCity] [varchar] (100) null,
[AddressState] [varchar] (100) null,
[AddressZip] [varchar] (100) null,
[OfficePhone] [varchar] (100) null,
	[ProviderIsPrimaryCare] [varchar] (1) null,
	[ProviderNetwork] [varchar] (500) null,
	[SpecialtyName] [varchar] (500) null,
	[SpecialtyCode] [varchar] (500) null,
[IsPrimaryTaxonomy] [varchar] (10) null,
	[ProviderServiceStatus] [varchar] (500) null,
	[ProviderServiceStatusReason] [varchar] (500) null,
	[VAProviderRelationship] [varchar] (500) null,
[PPMSModifiedOnDate] DateTime null,
	[CreatedBy] [varchar] (100) null,
	[CreatedOn] [DateTime] null,
	[DescriptionOfService] [varchar] (500) null,
	[DupCheck] [varchar] (500) null,
	[Limitation] [varchar] (500) null,
	[ModifiedBy] [varchar] (500) null,
	[OwningBusinessUnit] [varchar] (500) null,
	[OwningTeam] [varchar] (500) null,
	[OwningUser] [varchar] (500) null,
	[ProviderAcceptingNewPatients] [varchar] (10) null,
	[ProviderAcceptingVA] [varchar] (10) null,
	[ProviderGender] [varchar] (50) null,
	[QualityRankingTotalScore] [Int] null,
	[TimeZoneRuleVersionNumber] [Int] null,
	[UTCConversionTimeZoneCode] [Int] null,
	[VersionNumber] [BigInt] null,
	[WorkHours] [varchar] (500) null
	constraint [pk_ProviderService_stg] primary key([ProviderService_stg_Id]) with (fillfactor=80) on [Deffg]
) on [Deffg]
GO 

--
create table [dev_pie].[ProviderOtherIdentifier_stg](
	[ProviderOtherIdentifier_stg_Id] [int] identity(1,1) not null,
	[NonVAProvider_Id_FK] [int] not null,
	[IdentifierTypeCode] [varchar](500) null,
	[ProviderIdentifierStatus] [varchar](500) null,
	[ProviderIdentifierStatusReason] [varchar](500) null,
	[PPMSModifiedOn_Date] [date] null,
	[OtherIdentifierName] [varchar](500) null,
	[IdentifierState] [varchar](500) null,
	[IdentifierIssuer] [varchar](500) null,
	[InactiveFlag] [bit] not null constraint [df_ProviderOtherIdentifier_stg_InactiveFlag]  default (0),
	[InactiveDate] [date] null,
	[Created_By] [varchar](30) null,
	[Created_Date] [datetime]  null,
	[Modified_By] [varchar](30) null,
	[Modified_Date] [datetime] null,
 constraint [xPKProviderOtherIdentifier_stg] primary key clustered 
(
	[ProviderOtherIdentifier_stg_Id] asc
)with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];
--
create table [dev_pie].[ProviderAgreement_stg](
	[ProviderAgreement_stg_Id] [int] identity(1,1) not null,
	[NonVAProvider_Id_FK] [int] not null,
	[ProviderName] [varchar](500) null,
	[ProviderType] [varchar](500) null,
	[AgreementGuid] [varchar](500) null,
	[AgreementId] [varchar](500) null,
	[AgreementType] [varchar](500) null,
	[AssignedFacility] [varchar](500) null,
	[EffectiveDate] [datetime]  null,
	[ExpiratiOn_Date] [datetime]  null,
	[MedicalDirectorSignatureDate] [datetime]  null,
	[QualificationExpirationDate] [datetime]  null,
	[QualificationReviewDate] [datetime]  null,
	[SpecialtyCoverage] [varchar](500) null,
	[ProviderAgreementStatus] [varchar](500) null,
	[ProviderAgreementStatusReason] [varchar](500) null,
	[CalcAgreementExpirationDate] [datetime]  null,
	[CalcExpirationTimeFrameYears] [varchar](50) null,
	[DocumentLocation] [varchar](500) null,
	[ImportStatusField] [varchar](500) null,
	[Owner] [varchar](500) null,
	[OwningBusinessUnit] [varchar](500) null,
	[OwningTeam] [varchar](500) null,
	[OwningUser] [varchar](500) null,
	[PendingDateSet] [datetime]  null,
	[CancelDateSet] [datetime]  null,
	[RequiredAttachment] [varchar](500) null,
	[IsProviderCapacityLackOfAvailability] [varchar](10) null,
	[IsPreviousExperienceWithChoiceViaTW] [varchar](10) null,
	[IsDeclinedPreviousExpWithProvAgreement] [varchar](10) null,
	[IsDeclinedRefusalToCommitAtLowerLevel] [varchar](10) null,
	[IsDeclinedRefusalToCompleteOtherReqTraining] [varchar](10) null,
	[IsDeclinedRefusalToCompleteReqOpiodTraining] [varchar](10) null,
	[IsDeclinedRefusedCompleteQualificationDocument] [varchar](10) null,
	[IsDeclinedPaymentRateOrMethodology] [varchar](10) null,
	[IsDeclinedPreviousExperienceWithChoice] [varchar](10) null,
	[IsDeclinedTimelyReimbursements] [varchar](10) null,
	[IsStatusSwitch] [varchar](10) null,
	[StatusSwitchDate] [datetime]  null,
	[PPMSCreated_By] [varchar](100) null,
	[PPMSCreatedOn_Date] [date] null,
	[PPMSModified_By] [varchar](100) null,
	[PPMSModifiedOn_Date] [date] null,
	[InactiveFlag] [bit] not null constraint [df_ProviderAgreement_stg_InactiveFlag]  default (0),
	[InactiveDate] [date] null,
	[Created_By] [varchar](30) null,
	[Created_Date] [datetime]  null,
	[Modified_By] [varchar](30) null,
	[Modified_Date] [datetime] null,
 constraint [xPKProviderAgreement_stg] primary key clustered 
(
	[ProviderAgreement_stg_Id] asc
)with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];

--
create table [dev_pie].[ProviderMedicalEducation_stg](
	[ProviderMedicalEducation_stg_Id] [int] identity(1,1) not null,
	[NonVAProvider_Id_FK] [int] not null,
	[EducationName] [varchar](500) null,
	[GraduationDate] [varchar](500) null,
	[MedicalEducationStatus] [varchar](500) null,
	[MedicalEducationStatusReason] [varchar](500) null,
	[PPMSModifiedOn_Date] [date] null,
	[InactiveFlag] [bit] not null constraint [df_ProviderMedicalEducation_stg_InactiveFlag]  default (0),
	[InactiveDate] [date] null,
	[Created_By] [varchar](30) null,
	[Created_Date] [datetime]  null,
	[Modified_By] [varchar](30) null,
	[Modified_Date] [datetime] null,
 constraint [xPKProviderMedicalEducation_stg] primary key clustered 
(
	[ProviderMedicalEducation_stg_Id] asc
)with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];

-- 
create table [dev_pie].[ProviderDEASchedulePrivilege_stg](
	[ProviderDEASchedulePrivilege_stg_Id] [int] identity(1,1) not null,
	[NonVAProvider_Id_FK] [int] not null,
	[DeaNumber] [varchar](100) null,
	[Verifier] [varchar](500) null,
	[VerificationDate] [date] null,
	[ExpirationDate] [date] null,
	[AssociatedLocationName] [varchar](500) null,
	[HasScheduleII] [varchar](10) null,
	[HasScheduleIINonNarcotic] [varchar](10) null,
	[HasScheduleIII] [varchar](10) null,
	[HasScheduleIIINonNarcotic] [varchar](10) null,
	[HasScheduleIV] [varchar](10) null,
	[HasScheduleV] [varchar](10) null,
	[DEAStatus] [varchar](500) null,
	[DEAStatusReason] [varchar](500) null,
	[PPMSModifiedOn_Date] [date] null,
	[InactiveFlag] [bit] not null constraint [df_ProviderDEAPriv_stg_InactiveFlag]  default (0),
	[InactiveDate] [date] null,
	[Created_By] [varchar](30) null,
	[Created_Date] [datetime]  null,
	[Modified_By] [varchar](30) null,
	[Modified_Date] [datetime] null,
 constraint [xPKProviderDEASchedulePrivilege_stg] primary key clustered 
(
	[ProviderDEASchedulePrivilege_stg_Id] asc
)with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];

--
create table [dev_pie].[CareSite_stg](
	[CareSite_stg_Id] [int] identity(1,1) not null,
	[CareSiteGuid] [varchar](500) null,
	[CareSiteType] [varchar](500) null,
	[CareSiteName] [varchar](500) null,
	[AddressComposite] [varchar](500) null,
	[AddressStreet1] [varchar](500) null,
	[AddressStreet2] [varchar](500) null,
	[City] [varchar](500) null,
	[State] [varchar](500) null,
	[ZipCode] [varchar](100) null,
	[Country] [varchar](500) null,
	[Latitude] [float] null,
	[Longitude] [float] null,
	[MainSitePhone] [varchar](500) null,
	[DEANumber] [varchar](500) null,
	[CareSiteEmail] [varchar](500) null,
	[CareSiteFax] [varchar](500) null,
	[VersionNumber] [int] null,
	[CareSiteStatus] [varchar](500) null,
	[CareSiteStatusReason] [varchar](500) null,
	[OtherName] [varchar](500) null,
	[Owner] [varchar](500) null,
	[OwningUser] [varchar](500) null,
	[OwningTeam] [varchar](500) null,
	[OwningBusinessUnit] [varchar](500) null,
	[VISN] [varchar](500) null,
	[ParentStationNumber] [varchar](500) null,
	[SiteContact] [varchar](500) null,
	[IsExternal] [varchar](10) null,
	[IsHandicapAccessible] [varchar](10) null,
	[IsUseGeocodingService] [varchar](10) null,
	[IsUseAddressValidationService] [varchar](10) null,
	[ProviderGroup] [varchar](500) null,
	[StationName] [varchar](200) null,
	[Facility] [varchar](500) null,
	[FacilityType] [varchar](500) null,
	[PPMSInactive_Date] [datetime]  null,
	[CenterOfExcellence]  [varchar](500) null,
	[DeliveryPointValidation] [varchar](500) null,
	[BingConfidenceLevel] [varchar](500) null,
	[BingSuggestedPostalAddress] [varchar](200) null,
	[AddressValidationConfidenceScore] [int] null,
	[IsAddressValidated] [varchar](10) null,
	[IsVACareSite] [varchar](10) null,
	[IsGeocoded] [varchar](10) null,
	[PPMSCreated_By] [varchar](100) null,
	[PPMSCreatedOn_Date] [date] null,
	[PPMSModified_By] [varchar](100) null,
	[PPMSModifiedOn_Date] [date] null,
	[IsRetrieved] [varchar](10) null,
	[Created_By] [varchar](100) null,
	[Created_Date] [datetime]  null,
	[Modified_By] [varchar](100) null,
	[Modified_Date] [datetime] null,
 constraint [xPKCareSite_stg] primary key clustered 
([CareSite_stg_Id] asc
) with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];

--
create table [dev_pie].[ProviderServiceCareSite_stg](
	[ProviderServiceCareSite_stg_Id] [int] identity(1,1) not null,
	[NonVAProvider_Id_FK] [int] not null,
	[CareSite_Id_FK] [int] not null,
	[SpecialityCode] [varchar](100) null,
	[IsPrimaryTaxonomy] [varchar](10) null,
	[ProviderServiceStatus] [varchar](500) null,
	[ProviderServiceStatusReason] [varchar](500) null,
	[WorkHours] [varchar](500) null,
	[SpecialtyName] [varchar](500) null,
	[ProviderNetwork] [varchar](500) null,
	[ProviderAgreement] [varchar](500) null,
	[DescriptionOfService] [varchar](500) null,
	[VAProviderRelationship] [varchar](500) null,
	[QualityRankingTotalScore] [varchar](500) null,
	[IsPrimaryCare] [varchar](10) null,
	[IsProviderAcceptingVA] [varchar](10) null,
	[IsProviderAcceptingNewPatients] [varchar](10) null,
	[PPMSCreated_By] [varchar](100) null,
	[PPMSCreatedOn_Date] [date] null,
	[PPMSModified_By] [varchar](100) null,
	[PPMSModifiedOn_Date] [date] null,
	[IsProcessed] [bit] not null constraint [df_ProviderServiceCareSite_stg_IsProcessed] default (0),
	[IsRetrieved] [bit] not null constraint [df_ProviderServiceCareSite_stg_IsRetrieved] default (0),
	[InactiveFlag] [bit] not null constraint [df_ProviderServiceCareSite_stg_InactiveFlag] default (0),
	[InactiveDate] [date] null,
	[Created_By] [varchar](100) null,
	[Created_Date] [datetime]  null,
	[Modified_By] [varchar](100) null,
	[Modified_Date] [datetime] null,
 constraint [xPKProviderServiceCareSite_stg] primary key clustered 
(
	[ProviderServiceCareSite_stg_Id] asc
)with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];

--
--
--
drop view App.dev_NonVAProvider_stg_V;
GO
create view App.dev_NonVAProvider_stg_V as select * from [dev_pie].[NonVAProvider_stg];
GO

drop view App.dev_ProviderService_stg_V;
GO
create view App.dev_ProviderService_stg_V as select * from  [dev_pie].[ProviderService_stg];
GO

drop view App.dev_ProviderOtherIdentifier_stg_V;
GO
create view App.dev_ProviderOtherIdentifier_stg_V as select * from  [dev_pie].[ProviderOtherIdentifier_stg];
GO

drop view App.dev_ProviderAgreement_stg_V;
GO
create view App.dev_ProviderAgreement_stg_V as select * from  [dev_pie].[ProviderAgreement_stg];
GO

drop view App.dev_ProviderMedicalEducation_stg_V;
GO
create view App.dev_ProviderMedicalEducation_stg_V as select * from [dev_pie].[ProviderMedicalEducation_stg];
GO

drop view App.dev_ProviderDEASchedulePrivilege_stg_V;
GO
create view App.dev_ProviderDEASchedulePrivilege_stg_V as select * from [dev_pie].[ProviderDEASchedulePrivilege_stg];
GO

drop view App.dev_CareSite_stg_V;
GO
create view App.dev_CareSite_stg_V as select * from [dev_pie].[CareSite_stg];
GO
--
drop view App.dev_ProviderServiceCareSite_stg_V;
GO
create view App.dev_ProviderServiceCareSite_stg_V as select * from [dev_pie].[ProviderServiceCareSite_stg];
GO

--
--
--
--
create table [dev_pie].[VistA_OutResponse_H](
	[VistA_OutResponse_H_Id] [int] identity(1,1) not null,
	[TranCode] [varchar] (1) not null,
	[VistA_OutResponse_Id] [int] not null,
	[NonVAProvider_Id_FK] [int] not null,
	[Stationno] [varchar](30) null,
	[IsFail] [bit] not null constraint [df_VistA_OutResponse_H_IsFail] default (0),
	[ActionCode] [varchar] (10) null,
	[VistAResponseCode] [varchar] (10) null,
	[Response_Message_Text] [varchar](500) null,
	[Created_By] [varchar](30) null,
	[Created_Date] [datetime]  null,
	[Modified_By] [varchar](30) null,
	[Modified_Date] [datetime] null,
 constraint [xPKVistA_OutResponse_H] primary key clustered 
(
	[VistA_OutResponse_H_Id] asc
)with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];

--
DROP TRIGGER [dev_pie].[TR_VistA_OutResponse_INS_H]
GO
--
CREATE TRIGGER [dev_pie].[TR_VistA_OutResponse_INS_H] ON [dev_pie].[VistA_OutResponse]
WITH EXEC AS CALLER
AFTER INSERT
AS
BEGIN
INSERT INTO [dev_pie].[VistA_OutResponse_H]
    ([TranCode],
	[VistA_OutResponse_Id],
	[NonVAProvider_Id_FK],
	[Stationno],
	[IsFail],
	[ActionCode],
	[VistAResponseCode],
	[Response_Message_Text],
	[Created_By],
	[Created_Date],
	[Modified_By],
	[Modified_Date] )
SELECT 'I'
	,[VistA_OutResponse_Id]
	,[NonVAProvider_Id_FK]
	,[Stationno]
	,[IsFail]
	,[ActionCode]
	,[VistAResponseCode]
	,[Response_Message_Text]
	,'DBA'
	,getdate()
	,'DBA'
	,getdate()
  FROM inserted
END
GO
--
drop view App.dev_VistA_OutResponse_H_V
GO
create view App.dev_VistA_OutResponse_H_V as select * from [dev_pie].[VistA_OutResponse_H];
GO

