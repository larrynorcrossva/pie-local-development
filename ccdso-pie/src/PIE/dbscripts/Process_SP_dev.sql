USE [CC_PIE]
GO

/****** Object:  StoredProcedure [App].[dev_CalculateCareSitesVastRadius]    Script Date: 7/2/2019 4:42:36 PM ******/
DROP PROCEDURE [App].[dev_ProcessDEASchedulingPrivilegesVistaData]
GO
--
DROP PROCEDURE [App].[dev_ProcessMedicalEducationVistaData]
GO
--
DROP PROCEDURE [App].[dev_ProcessOtherIdentifierVistaData]
GO
--
DROP PROCEDURE [App].[dev_ProcessProviderServicesVistaData]
GO
--
DROP PROCEDURE [App].[dev_ProcessProviderVistaData]
GO
--
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--
CREATE PROCEDURE [App].[dev_ProcessDEASchedulingPrivilegesVistaData]
AS

DECLARE @T TABLE([ProviderDEASchedulePrivilege_stg_Id] INT);

MERGE [App].[dev_ProviderDEASchedulePrivilege_V] AS T
USING [App].[dev_ProviderDEASchedulePrivilege_stg_V] AS S
ON (T.[NonVAProvider_Id_FK] = S.[NonVAProvider_Id_FK]) AND (T.[DeaNumber] = S.[DeaNumber])
WHEN MATCHED THEN UPDATE
SET T.[NonVAProvider_Id_FK] = S.[NonVAProvider_Id_FK],
T.[DeaNumber] = S.[DeaNumber],
T.[Verifier] = S.[Verifier],
T.[VerificationDate] = S.[VerificationDate],
T.[ExpirationDate] = S.[ExpirationDate],
T.[AssociatedLocationName] = S.[AssociatedLocationName],
T.[HasScheduleII] = S.[HasScheduleII],
T.[HasScheduleIINonNarcotic] = S.[HasScheduleIINonNarcotic],
T.[HasScheduleIII] = S.[HasScheduleIII],
T.[HasScheduleIIINonNarcotic] = S.[HasScheduleIIINonNarcotic],
T.[HasScheduleIV] = S.[HasScheduleIV],
T.[HasScheduleV] = S.[HasScheduleV],
T.[DEAStatus] = S.[DEAStatus],
T.[DEAStatusReason] = S.[DEAStatusReason],
T.[PPMSModifiedOn_Date] = S.[PPMSModifiedOn_Date],
T.[InactiveFlag]  = S.[InactiveFlag],
T.[InactiveDate] = S.[InactiveDate],
T.[Created_By] = S.[Created_By],
T.[Created_Date] = S.[Created_Date],
T.[Modified_By] = S.[Modified_By],
T.[Modified_Date] = S.[Modified_Date]
WHEN NOT MATCHED BY TARGET THEN
INSERT (NonVAProvider_Id_FK,
DeaNumber,
Verifier,
VerificationDate,
ExpirationDate,
AssociatedLocationName,
HasScheduleII,
HasScheduleIINonNarcotic,
HasScheduleIII,
HasScheduleIIINonNarcotic,
HasScheduleIV,
HasScheduleV,
DEAStatus,
DEAStatusReason,
PPMSModifiedOn_Date,
InactiveFlag,
InactiveDate,
Created_By,
Created_Date,
Modified_By,
Modified_Date) VALUES (
S.[NonVAProvider_Id_FK],
S.[DeaNumber],
S.[Verifier],
S.[VerificationDate],
S.[ExpirationDate],
S.[AssociatedLocationName],
S.[HasScheduleII],
S.[HasScheduleIINonNarcotic],
S.[HasScheduleIII],
S.[HasScheduleIIINonNarcotic],
S.[HasScheduleIV],
S.[HasScheduleV],
S.[DEAStatus],
S.[DEAStatusReason],
S.[PPMSModifiedOn_Date],
S.[InactiveFlag],
S.[InactiveDate],
S.[Created_By],
S.[Created_Date],
S.[Modified_By],
S.[Modified_Date])
OUTPUT S.ProviderDEASchedulePrivilege_stg_Id INTO @T;
DELETE [App].[dev_ProviderDEASchedulePrivilege_stg_V] WHERE ProviderDEASchedulePrivilege_stg_Id in (SELECT ProviderDEASchedulePrivilege_stg_Id FROM @T);
GO
--
--
CREATE PROCEDURE [App].[dev_ProcessMedicalEducationVistaData]
AS

