#!/usr/bin/env bash

. app.env

echo "setting pgd consul settings"
REGISTER_PATH=http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services
APP_CONFIG_PATH=appconfig/local

curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT $REGISTER_PATH/pgd-fhir-services -d '{"location":"/pgd-fhir-services","service":"pgd-fhir-services","redirect":"off","headers":{"X-Real-IP":"$remote_addr", "Host": "$http_host"}}' > /dev/null
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT $REGISTER_PATH/fhir-rbac-svc -d '{"location":"/fhir-rbac-svc","service":"fhir-rbac-svc","redirect":"off","headers":{"X-Real-IP":"$remote_addr", "Host": "$http_host"}}' > /dev/null

curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/$APP_CONFIG_PATH/pgd-fhir-services/JWT_PUBLIC_KEY -d $JWT_PUBLIC_KEY > /dev/null && \
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/$APP_CONFIG_PATH/fhir-rbac-svc/JWT_PUBLIC_KEY -d $JWT_PUBLIC_KEY > /dev/null
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/$APP_CONFIG_PATH/vista-data-services/JWT_PUBLIC_KEY -d $JWT_PUBLIC_KEY > /dev/null && \
