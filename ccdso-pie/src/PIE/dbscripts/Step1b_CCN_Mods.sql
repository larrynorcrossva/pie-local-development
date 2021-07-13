Alter table pie.providers add [ProviderIdentifierType] [varchar](50) null;
go
Alter table pie.providers add [ProviderType] [varchar](50) null;
go
Alter table pie.providers add [QualityRankingTotalScore] [varchar](50) null;
go
Alter table pie.providers add [QualityRankingLastUpdated] [varchar](50) null;
go
Alter table pie.providers add [AddressCounty] [varchar](50) null;
go
Alter table pie.providers add [AddressCountry] [varchar](50) null;
go
Alter table pie.providers add [ProviderStatusReason] [varchar](50) null;
go
Alter table pie.providers add [IsDoDAffiliation] [bit] not null constraint [df_Providers_IsDoDAffiliation]  default (0);
go
Alter table pie.providers add [IsVaAcademicAffiliate] [bit] not null constraint [df_Providers_IsVaAcademicAffiliate]  default (0);
go
Alter table pie.providers add [IsPrimaryCarePhysician] [bit] not null constraint [df_Providers_IsPrimaryCarePhysician]  default (0);
go
Alter table pie.providers add [IsHighPerformance] [bit] not null constraint [df_Providers_IsHighPerformance]  default (0);
go
Alter table pie.providers add [IsFqhc] [bit] not null constraint [df_Providers_IsFqhc]  default (0);
go
Alter table pie.providers add [IsAccountableCareOrganization] [bit] not null constraint [df_Providers_IsAccountableCareOrganization]  default (0);
go
Alter table pie.providers add [IsAcceptingNewPatients] [bit] not null constraint [df_Providers_IsAcceptingNewPatients]  default (0);
go
Alter table pie.providers add [ProviderEthnicity] [varchar](50) null;
go
Alter table pie.providers add [Religion] [varchar](50) null;
go
Alter table pie.providers add [OrganizationId] [varchar](50) null;
go
Alter table pie.providers add [ServiceProviderType] [varchar](50) null;
go
Alter table pie.providers add [SpecialInstruction] [varchar](50) null;
go
Alter table pie.providers add [OwnedCareSiteName] [varchar](50) null;
go
Alter table pie.providers add [OrganizationFax] [varchar](50) null;
go
Alter table pie.providers add [OrganizationStatus] [varchar](50) null;
go
Alter table pie.providers add [IsExternal] [bit] not null constraint [df_Providers_IsExternal]  default (0);
go
Alter table pie.providers add [InternalType] [varchar](50) null;
go
Alter table pie.providers add [LicensingJurisdiction] [varchar](50) null;
go
Alter table pie.providers add [IsCanCreateHealthCareOrders] [bit] not null constraint [df_Providers_IsCanCreateHealthCareOrders]  default (0);
go
Alter table pie.providers add [InternalAppointmentStatus] [varchar](50) null;
go
Alter table pie.providers add [ExternalHealthProviderType] [varchar](50) null;
go
Alter table pie.providers add [IsOnLeie] [bit] not null constraint [df_Providers_IsOnLeie]  default (0);
go
Alter table pie.providers add [ExternalLeieCheckDate] [varchar](50) null;
go
Alter table pie.providers add [ValidationSource] [varchar](50) null;
go
Alter table pie.providers add [ContactMethod] [varchar](50) null;
go
Alter table pie.providers add [IsBulkEmailsAllowed] [bit] not null constraint [df_Providers_IsBulkEmailsAllowed]  default (0);
go
Alter table pie.providers add [IsBulkMailsAllowed] [bit] not null constraint [df_Providers_IsBulkMailsAllowed]  default (0);
go
Alter table pie.providers add [IsEmailsAllowed] [bit] not null constraint [df_Providers_IsEmailsAllowed]  default (0);
go
Alter table pie.providers add [IsMailsAllowed] [bit] not null constraint [df_Providers_IsMailsAllowed]  default (0);
go
Alter table pie.providers add [IsPhoneCallsAllowed] [bit] not null constraint [df_Providers_IsPhoneCallsAllowed]  default (0);
go
Alter table pie.providers add [IsFaxesAllowed] [bit] not null constraint [df_Providers_IsFaxesAllowed]  default (0);
go

