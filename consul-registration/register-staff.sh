#!/usr/bin/env bash

. app.env

curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/patient-context -d '{"location":"/patient-context","service":"patient-context","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/staff-videoconnect-resources -d '{"location":"/staff-videoconnect-resources","service":"staff-video-connect-service","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' >/dev/null && \

echo "********************* Register NextGen consul patient-context-services properties *********************"
curl -H "Content-Type: text/plain" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "mongodb://var-mongo-db-mock:27017/patientContext" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/local/patient-context/APP_MONGODB_CONN > /dev/null && \
curl -H "Content-Type: text/plain" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "testtesttest" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/local/patient-context/APP_JWT_SECRET > /dev/null

echo "********************* Register NextGen consul staff-videoconnect-resources properties *********************"
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "http://video-visits-service:8080/video-visit-resources/v2" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/staff-video-connect-service/APP_SVC_VIDEO_VISITS_SERVICE_URL >/dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "video-visits" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/staff-video-connect-service/APP_SVC_MONGO_SVC_DATABASE_NAME >/dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "mongodb://video-visits:1234@video-visits-mongo-mock:27017/video-visits?maxPoolSize=20&ssl=false" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/staff-video-connect-service/APP_SVC_MONGO_SVC_CONNECTIONURI >/dev/null
