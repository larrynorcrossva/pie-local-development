--
/*
drop table [dev_pie].[NonVAProvider_stg];
GO
drop table [dev_pie].[ProviderService_stg];
GO
drop table [dev_pie].[ProviderOtherIdentifier_stg];
GO
drop table [dev_pie].[ProviderAgreement_stg];
GO
drop table [dev_pie].[ProviderMedicalEducation_stg];
GO
drop table [dev_pie].[ProviderDEASchedulePrivilege_stg];
GO

drop view App.dev_NonVAProvider_stg_V;
GO
drop view App.dev_ProviderService_stg_V;
GO
drop view App.dev_ProviderOtherIdentifier_stg_V;
GO
drop view App.dev_ProviderAgreement_stg_V;
GO
drop view App.dev_ProviderMedicalEducation_stg_V;
GO
drop view App.dev_ProviderDEASchedulePrivilege_stg_V;
GO
--
DROP TRIGGER [dev_pie].[TR_VistA_OutResponse_INS_H]
GO
drop table [dev_pie].[VistA_OutResponse_H];
GO
drop view App.dev_VistA_OutResponse_H_V
GO

drop table [dev_pie].[ProviderOtherIdentifier];
GO
drop table [dev_pie].[ProviderAgreement];
GO
drop table [dev_pie].[ProviderMedicalEducation];
GO
drop table [dev_pie].[ProviderDEASchedulePrivilege];
GO
drop table [dev_pie].[ProviderServiceCareSite];
GO
drop table [dev_pie].[CareSiteStations];
GO
drop table [dev_pie].[CareSite];
GO
drop table [dev_pie].[NonVAProvider];
GO
drop table [dev_pie].[NonVAProvider_stg];
GO
drop table [dev_pie].[VistA_OutResponse];
GO
drop table [dev_pie].[PPMSNonVAInboundResponse];
GO
*/
--
set ansi_nulls on
GO
set quoted_Identifier on
GO
set ansi_padding on
GO
--
--
create table [dev_pie].[NonVAProvider](
	[NonVAProvider_Id] [int] identity (1,1) not null,
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
	[Gender] [varchar](100) null,
	[Religion] [varchar](500) null,
	[IndivProviderDateOfBirth] [datetime]  null,
	[OrganizationId] [varchar](500) null,
	[OrganizationStatus] [varchar](500) null,
	[BillingAddress1Name] [varchar](500) null,
	[BillingAddress1ID] [varchar](100) null,
	[BillingAddress1Composite] [varchar](500) null,
	[BillingAddress1Street1] [varchar](500) null,
	[BillingAddress1Street2] [varchar](500) null,
	[BillingAddress1Street3] [varchar](500) null,
	[BillingAddress1City] [varchar](500) null,
	[BillingAddress1County] [varchar](500) null,
	[BillingAddress1PostOfficeBox] [varchar](500) null,
	[BillingAddress1StateProvince] [varchar](500) null,
	[BillingAddress1ZipPostalCode] [varchar](100) null,
	[BillingAddress1CountryRegion] [varchar](500) null,
	[BillingAddress1Latitude] [float] null,
	[BillingAddress1Longitude] [float] null,
	[BillingAddressPhone] [varchar](500) null,
	[BillingAddress1Telephone2] [varchar](500) null,
	[BillingAddress1Telephone3] [varchar](500) null,
	[BillingAddress1Fax] [varchar](500) null,
	[AgreementCount] [varchar](100) null,
	[AgreementCountLastUpdatedOn] [datetime]  null,
	[AgreementCountState] [varchar](500) null,
	[ExternalHealthProviderType] [varchar](100) null,
	[ExternalInstitutionDEANumber] [varchar](100) null,
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
	[IsProcessed] [bit] not null constraint [df_NonVAProvider_IsProcessed]  default 0,
	[PPMSLastUpdatedDate] [date] null,
	[PPMSLastValidatedDate] [date] null,
	[PPMSCreated_By] [varchar](100) null,
	[PPMSCreatedOn_Date] [date] null,
	[PPMSModified_By] [varchar](100) null,
	[PPMSModifiedOn_Date] [date] null,
	[IsRetrieved] [bit] not null CONSTRAINT [df_NonVAProvider_IsRetrieved] DEFAULT 0,
	[InactiveFlag] [bit] not null constraint [df_NonVAProvider_InactiveFlag]  default (0),
	[InactiveDate] [date] null,
	[Created_By] varchar(30) not null constraint [df_NonVAProvider_Created_By]  default ('DBA'),
	[Created_Date] datetime not null constraint [df_NonVAProvider_Created_Date]  default (getdate()),
	[Modified_By] varchar(30) not null constraint [df_NonVAProvider_Modified_By]  default ('DBA'),
	[Modified_Date] datetime not null constraint [df_NonVA_Modified_Date]  default (getdate())
	constraint [pk_NonVAProvider] primary key([NonVAProvider_Id]) with (fillfactor=80) on [Deffg]
) on [Deffg]
GO
--
CREATE UNIQUE INDEX [UIDX_ProviderNpi] ON [dev_pie].[NonVAProvider]
(
    [ProviderNpi]
)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [deffg]
GO
--

