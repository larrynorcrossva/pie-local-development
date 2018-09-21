#!/usr/bin/env bash

. app.env

echo "********************* Register ADR with API Gateway *********************"
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/mock-adr -d '{"location":"/mock-adr","service":"mock-adr","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/adr -d '{"location":"/adr","service":"docker-adr-service","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null

echo "********************* Register consul variables for ADR *********************"
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "https://vaww.esrdev30.aac.va.gov:8430/esr-ws/spring-ws/getEESummary/" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/adr-services/WSDL_URL > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "https://vaww.esrdev30.aac.va.gov:8430/esr-ws/spring-ws/getEESummary/" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/adr-services/ENDPOINT_URL > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "mobileHealthRequest" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/adr-services/REQUEST_NAME > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -X PUT -d "mobileHealthAcct" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/adr-services/USERNAME > /dev/null
