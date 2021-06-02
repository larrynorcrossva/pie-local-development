#!/bin/bash

if [[ $VAULT_ADDR != *"/v1" ]] ;
then
    VAULT_ADDR="${VAULT_ADDR}/v1"
    NAMESPACE=local
fi

NAMESPACE="${NAMESPACE:-default}"
#REGISTRATOR_SERVICE_NAME="${REGISTRATOR_SERVICE_NAME:-vaos-export-service/v2}"
#CDW_DB_NAME="${CDW_DB_NAME:-OIA_MobileHealth_Lite}"

ENABLE_APPDYNAMICS="false"
APPDYNAMICS_NODE_NAME_PREFIX="${APPDYNAMICS_NODE_NAME_PREFIX:-MAE-devint}"
APPDYNAMICS_AGENT_APPLICATION_NAME="MAP-${REGISTRATOR_SERVICE_NAME:-MAP-cms-courier-resources-vet}"

LOGGING_LEVEL_ROOT="${LOGGING_LEVEL_ROOT:-WARN}"
LOGGING_LEVEL_GOV_VA="${LOGGING_LEVEL_GOV_VA:-INFO}"
LOGGING_LEVEL_GOV_VA_VAMF_CMS_EXPORT_JOB_CDWPATIENTPROTOCOLWRITER="${LOGGING_LEVEL_GOV_VA_VAMF_CMS_EXPORT_JOB_CDWPATIENTPROTOCOLWRITER:-DEBUG}"
LOGGING_LEVEL_ORG_SPRINGFRAMEWORK="${LOGGING_LEVEL_ORG_SPRINGFRAMEWORK:-INFO}"
LOGGING_LEVEL_ORG_SPRINGFRAMEWORK_BATCH="${LOGGING_LEVEL_ORG_SPRINGFRAMEWORK_BATCH:-INFO}"
LOGGING_LEVEL_ORG_SPRINGFRAMEWORK_BOOT_AUTOCONFIGURE="${LOGGING_LEVEL_ORG_SPRINGFRAMEWORK_BOOT_AUTOCONFIGURE:-INFO}"
LOGGING_LEVEL_ORG_SPRINGFRAMEWORK_ORM_JPA="${LOGGING_LEVEL_ORG_SPRINGFRAMEWORK_ORM_JPA:-INFO}"
LOGGING_LEVEL_COM_ZAXXER_HIKARI_POOL="${LOGGING_LEVEL_COM_ZAXXER_HIKARI_POOL:-WARN}"
LOGGING_LEVEL_ORG_HIBERNATE="${LOGGING_LEVEL_ORG_HIBERNATE:-WARN}"
LOGGING_LEVEL_ORG_HIBERNATE_STAT="${LOGGING_LEVEL_ORG_HIBERNATE_STAT:-DEBUG}"
LOGGING_LEVEL_ORG_HIBERNATE_SQL="${LOGGING_LEVEL_ORG_HIBERNATE_SQL:-WARN}"
LOGGING_LEVEL_ORG_MONGODB="${LOGGING_LEVEL_ORG_MONGODB:-WARN}"


curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/ENABLE_APPDYNAMICS -d ${ENABLE_APPDYNAMICS} >/dev/null
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/APPDYNAMICS_NODE_NAME_PREFIX -d ${APPDYNAMICS_NODE_NAME_PREFIX} >/dev/null
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/APPDYNAMICS_AGENT_APPLICATION_NAME -d ${APPDYNAMICS_AGENT_APPLICATION_NAME} >/dev/null
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/APPDYNAMICS_AGENT_TIER_NAME -d "Service" >/dev/null

curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/CDW_DB_URL -d "jdbc:jtds:sqlserver://mock-cdw:1433;databaseName=${CDW_DB_NAME};sendStringParametersAsUnicode=false" >/dev/null
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/CDW_DB_USERNAME -d "sa" >/dev/null
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/CDW_SCHEMA_NAME -d "App" >/dev/null
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/CDW_DATASOURCE_MAX_POOL_SIZE -d "20" >/dev/null
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/CDW_DATASOURCE_MIN_POOL_SIZE -d "5" >/dev/null
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/CDW_DATASOURCE_CONN_TIMEOUT -d "15000" >/dev/null
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/CDW_DATASOURCE_IDLE_TIMEOUT -d "48000" >/dev/null
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/CDW_DATASOURCE_VALIDATION_TIMEOUT -d "10000" >/dev/null
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/CDW_DATASOURCE_MAX_LIFETIME -d "54000" >/dev/null
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/LOCK_TIMEOUT -d "2000" >/dev/null

curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/CDW_FETCH_SIZE -d "100" >/dev/null
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/CDW_BATCH_SIZE -d "100" >/dev/null
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/CDW_FIRST_TIME_EXPORT -d "false" >/dev/null
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/CDW_COUNT_CHECK_MAX_RETRIES -d "3" >/dev/null
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/CDW_COUNT_CHECK_WAIT_TIME_MILLIS -d "5000" >/dev/null
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/ENABLE_HIBERNATE_STATS -d "false" >/dev/null

curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/PEEK_AHEAD_WINDOW_IN_MINS -d "120" >/dev/null
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/MONGO_READER_PAGE_SIZE -d "1000" >/dev/null
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/CHUNK_SIZE -d "100" >/dev/null
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/PATIENT_PROTOCOL_CHUNK_SIZE -d "10" >/dev/null
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/SCHEDULING_JOB_CRON -d "0 0/1 * * * ?" >/dev/null

curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/JWT_SECRET -d "testtesttest" >/dev/null
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/JWT_PUBLIC_KEY -d "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwRs7d+tRAuK4soDInuKp51lTTTXWN8okXR3/gSHiIB+q7vZeAGWA8ULjCYuECLde8tS6OxzZwSj/oM0kZBXuHKYq2ukWKjTEmOwlpQW4vBpX60bcx5rXwoEjbcWghK9oVWwY4OwbAJ4TYklkUHZC5buQ+8RU59u6FIWSv2N3D9VBkfYsHvp3O4aXVaE8dZ0dxldUdv/WePoLkCeUYgLsGyDg/zsZvDrX1+yvNsjNNmB/ksJSoptMv9CfyUpSXsfov+8GhBCtgzDvgn32kw79wPsrTkgnC0DRtuv+y3qCXX+ZJ6cbJ31tVus9AadUn2CrWJur6/KRYsniQaEnfA43hwIDAQAB" >/dev/null


curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_TOKEN}" -X PUT -d "${LOGGING_LEVEL_ROOT}" http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/LOGGING_LEVEL_ROOT
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_TOKEN}" -X PUT -d "${LOGGING_LEVEL_ORG_SPRINGFRAMEWORK}" http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/LOGGING_LEVEL_ORG_SPRINGFRAMEWORK
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_TOKEN}" -X PUT -d "${LOGGING_LEVEL_ORG_SPRINGFRAMEWORK_BATCH}" http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/LOGGING_LEVEL_ORG_SPRINGFRAMEWORK_BATCH
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_TOKEN}" -X PUT -d "${LOGGING_LEVEL_ORG_SPRINGFRAMEWORK_BOOT_AUTOCONFIGURE}" http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/LOGGING_LEVEL_ORG_SPRINGFRAMEWORK_BOOT_AUTOCONFIGURE
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_TOKEN}" -X PUT -d "${LOGGING_LEVEL_ORG_SPRINGFRAMEWORK_ORM_JPA}" http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/LOGGING_LEVEL_ORG_SPRINGFRAMEWORK_ORM_JPA
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_TOKEN}" -X PUT -d "${LOGGING_LEVEL_GOV_VA}" http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/LOGGING_LEVEL_GOV_VA
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_TOKEN}" -X PUT -d "${LOGGING_LEVEL_GOV_VA_VAMF_CMS_EXPORT_JOB_CDWPATIENTPROTOCOLWRITER}" http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/LOGGING_LEVEL_GOV_VA_VAMF_CMS_EXPORT_JOB_CDWPATIENTPROTOCOLWRITER
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_TOKEN}" -X PUT -d "${LOGGING_LEVEL_COM_ZAXXER_HIKARI_POOL}" http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/LOGGING_LEVEL_COM_ZAXXER_HIKARI_POOL
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_TOKEN}" -X PUT -d "${LOGGING_LEVEL_ORG_HIBERNATE}" http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/LOGGING_LEVEL_ORG_HIBERNATE
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_TOKEN}" -X PUT -d "${LOGGING_LEVEL_ORG_HIBERNATE_STAT}" http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/LOGGING_LEVEL_ORG_HIBERNATE_STAT
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_TOKEN}" -X PUT -d "${LOGGING_LEVEL_ORG_HIBERNATE_SQL}" http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/LOGGING_LEVEL_ORG_HIBERNATE_SQL
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_TOKEN}" -X PUT -d "${LOGGING_LEVEL_ORG_MONGODB}" http://$HOST_DOMAIN:8500/v1/kv/appconfig/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}/LOGGING_LEVEL_ORG_MONGODB


## Set Vault Properties ##
curl -X POST -H "X-Vault-Token: $ADMIN_VAULT_TOKEN" -d '{"CDW_DB_SECRET": "IS4skwnfuglx928361V", "MONGO_CONNECTION_URI": "mongodb://courier-v4:1234@cms-mongo-mock:27017/courier-v4"}' ${VAULT_ADDR}/secret/${NAMESPACE}/${REGISTRATOR_SERVICE_NAME}