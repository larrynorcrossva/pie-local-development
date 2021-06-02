--***********************************************************************************************
--** Programmer : Larry Norcross-Sustainment /  Bruce Hendrickson-MACM
--** Program Name: sm_report_metrics.sql
--** Description : Reporting Metrics
--**
--** This file reports on data within VARDB for Express Care Daily Monitoring
--**
--**  The SQL  scripts are called in the following order:
--**    Tables
--**      - Query tables joining AR_DETAIL_CODE == DETAIL_CODE == APPOINTMENT_REQUEST
--**    Do a check for an alert situation - if alert = true send the initial warning email
--**    If its been 7 days since the last report sent - run the full report and send results to Business Owner
--**
--**********************************************************************************************
--**
-- Parameter definitions
spool db_reports.log
-- E-mail address to notify report completes successfully
define report_status_notify = 'larry.norcross@vetsez.com'
define report_target_notify = 'kay.lawyer@va.gov'
define app_schema = 'VARDB'
define app_request_table = 'APPOINTMENT_REQUEST'
define app_status_table = 'AR_DETAIL_CODE'
define user_history_table = 'USER_HISTORY'
define app_status_detail = 'DETAIL_CODE'
set colsep ',';
set serveroutput on size 1000000;
 set wrap on;
 set pagesize 0;
 set linesize 5000;
 set trims on;
 set colsep ',';
 set heading on;
 set trimspool on;

whenever sqlerror exit sql.sqlcode
whenever oserror exit sql.oscode
alter session set current_schema="VARDB";
set echo ON
column PATIENT_ID for a25;
column TYPE_OF_CARE_ID for a30;
column FIRST_TOUCH for a21;
column COMBINED_STATUS for a21;
column FIRST_STATUS for a21;
column STATUS_HISTORY for a21;
column DETAIL_CODE_ID for a10;
column VETERAN_MESSAGE for a200;
column APPOINTMENT_REQUEST_ID for a75;
column PNAME for a50l