DECLARE @T TABLE([ProviderMedicalEducation_stg_Id] INT);

MERGE [App].[dev_ProviderMedicalEducation_V] AS T
USING [App].[dev_ProviderMedicalEducation_stg_V] AS S
ON (T.[NonVAProvider_Id_FK] = S.[NonVAProvider_Id_FK]) AND (T.[EducationName] = S.[EducationName])
WHEN MATCHED THEN UPDATE
SET T.[NonVAProvider_Id_FK] = S.[NonVAProvider_Id_FK],
T.[EducationName] = S.[EducationName],
T.[GraduationDate] = S.[GraduationDate],
T.[MedicalEducationStatus] = S.[MedicalEducationStatus],
T.[MedicalEducationStatusReason] = S.[MedicalEducationStatusReason],
T.[PPMSModifiedOn_Date] = S.[PPMSModifiedOn_Date],
T.[InactiveFlag]  = S.[InactiveFlag],
T.[InactiveDate] = S.[InactiveDate],
T.[Created_By] = S.[Created_By],
T.[Created_Date] = S.[Created_Date],
T.[Modified_By] = S.[Modified_By],
T.[Modified_Date] = S.[Modified_Date]
WHEN NOT MATCHED BY TARGET THEN
INSERT (NonVAProvider_Id_FK,
EducationName,
GraduationDate,
MedicalEducationStatus,
MedicalEducationStatusReason,
PPMSModifiedOn_Date,
InactiveFlag,
InactiveDate,
Created_By,
Created_Date,
Modified_By,
Modified_Date) VALUES (
S.[NonVAProvider_Id_FK],
S.[EducationName],
S.[GraduationDate],
S.[MedicalEducationStatus],
S.[MedicalEducationStatusReason],
S.[PPMSModifiedOn_Date],
S.[InactiveFlag],
S.[InactiveDate],
S.[Created_By],
S.[Created_Date],
S.[Modified_By],
S.[Modified_Date])
OUTPUT S.ProviderMedicalEducation_stg_Id INTO @T;
DELETE [App].[dev_ProviderMedicalEducation_stg_V] WHERE ProviderMedicalEducation_stg_Id in (SELECT ProviderMedicalEducation_stg_Id FROM @T);
GO
--
--
CREATE PROCEDURE [App].[dev_ProcessOtherIdentifierVistaData]
AS

DECLARE @T TABLE([ProviderOtherIdentifier_stg_Id] INT);

MERGE [App].[dev_ProviderOtherIdentifier_V] AS T
USING [App].[dev_ProviderOtherIdentifier_stg_V] AS S
ON (T.[NonVAProvider_Id_FK] = S.[NonVAProvider_Id_FK]) AND (T.[OtherIdentifierName] = S.[OtherIdentifierName])
WHEN MATCHED THEN UPDATE
SET T.[NonVAProvider_Id_FK] = S.[NonVAProvider_Id_FK],
T.[IdentifierTypeCode] = S.[IdentifierTypeCode],
T.[ProviderIdentifierStatus] = S.[ProviderIdentifierStatus],
T.[ProviderIdentifierStatusReason] = S.[ProviderIdentifierStatusReason],
T.[PPMSModifiedOn_Date] = S.[PPMSModifiedOn_Date],
T.[OtherIdentifierName] = S.[OtherIdentifierName],
T.[IdentifierState] = S.[IdentifierState],
T.[IdentifierIssuer] = S.[IdentifierIssuer],
T.[InactiveFlag]  = S.[InactiveFlag],
T.[InactiveDate] = S.[InactiveDate],
T.[Created_By] = S.[Created_By],
T.[Created_Date] = S.[Created_Date],
T.[Modified_By] = S.[Modified_By],
T.[Modified_Date] = S.[Modified_Date]
WHEN NOT MATCHED BY TARGET THEN
INSERT (NonVAProvider_Id_FK,
IdentifierTypeCode,
ProviderIdentifierStatus,
ProviderIdentifierStatusReason,
PPMSModifiedOn_Date,
OtherIdentifierName,
IdentifierState,
IdentifierIssuer,
InactiveFlag,
InactiveDate,
Created_By,
Created_Date,
Modified_By,
Modified_Date) VALUES (
S.[NonVAProvider_Id_FK],
S.[IdentifierTypeCode],
S.[ProviderIdentifierStatus],
S.[ProviderIdentifierStatusReason],
S.[PPMSModifiedOn_Date],
S.[OtherIdentifierName],
S.[IdentifierState],
S.[IdentifierIssuer],
S.[InactiveFlag],
S.[InactiveDate],
S.[Created_By],
S.[Created_Date],
S.[Modified_By],
S.[Modified_Date])
OUTPUT S.ProviderOtherIdentifier_stg_Id INTO @T;
DELETE [App].[dev_ProviderOtherIdentifier_stg_V] WHERE ProviderOtherIdentifier_stg_Id in (SELECT ProviderOtherIdentifier_stg_Id FROM @T);
GO
--
--
CREATE Procedure [App].[dev_ProcessProviderServicesVistaData] AS

