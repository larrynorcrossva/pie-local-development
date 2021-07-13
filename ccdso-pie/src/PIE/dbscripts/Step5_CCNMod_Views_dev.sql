USE [CC_PIE]
GO

/****** Object:  View [App].[dev_NewVistaProviders]    Script Date: 5/17/2019 8:11:28 AM ******/
DROP VIEW [App].[dev_NewVistaProviders]
GO

/****** Object:  View [App].[dev_NewVistaProviders]    Script Date: 5/17/2019 8:11:28 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO



CREATE View [App].[dev_NewVistaProviders] As

Select distinct 
-- 0 							1 					2 					3
 NVA.NonVaprovider_id, NVA.ProviderNpi, NVA.ProviderFirstName, NVA.ProviderLastName,  
-- 			4 					5 				6
 NVA.ProviderMiddleName,PS.StationNumber, PDS.DeaNumber, 
--		 7 					8 				9          		10 		11			 12
 NVA.MainPhone, PCS.SpecialityCode, CS.AddressStreet1, CS.City, CS.State, CS.ZipCode, 
--			 13 			14 						15 				16 							17
 PDS.HasScheduleII,PDS.HasScheduleIII,PDS.HasScheduleIV, PDS.HasScheduleIINonNarcotic,PDS.HasScheduleIIINonNarcotic, 
--			18 					19 					                       20 						21 			        	22 		23        		 24			
 PDS.HasScheduleV , convert(varchar, PDS.ExpirationDate, 112)  as expirationDate, POI.OtherIdentifierName, PCS.CareSite_Id_FK, CS.MainSitePhone, NVA.Gender ,
-- 			24 					25														26							27
 PCS.InactiveFlag, convert(varchar, PCS.InactiveDate , 112)  as inactiveDate , PS.CareSiteStations_Id,  PCS.ProviderServiceCareSite_Id 			
 From APP.dev_NonVAProvider_V NVA  
 Inner join APP.dev_ProviderServiceCareSite_V PCS on (NVA.NonVaprovider_id=PCS.NonVaprovider_id_FK)  
 Inner join APP.dev_CareSite_V CS  on (PCS.CareSite_Id_FK =CS.CareSite_Id)  
 Inner join APP.dev_CareSiteStations_V PS on ( CS.CareSite_Id = PS.CareSite_Id_FK)  
 Left outer join APP.dev_ProviderOtherIdentifier_V POI on (NVA.NonVaprovider_id=POI.NonVAProvider_Id_FK)  
 Left outer join APP.dev_VistA_OutResponse_V VOR on (VOR.CareSiteStations_Id_Fk=PS.CareSiteStations_Id)  
 Left outer join APP.dev_ProviderMedicalEducation_V PME on (NVA.NonVAProvider_Id=PME.NonVAProvider_Id_FK)  
 Left outer join APP.dev_ProviderDEASchedulePrivilege_V PDS on (NVA.NonVAProvider_Id=PDS.NonVAProvider_Id_FK)  
 Where NVA.IsProcessed=0 and PCS.IsProcessed=0 and  NVA.providertype='1'  and (VOR.NonVAProvider_Id_FK is null or VOR.ActionCode='delete') 
 and   NVA.NonVAProvider_Id=55511100

GO

/****** Object:  View [App].[dev_UpdatedVistaProviders]    Script Date: 5/17/2019 8:32:12 AM ******/
DROP VIEW [App].[dev_UpdatedVistaProviders]
GO

/****** Object:  View [App].[dev_UpdatedVistaProviders]    Script Date: 5/17/2019 8:32:12 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE View [App].[dev_UpdatedVistaProviders] As

Select distinct 
-- 0 							1 					2 					3
 NVA.NonVaprovider_id, NVA.ProviderNpi, NVA.ProviderFirstName, NVA.ProviderLastName,  
-- 			4 					5 				6
 NVA.ProviderMiddleName,PS.StationNumber, PDS.DeaNumber, 
--		 7 					8 				9          		10 		11			 12
 NVA.MainPhone, PCS.SpecialityCode, CS.AddressStreet1, CS.City, CS.State, CS.ZipCode, 
--			 13 			14 						15 				16 							17
 PDS.HasScheduleII,PDS.HasScheduleIII,PDS.HasScheduleIV, PDS.HasScheduleIINonNarcotic,PDS.HasScheduleIIINonNarcotic, 
--			18 					19 					                       20 						21 			        	22 		23        		 24			
 PDS.HasScheduleV , convert(varchar, PDS.ExpirationDate, 112)  as expirationDate, POI.OtherIdentifierName, PCS.CareSite_Id_FK, CS.MainSitePhone, NVA.Gender ,
-- 			24 					25														26							27
 PCS.InactiveFlag, convert(varchar, PCS.InactiveDate , 112)  as inactiveDate , PS.CareSiteStations_Id,  PCS.ProviderServiceCareSite_Id 			
 From APP.dev_NonVAProvider_V NVA  
Inner join APP.dev_ProviderServiceCareSite_V PCS on (NVA.NonVaprovider_id=PCS.NonVaprovider_id_FK) 
Inner join APP.dev_CareSite_V CS  on (PCS.CareSite_Id_FK =CS.CareSite_Id) 
Inner join APP.dev_CareSiteStations_V PS on ( CS.CareSite_Id = PS.CareSite_Id_FK)  
Inner join APP.dev_VistA_OutResponse_V VOR on ( VOR.CareSiteStations_Id_Fk=PS.CareSiteStations_Id) 
Left outer join APP.dev_ProviderOtherIdentifier_V POI on (NVA.NonVaprovider_id=POI.NonVAProvider_Id_FK) 
Left outer join APP.dev_ProviderDEASchedulePrivilege_V PDS on (NVA.NonVAProvider_Id=PDS.NonVAProvider_Id_FK) 
Where  NVA.IsProcessed=0 and PCS.IsProcessed=0  and  NVA.providertype='1'   and VOR.ActionCode!='Delete' 
and VOR.VistaResponseCode = 'AA'  and NVA.NonVAProvider_Id=55511100

		
GO