-- ProviderNPI
create table [pie].[ProviderNPI](
	[ProviderNPI_Id] [int] identity(1,1) not null,
	[Provider_Id_FK] [int] not null,
	[NPINumber] [varchar](50) null,
	[EntityTypeCode] [varchar](50) null,
	[IsSoleProprietor] [bit] not null constraint [df_ProviderNPI_IsSoleProprietor]  default (0),
	[IsOrganizationSubpart] [bit] not null constraint [df_ProviderNPI_IsOrganizationSubpart]  default (0),
	[SubpartLegalBusinessName] [varchar](50) null,
	[SubpartTaxId] [varchar](50) null,
	[Created_By] [varchar](30) null,
	[Created_Date] [datetime]  null,
	[Modified_By] [varchar](30) null,
	[Modified_Date] [datetime] null,
 constraint [xPKProviderNPI] primary key clustered 
(
	[ProviderNPI_Id] asc
)with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];

ALTER TABLE [pie].[ProviderNPI]  WITH CHECK ADD CONSTRAINT [FK_ProviderNPI_PROVIDER] FOREIGN KEY
([PROVIDER_ID_FK]) REFERENCES [pie].[Providers] ([Id]);
GO
ALTER TABLE [pie].[ProviderNPI] CHECK CONSTRAINT [FK_ProviderNPI_PROVIDER];
GO

-- ProviderTIN
create table [pie].[ProviderTIN](
	[ProviderTIN_Id] [int] identity(1,1) not null,
	[Provider_Id_FK] [int] not null,
	[TinNumber] [varchar](50) null,
	[Created_By] [varchar](30) null,
	[Created_Date] [datetime]  null,
	[Modified_By] [varchar](30) null,
	[Modified_Date] [datetime] null,
 constraint [xPKProviderTIN] primary key clustered 
(
	[ProviderTIN_Id] asc
)with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];

ALTER TABLE [pie].[ProviderTIN]  WITH CHECK ADD CONSTRAINT [FK_ProviderTIN_PROVIDER] FOREIGN KEY
([PROVIDER_ID_FK]) REFERENCES [pie].[Providers] ([Id]);
GO
ALTER TABLE [pie].[ProviderTIN] CHECK CONSTRAINT [FK_ProviderTIN_PROVIDER];
GO

-- ProviderCredential
create table [pie].[ProviderCredential](
	[ProviderCredential_Id] [int] identity(1,1) not null,
	[Provider_Id_FK] [int] not null,
	[CredentialName] [varchar](100) null,
	[CredentialNumber] [varchar](50) null,
	[CredentialType] [varchar](50) null,
	[CredentialingStatus] [varchar](50) null,
	[CredentialedDate] [varchar](50) null,
	[NextCredentialingDate] [varchar](50) null,
	[Description] [varchar](250) null,
	[Created_By] [varchar](30) null,
	[Created_Date] [datetime]  null,
	[Modified_By] [varchar](30) null,
	[Modified_Date] [datetime] null,
 constraint [xPKProviderCredential] primary key clustered 
(
	[ProviderCredential_Id] asc
)with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];

ALTER TABLE [pie].[ProviderCredential]  WITH CHECK ADD CONSTRAINT [FK_ProviderCredential_PROVIDER] FOREIGN KEY
([PROVIDER_ID_FK]) REFERENCES [pie].[Providers] ([Id]);
GO
ALTER TABLE [pie].[ProviderCredential] CHECK CONSTRAINT [FK_ProviderCredential_PROVIDER];
GO

