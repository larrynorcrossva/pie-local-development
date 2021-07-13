/****** Script for SelectTopNRows command from SSMS  ******/
--
-- 12/18/2018
-- Patch to accomodate the Conversation Number
-- Although this is for a one-time process,
-- this column will be persisted in the CC_PIE structures

alter table [CC_PIE].[dev_pie].[PPMS_OutboundMsg] add [Conversation_Number] varchar(50);
GO
--
alter table [CC_PIE].[nprod_pie].[PPMS_OutboundMsg] add [Conversation_Number] varchar(50);
GO
--
alter table [CC_PIE].[pie].[PPMS_OutboundMsg] add [Conversation_Number] varchar(50);
GO
--
alter table [CC_PIE].[pie].[PPMS_OutboundMsg_H] add [Conversation_Number] varchar(50);
GO
--
-- Adding Conversation_Number field to the ProviderResponse table
--
alter table [dev_pie].[PPMS_ProviderResponse] add [Conversation_Number] varchar(50);
GO
--
alter table [nprod_pie].[PPMS_ProviderResponse] add [Conversation_Number] varchar(50);
GO
--
alter table [CC_PIE].[pie].[PPMS_ProviderResponse] add [Conversation_Number] varchar(50);
GO
--

--
DROP TRIGGER [pie].[TR_PPMS_OutboundMsg_UPD_H]
GO
DROP TRIGGER [pie].[TR_PPMS_OutboundMsg_DEL_H]
GO
--
CREATE TRIGGER [pie].[TR_PPMS_OutboundMsg_UPD_H] ON [pie].[PPMS_OutboundMsg]
WITH EXEC AS CALLER
AFTER UPDATE
AS
BEGIN
INSERT INTO [pie].[PPMS_OutboundMsg_H]
    ([TranCode]
      ,[PPMS_OutboundMsg_Id]
      ,[Provider_Id_FK]
      ,[Outbound_Status_FK]
      ,[Transaction_Number]
      ,[Conversation_Number]
      ,[Transaction_Count]
      ,[Transaction_Date]
      ,[Created_By]
      ,[Created_Date]
      ,[Modified_By]
      ,[Modified_Date]
)
SELECT 'U'
,[PPMS_OutboundMsg_Id]
      ,[Provider_Id_FK]
      ,[Outbound_Status_FK]
      ,[Transaction_Number]
      ,[Conversation_Number]
      ,[Transaction_Count]
      ,[Transaction_Date]
      ,'DBA'
      ,getdate()
      ,'DBA'
      ,getdate()
  FROM deleted
END
GO
--
CREATE TRIGGER [pie].[TR_PPMS_OutboundMsg_DEL_H] ON [pie].[PPMS_OutboundMsg]
WITH EXEC AS CALLER
AFTER DELETE
AS
BEGIN
INSERT INTO [pie].[PPMS_OutboundMsg_H]
    ([TranCode]
      ,[PPMS_OutboundMsg_Id]
      ,[Provider_Id_FK]
      ,[Outbound_Status_FK]
      ,[Transaction_Number]
      ,[Conversation_Number]
      ,[Transaction_Count]
      ,[Transaction_Date]
      ,[Created_By]
      ,[Created_Date]
      ,[Modified_By]
      ,[Modified_Date] )
SELECT 'D'
      ,[PPMS_OutboundMsg_Id]
      ,[Provider_Id_FK]
      ,[Outbound_Status_FK]
      ,[Transaction_Number]
      ,[Conversation_Number]
      ,[Transaction_Count]
      ,[Transaction_Date]
      ,[Created_By]
      ,[Created_Date]
      ,[Modified_By]
      ,[Modified_Date]
  FROM deleted
END
GO
--

