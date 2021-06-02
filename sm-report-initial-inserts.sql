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
--**      - First Touch information provided by joining elements from USER_HISTORY with  USER_HISTORY == APPOINTMENT_REQUEST
--**    Do a check for an alert situation - if alert = true send the initial warning email
--**    If its been 7 days since the last report sent - run the full report and send results to Business Owner
--**
--**********************************************************************************************
--**
-- Parameter definitions
spool db_reports.log;
-- E-mail address to notify report completes successfully
define report_status_notify = 'larry.norcross@vetsez.com';
define report_target_notify = 'katherine.lawyer@va.gov';
define app_schema = 'VARDB';
define app_request_table = 'APPOINTMENT_REQUEST';
define app_status_table = 'AR_DETAIL_CODE';
define user_history_table = 'USER_HISTORY';
define app_status_detail = 'DETAIL_CODE';
set colsep ',';


ALTER TABLE MODIFY ROW_ID ALTER TABLE MODIFY

update AppointmentRequestPo SET FIRST_PROCESSED_DATE=current_date where APPOINTMENT_REQUEST_ID= '99484017'
update APPOINTMENT_REQUEST SET FIRST_PROCESSED_DATE=current_date where APPOINTMENT_REQUEST_ID= '99484017';
update APPOINTMENT_REQUEST SET FIRST_PROCESSED_DATE=current_date where APPOINTMENT_REQUEST_ID= '99484019'
99484019
spool db_reports.log append;
spool off;
exit;