-- ProviderOtherIdentifier
create table [pie].[ProviderOtherIdentifier](
	[ProviderOtherIdentifier_Id] [int] identity(1,1) not null,
	[Provider_Id_FK] [int] not null,
	[OtherIdentifierName] [varchar](100) null,
	[IdentifierTypeCode] [varchar](50) null,
	[IdentifierState] [varchar](50) null,
	[IdentifierIssuer] [varchar](50) null,
	[Created_By] [varchar](30) null,
	[Created_Date] [datetime]  null,
	[Modified_By] [varchar](30) null,
	[Modified_Date] [datetime] null,
 constraint [xPKProviderOtherIdentifier] primary key clustered 
(
	[ProviderOtherIdentifier_Id] asc
)with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];

ALTER TABLE [pie].[ProviderOtherIdentifier]  WITH CHECK ADD CONSTRAINT [FK_ProviderOtherIdentifier_PROVIDER] FOREIGN KEY
([PROVIDER_ID_FK]) REFERENCES [pie].[Providers] ([Id]);
GO
ALTER TABLE [pie].[ProviderOtherIdentifier] CHECK CONSTRAINT [FK_ProviderOtherIdentifier_PROVIDER];
GO

-- ProviderLicensure
create table [pie].[ProviderLicensure](
	[ProviderLicensure_Id] [int] identity(1,1) not null,
	[Provider_Id_FK] [int] not null,
[LicenseNumber] [varchar](50) null,
[LicensingState] [varchar](50) null,
[ExpirationDate] [varchar](50) null,
	[Created_By] [varchar](30) null,
	[Created_Date] [datetime]  null,
	[Modified_By] [varchar](30) null,
	[Modified_Date] [datetime] null,
 constraint [xPKProviderLicensure] primary key clustered 
(
	[ProviderLicensure_Id] asc
)with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];

ALTER TABLE [pie].[ProviderLicensure]  WITH CHECK ADD CONSTRAINT [FK_ProviderLicensure_PROVIDER] FOREIGN KEY
([PROVIDER_ID_FK]) REFERENCES [pie].[Providers] ([Id]);
GO
ALTER TABLE [pie].[ProviderLicensure] CHECK CONSTRAINT [FK_ProviderLicensure_PROVIDER];
GO

-- ProviderService
create table [pie].[ProviderService](
	[ProviderService_Id] [int] identity(1,1) not null,
	[Provider_Id_FK] [int] not null,
	[CorrelationId] [varchar](50) null,
	[CodedSpecialty] [varchar](50) null,
	[Relationship] [varchar](50) null,
	[Location] [varchar](50) null,
	[OrganizationNpi] [varchar](50) null,
	[ServiceAvailability] [varchar](50) null,
	[Created_By] [varchar](30) null,
	[Created_Date] [datetime]  null,
	[Modified_By] [varchar](30) null,
	[Modified_Date] [datetime] null,
 constraint [xPKProviderService] primary key clustered 
(
	[ProviderService_Id] asc
)with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];

ALTER TABLE [pie].[ProviderService]  WITH CHECK ADD CONSTRAINT [FK_ProviderService_PROVIDER] FOREIGN KEY
([PROVIDER_ID_FK]) REFERENCES [pie].[Providers] ([Id]);
GO
ALTER TABLE [pie].[ProviderService] CHECK CONSTRAINT [FK_ProviderService_PROVIDER];
GO

-- ProviderPersonContact
create table [pie].[ProviderPersonContact](
	[ProviderPersonContact_Id] [int] identity(1,1) not null,
	[Provider_Id_FK] [int] not null,
	[CorrelationId] [varchar](50) null,
	[FirstName] [varchar](50) null,
	[MiddleName] [varchar](50) null,
	[LastName] [varchar](50) null,
	[IsVeteran] [bit] not null constraint [df_ProviderPersonContact_IsVeteran]  default (0),
	[EffectiveDate] [varchar](50) null,
	[Created_By] [varchar](30) null,
	[Created_Date] [datetime]  null,
	[Modified_By] [varchar](30) null,
	[Modified_Date] [datetime] null,
 constraint [xPKProviderPersonContact] primary key clustered 
([ProviderPersonContact_Id] asc )with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];

ALTER TABLE [pie].[ProviderPersonContact]  WITH CHECK ADD CONSTRAINT [FK_PersonContact_PROVIDER] FOREIGN KEY
([PROVIDER_ID_FK]) REFERENCES [pie].[Providers] ([Id]);
GO
ALTER TABLE [pie].[ProviderPersonContact] CHECK CONSTRAINT [FK_PersonContact_PROVIDER];
GO

