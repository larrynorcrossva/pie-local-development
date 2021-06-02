#!/usr/bin/env bash

. app.env

echo "********************* Register VAR with API Gateway *********************"
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/var-web -d '{"location":"/var","service":"var-web","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/var-resources -d '{"location":"/var/VeteranAppointmentRequestService","service":"var-resources","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null

curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/var-web-beta -d '{"location":"/var-beta","service":"var-web-beta","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/var-resources-beta -d '{"location":"/var-beta/VeteranAppointmentRequestService","service":"var-resources-beta","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null

curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/patient-context -d '{"location":"/patient-context","service":"patient-context","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \

curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/fit-heart -d '{"location":"/fit-heart","service":"fit-heart-web","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \

echo "********************* Register NextGen consul variables for var-resources *********************"
curl  -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "var-mongo-db-mock:27017" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-resources/mongo.host > /dev/null
curl  -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "jdbc:oracle:thin:@var-oracle-db-mock:1521:xe" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-resources/vardb_url > /dev/null
curl  -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "false" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-resources/mongo.ssl > /dev/null
curl  -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "jdbc:jtds:sqlserver://var-cdw-mock:1433;databaseName=OIA_MOBILEHEALTH" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-resources/cdw_url > /dev/null
curl  -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "sa" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-resources/cdw_user > /dev/null
# curl  -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "http://${MDWS_HOST}/mdws3.2.8/" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-resources/scheduling.endBasePointUrl > /dev/null
curl  -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "http://vista-scheduling-service:8080/VistaSchedulingService/v2" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-resources/vss.scheduling.endPointUrl > /dev/null
curl  -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "true" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-resources/vssCall > /dev/null

curl  -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-resources/vmm.inapp.notification.fetch.uri -d "http://var-messaging-microservice:8080/VarMessaging/v2/rest/api/inApp/{assigningAuthority}/{uniqueId}" > /dev/null
curl  -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-resources/vmm.inapp.notification.mark.read -d "http://var-messaging-microservice:8080/VarMessaging/v2/rest/api/inApp/{messageIdentifer}/read" > /dev/null
curl  -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-resources/vmm.inapp.notification.delete.uri -d "http://var-messaging-microservice:8080/VarMessaging/v2/rest/api/inApp/{messageIdentifer}" > /dev/null
curl  -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-resources/vmm.token.uri -d "http://var-messaging-microservice:8080/VarMessaging/v2/rest/api/auth" > /dev/null
curl  -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-resources/vmm.post.recall.reminder.uri -d "http://var-messaging-microservice:8080/VarMessaging/v2/rest/api/message" > /dev/null
curl  -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-resources/facility.timezone.fetch.all.uri -d "http://facility-service:8080/FacilityService/v2/rest/public/facility/timezone" > /dev/null
curl  -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-resources/facility.timezone.fetch.by.id.uri -d "http://facility-service:8080/FacilityService/v2/rest/public/facility/{facility-id}/timezone" > /dev/null
curl  -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-resources/adr.cceligibility.uri -d "http://adr-service:8080/adr/v2/patients/{pt-id}/eligibility/communityCare" > /dev/null

printf "\n\n\nSetting var-resources FEATURE FLAG to ${VAR_DISABLED_FEATURES_SET}\n\n\n"

curl  -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "${VAR_DISABLED_FEATURES_SET}" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-resources/disabledFeatures.disabledFeatures > /dev/null
curl  -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "4" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-resources/numberOfMonthsForFutureAppointments > /dev/null


curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://localhost:8500/v1/kv/appconfig/local/var-resources/APPDYNAMICS_MONITORING_ENABLED -d 'true' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://localhost:8500/v1/kv/appconfig/local/var-resources/APPDYNAMICS_APPLICATION_NAME -d 'VAMF LOCAL VAR-RESOURCES' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://localhost:8500/v1/kv/appconfig/local/var-resources/APPDYNAMICS_NODE_PREFIX -d 'VAR-RESOURCES' > /dev/null



curl  -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-resources-beta/vmm.inapp.notification.fetch.uri -d "http://var-messaging-microservice-beta:8080/VarMessagingBeta/v2/rest/api/inApp/{assigningAuthority}/{uniqueId}" > /dev/null
curl  -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-resources-beta/vmm.inapp.notification.mark.read -d "http://var-messaging-microservice-beta:8080/VarMessagingBeta/v2/rest/api/inApp/{messageIdentifer}/read" > /dev/null
curl  -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-resources-beta/vmm.inapp.notification.delete.uri -d "http://var-messaging-microservice-beta:8080/VarMessagingBeta/v2/rest/api/inApp/{messageIdentifer}" > /dev/null
curl  -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-resources-beta/vmm.token.uri -d "http://var-messaging-microservice-beta:8080/VarMessagingBeta/v2/rest/api/auth" > /dev/null
curl  -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-resources-beta/vmm.post.recall.reminder.uri -d "http://var-messaging-microservice-beta:8080/VarMessagingBeta/v2/rest/api/message" > /dev/null
curl  -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-resources-beta/facility.timezone.fetch.all.uri -d "http://facility-service-beta:8080/FacilityServiceBeta/v2/rest/public/facility/timezone" > /dev/null
curl  -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-resources-beta/facility.timezone.fetch.by.id.uri -d "http://facility-service-beta:8080/FacilityServiceBeta/v2/rest/public/facility/{facility-id}/timezone" > /dev/null

curl  -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "${VAR_DISABLED_FEATURES_SET}" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-resources-beta/disabledFeatures.disabledFeatures > /dev/null
curl  -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "4" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-resources-beta/numberOfMonthsForFutureAppointments > /dev/null

curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://localhost:8500/v1/kv/appconfig/local/var-resources-beta/APPDYNAMICS_MONITORING_ENABLED -d 'true' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://localhost:8500/v1/kv/appconfig/local/var-resources-beta/APPDYNAMICS_APPLICATION_NAME -d 'VAMF LOCAL VAR-RESOURCES' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://localhost:8500/v1/kv/appconfig/local/var-resources-beta/APPDYNAMICS_NODE_PREFIX -d 'VAR-RESOURCES' > /dev/null


echo "********************* Register NextGen consul variables for var-web *********************"
curl  -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "false" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-web/ELIGIBILITY_SERVICE_DISABLED > /dev/null
curl  -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "false" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-web-beta/ELIGIBILITY_SERVICE_DISABLED > /dev/null

curl  -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "https://veteran.mobilehealth.va.gov/launchpad" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-web/LAUNCHPAD_URL > /dev/null
curl  -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "https://veteran.mobilehealth.va.gov/launchpad" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-web-beta/LAUNCHPAD_URL > /dev/null
curl  -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "https://vet-int.mobilehealth.va.gov/launchpad" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-web-sqa/LAUNCHPAD_URL > /dev/null ## TODO: remove ?

curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT http://localhost:8500/v1/kv/appconfig/local/patient-context/APP_MONGODB_CONN -d "mongodb://mongo:27017/patientContext" > /dev/null && \
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT http://localhost:8500/v1/kv/appconfig/local/patient-context/APP_JWT_SECRET  -d "testtesttest" > /dev/null
