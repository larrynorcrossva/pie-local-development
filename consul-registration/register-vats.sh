#!/usr/bin/env bash

. app.env

echo "********************* Register VATS with API Gateway *********************"
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/var-utility-resources -d '{"location":"/VarUtilityServices","service":"var-utility-resources-8080","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/var-utility-web -d '{"location":"/va-tool-set","service":"va-tool-set-web","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/var-utility-resources-beta -d '{"location":"/VarUtilityServicesBeta","service":"var-utility-resources-beta","basePath":"/VarUtilityServices","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/var-utility-web-beta -d '{"location":"/va-tool-set-beta","service":"va-tool-set-web-beta","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null


echo "********************* Register NextGen consul variables for var-utility-resources *********************"
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "sa" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-utility-resources/cdw_user > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "tk1ZXVsKmafZFt8p" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-utility-resources/cdw_password > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "jdbc:jtds:sqlserver://var-cdw-mock:1433;databaseName=OIA_MOBILEHEALTH" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-utility-resources/cdw_url > /dev/null


curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "https://veteran.mobilehealth.va.gov/launchpad" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-utility-web/LAUNCHPAD_URL > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "-Dorg.slf4j.simpleLogger.defaultLogLevel=debug -Dlogging.level.org.springframework.jdbc.core.JdbcTemplate=DEBUG" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-utility-resources/JAVA_OPTS > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "http://vista-emr-service-v2:8080/VistaEmrService/v2/sites/{site-id}/provider/{duz}/keys" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-utility-resources/user.security.keys.fetch.uri > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "http://docker-user-service:8080/users/v1/session/vars/userSecurityKeys" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-utility-resources/user.security.keys.session.uri > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "var-utility" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-utility-resources/mongo.database.name > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "var-mongo-db-mock:27017" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-utility-resources/mongo.host > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "false" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-utility-resources/mongo.ssl > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "false" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-utility-resources/ENABLE_APPDYNAMICS > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "false" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/cdw-service/ENABLE_APPDYNAMICS > /dev/null

curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "cdw-service" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT} > /dev/null