-- ProviderPersonContactEmail
create table [pie].[ProviderPersonContactEmail](
	[ProviderPersonContactEmail_Id] [int] identity(1,1) not null,
	[Provider_Id_FK] [int] not null,
	[EmailAddress] [varchar](100) null,
	[OfficialCredential] [varchar](50) null,
	[EmailSpecificUse] [varchar](50) null,
	[Created_By] [varchar](30) null,
	[Created_Date] [datetime]  null,
	[Modified_By] [varchar](30) null,
	[Modified_Date] [datetime] null,
 constraint [xPKProviderPersonContactEmail] primary key clustered 
([ProviderPersonContactEmail_Id] asc )with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];

ALTER TABLE [pie].[ProviderPersonContactEmail]  WITH CHECK ADD CONSTRAINT [FK_PersonContactEmail_PROVIDER] FOREIGN KEY
([PROVIDER_ID_FK]) REFERENCES [pie].[Providers] ([Id]);
GO
ALTER TABLE [pie].[ProviderPersonContactEmail] CHECK CONSTRAINT [FK_PersonContactEmail_PROVIDER];
GO

-- ProviderPersonContactAddress
create table [pie].[ProviderPersonContactAddress](
	[ProviderPersonContactAddress_Id] [int] identity(1,1) not null,
	[Provider_Id_FK] [int] not null,
	[Address1] [varchar](100) null,
	[Address2] [varchar](100) null,
	[Address3] [varchar](100) null,
	[City] [varchar](50) null,
	[State] [varchar](50) null,
	[PostalCode] [varchar](50) null,
	[County] [varchar](100) null,
	[CountryCode] [varchar](50) null,
	[Created_By] [varchar](30) null,
	[Created_Date] [datetime]  null,
	[Modified_By] [varchar](30) null,
	[Modified_Date] [datetime] null,
 constraint [xPKProviderPersonContactAddress] primary key clustered 
(
	[ProviderPersonContactAddress_Id] asc
)with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];

ALTER TABLE [pie].[ProviderPersonContactAddress]  WITH CHECK ADD CONSTRAINT [FK_PersonContactAddress_PROVIDER] FOREIGN KEY
([PROVIDER_ID_FK]) REFERENCES [pie].[Providers] ([Id]);
GO
ALTER TABLE [pie].[ProviderPersonContactAddress] CHECK CONSTRAINT [FK_PersonContactAddress_PROVIDER];
GO

-- ProviderPersonContactPhone
create table [pie].[ProviderPersonContactPhone](
	[ProviderPersonContactPhone_Id] [int] identity(1,1) not null,
	[Provider_Id_FK] [int] not null,
	[PhoneNumber] [varchar](50) null,
	[IsTextingAcceptable] [bit] not null constraint [df_PPCPhone_IsTextingAcceptable]  default (0),
	[Created_By] [varchar](30) null,
	[Created_Date] [datetime]  null,
	[Modified_By] [varchar](30) null,
	[Modified_Date] [datetime] null,
 constraint [xPKProviderPersonContactPhone] primary key clustered 
(
	[ProviderPersonContactPhone_Id] asc
)with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];

ALTER TABLE [pie].[ProviderPersonContactPhone]  WITH CHECK ADD CONSTRAINT [FK_PersonContactPhone_PROVIDER] FOREIGN KEY
([Provider_Id_FK]) REFERENCES [pie].[Providers] ([Id]);
GO
ALTER TABLE [pie].[ProviderPersonContactPhone] CHECK CONSTRAINT [FK_PersonContactPhone_PROVIDER];
GO