-- ProviderOtherIdentifier
create table [dev_pie].[ProviderOtherIdentifier](
	[ProviderOtherIdentifier_Id] [int] identity(1,1) not null,
	[NonVAProvider_Id_FK] [int] not null,
	[IdentifierTypeCode] [varchar](500) null,
	[ProviderIdentifierStatus] [varchar](500) null,
	[ProviderIdentifierStatusReason] [varchar](500) null,
	[PPMSModifiedOn_Date] [date] null,
	[OtherIdentifierName] [varchar](500) null,
	[IdentifierState] [varchar](500) null,
	[IdentifierIssuer] [varchar](500) null,
	[InactiveFlag] [bit] not null constraint [df_ProviderOtherIdentifier_InactiveFlag]  default (0),
	[InactiveDate] [date] null,
	[Created_By] [varchar](100) null,
	[Created_Date] [datetime]  null,
	[Modified_By] [varchar](100) null,
	[Modified_Date] [datetime] null,
 constraint [xPKProviderOtherIdentifier] primary key clustered 
(
	[ProviderOtherIdentifier_Id] asc
)with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];

ALTER TABLE [dev_pie].[ProviderOtherIdentifier]  WITH CHECK ADD CONSTRAINT [FK_ProviderOtherIdentifier_NonVAProvider] FOREIGN KEY
([NonVAProvider_Id_FK]) REFERENCES [dev_pie].[NonVAProvider] ([NonVAProvider_Id]);
GO
ALTER TABLE [dev_pie].[ProviderOtherIdentifier] CHECK CONSTRAINT [FK_ProviderOtherIdentifier_NonVAProvider];
GO
--
create table [dev_pie].[ProviderAgreement](
	[ProviderAgreement_Id] [int] identity(1,1) not null,
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
	[SpecialtyCoverage] [varchar](200) null,
	[ProviderAgreementStatus] [varchar](500) null,
	[ProviderAgreementStatusReason] [varchar](500) null,
	[CalcAgreementExpirationDate] [datetime]  null,
	[CalcExpirationTimeFrameYears] [varchar](10) null,
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
	[InactiveFlag] [bit] not null constraint [df_ProviderAgreement_InactiveFlag]  default (0),
	[InactiveDate] [date] null,
	[Created_By] [varchar](100) null,
	[Created_Date] [datetime]  null,
	[Modified_By] [varchar](100) null,
	[Modified_Date] [datetime] null,
 constraint [xPKProviderAgreement] primary key clustered 
(
	[ProviderAgreement_Id] asc
)with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];

