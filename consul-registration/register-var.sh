#!/usr/bin/env bash

. app.env

echo "********************* Register VAR with API Gateway *********************"
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/var -d '{"location":"/var","service":"var-web","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/var-resources -d '{"location":"/var/VeteranAppointmentRequestService","service":"var-resources","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null

echo "********************* Register NextGen consul variables for var-resources *********************"
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "var-mongo-db-mock:27017" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-resources/mongo.host > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "jdbc:oracle:thin:@var-oracle-db-mock:1521:xe" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-resources/vardb_url > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "false" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-resources/mongo.ssl > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "jdbc:jtds:sqlserver://${CDW_HOST}:1433" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-resources/cdw_url > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "http://${MDWS_HOST}/mdws3.2.8/" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-resources/scheduling.endBasePointUrl > /dev/null


echo "\n\n\nSetting var-resources FEATURE FLAG to ${VAR_DISABLED_FEATURES_SET}\n\n\n"

curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "${VAR_DISABLED_FEATURES_SET}" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-resources/disabledFeatures.disabledFeatures > /dev/null



curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT localhost:8500/v1/kv/appconfig/local/var-resources/APPDYNAMICS_MONITORING_ENABLED -d 'true' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT localhost:8500/v1/kv/appconfig/local/var-resources/APPDYNAMICS_APPLICATION_NAME -d 'VAMF LOCAL VAR-RESOURCES' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT localhost:8500/v1/kv/appconfig/local/var-resources/APPDYNAMICS_NODE_PREFIX -d 'VAR-RESOURCES' > /dev/null


echo "********************* Register NextGen consul variables for var-web *********************"
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "true" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-web/ELIGIBILITY_SERVICE_DISABLED > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "false" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-web-beta/ELIGIBILITY_SERVICE_DISABLED > /dev/null