-- OrganizationAuthorizedOfficial
create table [pie].[OrganizationAuthorizedOfficial](
	[OrganizationAuthorizedOfficial_Id] [int] identity(1,1) not null,
	[Provider_Id_FK] [int] not null,
	[OfficialName] [varchar](50) null,
	[OfficialCredential] [varchar](50) null,
	[TitleOrPosition] [varchar](50) null,
	[PhoneNumber] [varchar](50) null,
	[Created_By] [varchar](30) null,
	[Created_Date] [datetime]  null,
	[Modified_By] [varchar](30) null,
	[Modified_Date] [datetime] null,
 constraint [xPKOrganizationAuthorizedOfficial] primary key clustered 
(
	[OrganizationAuthorizedOfficial_Id] asc
)with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];

ALTER TABLE [pie].[OrganizationAuthorizedOfficial]  WITH CHECK ADD CONSTRAINT [FK_OrgAuthOfficial_PROVIDER] FOREIGN KEY
([PROVIDER_ID_FK]) REFERENCES [pie].[Providers] ([Id]);
GO
ALTER TABLE [pie].[OrganizationAuthorizedOfficial] CHECK CONSTRAINT [FK_OrgAuthOfficial_PROVIDER];
GO

-- ProviderMedicalEducation
create table [pie].[ProviderMedicalEducation](
	[ProviderMedicalEducation_Id] [int] identity(1,1) not null,
	[Provider_Id_FK] [int] not null,
	[EducationName] [varchar](50) null,
	[GraduationDate] [varchar](50) null,
	[Created_By] [varchar](30) null,
	[Created_Date] [datetime]  null,
	[Modified_By] [varchar](30) null,
	[Modified_Date] [datetime] null,
 constraint [xPKProviderMedicalEducation] primary key clustered 
(
	[ProviderMedicalEducation_Id] asc
)with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];

ALTER TABLE [pie].[ProviderMedicalEducation]  WITH CHECK ADD CONSTRAINT [FK_MedicalEducation_PROVIDER] FOREIGN KEY
([PROVIDER_ID_FK]) REFERENCES [pie].[Providers] ([Id]);
GO
ALTER TABLE [pie].[ProviderMedicalEducation] CHECK CONSTRAINT [FK_MedicalEducation_PROVIDER];
GO

-- ProviderBoardCertification
create table [pie].[ProviderBoardCertification](
	[ProviderBoardCertification_Id] [int] identity(1,1) not null,
	[Provider_Id_FK] [int] not null,
	[BoardCertificationName] [varchar](50) null,
	[IsBoardCertified] [bit] not null constraint [df_ProviderBoardCertification_IsBoardCertified]  default (0),
	[BoardId] [varchar](50) null,
	[CertificationDate] [varchar](50) null,
	[RecertificationDate] [varchar](50) null,
	[Created_By] [varchar](30) null,
	[Created_Date] [datetime]  null,
	[Modified_By] [varchar](30) null,
	[Modified_Date] [datetime] null,
 constraint [xPKProviderBoardCertification] primary key clustered 
(
	[ProviderBoardCertification_Id] asc
)with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];

ALTER TABLE [pie].[ProviderBoardCertification]  WITH CHECK ADD CONSTRAINT [FK_BoardCertification_PROVIDER] FOREIGN KEY
([PROVIDER_ID_FK]) REFERENCES [pie].[Providers] ([Id]);
GO
ALTER TABLE [pie].[ProviderBoardCertification] CHECK CONSTRAINT [FK_BoardCertification_PROVIDER];
GO

-- ProviderPrivilege
create table [pie].[ProviderPrivilege](
	[ProviderPrivilege_Id] [int] identity(1,1) not null,
	[Provider_Id_FK] [int] not null,
	[PrivilegeName] [varchar](50) null,
	[ExpirationDate] [varchar](50) null,
	[Comment] [varchar](250) null,
	[CareSite] [varchar](50) null,
	[Created_By] [varchar](30) null,
	[Created_Date] [datetime]  null,
	[Modified_By] [varchar](30) null,
	[Modified_Date] [datetime] null,
 constraint [xPKProviderPrivilege] primary key clustered 
(
	[ProviderPrivilege_Id] asc
)with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];

