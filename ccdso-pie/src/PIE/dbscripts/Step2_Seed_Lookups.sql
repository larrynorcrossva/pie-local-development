set identity_insert pie.Outbound_Status ON;
insert into pie.Outbound_Status (Id,Code,Description,Created_By,Created_Date,Modified_By,Modified_Date) values (1, '200', 'OK', 'DBA', getdate(), 'DBA', getdate());
insert into pie.Outbound_Status (Id,Code,Description,Created_By,Created_Date,Modified_By,Modified_Date) values (2, '201', 'Created', 'DBA', getdate(), 'DBA', getdate());
insert into pie.Outbound_Status (Id,Code,Description,Created_By,Created_Date,Modified_By,Modified_Date) values (3, '204', 'No Content', 'DBA', getdate(), 'DBA', getdate());
insert into pie.Outbound_Status (Id,Code,Description,Created_By,Created_Date,Modified_By,Modified_Date) values (4, '400', 'Bad Request', 'DBA', getdate(), 'DBA', getdate());
insert into pie.Outbound_Status (Id,Code,Description,Created_By,Created_Date,Modified_By,Modified_Date) values (5, '401', 'Unauthorized', 'DBA', getdate(), 'DBA', getdate());
insert into pie.Outbound_Status (Id,Code,Description,Created_By,Created_Date,Modified_By,Modified_Date) values (6, '403', 'Forbidden', 'DBA', getdate(), 'DBA', getdate());
insert into pie.Outbound_Status (Id,Code,Description,Created_By,Created_Date,Modified_By,Modified_Date) values (7, '404', 'Not Found', 'DBA', getdate(), 'DBA', getdate());
insert into pie.Outbound_Status (Id,Code,Description,Created_By,Created_Date,Modified_By,Modified_Date) values (8, '409', 'Conflict', 'DBA', getdate(), 'DBA', getdate());
insert into pie.Outbound_Status (Id,Code,Description,Created_By,Created_Date,Modified_By,Modified_Date) values (9, '500', 'Internal Server Error', 'DBA', getdate(), 'DBA', getdate());
insert into pie.Outbound_Status (Id,Code,Description,Created_By,Created_Date,Modified_By,Modified_Date) values (10,'503', 'Resource Not Found','DBA',GETDATE(), 'DBA',GETDATE());
set identity_insert pie.Outbound_Status OFF;

--
/*
set identity_insert pie.ProviderResponse_Status ON;
insert into pie.ProviderResponse_Status (Id,Code,Description,Created_By,Created_Date,Modified_By,Modified_Date) values (1, '100', 'Success', 'DBA', getdate(), 'DBA', getdate());
insert into pie.ProviderResponse_Status (Id,Code,Description,Created_By,Created_Date,Modified_By,Modified_Date) values (2, '101', 'Address Error', 'DBA', getdate(), 'DBA', getdate());
insert into pie.ProviderResponse_Status (Id,Code,Description,Created_By,Created_Date,Modified_By,Modified_Date) values (3, '102', 'NPI Error', 'DBA', getdate(), 'DBA', getdate());
insert into pie.ProviderResponse_Status (Id,Code,Description,Created_By,Created_Date,Modified_By,Modified_Date) values (4, '103', 'DEA Error', 'DBA', getdate(), 'DBA', getdate());
insert into pie.ProviderResponse_Status (Id,Code,Description,Created_By,Created_Date,Modified_By,Modified_Date) values (5, '104', 'LEIE Error', 'DBA', getdate(), 'DBA', getdate());
set identity_insert pie.ProviderResponse_Status OFF;
*/

