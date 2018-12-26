#!/usr/bin/env bash

. app.env

echo "********************* Register SM with API Gateway *********************"
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/scheduling-manager-resources -d '{"location":"/SchedulingManagerService","service":"scheduling-manager-resources-8080","redirect":"off","headers":{"X-Real-IP":"$remote_addr","host":"$http_host"}}' > /dev/null && \
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/scheduling-manager -d '{"location":"/scheduling-manager","service":"scheduling-manager-web","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \

echo "********************* Register NextGen consul variables for scheduling-manager-resources *********************"
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "var-mongo-db-mock:27017" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/scheduling-manager-resources/mongo.host > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "var-mongo-db-mock:27017" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/scheduling-manager-resources/facility.mongo.host > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "jdbc:oracle:thin:@var-oracle-db-mock:1521:xe" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/scheduling-manager-resources/vardb_url > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "false" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/scheduling-manager-resources/mongo.ssl > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "http://mdws-via-adapter:8080/MdwsViaAdapter/v1/services/" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/scheduling-manager-resources/scheduling.endBasePointUrl > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "jdbc:jtds:sqlserver://${CDW_HOST}:1433" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/scheduling-manager-resources/cdw_url > /dev/null

# Passwords -- WHen Vault is enabled, these should be moved to Vault
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "Agilexadmin99$" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/scheduling-manager-resources/vardb_password > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "tk1ZXVsKmafZFt8p" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/scheduling-manager-resources/cdw_password > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "AmnHW8q6mYRKqNHeYisDTW3FlfaXcgbijqwqq/hI/0hJ" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/scheduling-manager-resources/smtp.password > /dev/null