ALTER TABLE [dev_pie].[ProviderAgreement]  WITH CHECK ADD CONSTRAINT [FK_ProviderAgreement_NonVAProvider] FOREIGN KEY
([NonVAProvider_Id_FK]) REFERENCES [dev_pie].[NonVAProvider] ([NonVAProvider_Id]);
GO
ALTER TABLE [dev_pie].[ProviderAgreement] CHECK CONSTRAINT [FK_ProviderAgreement_NonVAProvider];
GO
--
create table [dev_pie].[ProviderMedicalEducation](
	[ProviderMedicalEducation_Id] [int] identity(1,1) not null,
	[NonVAProvider_Id_FK] [int] not null,
	[EducationName] [varchar](500) null,
	[GraduationDate] [varchar](500) null,
	[MedicalEducationStatus] [varchar](500) null,
	[MedicalEducationStatusReason] [varchar](500) null,
	[PPMSModifiedOn_Date] [date] null,
	[InactiveFlag] [bit] not null constraint [df_ProviderMedicalEducation_InactiveFlag]  default (0),
	[InactiveDate] [date] null,
	[Created_By] [varchar](100) null,
	[Created_Date] [datetime]  null,
	[Modified_By] [varchar](100) null,
	[Modified_Date] [datetime] null,
 constraint [xPKProviderMedicalEducation] primary key clustered 
(
	[ProviderMedicalEducation_Id] asc
)with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];

ALTER TABLE [dev_pie].[ProviderMedicalEducation]  WITH CHECK ADD CONSTRAINT [FK_MedicalEducation_NonVAProvider] FOREIGN KEY
([NonVAProvider_Id_FK]) REFERENCES [dev_pie].[NonVAProvider] ([NonVAProvider_Id]);
GO
ALTER TABLE [dev_pie].[ProviderMedicalEducation] CHECK CONSTRAINT [FK_MedicalEducation_NonVAProvider];
GO

-- ProviderDEASchedulePrivilege
create table [dev_pie].[ProviderDEASchedulePrivilege](
	[ProviderDEASchedulePrivilege_Id] [int] identity(1,1) not null,
	[NonVAProvider_Id_FK] [int] not null,
	[DeaNumber] [varchar](500) null,
	[Verifier] [varchar](500) null,
	[VerificationDate] [varchar](500) null,
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
	[InactiveFlag] [bit] not null constraint [df_ProviderDEAPriv_InactiveFlag]  default (0),
	[InactiveDate] [date] null,
	[Created_By] [varchar](100) null,
	[Created_Date] [datetime]  null,
	[Modified_By] [varchar](100) null,
	[Modified_Date] [datetime] null,
 constraint [xPKProviderDEASchedulePrivilege] primary key clustered 
(
	[ProviderDEASchedulePrivilege_Id] asc
)with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];

ALTER TABLE [dev_pie].[ProviderDEASchedulePrivilege]  WITH CHECK ADD CONSTRAINT [FK_DEASchedulePrivilege_NonVAProvider] FOREIGN KEY
([NonVAProvider_Id_FK]) REFERENCES [dev_pie].[NonVAProvider] ([NonVAProvider_Id]);
GO
ALTER TABLE [dev_pie].[ProviderDEASchedulePrivilege] CHECK CONSTRAINT [FK_DEASchedulePrivilege_NonVAProvider];
GO

--
create table [dev_pie].[CareSite](
	[CareSite_Id] [int] identity(1,1) not null,
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
 constraint [xPKCareSite] primary key clustered 
([CareSite_Id] asc
) with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];

--
create table [dev_pie].[ProviderServiceCareSite](
	[ProviderServiceCareSite_Id] [int] identity(1,1) not null,
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
	[IsProcessed] [bit] not null constraint [df_ProviderServiceCareSite_IsProcessed] default (0),
	[IsRetrieved] [bit] not null constraint [df_ProviderServiceCareSite_stg_IsRetrieved] default (0),
	[InactiveFlag] [bit] not null constraint [df_ProviderServiceCareSite_InactiveFlag] default (0),
	[InactiveDate] [date] null,
	[Created_By] [varchar](100) null,
	[Created_Date] [datetime]  null,
	[Modified_By] [varchar](100) null,
	[Modified_Date] [datetime] null,
 constraint [xPKProviderServiceCareSite] primary key clustered 
(
	[ProviderServiceCareSite_Id] asc
)with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];