ALTER TABLE [pie].[ProviderPrivilege]  WITH CHECK ADD CONSTRAINT [FK_ProviderPrivilege_PROVIDER] FOREIGN KEY
([PROVIDER_ID_FK]) REFERENCES [pie].[Providers] ([Id]);
GO
ALTER TABLE [pie].[ProviderPrivilege] CHECK CONSTRAINT [FK_ProviderPrivilege_PROVIDER];
GO

-- ProviderOtherName
create table [pie].[ProviderOtherName](
	[ProviderOtherName_Id] [int] identity(1,1) not null,
	[Provider_Id_FK] [int] not null,
	[OtherName] [varchar](50) null,
	[OtherNameType] [varchar](50) null,
	[OtherCredential] [varchar](50) null,
	[Created_By] [varchar](30) null,
	[Created_Date] [datetime]  null,
	[Modified_By] [varchar](30) null,
	[Modified_Date] [datetime] null,
 constraint [xPKProviderOtherName] primary key clustered 
(
	[ProviderOtherName_Id] asc
)with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];

ALTER TABLE [pie].[ProviderOtherName]  WITH CHECK ADD CONSTRAINT [FK_ProviderOtherName_PROVIDER] FOREIGN KEY
([PROVIDER_ID_FK]) REFERENCES [pie].[Providers] ([Id]);
GO
ALTER TABLE [pie].[ProviderOtherName] CHECK CONSTRAINT [FK_ProviderOtherName_PROVIDER];
GO

-- ProviderDEASchedulePrivilege
create table [pie].[ProviderDEASchedulePrivilege](
	[ProviderDEASchedulePrivilege_Id] [int] identity(1,1) not null,
	[Provider_Id_FK] [int] not null,
	[DeaNumber] [varchar](50) null,
	[ExpirationDate] [varchar](50) null,
	[VerificationDate] [varchar](50) null,
	[Verifier] [varchar](50) null,
	[HasScheduleII] [bit] not null constraint [df_DEAPriv_HasScheduleII]  default (0),
	[HasScheduleIINonNarcotic] [bit] not null constraint [df_DEAPriv_HasScheduleIINonNarcotic]  default (0),
	[HasScheduleIII] [bit] not null constraint [df_DEAPriv_HasScheduleIII]  default (0),
	[HasScheduleIIINonNarcotic] [bit] not null constraint [df_DEAPriv_HasScheduleIIINonNarcotic]  default (0),
	[HasScheduleIV] [bit] not null constraint [df_DEAPriv_HasScheduleIV]  default (0),
	[HasScheduleV] [bit] not null constraint [df_DEAPriv_HasScheduleV]  default (0),
	[Created_By] [varchar](30) null,
	[Created_Date] [datetime]  null,
	[Modified_By] [varchar](30) null,
	[Modified_Date] [datetime] null,
 constraint [xPKProviderDEASchedulePrivilege] primary key clustered 
(
	[ProviderDEASchedulePrivilege_Id] asc
)with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];

ALTER TABLE [pie].[ProviderDEASchedulePrivilege]  WITH CHECK ADD CONSTRAINT [FK_DEASchedulePrivilege_PROVIDER] FOREIGN KEY
([PROVIDER_ID_FK]) REFERENCES [pie].[Providers] ([Id]);
GO
ALTER TABLE [pie].[ProviderDEASchedulePrivilege] CHECK CONSTRAINT [FK_DEASchedulePrivilege_PROVIDER];
GO

-- ProviderOrganizationContacts
create table [pie].[ProviderOrganizationContacts](
	[ProviderOrganizationContacts_Id] [int] identity(1,1) not null,
	[Provider_Id_FK] [int] not null,
	[ContactInformation] [varchar](50) null,
	[IsPrimaryPoc] [bit] not null constraint [df_ProviderOrganizationContacts_IsPrimaryPoc]  default (0),
	[ContactInfoType] [varchar](50) null,
	[IsActive] [bit] not null constraint [df_ProviderOrganizationContacts_IsActive]  default (0),
	[Created_By] [varchar](30) null,
	[Created_Date] [datetime]  null,
	[Modified_By] [varchar](30) null,
	[Modified_Date] [datetime] null,
 constraint [xPKProviderOrganizationContacts] primary key clustered 
(
	[ProviderOrganizationContacts_Id] asc
)with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];

