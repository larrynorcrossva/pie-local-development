------------------------------------
-- Create schema for DEV (co-existing with Prod!)
-- authorization is uCC_PIE
------------------------------------
use [cc_pie]
GO

CREATE SCHEMA [dev_pie] AUTHORIZATION [uCC_PIE]
GO
------------------------------------
-- Create the PIE tables
------------------------------------
set ansi_nulls on
GO
set quoted_Identifier on
GO
set ansi_padding on
GO

create table [dev_pie].[Providers](
	[Id] [int] identity (1,1) not null,
	[StaffSID] [int] not null,
	[StaffIEN] [varchar](50) not null,
	[NPI] [varchar](50) null,
	[DEA] [varchar](50) null,
	[StaffName] [varchar](100) null,
	[LastName] [varchar](50) null,
	[FirstName] [varchar](50) null,
	[MiddleName] [varchar](50) null,
	[OfficePhone] [varchar](50) null,
	[FaxNumber] [varchar](50) null,
	[EmailAddress] [varchar](100) null,
	[Gender] [varchar](50) null,
	[TerminationDate] [date] null,
	[X12Code] [varchar](50) null,
	[Sta3n] [smallint] null,
	[Sta6a] [varchar](50) null,
	[VISN] [smallint] null,
	[Region] [smallint] null,
	[StreetAddress1] [varchar](100) null,
	[StreetAddress2] [varchar](100) null,
	[City] [varchar](50) null,
	[State] [varchar](50) null,
	[Zip] [varchar](50) null,
	[IsProcessed] [bit] not null constraint [df_Providers_IsProcessed]  default (0),
	[PPMS_CorrelationId] [varchar](50) null,
	[Created_By] varchar(30) not null constraint [df_Providers_Created_By]  default ('DBA'),
	[Created_Date] datetime not null constraint [df_Providers_Created_Date]  default (getdate()),
	[Modified_By] varchar(30) not null constraint [df_Providers_Modified_By]  default ('DBA'),
	[Modified_Date] datetime not null constraint [df_Providers_Modified_Date]  default (getdate())
	constraint [pk_Providers] primary key([Id]) with (fillfactor=80) on [Deffg]
) on [Deffg]