ALTER TABLE [dev_pie].[ProviderServiceCareSite]  WITH CHECK ADD CONSTRAINT [FK_ProviderServiceCareSite_NonVAProvider] FOREIGN KEY
([NonVAProvider_Id_FK]) REFERENCES [dev_pie].[NonVAProvider] ([NonVAProvider_Id]);
GO
ALTER TABLE [dev_pie].[ProviderServiceCareSite] CHECK CONSTRAINT [FK_ProviderServiceCareSite_NonVAProvider];
GO
--
ALTER TABLE [dev_pie].[ProviderServiceCareSite]  WITH CHECK ADD CONSTRAINT [FK_ProviderServiceCareSite_CareSite] FOREIGN KEY
([CareSite_Id_FK]) REFERENCES [dev_pie].[CareSite] ([CareSite_Id]);
GO
ALTER TABLE [dev_pie].[ProviderServiceCareSite] CHECK CONSTRAINT [FK_ProviderServiceCareSite_CareSite];
GO
--
create table [dev_pie].[CareSiteStations](
	[CareSiteStations_Id] [int] identity(1,1) not null,
	[CareSite_Id_FK]  [int] not null,
	[StationNumber] [varchar](10) null,
	[VISN] [varchar](500) null,
	[IsProcessed] [bit] not null constraint [df_CareSiteStations_IsProcessed]  default 0,
	[IsRetrieved] [varchar](10) null,
	[InactiveFlag] [bit] not null constraint [df_CareSiteStations_InactiveFlag]  default (0),
	[InactiveDate] [date] null,
	[Created_By] [varchar](100) null,
	[Created_Date] [datetime]  null,
	[Modified_By] [varchar](100) null,
	[Modified_Date] [datetime] null,
 constraint [xPKCareSiteStations] primary key clustered 
(
	[CareSiteStations_Id] asc
)with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];

--
ALTER TABLE [dev_pie].[CareSiteStations]  WITH CHECK ADD CONSTRAINT [FK_CareSiteStations_CareSite] FOREIGN KEY
([CareSite_Id_FK]) REFERENCES [dev_pie].[CareSite] ([CareSite_Id]);
GO
ALTER TABLE [dev_pie].[CareSiteStations] CHECK CONSTRAINT [FK_CareSiteStations_CareSite];
GO
--
create table [dev_pie].[VistA_OutResponse](
	[VistA_OutResponse_Id] [int] identity(1,1) not null,
	[NonVAProvider_Id_FK] [int] not null,
	[Stationno] [varchar](30) null,
	[IsFail] [bit] not null constraint [df_VistA_OutResponse_IsFail] default (0),
	[ActionCode] [varchar] (10) null,
	[VistAResponseCode] [varchar] (10) null,
	[Response_Message_Text] [varchar](500) null,
	[Created_By] [varchar](100) null,
	[Created_Date] [datetime]  null,
	[Modified_By] [varchar](100) null,
	[Modified_Date] [datetime] null,
 constraint [xPKVistA_OutResponse] primary key clustered 
(
	[VistA_OutResponse_Id] asc
)with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];
--
ALTER TABLE [dev_pie].[VistA_OutResponse]  WITH CHECK ADD CONSTRAINT [FK_VistA_OutResponse_NonVAProvider] FOREIGN KEY
([NonVAProvider_Id_FK]) REFERENCES [dev_pie].[NonVAProvider] ([NonVAProvider_Id])
GO
ALTER TABLE [dev_pie].[VistA_OutResponse] CHECK CONSTRAINT [FK_VistA_OutResponse_NonVAProvider]
GO
--

