USE [CC_PIE]
GO

/****** Object:  View [App].[UpdatedVistaProviders]    Script Date: 9/12/2019 2:48:38 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO













CREATE View [App].[UpdatedVistaProviders] As

Select distinct 
-- 0 							1 											2 									3
 NVA.NonVaprovider_id, NVA.ProviderNpi, SUBSTRING(NVA.ProviderFirstName,1,35) ProviderFirstName, SUBSTRING(NVA.ProviderLastName,1,35) ProviderLastName,  
-- 								4 								5 				6
 SUBSTRING(NVA.ProviderMiddleName,1,35) ProviderMiddleName, PS.StationNumber, PDS.DeaNumber, 
--		 7 					8 									9          								10 							11						12
 NVA.MainPhone, PCS.SpecialityCode, SUBSTRING(CS.AddressStreet1,1,35) AddressStreet1, SUBSTRING(CS.City,1,35) City, SUBSTRING(CS.State,1,35) State, SUBSTRING(CS.ZipCode,1,35) ZipCode, 
--			 13 			14 						15 				16 							17
 PDS.HasScheduleII,PDS.HasScheduleIII,PDS.HasScheduleIV, PDS.HasScheduleIINonNarcotic,PDS.HasScheduleIIINonNarcotic, 
--			18 					19 					                       20 						21 			        	22 		23        		 24			
 PDS.HasScheduleV , convert(varchar, PDS.ExpirationDate, 112)  as expirationDate, POI.OtherIdentifierName, PCS.CareSite_Id_FK, CS.MainSitePhone, NVA.Gender ,
-- 			24 					25														26							27							28
 PCS.InactiveFlag, convert(varchar, PCS.InactiveDate , 112)  as inactiveDate , PS.CareSiteStations_Id,  PCS.ProviderServiceCareSite_Id ,'' as VistA_OutResponse_Id,
 --        29  
 NVA.IsRetrieved,

 --			30									31														32
 PDS.InactiveFlag as DeaInactiveFlag, convert(varchar, PDS.InactiveDate , 112) as DeaInactiveDate, PDS.DeaStatusReason
  

 From APP.NonVAProvider_V NVA  
Inner join APP.ProviderServiceCareSite_V PCS on (NVA.NonVaprovider_id=PCS.NonVaprovider_id_FK) 
Inner join APP.CareSite_V CS  on (PCS.CareSite_Id_FK =CS.CareSite_Id) 
Inner join APP.CareSiteStations_V PS on ( CS.CareSite_Id = PS.CareSite_Id_FK)  
Inner join APP.VistA_OutResponse_V VOR on ( NVA.NonVAProvider_Id=VOR.NonVAProvider_Id_FK) 
Left outer join APP.ProviderOtherIdentifier_V POI on (NVA.NonVaprovider_id=POI.NonVAProvider_Id_FK) 
Left outer join APP.ProviderDEASchedulePrivilege_V PDS on (NVA.NonVAProvider_Id=PDS.NonVAProvider_Id_FK) 
Where  NVA.providertype='Individual'   and VOR.ActionCode!='Delete' and NVA.IsRetrieved=0
and VOR.VistaResponseCode = 'AA' 
and StationNumber = '999'
--and NVA.NonVAProvider_Id=55997073
--and PS.StationNumber = 515   



GO


