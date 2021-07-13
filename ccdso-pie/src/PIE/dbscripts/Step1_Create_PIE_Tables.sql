--drop table pie.Providers;
--GO
--drop table pie.Providers_H;
--GO
--drop table pie.Providers_Stg;
--GO
--drop table pie.PPMS_OutboundMsg;
--GO
--drop table pie.PPMS_OutboundMsg_H;
--GO
--drop table pie.PPMS_ProviderResponse;
--GO
--drop table pie.PPMS_ProviderResponseDetail;
--GO
--drop table pie.Outbound_Status;
--GO

use [cc_pie]
GO

set ansi_nulls on
GO
set quoted_Identifier on
GO
set ansi_padding on
GO

create table [pie].[Providers](
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
create table [pie].[Providers_H](
	[Id_H] [int] identity (1,1) not null,
	[TranCode] [varchar] (1) not null,
	[Id] [int] not null,
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
	[IsProcessed] [bit],
	[PPMS_CorrelationId] [varchar](50) null,
	[Created_By] varchar(30) null,
	[Created_Date] datetime null,
	[Modified_By] varchar(30) null,
	[Modified_Date] datetime null,
	constraint [pk_Providers_H] primary key([Id_H]) with (fillfactor=80) on [Deffg]
) on [Deffg]

GO
--
create table [pie].[Providers_Stg](
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
	[Created_By] varchar(30) null,
	[Created_Date] datetime null,
	[Modified_By] varchar(30) null,
	[Modified_Date] datetime null
) on [Deffg]
GO
--
create table [pie].[PPMS_OutboundMsg](
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
create table [pie].[PPMS_OutboundMsg_H](
	[Id_H] [int] identity(1,1) not null,
	[TranCode] [varchar] (1) not null,
	[PPMS_OutboundMsg_Id] [int],
	[Provider_Id_FK] [int] not null,
	[Outbound_Status_FK] [int],
	[Transaction_Number] [varchar] (50) not null,
	[Transaction_Count] [int],
	[Transaction_Date] [datetime] null,
	[Created_By] [varchar](30),
	[Created_Date] [datetime],
	[Modified_By] [varchar](30),
	[Modified_Date] [datetime],
 constraint [xPKPPMS_OutboundMsg_H] primary key clustered 
(
	[Id_H] asc
)with (pad_index = off, statistics_norecompute = off, ignore_dup_key = off, allow_row_locks = on, allow_page_locks = on) on [Deffg]
) on [Deffg];
--
-- Store the overall response being returned at the StaffSID level
--
create table [pie].[PPMS_ProviderResponse](
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
-- Detail table to store the individual attribute results
-- For a given StaffSID, check to see which attribute(s) match
-- The Provider_Id is being stamped here so that we know the matching individual record in our Providers table
--
create table [pie].[PPMS_ProviderResponseDetail](
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
create table [pie].[Outbound_Status](
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
ALTER TABLE [pie].[PPMS_OutboundMsg]  WITH CHECK ADD CONSTRAINT [FK_PPMS_OutboundMsg_PROVIDER] FOREIGN KEY
([PROVIDER_ID_FK]) REFERENCES [pie].[Providers] ([Id])
GO
ALTER TABLE [pie].[PPMS_OutboundMsg] CHECK CONSTRAINT [FK_PPMS_OutboundMsg_PROVIDER]
GO
--
ALTER TABLE [pie].[PPMS_OutboundMsg]  WITH CHECK ADD CONSTRAINT [FK_PPMS_OutboundMsg_OUTBOUND_STATUS] FOREIGN KEY
([OUTBOUND_STATUS_FK]) REFERENCES [pie].[OUTBOUND_STATUS] ([Id])
GO
ALTER TABLE [pie].[PPMS_OutboundMsg] CHECK CONSTRAINT [FK_PPMS_OutboundMsg_OUTBOUND_STATUS]
GO
--
ALTER TABLE [pie].[PPMS_ProviderResponseDetail]  WITH CHECK ADD CONSTRAINT [FK_PPMS_ProviderResponseDetail_PResponse] FOREIGN KEY
([PPMS_ProviderResponse_Id_FK]) REFERENCES [pie].[PPMS_ProviderResponse] ([PPMS_ProviderResponse_Id])
GO
ALTER TABLE [pie].[PPMS_ProviderResponseDetail] CHECK CONSTRAINT [FK_PPMS_ProviderResponseDetail_PResponse]
GO
--
ALTER TABLE [pie].[PPMS_ProviderResponse]  WITH CHECK ADD CONSTRAINT [FK_PPMS_ProviderResponse_Provider] FOREIGN KEY
([Provider_Id_FK]) REFERENCES [pie].[Providers] ([Id])
GO
ALTER TABLE [pie].[PPMS_ProviderResponse] CHECK CONSTRAINT [FK_PPMS_ProviderResponse_Provider]
GO

---------------------
-- Regular Indexes - sorting
---------------------

CREATE INDEX [IX_PROVIDER_MODIFIED_DATE] ON [pie].[PROVIDERS] ([MODIFIED_DATE])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [DefFG]
GO

CREATE INDEX [IX_PROVIDER_IsProcessed] ON [pie].[PROVIDERS] ([IsProcessed])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [DefFG]
GO

---------------------
-- NC Indexes - no sorting
---------------------
/*
CREATE NONCLUSTERED INDEX [IXNC_PROVIDER_OfficePhone] ON [pie].[PROVIDERS] ([OfficePhone] ASC)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [DefFG]
GO
*/

