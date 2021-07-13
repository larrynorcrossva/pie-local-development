-- add the field to the Provider tables
alter table [dev_pie].[Providers] add [MedOrdersAuthorizedFlag] varchar(1) null;
alter table [nprod_pie].[Providers] add [MedOrdersAuthorizedFlag] varchar(1) null;
alter table [pie].[Providers] add [MedOrdersAuthorizedFlag] varchar(1) null;
alter table [pie].[Providers_H] add [MedOrdersAuthorizedFlag] varchar(1) null;
alter table [pie].[Providers_Stg] add [MedOrdersAuthorizedFlag] varchar(1) null;
-- rebuild the views
drop view App.dev_Providers_V;
GO
create view App.dev_Providers_V as select * from dev_pie.Providers;
GO
--
drop view App.nprod_Providers_V;
GO
create view App.nprod_Providers_V as select * from nprod_pie.Providers;
GO
--
drop view App.Providers_V;
GO
create view App.Providers_V as select * from pie.Providers;
GO
--
drop view App.dev_FirstProviderRecord_V;
GO
--
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE view [App].[dev_FirstProviderRecord_V] as
with x as (SELECT id, staffsid ,row_number() OVER (PARTITION BY staffsid ORDER BY id) rn
FROM dev_pie.providers where isProcessed = 0 and MedOrdersAuthorizedFlag = 'Y')
(select top 2000 p.[Id], p.[StaffSID], p.[StaffIEN], p.[NPI], p.[DEA]
	,substring (p.lastname, len(p.lastname)-3,3)+','+substring (p.firstname, len(p.lastname)-3,3)+' '+p.MiddleName as StaffName
	,substring (p.lastname, len(p.lastname)-3,3) as LastName
	,substring (p.firstname, len(p.lastname)-3,3) as FirstName
	, p.[MiddleName]
	, p.[OfficePhone], p.[FaxNumber], p.[EmailAddress], p.[Gender], p.[TerminationDate], p.[X12Code]
	, p.[Sta3n], p.[Sta6a], p.[VISN]
	, p.[Region], p.[StreetAddress1], p.[StreetAddress2], p.[City], p.[State], p.[Zip], p.[IsProcessed]
	, p.[PPMS_CorrelationId], p.[Created_By], p.[Created_Date], p.[Modified_By], p.[Modified_Date]
from dev_pie.providers p 
join x on x.id = p.id
where p.StaffIEN is not null
and x.rn = 1
);
GO
--
drop view App.nprod_FirstProviderRecord_V;
GO
--
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE view [App].[nprod_FirstProviderRecord_V] as
with x as (SELECT id, staffsid
       ,row_number() OVER (
PARTITION BY staffsid ORDER BY id) rn
FROM nprod_pie.providers
where isProcessed = 0 and MedOrdersAuthorizedFlag = 'Y')
(select top 2000 p.[Id], p.[StaffSID], p.[StaffIEN], p.[NPI], p.[DEA]
	,p.lastname +','+ p.firstname+' '+p.MiddleName as StaffName
	,p.lastname  as LastName
	,p.firstname as FirstName
	, p.[MiddleName]
	, p.[OfficePhone], p.[FaxNumber], p.[EmailAddress], p.[Gender], p.[TerminationDate], p.[X12Code]
	, p.[Sta3n], p.[Sta6a], p.[VISN]
	, p.[Region], p.[StreetAddress1], p.[StreetAddress2], p.[City], p.[State], p.[Zip], p.[IsProcessed]
	, p.[PPMS_CorrelationId], p.[Created_By], p.[Created_Date], p.[Modified_By], p.[Modified_Date]
from nprod_pie.providers p 
join x on x.id = p.id
where p.StaffIEN is not null
and x.rn = 1
);
GO
--
drop view App.FirstProviderRecord_V;
GO
--
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE view [App].[FirstProviderRecord_V] as
with x as (SELECT id, NPI
       ,row_number() OVER (
PARTITION BY NPI ORDER BY id) rn
FROM pie.providers
where isProcessed = 0
--and MedOrdersAuthorizedFlag = 'Y'
)
(select top 2000 p.[Id], p.[StaffSID], p.[StaffIEN], p.[NPI], p.[DEA]
	,p.lastname +','+ p.firstname+' '+p.MiddleName as StaffName
	,p.lastname  as LastName
	,p.firstname as FirstName
	, p.[MiddleName]
	, p.[OfficePhone], p.[FaxNumber], p.[EmailAddress], p.[Gender], p.[TerminationDate], p.[X12Code]
	, p.[Sta3n], p.[Sta6a], p.[VISN]
	, p.[Region], p.[StreetAddress1], p.[StreetAddress2], p.[City], p.[State], p.[Zip], p.[IsProcessed]
	, p.[PPMS_CorrelationId], p.[Created_By], p.[Created_Date], p.[Modified_By], p.[Modified_Date]
from pie.providers p 
join x on x.id = p.id
where p.StaffIEN is not null
and x.rn = 1
);
GO
--
-- update the field
UPDATE pp
SET    pp.MedOrdersAuthorizedFlag = cdw.MedOrdersAuthorizedFlag
FROM   pie.Providers pp
       JOIN [CDWWork].[SStaff].[SStaff] cdw
         ON pp.StaffSID = cdw.StaffSID
		 where pp.MedOrdersAuthorizedFlag is NULL
--		 and pp.StaffSID = 926
GO
--
select top 100 * from dev_pie.Providers
		 where StaffSID = 926

-- modify the SSIS packages

