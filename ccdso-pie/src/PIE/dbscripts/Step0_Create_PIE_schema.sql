------------------------------------
-- Use fqdn to connect to CDW, since name servers can be down
-- 165 GB available. Will also be building an application enclave for our project
------------------------------------
-- vhaCDWA01.vha.med.va.gov
------------------------------------

------------------------------------
-- VHACDWDWHSQL52 - is the new CDW server!
------------------------------------

-- ETL account will be issued and will get SSIS 2014 account
-- Update - CDW will be setting up an enclave server in AITC with SQL Server 2016
-- Then we will get access to SSIS and new server.
-- ETL developer's guide, CDW Guide to incremental development
-- The SPV views get refreshed daily by around 2 a.m. - 3 a.m.
-- Table is called ExtractBatch - use ETLBatchId to get the latest batch
-- Our local copy of SStaff can be refreshed with incrementals by 6 a.m. - 7 a.m.
------------------------------------
-- Use SPV views for the extract 

------------------------------------
-- Create A PIE schema, CDW prefers unique schemas
-- authorization is uCC_PIE
------------------------------------

CREATE SCHEMA [pie] AUTHORIZATION [uCC_PIE]
GO

--

USE [CC_PIE]
GO

--
CREATE SCHEMA [app] AUTHORIZATION [uCC_PIE]
GO

------------------------------------
-- Initial Setup of PIE_SSTAFF
-- using "select into"
------------------------------------
/*
select top 10 * into pie.PIE_SSTAFF
from cdwwork.sstaff.sstaff
where providerclass is not null
GO
*/


/*
-- truncate table pie.PIE_SSTAFF
-- GO
*/


/*
 select count (*) from pie.PIE_SSTAFF
*/

------------------------------------
-- Populating PIE_SSTAFF
-- currently using ProviderClass - but prefer to use NPPES NPI? N/A - we are using MEDORDERSAUTHFLAG
-- use dim.VistaSite - to get the VISN
-- use dim.Station - wanted to default with address of station for NULL provider addresses
-- But a station can have many addresses. Where can station address be found? --
------------------------------------

INSERT INTO pie.PIE_SSTAFF
SELECT * 
from cdwwork.sstaff.sstaff
where providerclass is not null
GO
--
UPDATE pst
SET VISN = stn.VISNFY17
FROM pie.PIE_SSTAFF pst
LEFT JOIN CDWWork.Dim.Sta3n stn ON pst.sta3n = stn.sta3n
WHERE pst.VISN IS NULL
GO

/*
SELECT count (*) 
from cdwwork.sstaff.sstaff
where providerclass is not null
-- 4287416
*/


/*
SELECT count (*) 
from cdwwork.sstaff.sstaff
where NPI is not null
-- 3730834
*/