GO
--
create table [dev_pie].[PPMS_OutboundMsg](
	[PPMS_OutboundMsg_Id] [int] identity(1,1) not null,
	[Provider_Id_FK] [int] not null,
	[Outbound_Status_FK] [int] not null,
	[Transaction_Number] [varchar] (50) not null,
	[Transaction_Count] [int] not null constraint [df_Outbound_TranCount] default (0),
	[Transaction_Date] [datetime] null,
	[Created_By] [varchar](30) null,
	[Created_Date] [datetime]  null,
	[Modified_By] [varchar](30) null,
	[Modified_Date] [datetime] null,
 constraint [xPKPPMS_OutboundMsg] primary key clustered 
(
	[PPMS_OutboundMsg_Id] asc
)with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];
--
create table [dev_pie].[PPMS_ProviderResponse](
	[PPMS_ProviderResponse_Id] [int] identity(1,1) not null,
	[IsFail] [bit] not null constraint [df_PPMS_ProviderResponse_IsFail]  default (0),
	[Provider_Id_FK] [int] not null,
	[Created_By] [varchar](30) null,
	[Created_Date] [datetime]  null,
	[Modified_By] [varchar](30) null,
	[Modified_Date] [datetime] null,
 constraint [xPKPPMS_ProviderResponse] primary key clustered 
(
	[PPMS_ProviderResponse_Id] asc
)with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];
--
create table [dev_pie].[PPMS_ProviderResponseDetail](
	[PPMS_ProviderResponseDetail_Id] [int] identity(1,1) not null,
	[PPMS_ProviderResponse_Id_FK] [int] not null,
	[Detail_CorrelationId] [varchar](50) null,
	[IsFail] [bit] not null constraint [df_PPMS_ProviderResponseDetail_IsFail]  default (0),
	[Response_Id_Text] [varchar](50) null,
	[Response_Header_Text] [varchar](500) null,
	[Response_Message_Text] [varchar](500) null,
	[Response_Type_Text] [varchar](200) null,
	[Created_By] [varchar](30) null,
	[Created_Date] [datetime]  null,
	[Modified_By] [varchar](30) null,
	[Modified_Date] [datetime] null
 constraint [xPKPPMS_ProviderResponseDetail] primary key clustered 
(
	[PPMS_ProviderResponseDetail_Id] asc
)with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];
--
create table [dev_pie].[Outbound_Status](
	[Id] [int] identity(1,1) not null,
	[Code] [varchar](80) null,
	[Description] [varchar](250) null,
	[Created_By] [varchar](30) not null,
	[Created_Date] [datetime] not null,
	[Modified_By] [varchar](30) not null,
	[Modified_Date] [datetime] not null,
 constraint [xPKOutbound_Status] primary key clustered 
(
	[Id] asc
)with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg]
GO
--
SET ANSI_PADDING ON
GO
---------------------
-- Constraints
---------------------
ALTER TABLE [dev_pie].[PPMS_OutboundMsg]  WITH CHECK ADD CONSTRAINT [FK_PPMS_OutboundMsg_PROVIDER] FOREIGN KEY
([PROVIDER_ID_FK]) REFERENCES [dev_pie].[Providers] ([Id])
GO
ALTER TABLE [dev_pie].[PPMS_OutboundMsg] CHECK CONSTRAINT [FK_PPMS_OutboundMsg_PROVIDER]
GO
--
ALTER TABLE [dev_pie].[PPMS_OutboundMsg]  WITH CHECK ADD CONSTRAINT [FK_PPMS_OutboundMsg_OUTBOUND_STATUS] FOREIGN KEY
([OUTBOUND_STATUS_FK]) REFERENCES [dev_pie].[OUTBOUND_STATUS] ([Id])
GO
ALTER TABLE [dev_pie].[PPMS_OutboundMsg] CHECK CONSTRAINT [FK_PPMS_OutboundMsg_OUTBOUND_STATUS]
GO
--
ALTER TABLE [dev_pie].[PPMS_ProviderResponseDetail]  WITH CHECK ADD CONSTRAINT [FK_PPMS_ProviderResponseDetail_PResponse] FOREIGN KEY
([PPMS_ProviderResponse_Id_FK]) REFERENCES [dev_pie].[PPMS_ProviderResponse] ([PPMS_ProviderResponse_Id])
GO
ALTER TABLE [dev_pie].[PPMS_ProviderResponseDetail] CHECK CONSTRAINT [FK_PPMS_ProviderResponseDetail_PResponse]
GO
--
ALTER TABLE [dev_pie].[PPMS_ProviderResponse]  WITH CHECK ADD CONSTRAINT [FK_PPMS_ProviderResponse_Provider] FOREIGN KEY
([Provider_Id_FK]) REFERENCES [dev_pie].[Providers] ([Id])
GO
ALTER TABLE [dev_pie].[PPMS_ProviderResponse] CHECK CONSTRAINT [FK_PPMS_ProviderResponse_Provider]
GO
---------------------
-- Regular Indexes - sorting
---------------------
CREATE INDEX [IX_PROVIDER_MODIFIED_DATE] ON [dev_pie].[PROVIDERS] ([MODIFIED_DATE])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [DefFG]
GO
--
CREATE INDEX [IX_PROVIDER_IsProcessed] ON [dev_pie].[PROVIDERS] ([IsProcessed])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [DefFG]
GO
---------------------
-- Create the Views
---------------------
drop view App.dev_FirstProviderRecord_V;
GO
--
create view App.dev_FirstProviderRecord_V as
with x as (SELECT id, staffsid
       ,row_number() OVER (
PARTITION BY staffsid ORDER BY id) rn
FROM dev_pie.providers
where isProcessed = 0)
(select top 200 p.[Id], p.[StaffSID], p.[StaffIEN], p.[NPI], p.[DEA]
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
create view App.dev_Providers_V as select * from dev_pie.Providers;
GO
--
create view App.dev_PPMS_OutboundMsg_V as select * from dev_pie.PPMS_OutboundMsg;
GO
--
create view App.dev_PPMS_ProviderResponse_V as select * from dev_pie.PPMS_ProviderResponse;
GO
--
create view App.dev_PPMS_ProviderResponseDetail_V as select * from dev_pie.PPMS_ProviderResponseDetail;
GO
--
create view App.dev_Outbound_Status_V as select * from dev_pie.Outbound_Status;
GO

/*
--
insert into [dev_pie].[Providers] select top 10000 
[StaffSID], [StaffIEN], [NPI], [DEA], [StaffName], [LastName], [FirstName], [MiddleName]
, [OfficePhone], [FaxNumber], [EmailAddress], [Gender], [TerminationDate], [X12Code]
, [Sta3n], [Sta6a], [VISN], [Region], [StreetAddress1], [StreetAddress2], [City], [State], [Zip]
, [IsProcessed], [PPMS_CorrelationId], [Created_By], [Created_Date], [Modified_By], [Modified_Date]
from [pie].[Providers]
--
select * from [dev_pie].[Providers]
--
ALTER TABLE [dev_pie].[PPMS_ProviderResponse] DROP CONSTRAINT [FK_PPMS_ProviderResponse_Provider]
GO
ALTER TABLE [dev_pie].[PPMS_OutboundMsg] DROP CONSTRAINT [FK_PPMS_OutboundMsg_PROVIDER]
GO
--
ALTER TABLE [dev_pie].[PPMS_ProviderResponse]  WITH CHECK ADD  CONSTRAINT [FK_PPMS_ProviderResponse_Provider] FOREIGN KEY([Provider_Id_FK])
REFERENCES [dev_pie].[Providers] ([Id])
GO
ALTER TABLE [dev_pie].[PPMS_ProviderResponse] CHECK CONSTRAINT [FK_PPMS_ProviderResponse_Provider]
GO
ALTER TABLE [dev_pie].[PPMS_OutboundMsg]  WITH CHECK ADD  CONSTRAINT [FK_PPMS_OutboundMsg_PROVIDER] FOREIGN KEY([Provider_Id_FK])
REFERENCES [dev_pie].[Providers] ([Id])
GO
ALTER TABLE [dev_pie].[PPMS_OutboundMsg] CHECK CONSTRAINT [FK_PPMS_OutboundMsg_PROVIDER]
GO
--
truncate table [dev_pie].[Providers]

*/