Update Staging Set
Staging.NvaProvider_Id_fk=NVA.NonVAProvider_Id
from APP.dev_ProviderService_stg_V Staging
inner join App.dev_NonVAProvider_V NVA on (Staging.ProviderNpi=NVA.ProviderNpi)

Delete from App.dev_ProviderService_stg_V where NvaProvider_Id_fk is null

update App.dev_ProviderService_stg_V set IsPrimaryTaxonomy=null where IsPrimaryTaxonomy='null'

Insert into App.dev_CareSite_V (CareSiteGuid, CareSiteType, CareSiteName, AddressComposite, AddressStreet1,
			AddressStreet2,	City,	State,	ZipCode,	MainSitePhone,	Latitude,	Longitude,
			Created_by,	Created_Date)
select 
distinct Staging.CareSiteGuid,
Staging.CareSiteType,
Staging.CareSiteName,
Staging.AddressComposite,
Staging.AddressStreet1,
Staging.AddressStreet2,
Staging.AddressCity,
Staging.AddressState,
Staging.AddressZip,
Staging.OfficePhone,
Staging.Latitude,
Staging.Longitude,
'PIE',
getdate()
from APP.dev_ProviderService_stg_V Staging
Left outer join App.dev_CareSite_V CareSite on (Staging.CareSiteGuid=CareSite.CareSiteGuid)
Where CareSite.CareSiteGuid is null
	
Update CareSite set
CareSite.CareSiteGuid=Staging.CareSiteGuid,
CareSite.CareSiteType=Staging.CareSiteType,
CareSite.CareSiteName=Staging.CareSiteName,
CareSite.AddressComposite=Staging.AddressComposite,
CareSite.AddressStreet1=Staging.AddressStreet1,
CareSite.AddressStreet2=Staging.AddressStreet2,
CareSite.City=Staging.AddressCity,
CareSite.State=Staging.AddressState,
CareSite.ZipCode=Staging.AddressZip,
CareSite.MainSitePhone=Staging.OfficePhone,
CareSite.Latitude=Staging.Latitude,
CareSite.Longitude=Staging.Longitude,
CareSite.Modified_By='PIE',
CareSite.Modified_Date=getdate()
from APP.dev_ProviderService_stg_V Staging
inner join App.dev_CareSite_V CareSite on (Staging.CareSiteGuid=CareSite.CareSiteGuid)

Update Staging Set
Staging.CareSite_Id_Fk=CareSite.CareSite_Id
from APP.dev_ProviderService_stg_V Staging
inner join App.dev_CareSite_V CareSite on (Staging.CareSiteGuid=CareSite.CareSiteGuid)

Insert into App.dev_ProviderServiceCareSite_V (NonVAProvider_Id_FK,CareSite_Id_FK,SpecialityCode,IsPrimaryTaxonomy, ProviderServiceStatus,
ProviderServiceStatusReason,ProviderNetwork, PPMSModifiedOn_Date,InactiveDate, InactiveFlag, Created_by, Created_Date)
Select
Staging.NVAProvider_Id_FK,
Staging.CareSite_Id_FK,
Staging.SpecialtyCode,
Staging.IsPrimaryTaxonomy, 
Staging.ProviderServiceStatus,
Staging.ProviderServiceStatusReason,
Staging.ProviderNetwork, 
Staging.PPMSModifiedOnDate,
Case When Staging.ProviderServiceStatusReason='Inactive' Then staging.PPMSModifiedOnDate Else Null END,
Case When Staging.ProviderServiceStatusReason='Inactive' Then 1 Else 0 END,
'PIE',
 getdate()
