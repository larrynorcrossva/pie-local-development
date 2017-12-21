BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE VARDB.BEST_TIME_TO_CALL';
  EXECUTE IMMEDIATE 'DROP TABLE VARDB.AR_DETAIL_CODE';
  EXECUTE IMMEDIATE 'DROP TABLE VARDB.APPOINTMENT_REQUEST_MESSAGE';
  EXECUTE IMMEDIATE 'DROP TABLE VARDB.APPOINTMENT_METRICS';
  EXECUTE IMMEDIATE 'DROP TABLE VARDB.APPT_REQ_INPROCESS';
  EXECUTE IMMEDIATE 'DROP TABLE VARDB.CC_APPOINTMENT_REQUEST';
  EXECUTE IMMEDIATE 'DROP TABLE VARDB.DATABASECHANGELOG';
  EXECUTE IMMEDIATE 'DROP TABLE VARDB.DATABASECHANGELOGLOCK';
  EXECUTE IMMEDIATE 'DROP TABLE VARDB.DETAIL_CODE';
  EXECUTE IMMEDIATE 'DROP TABLE VARDB.FACILITY';
  EXECUTE IMMEDIATE 'DROP TABLE VARDB.NOTIFICATION_PREFERENCE';
  EXECUTE IMMEDIATE 'DROP TABLE VARDB.PATIENT';
  EXECUTE IMMEDIATE 'DROP TABLE VARDB.PATIENT_METADATA';
  EXECUTE IMMEDIATE 'DROP TABLE VARDB.USER_FEEDBACK';
  EXECUTE IMMEDIATE 'DROP TABLE VARDB.USER_HISTORY';
  EXECUTE IMMEDIATE 'DROP TABLE VARDB.APPOINTMENT_REQUEST';
  EXECUTE IMMEDIATE 'DROP USER VARDB';
EXCEPTION
  WHEN OTHERS THEN
    IF SQLCODE != -942 THEN
      RAISE;
    END IF;
END;

