CREATE BIGFILE TABLESPACE MOCKDB DATAFILE 'mockdb.dat' SIZE 10M AUTOEXTEND ON;

CREATE USER MOCKDB IDENTIFIED BY Agilexadmin99$ DEFAULT TABLESPACE MOCKDB;
GRANT CONNECT, RESOURCE, DBA to MOCKDB;
GRANT CREATE SESSION TO MOCKDB;
GRANT UNLIMITED TABLESPACE TO MOCKDB;

--------------------------------------------------------
--  DDL for Table DATABASECHANGELOG
--------------------------------------------------------

  CREATE TABLE "MOCKDB"."DATABASECHANGELOG"
   (	"ID" VARCHAR2(63 BYTE),
	"AUTHOR" VARCHAR2(63 BYTE),
	"FILENAME" VARCHAR2(200 BYTE),
	"DATEEXECUTED" TIMESTAMP (6),
	"ORDEREXECUTED" NUMBER(*,0),
	"EXECTYPE" VARCHAR2(10 BYTE),
	"MD5SUM" VARCHAR2(35 BYTE),
	"DESCRIPTION" VARCHAR2(255 BYTE),
	"COMMENTS" VARCHAR2(255 BYTE),
	"TAG" VARCHAR2(255 BYTE),
	"LIQUIBASE" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "MOCKDB" ;
--------------------------------------------------------
--  DDL for Table DATABASECHANGELOGLOCK
--------------------------------------------------------

  CREATE TABLE "MOCKDB"."DATABASECHANGELOGLOCK"
   (	"ID" NUMBER(*,0),
	"LOCKED" NUMBER(1,0),
	"LOCKGRANTED" TIMESTAMP (6),
	"LOCKEDBY" VARCHAR2(255 BYTE)
   ) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "MOCKDB" ;
--------------------------------------------------------
--  DDL for Table MOCK_PATIENTS
--------------------------------------------------------

  CREATE TABLE "MOCKDB"."MOCK_PATIENTS"
   (	"MOCK_PAT_ID" NUMBER(19,0),
	"MOCK_FIRST_NAME" VARCHAR2(50 BYTE),
	"MOCK_LAST_NAME" VARCHAR2(50 BYTE),
	"ASSIGNING_AUTHORITY" VARCHAR2(20 BYTE),
	"UNIQUE_ID" VARCHAR2(20 BYTE),
	"MOCK_DOB" VARCHAR2(10 BYTE),
	"MOCK_GENDER" VARCHAR2(6 BYTE),
	"MOCK_SSN" VARCHAR2(10 BYTE),
	"MOCK_INPATIENT" NUMBER(1,0),
	"MOCK_ROOMBED" VARCHAR2(10 BYTE),
	"MOCK_WARDLOCATION" VARCHAR2(10 BYTE)
   ) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "MOCKDB" ;
--------------------------------------------------------
--  DDL for Table MOCK_USERS
--------------------------------------------------------

  CREATE TABLE "MOCKDB"."MOCK_USERS"
   (	"MOCK_USER_ID" NUMBER(19,0),
	"MOCK_ACCESS_CODE" VARCHAR2(50 BYTE), 
	"MOCK_DISPLAY_NAME" VARCHAR2(50 BYTE),
	"MOCK_FIRST_NAME" VARCHAR2(50 BYTE),
	"MOCK_LAST_NAME" VARCHAR2(50 BYTE),
	"ASSIGNING_AUTHORITY" VARCHAR2(20 BYTE),
	"UNIQUE_ID" VARCHAR2(20 BYTE),
	"MOCK_DOB" VARCHAR2(10 BYTE),
	"MOCK_GENDER" VARCHAR2(6 BYTE),
	"MOCK_SSN" VARCHAR2(10 BYTE),
	"MOCK_ADVOCATE_ID" NUMBER(19,0)
   ) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "MOCKDB" ;
--------------------------------------------------------
--  DDL for Table MOCK_USER_PAT_ID
--------------------------------------------------------

  CREATE TABLE "MOCKDB"."MOCK_USER_PAT_ID"
   (	"MOCK_USER_PAT_ID" NUMBER(19,0),
	"MOCK_USER_ID" NUMBER(19,0),
	"ASSIGNING_AUTHORITY" VARCHAR2(20 BYTE),
	"UNIQUE_ID" VARCHAR2(20 BYTE)
   )
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
 NOCOMPRESS LOGGING
  TABLESPACE "MOCKDB" ;
--------------------------------------------------------
--  DDL for Index MOCK_USERS_ACCESS_AK
--------------------------------------------------------

  CREATE UNIQUE INDEX "MOCKDB"."MOCK_USERS_ACCESS_AK" ON "MOCKDB"."MOCK_USERS" ("MOCK_ACCESS_CODE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "MOCKDB" ;
--------------------------------------------------------
--  DDL for Index PK_DATABASECHANGELOGLOCK
--------------------------------------------------------

  CREATE UNIQUE INDEX "MOCKDB"."PK_DATABASECHANGELOGLOCK" ON "MOCKDB"."DATABASECHANGELOGLOCK" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "MOCKDB" ;
--------------------------------------------------------
--  DDL for Index PK_DATABASECHANGELOG
--------------------------------------------------------

  CREATE UNIQUE INDEX "MOCKDB"."PK_DATABASECHANGELOG" ON "MOCKDB"."DATABASECHANGELOG" ("ID", "AUTHOR", "FILENAME") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "MOCKDB" ;
--------------------------------------------------------
--  DDL for Index MOCK_USER_PAT_ID_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "MOCKDB"."MOCK_USER_PAT_ID_PK" ON "MOCKDB"."MOCK_USER_PAT_ID" ("MOCK_USER_PAT_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  TABLESPACE "MOCKDB" ;
--------------------------------------------------------
--  DDL for Index MOCK_PATIENTS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "MOCKDB"."MOCK_PATIENTS_PK" ON "MOCKDB"."MOCK_PATIENTS" ("MOCK_PAT_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "MOCKDB" ;
--------------------------------------------------------
--  DDL for Index MOCK_USER_PAT_ID_AK
--------------------------------------------------------

  CREATE UNIQUE INDEX "MOCKDB"."MOCK_USER_PAT_ID_AK" ON "MOCKDB"."MOCK_USER_PAT_ID" ("MOCK_USER_ID", "ASSIGNING_AUTHORITY", "UNIQUE_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  TABLESPACE "MOCKDB" ;
--------------------------------------------------------
--  DDL for Index MOCK_USERS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "MOCKDB"."MOCK_USERS_PK" ON "MOCKDB"."MOCK_USERS" ("MOCK_USER_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "MOCKDB" ;
--------------------------------------------------------
--  Constraints for Table MOCK_USER_PAT_ID
--------------------------------------------------------

  ALTER TABLE "MOCKDB"."MOCK_USER_PAT_ID" ADD CONSTRAINT "MOCK_USER_PAT_ID_AK" UNIQUE ("MOCK_USER_ID", "ASSIGNING_AUTHORITY", "UNIQUE_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  TABLESPACE "MOCKDB"  ENABLE;
  ALTER TABLE "MOCKDB"."MOCK_USER_PAT_ID" ADD CONSTRAINT "MOCK_USER_PAT_ID_PK" PRIMARY KEY ("MOCK_USER_PAT_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  TABLESPACE "MOCKDB"  ENABLE;
  ALTER TABLE "MOCKDB"."MOCK_USER_PAT_ID" MODIFY ("UNIQUE_ID" NOT NULL ENABLE);
  ALTER TABLE "MOCKDB"."MOCK_USER_PAT_ID" MODIFY ("ASSIGNING_AUTHORITY" NOT NULL ENABLE);
  ALTER TABLE "MOCKDB"."MOCK_USER_PAT_ID" MODIFY ("MOCK_USER_ID" NOT NULL ENABLE);
  ALTER TABLE "MOCKDB"."MOCK_USER_PAT_ID" MODIFY ("MOCK_USER_PAT_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table DATABASECHANGELOG
--------------------------------------------------------

  ALTER TABLE "MOCKDB"."DATABASECHANGELOG" ADD CONSTRAINT "PK_DATABASECHANGELOG" PRIMARY KEY ("ID", "AUTHOR", "FILENAME")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "MOCKDB"  ENABLE;
  ALTER TABLE "MOCKDB"."DATABASECHANGELOG" MODIFY ("EXECTYPE" NOT NULL ENABLE);
  ALTER TABLE "MOCKDB"."DATABASECHANGELOG" MODIFY ("ORDEREXECUTED" NOT NULL ENABLE);
  ALTER TABLE "MOCKDB"."DATABASECHANGELOG" MODIFY ("DATEEXECUTED" NOT NULL ENABLE);
  ALTER TABLE "MOCKDB"."DATABASECHANGELOG" MODIFY ("FILENAME" NOT NULL ENABLE);
  ALTER TABLE "MOCKDB"."DATABASECHANGELOG" MODIFY ("AUTHOR" NOT NULL ENABLE);
  ALTER TABLE "MOCKDB"."DATABASECHANGELOG" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table DATABASECHANGELOGLOCK
--------------------------------------------------------

  ALTER TABLE "MOCKDB"."DATABASECHANGELOGLOCK" ADD CONSTRAINT "PK_DATABASECHANGELOGLOCK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "MOCKDB"  ENABLE;
  ALTER TABLE "MOCKDB"."DATABASECHANGELOGLOCK" MODIFY ("LOCKED" NOT NULL ENABLE);
  ALTER TABLE "MOCKDB"."DATABASECHANGELOGLOCK" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table MOCK_PATIENTS
--------------------------------------------------------

  ALTER TABLE "MOCKDB"."MOCK_PATIENTS" ADD CONSTRAINT "MOCK_PATIENTS_PK" PRIMARY KEY ("MOCK_PAT_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "MOCKDB"  ENABLE;
  ALTER TABLE "MOCKDB"."MOCK_PATIENTS" MODIFY ("UNIQUE_ID" NOT NULL ENABLE);
  ALTER TABLE "MOCKDB"."MOCK_PATIENTS" MODIFY ("ASSIGNING_AUTHORITY" NOT NULL ENABLE);
  ALTER TABLE "MOCKDB"."MOCK_PATIENTS" MODIFY ("MOCK_LAST_NAME" NOT NULL ENABLE);
  ALTER TABLE "MOCKDB"."MOCK_PATIENTS" MODIFY ("MOCK_FIRST_NAME" NOT NULL ENABLE);
  ALTER TABLE "MOCKDB"."MOCK_PATIENTS" MODIFY ("MOCK_PAT_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table MOCK_USERS
--------------------------------------------------------

  ALTER TABLE "MOCKDB"."MOCK_USERS" ADD CONSTRAINT "MOCK_USERS_ACCESS_AK" UNIQUE ("MOCK_ACCESS_CODE")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "MOCKDB"  ENABLE;
  ALTER TABLE "MOCKDB"."MOCK_USERS" ADD CONSTRAINT "MOCK_USERS_PK" PRIMARY KEY ("MOCK_USER_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "MOCKDB"  ENABLE;
  ALTER TABLE "MOCKDB"."MOCK_USERS" MODIFY ("MOCK_LAST_NAME" NOT NULL ENABLE);
  ALTER TABLE "MOCKDB"."MOCK_USERS" MODIFY ("MOCK_FIRST_NAME" NOT NULL ENABLE);
  ALTER TABLE "MOCKDB"."MOCK_USERS" MODIFY ("MOCK_DISPLAY_NAME" NOT NULL ENABLE);
  ALTER TABLE "MOCKDB"."MOCK_USERS" MODIFY ("MOCK_ACCESS_CODE" NOT NULL ENABLE);
  ALTER TABLE "MOCKDB"."MOCK_USERS" MODIFY ("MOCK_USER_ID" NOT NULL ENABLE);

--------------------------------------------------------
--  DDL for Table ALLINSTITUTIONS
--------------------------------------------------------
CREATE TABLE "MOCKDB"."ALLINSTITUTIONS" 
   (	"INSTITUTIONSID" NUMBER(10,0), 
	"INSTITUTIONNAME" VARCHAR2(50 CHAR), 
	"INSTITUTIONCODE" VARCHAR2(10 CHAR), 
	"FACILITYTYPE" VARCHAR2(50 CHAR), 
	"AGENCY" VARCHAR2(50 CHAR), 
	"STA3N" NUMBER(10,0), 
	"STREETADDRESS1" VARCHAR2(50 CHAR), 
	"STREETADDRESS2" VARCHAR2(50 CHAR), 
	"CITY" VARCHAR2(50 CHAR), 
	"STATEABBREV" VARCHAR2(5 CHAR), 
	"ZIP" VARCHAR2(10 CHAR)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "MOCKDB_DATA" ;
  
--------------------------------------------------------
--  Ref Constraints for Table MOCK_USERS
--------------------------------------------------------

  ALTER TABLE "MOCKDB"."MOCK_USERS" ADD CONSTRAINT "MOCK_ADVOCATE_FK" FOREIGN KEY ("MOCK_ADVOCATE_ID")
	  REFERENCES "MOCKDB"."MOCK_PATIENTS" ("MOCK_PAT_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table MOCK_USER_PAT_ID
--------------------------------------------------------

  ALTER TABLE "MOCKDB"."MOCK_USER_PAT_ID" ADD CONSTRAINT "MOCK_USER_PAT_FK" FOREIGN KEY ("MOCK_USER_ID")
	  REFERENCES "MOCKDB"."MOCK_USERS" ("MOCK_USER_ID") ENABLE;

--------------------------------------------------------
--  Constraints for Table ALLINSTITUTIONS
--------------------------------------------------------

  ALTER TABLE "MOCKDB"."ALLINSTITUTIONS" MODIFY ("INSTITUTIONSID" NOT NULL ENABLE);