ALTER TABLE [pie].[ProviderOrganizationContacts]  WITH CHECK ADD CONSTRAINT [FK_OrganizationContacts_PROVIDER] FOREIGN KEY
([PROVIDER_ID_FK]) REFERENCES [pie].[Providers] ([Id]);
GO
ALTER TABLE [pie].[ProviderOrganizationContacts] CHECK CONSTRAINT [FK_OrganizationContacts_PROVIDER];
GO

-- ProviderSpecialties
create table [pie].[ProviderSpecialties](
	[ProviderSpecialties_Id] [int] identity(1,1) not null,
	[Provider_Id_FK] [int] not null,
	[CodedSpecialtyName] [varchar](50) null,
	[IsPrimaryTaxonomy] [bit] not null constraint [df_ProviderSpecialties_IsPrimaryTaxonomy]  default (0),
	[Created_By] [varchar](30) null,
	[Created_Date] [datetime]  null,
	[Modified_By] [varchar](30) null,
	[Modified_Date] [datetime] null,
 constraint [xPKProviderSpecialties] primary key clustered 
(
	[ProviderSpecialties_Id] asc
)with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];

ALTER TABLE [pie].[ProviderSpecialties]  WITH CHECK ADD CONSTRAINT [FK_ProviderSpecialties_PROVIDER] FOREIGN KEY
([PROVIDER_ID_FK]) REFERENCES [pie].[Providers] ([Id]);
GO
ALTER TABLE [pie].[ProviderSpecialties] CHECK CONSTRAINT [FK_ProviderSpecialties_PROVIDER];
GO

-- ProviderServiceAvailability
create table [pie].[ProviderServiceAvailability](
	[ProviderServiceAvailability_Id] [int] identity(1,1) not null,
	[Provider_Id_FK] [int] not null,
	[DayOfTheWeek] [varchar](50) null,
	[StartTime] [varchar](50) null,
	[EndTime] [varchar](50) null,
	[Created_By] [varchar](30) null,
	[Created_Date] [datetime]  null,
	[Modified_By] [varchar](30) null,
	[Modified_Date] [datetime] null,
 constraint [xPKProviderServiceAvailability] primary key clustered 
(
	[ProviderServiceAvailability_Id] asc
)with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];

ALTER TABLE [pie].[ProviderServiceAvailability]  WITH CHECK ADD CONSTRAINT [FK_ServiceAvailability_PROVIDER] FOREIGN KEY
([PROVIDER_ID_FK]) REFERENCES [pie].[Providers] ([Id]);
GO
ALTER TABLE [pie].[ProviderServiceAvailability] CHECK CONSTRAINT [FK_ServiceAvailability_PROVIDER];
GO

-- ProviderCareSite
create table [pie].[ProviderCareSite](
	[ProviderCareSite_Id] [int] identity(1,1) not null,
	[Provider_Id_FK] [int] not null,
	[CorrelationId] [varchar](50) null,
	[SiteName] [varchar](50) null,
	[SiteAddress] [varchar](50) null,
	[SiteType] [varchar](50) null,
	[SiteId] [varchar](50) null,
	[OtherName] [varchar](50) null,
	[IsHandicapAccessible] [bit] not null constraint [df_ProviderCareSite_IsHandicapAccessible]  default (0),
	[VISN] [varchar](50) null,
	[Created_By] [varchar](30) null,
	[Created_Date] [datetime]  null,
	[Modified_By] [varchar](30) null,
	[Modified_Date] [datetime] null,
 constraint [xPKProviderCareSite] primary key clustered 
(
	[ProviderCareSite_Id] asc
)with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];

ALTER TABLE [pie].[ProviderCareSite]  WITH CHECK ADD CONSTRAINT [FK_ProviderCareSite_PROVIDER] FOREIGN KEY
([PROVIDER_ID_FK]) REFERENCES [pie].[Providers] ([Id]);
GO
ALTER TABLE [pie].[ProviderCareSite] CHECK CONSTRAINT [FK_ProviderCareSite_PROVIDER];
GO


