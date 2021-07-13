--  new view in order to have a simple SQL statement in the code
-- This will return the earliest single provider record for a given StaffSID
drop view App.FirstProviderRecord_V;
GO

create view app.FirstProviderRecord_V as
with x as (SELECT id, staffsid
       ,row_number() OVER (PARTITION BY staffsid ORDER BY id) rn
FROM pie.providers 
where isProcessed = 0 and StaffIEN is not null)
(select p.*
from pie.providers p 
join x on x.id = p.id
where p.isProcessed = 0
and p.StaffIEN is not null
and x.rn = 1
);
GO
--
create view App.Providers_V as select * from pie.Providers;
GO

create view App.PPMS_OutboundMsg_V as select * from pie.PPMS_OutboundMsg;
GO

create view App.PPMS_ProviderResponse_V as select * from pie.PPMS_ProviderResponse;
GO

create view App.PPMS_ProviderResponseDetail_V as select * from pie.PPMS_ProviderResponseDetail;
GO

create view App.Outbound_Status_V as select * from pie.Outbound_Status;
GO

--
-- ***** For DEV only: -- ***** 
--
drop view App.FirstProviderRecord_V;
GO

create view App.FirstProviderRecord_V as
with x as (SELECT id, staffsid
       ,row_number() OVER (
PARTITION BY staffsid ORDER BY id) rn
FROM pie.providers
where isProcessed = 0
and p.StaffIEN is not null)
(select top 100 p.[Id], p.[StaffSID], p.[StaffIEN], p.[NPI], p.[DEA]
	,substring (p.lastname, len(p.lastname)-3,3)+','+substring (p.firstname, len(p.lastname)-3,3)+' '+p.MiddleName as StaffName
	,substring (p.lastname, len(p.lastname)-3,3) as LastName
	,substring (p.firstname, len(p.lastname)-3,3) as FirstName
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

-- Temporary Test --
drop view App.FirstProviderRecord_V;
GO

create view App.FirstProviderRecord_V as
with x as (SELECT id, staffsid
       ,row_number() OVER (
PARTITION BY staffsid ORDER BY id) rn
FROM pie.providers
where IsProcessed = 0
and p.StaffIEN is not null)
(select p.[Id], p.[StaffSID], p.[StaffIEN], p.[NPI], p.[DEA]
	,substring (p.lastname, len(p.lastname)-3,3)+','+substring (p.firstname, len(p.lastname)-3,3)+' '+p.MiddleName as StaffName
	,substring (p.lastname, len(p.lastname)-3,3) as LastName
	,substring (p.firstname, len(p.lastname)-3,3) as FirstName
	, p.[MiddleName]
	, p.[OfficePhone], p.[FaxNumber], p.[EmailAddress], p.[Gender], p.[TerminationDate], p.[X12Code]
	, p.[Sta3n], p.[Sta6a], p.[VISN]
	, p.[Region], p.[StreetAddress1], p.[StreetAddress2], p.[City], p.[State], p.[Zip], p.[IsProcessed]
	, p.[PPMS_CorrelationId], p.[Created_By], p.[Created_Date], p.[Modified_By], p.[Modified_Date]
from pie.providers p 
join x on x.id = p.id
where p.StaffIEN is not null
and p.staffsid = (32422)
and x.rn = 1);
GO




