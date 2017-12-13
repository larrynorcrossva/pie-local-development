#!/bin/bash
export DTR_PREFIX="dtr-demo.vaftl.us/"
export DTR_ORG="vamis"
export CONSUL_SERVER=consul:8500
export CONSUL_HTTP_ADDR=localhost:8500
export CONSUL_MASTER_TOKEN=7BE784A4-7498-4469-BE2F-9C3B9444DFEF
export VAMF_ENVIRONMENT=local
export JWT_SECRET=testtesttest
export TRACE_URL=http://zipkin:9411/api/v1/spans
export HOST_DOMAIN=localhost

#make sure there isn't anything running from previous deployments
docker-compose down

echo "\nCheck for and Pull the latest docker images..\n"
docker-compose pull --ignore-pull-failures 

#start consul first
echo "\nStarting Consul and Vault\n"
docker-compose up -d consul vault || (echo "*** FAILED: Could not start Consul using docker-compose." && exit -1)

# Wait 10s for consul to start up, then register endpoints
sleep 10
echo  "\nRegister NextGen services in consul...\n"

curl -H "X-Consul-Token: 7BE784A4-7498-4469-BE2F-9C3B9444DFEF" -s -X PUT localhost:8500/v1/kv/appconfig/local/apigateway/USERSVC_URL -d 'http://user-services:8080/users/v1/session/jwt' > /dev/null && \

curl -H "X-Consul-Token: 7BE784A4-7498-4469-BE2F-9C3B9444DFEF" -s -X PUT localhost:8500/v1/kv/vamf/local/apigateway/1.0/services/roa -d '{"location":"/roa","service":"docker-roa-service","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \

curl -H "X-Consul-Token: 7BE784A4-7498-4469-BE2F-9C3B9444DFEF" -s -X PUT localhost:8500/v1/kv/vamf/local/apigateway/1.0/services/users -d '{"location":"/users","service":"docker-user-service","redirect":"off","headers":{"X-Forwarded-Host" : "$http_host", "X-Forwarded-proto" : "$scheme"}}' > /dev/null && \

curl -H "X-Consul-Token: 7BE784A4-7498-4469-BE2F-9C3B9444DFEF" -s -X PUT localhost:8500/v1/kv/vamf/local/apigateway/1.0/services/roa-web -d '{"location":"/roa-web","service":"roa-web-80","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \

curl -H "X-Consul-Token: 7BE784A4-7498-4469-BE2F-9C3B9444DFEF" -s -X PUT localhost:8500/v1/kv/vamf/local/apigateway/1.0/services/wayf -d '{"location":"/wayf","service":"wayf-web","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \

curl -H "X-Consul-Token: 7BE784A4-7498-4469-BE2F-9C3B9444DFEF" -s -X PUT localhost:8500/v1/kv/vamf/local/apigateway/1.0/services/var -d '{"location":"/var","service":"var-web","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \

curl -H "X-Consul-Token: 7BE784A4-7498-4469-BE2F-9C3B9444DFEF" -s -X PUT localhost:8500/v1/kv/vamf/local/apigateway/1.0/services/var-resources -d '{"location":"/VeteranAppointmentRequestService","service":"var-resources-8080","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null

curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/video-visit-resources -d '{"location":"/video-visit-resources","service":"video-visits-service","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \

curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/veteran-videoconnect-resources -d '{"location":"/veteran-videoconnect-resources","service":"veteran-videoconnect-service","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \

curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/personal-preferences -d '{"location":"/personal-preferences","service":"personal-preference-service","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \

curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/messaging-publisher -d '{"location":"/messaging-publisher","service":"messaging-publisher","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null

echo "\nBootstrap Vault...\n"

## Alow time for Vault to start
sleep 15s



echo '{"rules":"{\"path\": {\"secret/'${VAMF_ENVIRNOMENT}'/*\":{\"policy\":\"read\"}}}"}' > ${VAMF_ENVIRNOMENT}.json

## Create a Policy and assign the Rule
curl -X POST -H "X-Vault-Token: $ADMIN_VAULT_TOKEN" -d @${VAMF_ENVIRNOMENT}.json $VAULT_ADDR/v1/sys/policy/${VAMF_ENVIRNOMENT}-read

### MODIFY THE CIDR BLOCK TO MATCH THE ENVIROMMENT ######
## Create a Role and add the Policy
#########################################################
curl -X POST -H "X-Vault-Token: $ADMIN_VAULT_TOKEN" -d '{ "policies":"'${VAMF_ENVIRNOMENT}'-read", "bind_secret_id":false,"bound_cidr_list":"0.0.0.0/0", "role_id":"'${VAMF_ENVIRNOMENT}'-read"}' $VAULT_ADDR/v1/auth/approle/role/${VAMF_ENVIRNOMENT}-read

### Add JWT secrets for microservice
curl -X POST -H "X-Vault-Token: $ADMIN_VAULT_TOKEN" -d '{"JWT_SECRET" : "testtesttest"}' $VAULT_ADDR/v1/secret/${VAMF_ENVIRNOMENT}/user-services
curl -X POST -H "X-Vault-Token: $ADMIN_VAULT_TOKEN" -d '{"JWT_SECRET" : "testtesttest"}' $VAULT_ADDR/v1/secret/${VAMF_ENVIRNOMENT}/roa