CREATE TABLE [dev_pie].[PPMSNonVAInboundResponse](
	[PPMSNonVAInboundResponse_Id] [int] IDENTITY(1,1) NOT NULL,
	[Transaction_Number] [varchar](500) NOT NULL, -- get a GUID
	[Transaction_Type] [varchar](10) NOT NULL, -- set it to CDW or VistA
	[Transaction_Count] [int] NOT NULL constraint [df_PPMSNonVAInboundResponse_TranCount] DEFAULT 0,
	[Response_Message_Text] [varchar](500) NULL,
	[IsProviderFail] [bit] not null constraint [df_PPMSNonVAInboundResponse_IsProviderFail]  default 0,
	[IsProviderServiceFail] [bit] not null constraint [df_PPMSNonVAInboundResponse_IsProviderServiceFail]  default 0,
	[IsProviderDEAFail] [bit] not null constraint [df_PPMSNonVAInboundResponse_IsProviderDEAFail]  default 0,
	[IsProviderOtherIdentifierFail] [bit] not null constraint [df_PPMSNonVAInboundResponse_IsProviderOtherIdentifierFail]  default 0,
	[IsProviderMedicalEducationFail] [bit] not null constraint [df_PPMSNonVAInboundResponse_IsProviderMedicalEducationFail]  default 0,
	[IsProviderAgreementFail] [bit] not null constraint [df_PPMSNonVAInboundResponse_IsProviderAssignmentFail]  default 0,
	[IsCareSiteFail] [bit] not null constraint [df_PPMSNonVAInboundResponse_IsCareSiteFail]  default 0,
	[ProviderLastSuccess_Date] [date] NULL,
	[ProviderServiceLastSuccess_Date] [date] NULL,
	[ProviderDEALastSuccess_Date] [date] NULL,
	[ProviderOtherIdentifierLastSuccess_Date] [date] NULL,
	[ProviderMedicalEducationLastSuccess_Date] [date] NULL,
	[ProviderAgreementLastSuccess_Date] [date] NULL,
	[CareSiteLastSuccess_Date] [date] NULL,
	[Created_By] [varchar](100) NULL,
	[Created_Date] [datetime] NULL,
	[Modified_By] [varchar](100) NULL,
	[Modified_Date] [datetime] NULL,
 CONSTRAINT [xPKPPMSNonVAInboundResponse] PRIMARY KEY CLUSTERED 
(
	[PPMSNonVAInboundResponse_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [DefFG]
) ON [DefFG]
GO
--
-- Create the App views
--
drop view App.dev_NonVAProvider_V;
GO
create view App.dev_NonVAProvider_V as select * from [dev_pie].[NonVAProvider];
GO
drop view App.dev_ProviderOtherIdentifier_V;
GO
create view App.dev_ProviderOtherIdentifier_V as select * from [dev_pie].[ProviderOtherIdentifier];
GO
drop view App.dev_ProviderMedicalEducation_V;
GO
create view App.dev_ProviderMedicalEducation_V as select * from [dev_pie].[ProviderMedicalEducation];
GO
drop view App.dev_ProviderDEASchedulePrivilege_V;
GO
create view App.dev_ProviderDEASchedulePrivilege_V as select * from [dev_pie].[ProviderDEASchedulePrivilege];
GO
drop view App.dev_CareSite_V;
GO
create view App.dev_CareSite_V as select * from [dev_pie].[CareSite];
GO
drop view App.dev_VistA_OutResponse_V;
GO
create view App.dev_VistA_OutResponse_V as select * from [dev_pie].[VistA_OutResponse];
GO
drop view App.dev_CareSiteStations_V;
GO
create view App.dev_CareSiteStations_V as select * from [dev_pie].[CareSiteStations];
GO
drop view App.dev_ProviderServiceCareSite_V;
GO
create view App.dev_ProviderServiceCareSite_V as select * from [dev_pie].[ProviderServiceCareSite];
GO
drop view App.dev_PPMSNonVAInboundResponse_V;
GO
create view App.dev_PPMSNonVAInboundResponse_V as select * from [dev_pie].[PPMSNonVAInboundResponse];
GO

-- ProviderNPI
-- ProviderTIN
-- ProviderCredential
-- ProviderService
-- ProviderLicensure
-- ProviderPersonContact
-- ProviderPersonContactEmail
-- ProviderPersonContactAddress
-- ProviderPersonContactPhone
-- OrganizationAuthorizedOfficial
-- ProviderBoardCertification
-- ProviderPrivilege
-- ProviderOtherName
-- ProviderMedicalEducation



