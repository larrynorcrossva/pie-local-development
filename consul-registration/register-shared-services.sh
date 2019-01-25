#!/usr/bin/env bash

. app.env

echo "********************* Register Shared Services with API Gateway *********************"
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/video-visit-resources -d '{"location":"/video-visit-resources","service":"video-visits-service","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/veteran-videoconnect-resources -d '{"location":"/veteran-videoconnect-resources","service":"veteran-videoconnect-service","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/personal-preferences -d '{"location":"/personal-preferences","service":"personal-preference-service","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/messaging-publisher -d '{"location":"/messaging-publisher","service":"messaging-publisher","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/apt-reminder-subscriber -d '{"location":"/apt-reminder-subscriber","service":"apt-reminder-subscriber","redirect":"off","headers":{"X-Real-IP":"$remote_addr", "HOST":"$http_host"}}' >/dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/trs -d '{"location":"/trs","service":"task-resources","redirect":"off","headers":{"X-Real-IP":"$remote_addr", "X-Forwarded-Host" : "$host"}}' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/appointment-service -d '{"location":"/appointments/v1","service":"appointment-service","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/facility-service -d '{"location":"/FacilityService","service":"facility-resources","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/adr -d '{"location":"/adr","service":"adr-service","redirect":"off","request_headers":"on","headers":{"X-Real-IP":"$remote_addr","X-Forwarded-For":"$proxy_add_x_forwarded_for","X-Forwarded-Server":"$host","X-Forwarded-Host":"$host"}}' > /dev/null

echo "********************* Register Shared Services Beta with API Gateway *********************"
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/facility-service-beta -d '{"location":"/FacilityServiceBeta","service":"facility-resources-beta","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null

echo "********************* Register NextGen consul variables for video-visits-service  *********************"
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "http://${MDWS_HOST}/mdws3.2.8/SchedulingSvc.asmx" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/video-visits-service/APP_VVS_MDWS_SCHEDULINGSERVICE_SOAP_URI > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "http://messaging-publisher:8080/messaging-publisher/v1/communication-requests" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/video-visits-service/APP_VVS_MESSAGING_API_URL > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "http://vmr-mock-service:8080/vmr-mock-services/v2/ws" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/video-visits-service/APP_VVS_VMR_SOAP_URI > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "video-visits" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/video-visits-service/APP_VVS_MONGO_VVS_DATABASE_NAME > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "mongodb://video-visits:1234@video-visits-mongo-mock:27017/video-visits?maxPoolSize=20&ssl=false" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/video-visits-service/APP_VVS_MONGO_VVS_CONNECTIONURI > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "https://care.va.gov/vvc-app" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/video-visits-service/APP_VVS_VMR_BASE_DIAL_URL > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "care.va.gov" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/video-visits-service/APP_VVS_VMR_DOMAIN_NAME > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "false" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/video-visits-service/APP_VVS_SEND_APPOINTMENT_REMINDERS > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "http://www.va-vvc.va.gov" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/video-visits-service/APP_VVS_EMAIL_VAR_PERSONAL_PREFERENCES > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "https://${HOST_DOMAIN}/var/v4/#appointment" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/video-visits-service/APP_VVS_EMAIL_CANCEL_NON_VIDEO_APPT_LINK > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "https://${HOST_DOMAIN}/var/v4/#appointment/booked-details?conference={conferenceId}&pin={conferencePin}" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/video-visits-service/APP_VVS_EMAIL_CANCEL_VIDEO_APPT_LINK > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "100" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/video-visits-service/APP_VVS_VMR_PERIOD_LENGTH > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "5" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/video-visits-service/APP_VVS_VMR_MAX_ATTEMPTS > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "Note: This is an ad hoc encounter. Provider is responsible for aligning work and note to proper VA clinic." http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/video-visits-service/APP_VVS_EMAIL_PROVIDER_NOTE_ADHOC > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "https://care.va.gov/vvc-app/?join=1&media=1&escalate=1&conference={conference-name}@{vmr.domain.name}&pin={vetern-pin}" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/video-visits-service/APP_VVS_VETERAN_VMR_MEETING_URL > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "https://care.va.gov/vvc-app/?name={provider-name}&join=1&media=1&escalate=1&conference={conference-name}@{vmr.domain.name}&pin={provider-pin}" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/video-visits-service/APP_VVS_PROVIDER_VMR_MEETING_URL > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "https://${HOST_DOMAIN}/var/v4/#appointment/booked-details?conference={conferenceId}&pin={conferencePin}" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/video-visits-service/APP_VVS_VMR_VETERAN_CANCEL_LINK > /dev/null

echo "********************* Register NextGen consul variables for veteran-video-connect-service  *********************"
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "http://video-visits-service:8080/video-visit-resources/v2/" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/veteran-video-connect-service/APP_VVC_VIDEO_VISITS_SERVICE_URL  > /dev/null

echo "********************* Register NextGen consul variables for personal-preference-service  *********************"
#curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "mongodb://personalpreferences:1234@var-mongo-db-mock:27017/personalpreferences?maxPoolSize=20&ssl=false" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/local/personal-preference-service/APP_PPS_MONGO_PPS_CONNECTIONURI > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "http://${HOST_DOMAIN}:8888/VeteranAppointmentRequestService/v5/rest/" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/local/personal-preference-service/APP_PPS_VAR_RESOURCES_SERVICE_URL > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "false" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/local/personal-preference-service/APP_PPS_VAR_RESOURCES_USE_FLAG > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "personalpreferences" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/local/personal-preference-service/APP_PPS_MONGO_PPS_DATABASE_NAME > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "mongodb://personalpreferences:1234@video-visits-mongo-mock:27017/personalpreferences?maxPoolSize=20&ssl=false" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/local/personal-preference-service/APP_PPS_MONGO_PPS_CONNECTIONURI > /dev/null

echo "********************* Register NextGen consul variables for messaging-publisher  *********************"
curl  -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "relay_machine@ablevets.com" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/messaging-publisher/APP_PUBLISHER_EMAIL_FROM   > /dev/null
curl  -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d ${SMTP_HOST} http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/messaging-publisher/APP_PUBLISHER_SMTP_HOST  > /dev/null
curl  -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "25" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/messaging-publisher/APP_PUBLISHER_SMTP_PORT   > /dev/null
curl  -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "test" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/messaging-publisher/APP_PUBLISHER_SMTP_USERNAME   > /dev/null
curl  -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d ${SMTP_HOST_AV_PASS} http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/messaging-publisher/APP_PUBLISHER_SMTP_SECRETKEY  > /dev/null
curl  -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "true" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/messaging-publisher/APP_PUBLISHER_SMTP_AUTH       > /dev/null
curl  -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "smtp" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/messaging-publisher/APP_PUBLISHER_SMTP_TRANSPORT_PROTOCOL  > /dev/null
curl  -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "false" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/messaging-publisher/APP_PUBLISHER_SMTP_START_TLS_ENABLE > /dev/null
curl  -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "http://personal-preference-service:8080/personal-preferences/v2/" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/messaging-publisher/APP_PPS_RESOURCES_SERVICE_URL > /dev/null

echo "********************* Register NextGen consul variables for facility-service *********************"
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT localhost:8500/v1/kv/appconfig/local/facility-resources/APPDYNAMICS_MONITORING_ENABLED -d 'true' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT localhost:8500/v1/kv/appconfig/local/facility-resources/APPDYNAMICS_APPLICATION_NAME -d 'VAMF LOCAL FACILITY-SERVICE' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT localhost:8500/v1/kv/appconfig/local/facility-resources/APPDYNAMICS_NODE_PREFIX -d 'FACILITY-SERVICE' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT localhost:8500/v1/kv/appconfig/local/facility-resources/app.var.facility.mongo.host -d 'var-mongo-db-mock:27017' > /dev/null


echo "********************* Register NextGen consul variables for veteran-messaging-microservice *********************"
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT localhost:8500/v1/kv/appconfig/local/var-messaging-microservice/APPDYNAMICS_MONITORING_ENABLED -d 'true' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT localhost:8500/v1/kv/appconfig/local/var-messaging-microservice/APPDYNAMICS_APPLICATION_NAME -d 'VAMF LOCAL VAR-MESSAGING-MICROSERVICE' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT localhost:8500/v1/kv/appconfig/local/var-messaging-microservice/APPDYNAMICS_NODE_PREFIX -d 'VAR-MESSAGING-MICROSERVICE' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT localhost:8500/v1/kv/appconfig/local/var-messaging-microservice/app.vmm.mongo.connection -d 'mongodb://vmm:vmm@var-mongo-db-mock:27017/vmm' > /dev/null

curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-messaging-microservice/app.vmm.smtp.host -d ${SMTP_HOST}  > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-messaging-microservice/app.vmm.smtp.port -d "25"  > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-messaging-microservice/app.vmm.smtp.useSSL -d "false" > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-messaging-microservice/app.vmm.smtp.from.password -d ${SMTP_HOST_AV_PASS} > /dev/null

echo "********************* Register NextGen consul variables for facility-service-beta *********************"
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT localhost:8500/v1/kv/appconfig/local/facility-resources-beta/APPDYNAMICS_MONITORING_ENABLED -d 'true' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT localhost:8500/v1/kv/appconfig/local/facility-resources-beta/APPDYNAMICS_APPLICATION_NAME -d 'VAMF LOCAL FACILITY-SERVICE' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT localhost:8500/v1/kv/appconfig/local/facility-resources-beta/APPDYNAMICS_NODE_PREFIX -d 'FACILITY-SERVICE' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT localhost:8500/v1/kv/appconfig/local/facility-resources-beta/app.var.facility.mongo.host -d 'var-mongo-db-mock:27017' > /dev/null

echo "********************* Register NextGen consul variables for veteran-messaging-microservice-beta *********************"
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT localhost:8500/v1/kv/appconfig/local/var-messaging-microservice-beta/APPDYNAMICS_MONITORING_ENABLED -d 'true' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT localhost:8500/v1/kv/appconfig/local/var-messaging-microservice-beta/APPDYNAMICS_APPLICATION_NAME -d 'VAMF LOCAL VAR-MESSAGING-MICROSERVICE' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT localhost:8500/v1/kv/appconfig/local/var-messaging-microservice-beta/APPDYNAMICS_NODE_PREFIX -d 'VAR-MESSAGING-MICROSERVICE' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT localhost:8500/v1/kv/appconfig/local/var-messaging-microservice-beta/app.vmm.mongo.connection -d 'mongodb://vmm:vmm@var-mongo-db-mock:27017/vmm' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-messaging-microservice-beta/app.vmm.smtp.host -d ${SMTP_HOST}  > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-messaging-microservice-beta/app.vmm.smtp.port -d "25"  > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-messaging-microservice-beta/app.vmm.smtp.useSSL -d "false" > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-messaging-microservice-beta/app.vmm.smtp.from.password -d ${SMTP_HOST_AV_PASS} > /dev/null



echo "********************* Register NextGen consul variables for task-resources-service *********************"
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT localhost:8500/v1/kv/appconfig/local/trs/MONGODB_TASK_RESOURCES_COLLECTION -d 'taskresources' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT localhost:8500/v1/kv/appconfig/local/trs/MONGODB_TASK_RESOURCES_DB -d 'taskresourcesdb' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT localhost:8500/v1/kv/appconfig/local/trs/MONGODB_TASK_RESOURCES_URI -d 'mongodb://testuser:testpass@mongo:27017/taskresourcesdb?ssl=false&connectTimeoutMS=20000&maxPoolSize=100' > /dev/null





echo "********************* Register NextGen consul variables for task-resources-service *********************"
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT localhost:8500/v1/kv/appconfig/local/trs/MONGODB_TASK_RESOURCES_COLLECTION -d 'taskresources' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT localhost:8500/v1/kv/appconfig/local/trs/MONGODB_TASK_RESOURCES_DB -d 'taskresourcesdb' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT localhost:8500/v1/kv/appconfig/local/trs/MONGODB_TASK_RESOURCES_URI -d 'mongodb://testuser:testpass@mongo:27017/taskresourcesdb?ssl=false&connectTimeoutMS=20000&maxPoolSize=100' > /dev/null

## user service app token public key adding telehealth.messaging.subscriber.v1  just for reference
## curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/user-services/JWTISSUER_CONFIG_JSON -d "[{\"rsaPublicKeyB64\":\"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtxwSTqAC49xXxWIX/kpb4EWXjnpN8ii5FbjAOmubKA32ZVHC+OSj14uRkKJp5EgU0aOO6cbm7jTnGjOFq+KC540qtBMP6/bd/rcXNCb7t8ajb2eWC1PdvYCrpXM/IAZvdrOGhc9sU+8DeZj2dJg+WIlWkrnXsHMbAd5ePt1mCx6NPADFBRVSJg9MAN04yzBAcgmAeOgcZGYqHcihov3hIi0UnAwi0wtBHIquGcRKhUAG5clOY3YxyNJZ2HWglrCHtz1sHe7OGFBfGGR18C+S6CK7GDB2zm3IkJ16uqf/F3s+tF7u88mE3t6gr/Y/1U6OH99xm1Ta0Yqzie351qEXSwIDAQAB\",\"iss\":\"gov.va.mhv.idp.v1\",\"userType\":\"VETERAN\"},{\"rsaPublicKeyB64\":\"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtxwSTqAC49xXxWIX/kpb4EWXjnpN8ii5FbjAOmubKA32ZVHC+OSj14uRkKJp5EgU0aOO6cbm7jTnGjOFq+KC540qtBMP6/bd/rcXNCb7t8ajb2eWC1PdvYCrpXM/IAZvdrOGhc9sU+8DeZj2dJg+WIlWkrnXsHMbAd5ePt1mCx6NPADFBRVSJg9MAN04yzBAcgmAeOgcZGYqHcihov3hIi0UnAwi0wtBHIquGcRKhUAG5clOY3YxyNJZ2HWglrCHtz1sHe7OGFBfGGR18C+S6CK7GDB2zm3IkJ16uqf/F3s+tF7u88mE3t6gr/Y/1U6OH99xm1Ta0Yqzie351qEXSwIDAQAB\",\"iss\":\"external.idp.test1\",\"userType\":\"VETERAN\"},{\"rsaPublicKeyB64\":\"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC/FshF558yJSPEGm11CZBPYP7D5+dPd2THXK82yJ4GeKGaHGUK0GzzGlPbls0SEPV24eS2EEoPJNqGD05hnogTzykvwO1wdNPZRNJil3qDx3xDXz7SXnxBrhwyyYrl+ehwPcPz3cyXqYIs4QRwoKGDhihRKZ+RHz8BcPdEM6mLnwIDAQAB\",\"iss\":\"telehealth.messaging.subscriber.v1\",\"userType\":\"VETERAN\"}]" > /dev/null

echo "********************* Register NextGen consul variables for ADR *********************"
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/adr-service/WSDL_URL     -d "http://mock-adr:8080/adr/mockeeSummaryPortSoap11?WSDL"  > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/adr-service/ENDPOINT_URL -d "http://mock-adr:8080/adr/mockeeSummaryPortSoap11"  > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/adr-service/TRACE_URL    -d "http://zipkin:9411/api/v1/spans"  > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/adr-service/JWT_SECRET   -d "testtesttest"  > /dev/null