From APP.dev_ProviderService_stg_V Staging
Left outer join App.dev_ProviderServiceCareSite_V PS 
	on ( Staging.NvaProvider_Id_fk= PS.NonVAProvider_Id_FK and 	Staging.CareSite_Id_Fk=PS.CareSite_Id_Fk and
		 Staging.SpecialtyCode=PS.SpecialityCode and Staging.ProviderNetwork=PS.ProviderNetwork)
where PS.NonVAProvider_Id_FK is null and 	PS.CareSite_Id_Fk is null and PS.SpecialityCode is null  and PS.ProviderNetwork is null

Update PS Set
PS.NonVAProvider_Id_FK=Staging.NvaProvider_Id_fk,
PS.CareSite_Id_FK=Staging.CareSite_Id_Fk,
PS.SpecialityCode=Staging.SpecialtyCode,
PS.IsPrimaryTaxonomy=Staging.IsPrimaryTaxonomy, 
PS.ProviderServiceStatus=Staging.ProviderServiceStatus,
PS.ProviderServiceStatusReason=Staging.ProviderServiceStatusReason,
PS.ProviderNetwork=Staging.ProviderNetwork, 
PS.PPMSModifiedOn_Date=Staging.PPMSModifiedOnDate,
PS.InactiveDate = Case When Staging.ProviderServiceStatusReason='Inactive' Then staging.PPMSModifiedOnDate Else Null END,
PS.InactiveFlag=Case When Staging.ProviderServiceStatusReason='Inactive' Then 1 Else 0 END,
PS.Modified_By='PIE',
PS.Modified_Date=getdate()
From App.dev_ProviderServiceCareSite_V PS
inner join  APP.dev_ProviderService_stg_V Staging  
	on ( Staging.NvaProvider_Id_fk= PS.NonVAProvider_Id_FK and 	Staging.CareSite_Id_Fk=PS.CareSite_Id_Fk and
		 Staging.SpecialtyCode=PS.SpecialityCode and Staging.ProviderNetwork=PS.ProviderNetwork)

GO
--
--
CREATE Procedure [App].[dev_ProcessProviderVistaData] AS
	
Insert  into APP.dev_NonVAProvider_V (ProviderNpi,ProviderType,ProviderFirstName,ProviderMiddleName,
ProviderLastName,MainPhone,Fax,PPMSModifiedOn_Date,ProviderStatus,ProviderStatusReason,Gender,InactiveDate, InactiveFlag, IsProcessed ,
Created_by,	Created_Date) 
Select distinct
Staging.ProviderNpi,
Staging.ProviderType,
Staging.ProviderFirstName,
Staging.ProviderMiddleName,
Staging.ProviderLastName,
Staging.MainPhone,
Staging.Fax,
Staging.PPMSModifiedOn_Date,
Staging.ProviderStatus,
Staging.ProviderStatusReason,
Staging.Gender, 
Case When Staging.ProviderStatusReason='Inactive' Then staging.PPMSModifiedOn_Date Else Null END,
Case When Staging.ProviderStatusReason='Inactive' Then 1 Else 0 END,
0,
'PIE',
getdate()
from APP.dev_NonVAProvider_stg_V Staging
left outer join App.dev_NonVAProvider_V NVA on (Staging.ProviderNpi=NVA.ProviderNpi)
Where NVA.ProviderNpi is null

Update NVA Set
NVA.ProviderNpi=Staging.ProviderNpi,
NVA.ProviderType=Staging.ProviderType,
NVA.ProviderFirstName=Staging.ProviderFirstName,
NVA.ProviderMiddleName=Staging.ProviderMiddleName,
NVA.ProviderLastName=Staging.ProviderLastName,
NVA.MainPhone=Staging.MainPhone,
NVA.Fax=Staging.Fax,
NVA.ProviderStatus=Staging.ProviderStatus,
NVA.ProviderStatusReason=Staging.ProviderStatusReason,
NVA.Gender=Staging.Gender, 
NVA.InactiveDate = Case When Staging.ProviderStatusReason='Inactive' Then staging.PPMSModifiedOn_Date Else Null END,
InactiveFlag=Case When Staging.ProviderStatusReason='Inactive' Then 1 Else 0 END,
isProcessed=0,
NVA.Modified_By='PIE',
NVA.Modified_Date=getdate()
from APP.dev_NonVAProvider_stg_V Staging
inner join App.dev_NonVAProvider_V NVA on (Staging.ProviderNpi=NVA.ProviderNpi)

GO