echo "********************* Register NextGen consul variables video-visits-service  *********************"
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "http://mdwshost/mdws/mdws3.2.8/SchedulingSvc.asmx" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/video-visits-service/APP_VVS_MDWS_SCHEDULINGSERVICE_SOAP_URI > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "http://messaging-publisher:8080/messaging-publisher/v1/communication-requests" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/video-visits-service/APP_VVS_MESSAGING_API_URL > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "http://vmr-mock-service:8080/vmr-mock-services/v2/ws" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/video-visits-service/APP_VVS_VMR_SOAP_URI > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "video-visits" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/video-visits-service/APP_VVS_MONGO_VVS_DATABASE_NAME > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "mongodb://video-visits:1234@video-visits-mongo-mock:27017/video-visits?maxPoolSize=20&ssl=false" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/video-visits-service/APP_VVS_MONGO_VVS_CONNECTIONURI > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "https://care.va.gov/vvc-app" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/video-visits-service/APP_VVS_VMR_BASE_DIAL_URL > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "care.va.gov" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/video-visits-service/APP_VVS_VMR_DOMAIN_NAME > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "false" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/video-visits-service/APP_VVS_SEND_APPOINTMENT_REMINDERS > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "http://www.va-vvc.va.gov" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/video-visits-service/APP_VVS_EMAIL_VAR_PERSONAL_PREFERENCES > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "https://localhost/var4.0.0/#appointment" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/video-visits-service/APP_VVS_EMAIL_CANCEL_NON_VIDEO_APPT_LINK > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "https://localhost/var4.0.0/#appointment/booked-details?conference={conferenceId}&pin={conferencePin}" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/video-visits-service/APP_VVS_EMAIL_CANCEL_VIDEO_APPT_LINK > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "100" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/video-visits-service/APP_VVS_VMR_PERIOD_LENGTH > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "5" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/video-visits-service/APP_VVS_VMR_MAX_ATTEMPTS > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "Note: This is an ad hoc encounter. Provider is responsible for aligning work and note to proper VA clinic." http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/video-visits-service/APP_VVS_EMAIL_PROVIDER_NOTE_ADHOC > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "https://care.va.gov/vvc-app/?join=1&media=1&escalate=1&conference={conference-name}@{vmr.domain.name}&pin={vetern-pin}" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/video-visits-service/APP_VVS_VETERAN_VMR_MEETING_URL > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "https://care.va.gov/vvc-app/?name={provider-name}&join=1&media=1&escalate=1&conference={conference-name}@{vmr.domain.name}&pin={provider-pin}" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/video-visits-service/APP_VVS_PROVIDER_VMR_MEETING_URL > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "https://localhost/var4.0.0/#appointment/booked-details?conference={conferenceId}&pin={conferencePin}" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/video-visits-service/APP_VVS_VMR_VETERAN_CANCEL_LINK > /dev/null

## veteran-video-connect-service
echo "********************* Register NextGen consul variables veteran-video-connect-service  *********************"
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "http://video-visits-service:8080/video-visit-resources/v2/" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/veteran-video-connect-service/APP_VVC_VIDEO_VISITS_SERVICE_URL  > /dev/null

## personal-preference-service
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "http://localhost:8888/VeteranAppointmentRequestService/v5/rest/" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/local/personal-preference-service/APP_PPS_VAR_RESOURCES_SERVICE_URL > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "false" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/local/personal-preference-service/APP_PPS_VAR_RESOURCES_USE_FLAG > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "personalpreferences" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/local/personal-preference-service/APP_PPS_MONGO_PPS_DATABASE_NAME > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "mongodb://personalpreferences:1234@video-visits-mongo-mock:27017/personalpreferences?maxPoolSize=20&ssl=false" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/local/personal-preference-service/APP_PPS_MONGO_PPS_CONNECTIONURI > /dev/null

## messaging-publisher
echo "********************* Register NextGen consul variables messaging-publisher  *********************"

curl  -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "test@test.local" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/messaging-publisher/APP_PUBLISHER_EMAIL_FROM   > /dev/null
curl  -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "52.52.65.44" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/messaging-publisher/APP_PUBLISHER_SMTP_HOST  > /dev/null
curl  -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "2525" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/messaging-publisher/APP_PUBLISHER_SMTP_PORT   > /dev/null
curl  -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "test" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/messaging-publisher/APP_PUBLISHER_SMTP_USERNAME   > /dev/null
curl  -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "test" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/messaging-publisher/APP_PUBLISHER_SMTP_SECRETKEY  > /dev/null
curl  -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "true" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/messaging-publisher/APP_PUBLISHER_SMTP_AUTH       > /dev/null
curl  -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "smtp" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/messaging-publisher/APP_PUBLISHER_SMTP_TRANSPORT_PROTOCOL  > /dev/null
curl  -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "false" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/messaging-publisher/APP_PUBLISHER_SMTP_START_TLS_ENABLE > /dev/null
curl  -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "http://personal-preference-service:8080/personal-preferences/v2/" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/messaging-publisher/APP_PPS_RESOURCES_SERVICE_URL > /dev/null



echo "*** Standing up images..."

docker-compose up -d || (echo "*** FAILED: Could not start containers using docker-compose." && exit -1)

echo "\nOpening docker logs…\n"
docker-compose logs -f