--  THE EXPRESS_CARE_ALERT CONDITION
SELECT count(APPOINTMENT_REQUEST_ID) as EXPRESS_CARE_ALERT from APPOINTMENT_REQUEST where TYPE_OF_CARE_ID = 'CR1' AND STATUS = 'Submitted' and round((24 * 60 *(to_date(to_char(sysdate, 'YYYY-MM-DD hh24:mi'), 'YYYY-MM-DD hh24:mi') - to_date(to_char(CREATED_DATE, 'YYYY-MM-DD hh24:mi'), 'YYYY-MM-DD hh24:mi'))),2) > 1440;
--  IF this condition is greater than 1 send the alert
--  The remainder of the script is for creating the contents of the report
spool db_reports.log append
--  THE EXPRESS_CARE_ALERT RECORDS
SELECT APPOINTMENT_REQUEST_ID as ID , CREATED_DATE from APPOINTMENT_REQUEST where TYPE_OF_CARE_ID = 'CR1' AND STATUS = 'Submitted' and round((24 * 60 *(to_date(to_char(sysdate, 'YYYY-MM-DD hh24:mi'), 'YYYY-MM-DD hh24:mi') - to_date(to_char(CREATED_DATE, 'YYYY-MM-DD hh24:mi'), 'YYYY-MM-DD hh24:mi'))),2) > 1440;
-- If anything is found here,
spool db_reports.log append;
SELECT a.APPOINTMENT_REQUEST_ID || '|' || a.PATIENT_ID, CONCAT(CONCAT(p.FIRST_NAME,' ' ), p.LAST_NAME)  as PNAME ,   a.CREATED_DATE || '|' ||  a.APPOINTMENT_TYPE,  a.FACILITY_CODE,  a.REASON_FOR_VISIT,  '|>' as APPT_STATUS_TBL, ft as FIRST_TOUCH, adc.DETAIL_CODE_ID , d.DETAIL_CODE_ID, adc.CREATED_DATE, adc.USER_ID, '|' as APPT_DETAIL,  d.PROVIDER_MESSAGE as msg,
        round((24 * 60 *(to_date(to_char(ft, 'YYYY-MM-DD hh24:mi'), 'YYYY-MM-DD hh24:mi') - to_date(to_char(a.CREATED_DATE, 'YYYY-MM-DD hh24:mi'), 'YYYY-MM-DD hh24:mi'))),2) as MIN_TO_FIRST_TOUCH ,
        round((24 * 60 * (to_date(to_char(adc.CREATED_DATE, 'YYYY-MM-DD hh24:mi'), 'YYYY-MM-DD hh24:mi') - to_date(to_char(a.CREATED_DATE, 'YYYY-MM-DD hh24:mi'), 'YYYY-MM-DD hh24:mi'))),2) as MIN_TO_RESOLUTION ,
        round((24 * 60 * (to_date(to_char(q4.s2, 'YYYY-MM-DD hh24:mi'), 'YYYY-MM-DD hh24:mi') - to_date(to_char(q3.s1, 'YYYY-MM-DD hh24:mi'), 'YYYY-MM-DD hh24:mi'))),2) as MIN_ESC_TO_RESOLUTION ,
        round((24 * 60 * (to_date(to_char(sysdate, 'YYYY-MM-DD hh24:mi'), 'YYYY-MM-DD hh24:mi') - to_date(to_char(q3.s1, 'YYYY-MM-DD hh24:mi'), 'YYYY-MM-DD hh24:mi'))),2) as MIN_ESC_PENDING ,
        CONCAT(CONCAT(s3,'  '), s4) as combined_status ,
        SUBSTR(s3, INSTR(s3, '|')+1) AS first_status ,
        CASE WHEN SUBSTR(s4, INSTR(s4, '|')+1) is NULL THEN CASE WHEN SUBSTR(s3, INSTR(s3, '|')+1) is NULL THEN a.STATUS ELSE SUBSTR(s3, INSTR(s3, '|')+1) END ELSE CONCAT(CONCAT(SUBSTR(s3, INSTR(s3, '|')+1),'>'),SUBSTR(s4, INSTR(s4, '|')+1)) END as STATUS_HISTORY
        from APPOINTMENT_REQUEST a
        JOIN PATIENT p
        ON p.PATIENT_ID = a.PATIENT_ID
    AND a.CREATED_DATE >= DATE '2020-05-01'
    -- Get the User History related to this record, this provides the initial Touch Date
    LEFT OUTER JOIN
     (SELECT min(ACTION_DATE) as ft, ROW_ID FROM USER_HISTORY u
     WHERE ROW_ID IN (SELECT APPOINTMENT_REQUEST_ID FROM APPOINTMENT_REQUEST where TYPE_OF_CARE_ID='CR1')
        GROUP BY row_id
      ) q2
        ON q2.ROW_ID = a.APPOINTMENT_REQUEST_ID
    -- Get the AR_DETAIL_CODE records related to this appoint, provides all statuses.  Status are displayed concatenated
      LEFT OUTER JOIN
      AR_DETAIL_CODE adc ON
      adc.APPOINTMENT_REQUEST_ID = a.APPOINTMENT_REQUEST_ID
      LEFT OUTER JOIN
      DETAIL_CODE d ON
      d.DETAIL_CODE_ID = adc.AR_DETAIL_CODE_ID
     --Get the date of the first status update (For records that were escalated and then resolved this provides the first date)
     LEFT OUTER JOIN
     (SELECT min(CREATED_DATE) as s1, APPOINTMENT_REQUEST_ID FROM AR_DETAIL_CODE
      WHERE APPOINTMENT_REQUEST_ID IN (SELECT APPOINTMENT_REQUEST_ID FROM APPOINTMENT_REQUEST where TYPE_OF_CARE_ID='CR1')
        GROUP BY APPOINTMENT_REQUEST_ID
      ) q3
        ON q3.APPOINTMENT_REQUEST_ID = a.APPOINTMENT_REQUEST_ID
     --Get the date of the second status update (For records that were escalated and then resolved this provides the final date)
     LEFT OUTER JOIN
     (SELECT max(CREATED_DATE) as s2, APPOINTMENT_REQUEST_ID FROM AR_DETAIL_CODE
      WHERE APPOINTMENT_REQUEST_ID IN (SELECT APPOINTMENT_REQUEST_ID FROM APPOINTMENT_REQUEST where TYPE_OF_CARE_ID='CR1')
        GROUP BY APPOINTMENT_REQUEST_ID
      ) q4
        ON q4.APPOINTMENT_REQUEST_ID = a.APPOINTMENT_REQUEST_ID
      LEFT OUTER JOIN
     (SELECT min(CONCAT(CONCAT(TO_CHAR(CREATED_DATE, 'YYYY-MM-DD HH24:MI') ,'|'),DETAIL_CODE_ID)) as s3, APPOINTMENT_REQUEST_ID as s3a FROM AR_DETAIL_CODE
      WHERE APPOINTMENT_REQUEST_ID IN (SELECT APPOINTMENT_REQUEST_ID FROM APPOINTMENT_REQUEST where TYPE_OF_CARE_ID='CR1')
        GROUP BY APPOINTMENT_REQUEST_ID
      ) q5
        ON s3a = a.APPOINTMENT_REQUEST_ID
        LEFT OUTER JOIN
        (SELECT max(CONCAT(CONCAT(TO_CHAR(CREATED_DATE, 'YYYY-MM-DD HH24:MI') ,'|'),DETAIL_CODE_ID)) as s4, APPOINTMENT_REQUEST_ID as s4a FROM AR_DETAIL_CODE
        WHERE APPOINTMENT_REQUEST_ID IN (SELECT APPOINTMENT_REQUEST_ID FROM APPOINTMENT_REQUEST where TYPE_OF_CARE_ID='CR1')
        GROUP BY APPOINTMENT_REQUEST_ID
         ) q4
        ON s4a = a.APPOINTMENT_REQUEST_ID  ;
-- Add a record into the BUILD table for this deployment
        select count(q2.APPOINTMENT_REQUEST_ID) as ESCALATED_VIDEO ,
        round(count(q2.APPOINTMENT_REQUEST_ID) / count(a.APPOINTMENT_REQUEST_ID),2) as AVERAGE_ESCALATED_VIDEO
        from APPOINTMENT_REQUEST a
        JOIN PATIENT p
        ON p.PATIENT_ID = a.PATIENT_ID
    AND a.CREATED_DATE >= DATE '2020-05-01'
    LEFT OUTER JOIN
     (SELECT APPOINTMENT_REQUEST_ID as ft, APPOINTMENT_REQUEST_ID FROM AR_DETAIL_CODE adc
     WHERE APPOINTMENT_REQUEST_ID IN (SELECT APPOINTMENT_REQUEST_ID FROM APPOINTMENT_REQUEST where TYPE_OF_CARE_ID='CR1') AND adc.DETAIL_CODE_ID <> 'DETCODE34' AND adc.DETAIL_CODE_ID <> 'DETCODE35'
        GROUP BY APPOINTMENT_REQUEST_ID
      ) q2
        ON q2.APPOINTMENT_REQUEST_ID = a.APPOINTMENT_REQUEST_ID OR q2.APPOINTMENT_REQUEST_ID = NULL;
spool db_reports.log append
spool off
exit