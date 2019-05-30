#!/usr/bin/env bash

. app.env

echo "********************* Register VATS with API Gateway *********************"
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/var-utility-resources -d '{"location":"/VarUtilityServices","service":"var-utility-resources-8080","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/var-utility-web -d '{"location":"/va-tool-set","service":"va-tool-set-web","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null


curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/var-utility-resources-beta -d '{"location":"/VarUtilityServicesBeta","service":"var-utility-resources-beta","basePath":"/VarUtilityServices","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null

curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/var-utility-web-beta -d '{"location":"/va-tool-set-beta","service":"va-tool-set-web-beta","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null


echo "********************* Register NextGen consul variables for var-utility-resources *********************"
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "jdbc:jtds:sqlserver://var-cdw-mock:1433;databaseName=OIA_MOBILEHEALTH" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-utility-resources/cdw_url > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "sa" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-utility-resources/cdw_user > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "tk1ZXVsKmafZFt8p" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-utility-resources/cdw_password > /dev/null


curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "https://veteran.mobilehealth.va.gov/launchpad" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-utility-web/LAUNCHPAD_URL > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "https://veteran.mobilehealth.va.gov/launchpad" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-utility-web-beta/LAUNCHPAD_URL > /dev/null

