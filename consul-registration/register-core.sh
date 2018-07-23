#!/usr/bin/env bash

. app.env

curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/apigateway/USERSVC_URL -d 'http://user-services:8080/users/v1/session/jwt' > /dev/null && \
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/cdw -d '{"location":"/cdw","service":"docker-cdw-service","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/adr -d '{"location":"/adr","service":"docker-adr-service","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/roa -d '{"location":"/roa","service":"docker-roa-service","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/users -d '{"location":"/users","service":"docker-user-service","redirect":"off","headers":{"X-Forwarded-Host" : "$http_host", "X-Forwarded-proto" : "$scheme"}}' > /dev/null && \
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/roa-web -d '{"location":"/roa-web","service":"roa-web-80","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/eula-web -d '{"location":"/eula-web","service":"eula-web","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/eula-service -d '{"location":"/eula","service":"eula-service","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/wayf -d '{"location":"/wayf","service":"wayf-web","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/pcs -d '{"location":"/pcs","service":"pcs-web","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/hdr -d '{"location":"/hdr","service":"hdr-service","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/sud -d '{"location":"/sud","service":"docker-sud-service","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/sud-web -d '{"location":"/sud-web","service":"docker-sud-web-80","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/vistadataservices -d '{  "location":"/VistaDataServices",  "service":"vista-data-services_v1",  "redirect":"off",  "request_headers":"on",  "headers": { "X-Real-IP":"$remote_addr", "X-Forwarded-Host" : "$host" }}' > /dev/null && \
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/pns -d '{"location":"/pns","service":"vamf-notification-services","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null

# echo '{"rules":"{\"path\": {\"secret/'${VAMF_ENVIRONMENT}'/*\":{\"policy\":\"read\"}}}"}' > ${VAMF_ENVIRONMENT}.json

## Create a Policy and assign the Rule
# curl -X POST -H "X-Vault-Token: $ADMIN_VAULT_TOKEN" -d @${VAMF_ENVIRONMENT}.json $VAULT_ADDR/v1/sys/policy/${VAMF_ENVIRONMENT}-read

### MODIFY THE CIDR BLOCK TO MATCH THE ENVIROMMENT ######
## Create a Role and add the Policy
#########################################################
# curl -X POST -H "X-Vault-Token: $ADMIN_VAULT_TOKEN" -d '{ "policies":"'${VAMF_ENVIRONMENT}'-read", "bind_secret_id":false,"bound_cidr_list":"0.0.0.0/0", "role_id":"'${VAMF_ENVIRONMENT}'-read"}' $VAULT_ADDR/v1/auth/approle/role/${VAMF_ENVIRONMENT}-read

### Add JWT secrets for microservice
# curl -X POST -H "X-Vault-Token: $ADMIN_VAULT_TOKEN" -d '{"JWT_SECRET" : "testtesttest"}' $VAULT_ADDR/v1/secret/${VAMF_ENVIRONMENT}/user-services
# curl -X POST -H "X-Vault-Token: $ADMIN_VAULT_TOKEN" -d '{"JWT_SECRET" : "testtesttest"}' $VAULT_ADDR/v1/secret/${VAMF_ENVIRONMENT}/roa