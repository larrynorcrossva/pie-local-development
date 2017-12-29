
CREATE BIGFILE TABLESPACE VARDB DATAFILE 'vardb.dat' SIZE 10M AUTOEXTEND ON;

CREATE USER VARDB IDENTIFIED BY Agilexadmin99$ DEFAULT TABLESPACE VARDB;
GRANT CONNECT, RESOURCE, DBA to VARDB;
GRANT CREATE SESSION GRANT ANY PRIVILEGE TO VARDB;
GRANT UNLIMITED TABLESPACE TO VARDB;



CREATE TABLE VARDB.APPOINTMENT_REQUEST_MESSAGE 
(
  ID VARCHAR2(32 BYTE) NOT NULL 
, APPOINTMENT_REQUEST_ID VARCHAR2(32 BYTE) NOT NULL 
, SENDER_ID VARCHAR2(255 BYTE) NOT NULL 
, MESSAGE_TEXT VARCHAR2(255 BYTE) NOT NULL 
, MESSAGE_DATE_TIME TIMESTAMP(6) NOT NULL 
, MESSAGE_TYPE VARCHAR2(32 BYTE) 
);
CREATE TABLE VARDB.APPOINTMENT_METRICS 
(
  NAME VARCHAR2(100 BYTE) NOT NULL 
, VALUE NUMBER(9, 0) DEFAULT 0 NOT NULL 
);
CREATE TABLE VARDB.APPOINTMENT_REQUEST 
(
  APPOINTMENT_REQUEST_ID VARCHAR2(32 BYTE) NOT NULL 
, PATIENT_ID VARCHAR2(255 BYTE) NOT NULL 
, LAST_UPDATED_DATE TIMESTAMP(6) NOT NULL 
, CREATED_DATE TIMESTAMP(6) NOT NULL 
, DELETED_DATE TIMESTAMP(6) 
, ACTIVE NUMBER(1, 0) NOT NULL 
, SECOND_REQUEST NUMBER(1, 0) NOT NULL 
, APPOINTMENT_DATE VARCHAR2(50 BYTE) 
, APPOINTMENT_TIME VARCHAR2(50 BYTE) 
, OPTION_DATE_1 VARCHAR2(50 BYTE) NOT NULL 
, OPTION_TIME_1 VARCHAR2(50 BYTE) NOT NULL 
, OPTION_DATE_2 VARCHAR2(50 BYTE) NOT NULL 
, OPTION_TIME_2 VARCHAR2(50 BYTE) NOT NULL 
, OPTION_DATE_3 VARCHAR2(50 BYTE) NOT NULL 
, OPTION_TIME_3 VARCHAR2(50 BYTE) NOT NULL 
, STATUS VARCHAR2(255 BYTE) NOT NULL 
, APPOINTMENT_TYPE VARCHAR2(255 BYTE) NOT NULL 
, FACILITY_CODE VARCHAR2(100 BYTE) NOT NULL 
, EMAIL VARCHAR2(255 BYTE) 
, PHONE_NUMBER VARCHAR2(255 BYTE) NOT NULL 
, TEXT_MESSAGING_ALLOWED NUMBER(1, 0) NOT NULL 
, TEXT_MESSAGING_PHONE_NUMBER VARCHAR2(255 BYTE) 
, PURPOSE_OF_VISIT VARCHAR2(255 BYTE) NOT NULL 
, OTHER_PURPOSE_OF_VISIT VARCHAR2(100 BYTE) 
, VISIT_TYPE VARCHAR2(40 BYTE) NOT NULL 
, PROVIDER_ID VARCHAR2(255 BYTE) NOT NULL 
, PROVIDER_NAME VARCHAR2(255 BYTE) 
, PROVIDER_PERSON_CLASS VARCHAR2(255 BYTE) 
, PROVIDER_OPTION VARCHAR2(100 BYTE) 
, SECOND_REQUEST_SUBMITTED NUMBER(1, 0) NOT NULL 
, PARENT_REQUEST_ID VARCHAR2(32 BYTE) 
, HAS_VETERAN_NEW_MESSAGE NUMBER(1, 0) DEFAULT 0 
, HAS_PROVIDER_NEW_MESSAGE NUMBER(1, 0) DEFAULT 0 
, PROVIDER_SEEN_APPT_REQ NUMBER(1, 0) DEFAULT 0 
, REQUESTED_PHONE_CALL NUMBER(1, 0) DEFAULT 0 
, TYPE_OF_CARE_ID VARCHAR2(50 CHAR) 
, REASON_FOR_VISIT VARCHAR2(50 CHAR) 
, OTHER_REASON_FOR_VISIT VARCHAR2(255 CHAR) 
, ADDITIONAL_INFORMATION VARCHAR2(255 CHAR) 
, BOOKED_APPT_DATETIME TIMESTAMP(6) 
, LOCATION_ID VARCHAR2(100 BYTE) 
, AUTHORITATIVE_NAME VARCHAR2(255 BYTE) 
, FRIENDLY_NAME VARCHAR2(255 BYTE) 
);
CREATE TABLE VARDB.APPT_REQ_INPROCESS 
(
  APPT_REQ_ID VARCHAR2(32 BYTE) NOT NULL 
, USER_ID VARCHAR2(255 BYTE) NOT NULL 
, FIRST_NAME VARCHAR2(255 BYTE) NOT NULL 
, LAST_NAME VARCHAR2(255 BYTE) NOT NULL 
);
CREATE TABLE VARDB.AR_DETAIL_CODE 
(
  AR_DETAIL_CODE_ID VARCHAR2(32 BYTE) NOT NULL 
, DETAIL_CODE_ID VARCHAR2(32 BYTE) NOT NULL 
, APPOINTMENT_REQUEST_ID VARCHAR2(32 BYTE) NOT NULL 
, CREATED_DATE TIMESTAMP(6) NOT NULL 
, USER_ID VARCHAR2(255 BYTE) NOT NULL 
);
CREATE TABLE VARDB.BEST_TIME_TO_CALL 
(
  APPOINTMENT_REQUEST_ID VARCHAR2(32 BYTE) NOT NULL 
, BEST_TIME VARCHAR2(40 BYTE) NOT NULL 
);
CREATE TABLE VARDB.DATABASECHANGELOG 
(
  ID VARCHAR2(63 BYTE) NOT NULL 
, AUTHOR VARCHAR2(63 BYTE) NOT NULL 
, FILENAME VARCHAR2(200 BYTE) NOT NULL 
, DATEEXECUTED TIMESTAMP(6) NOT NULL 
, ORDEREXECUTED NUMBER(*, 0) NOT NULL 
, EXECTYPE VARCHAR2(10 BYTE) NOT NULL 
, MD5SUM VARCHAR2(35 BYTE) 
, DESCRIPTION VARCHAR2(255 BYTE) 
, COMMENTS VARCHAR2(255 BYTE) 
, TAG VARCHAR2(255 BYTE) 
, LIQUIBASE VARCHAR2(20 BYTE) 
);
CREATE TABLE VARDB.DATABASECHANGELOGLOCK 
(
  ID NUMBER(*, 0) NOT NULL 
, LOCKED NUMBER(1, 0) NOT NULL 
, LOCKGRANTED TIMESTAMP(6) 
, LOCKEDBY VARCHAR2(255 BYTE) 
);
CREATE TABLE VARDB.DETAIL_CODE 
(
  DETAIL_CODE_ID VARCHAR2(32 BYTE) NOT NULL 
, PROVIDER_MESSAGE VARCHAR2(2000 BYTE) NOT NULL 
, VETERAN_MESSAGE VARCHAR2(2000 BYTE) NOT NULL 
);
CREATE TABLE VARDB.FACILITY 
(
  NAME VARCHAR2(100 BYTE) NOT NULL 
, TYPE VARCHAR2(100 BYTE) 
, FACILITY_CODE VARCHAR2(100 BYTE) NOT NULL 
, STATE VARCHAR2(50 BYTE) 
, CITY VARCHAR2(50 BYTE) 
, ADDRESS VARCHAR2(100 BYTE) 
, PARENT_SITE_CODE VARCHAR2(100 BYTE) NOT NULL 
);
CREATE TABLE VARDB.NOTIFICATION_PREFERENCE 
(
  PATIENT_ID VARCHAR2(255 BYTE) NOT NULL 
, NOTIFICATION_FREQUENCY VARCHAR2(255 BYTE) 
, EMAIL_ALLOWED NUMBER(1, 0) NOT NULL 
, EMAIL_ADDRESS VARCHAR2(255 BYTE) 
, TEXT_MESSAGING_ALLOWED NUMBER(1, 0) NOT NULL 
, TEXT_MESSAGING_PHONE_NUMBER VARCHAR2(255 BYTE) 
);
CREATE TABLE VARDB.PATIENT 
(
  PATIENT_ID VARCHAR2(255 BYTE) NOT NULL 
, FIRST_NAME VARCHAR2(100 BYTE) NOT NULL 
, LAST_NAME VARCHAR2(100 BYTE) NOT NULL 
, SSN VARCHAR2(20 BYTE) NOT NULL 
, DATE_OF_BIRTH TIMESTAMP(6) NOT NULL 
, TEXT_MESSAGING_ALLOWED NUMBER(1, 0) NOT NULL 
, TEXT_MESSAGING_PHONE_NUMBER VARCHAR2(255 BYTE) 
);
CREATE TABLE VARDB.PATIENT_METADATA 
(
  PATIENT_ID VARCHAR2(255 BYTE) NOT NULL 
, LAST_APPT_REQ_ACCESS_DATE TIMESTAMP(6) 
);
CREATE TABLE VARDB.USER_FEEDBACK 
(
  USER_FEEDBACK_ID VARCHAR2(32 BYTE) NOT NULL 
, RATING NUMBER(3, 0) NOT NULL 
, APPT_PROCESSED_TIMELY VARCHAR2(32 BYTE) NOT NULL 
, COMMENTS CLOB 
, CREATED_DATE TIMESTAMP(6) NOT NULL 
);
CREATE TABLE VARDB.USER_HISTORY 
(
  ID RAW(16) NOT NULL 
, USER_ID VARCHAR2(255 BYTE) 
, TABLE_NAME VARCHAR2(255 BYTE) 
, ROW_ID RAW(16) 
, OPERATION VARCHAR2(255 BYTE) 
, ACTION_DATE TIMESTAMP(6) 
, DATA CLOB 
);

commit;