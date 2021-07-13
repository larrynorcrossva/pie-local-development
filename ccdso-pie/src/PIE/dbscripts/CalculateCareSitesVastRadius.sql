USE [CC_PIE]
GO

/****** Object:  StoredProcedure [App].[Dev_CalculateCareSitesVastRadius]    Script Date: 7/3/2019 10:41:41 AM ******/
DROP PROCEDURE [App].[Dev_CalculateCareSitesVastRadius]
GO

/****** Object:  StoredProcedure [App].[Dev_CalculateCareSitesVastRadius]    Script Date: 7/3/2019 10:41:41 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROC [App].[Dev_CalculateCareSitesVastRadius]
As

if OBJECT_ID('tempdb..#TMP_VAST_DIST') is NOT NULL
	Drop table #TMP_VAST_DIST;

/*****Set isProcessed in caresite stations based on provider services isprocessed value.***************/
update App.dev_CareSiteStations_V set isProcessed=PCS.isProcessed
From APP.dev_ProviderServiceCareSite_V PCS
Inner join APP.dev_CareSite_V CS on (PCS.CareSite_Id_FK=CS.CareSite_Id)
Inner join App.dev_CareSiteStations_V CSS on (CSS.CareSite_Id_FK=CS.CareSite_Id)
Where Pcs.isProcessed=0

/***** Calculate Max distance between a VAMC and a CBOC***************/
Select 
VAMC.stationno Vast_Station,
VAMC.lat Vast_Lat, 
VAMC.lon Vast_Lon, 
VAMC.visn Visn,
(Max(GEOGRAPHY::Point(VAMC.lat , VAMC.Lon, 4326).STDistance(GEOGRAPHY::Point(CBOC.lat, CBOC.Lon, 4326))/ 1609.344) ) Max_Distance
into #TMP_VAST_DIST
From APP.VAST_SITES_V VAMC 
inner join APP.VAST_SITES_V CBOC  on (VAMC.stationno=CBOC.Par_sta_no)   
where len(VAMC.stationno)=3 and VAMC.lat is not null and VAMC.lon is not null and CBOC.lat is not null and CBOC.lon is not null
Group by VAMC.stationno , VAMC.lat , VAMC.lon ,VAMC.visn 
order by VAMC.stationno;

/********* Insert Provider Stations for the care site that fall in VAMC And Cboc radius)  *************/
Insert into APP.dev_CareSiteStations_V( StationNumber, VISN,Created_By, Created_Date, CareSite_Id_FK)
Select distinct
T.Vast_Station,
T.Visn,
'System', 
getDate(),
C.CareSite_Id
From App.dev_CareSite_V  C 
left outer join APP.dev_CareSiteStations_V PS on (C.CareSite_Id=PS.CareSite_Id_FK)
Cross join #TMP_VAST_DIST T
where PS.CareSite_Id_FK is null 
And  (GEOGRAPHY::Point(C.Latitude , C.Longitude, 4326).STDistance(GEOGRAPHY::Point(T.Vast_lat, T.Vast_lon, 4326))/ 1609.344)  <= (T.Max_Distance+100);

GO


