#!/usr/bin/env bash

. app.env

curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/apigateway/USERSVC_URL -d 'http://user-services:8080/users/v1/session/jwt' > /dev/null && \
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/cdw -d '{"location":"/cdw","service":"cdw-service","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/roa -d '{"location":"/roa","service":"docker-roa-service","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/users -d '{"location":"/users","service":"docker-user-service","redirect":"off","headers":{"X-Forwarded-Host" : "$http_host", "X-Forwarded-proto" : "$scheme"}}' > /dev/null && \
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/roa-web -d '{"location":"/roa-web","service":"roa-web-80","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/eula-web -d '{"location":"/eula-web","service":"eula-web","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/eula-service -d '{"location":"/eula","service":"eula-service","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/wayf -d '{"location":"/wayf","service":"wayf-web","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/pcs-web -d '{"location":"/pcs-web","service":"pcs-web","redirect":"off","headers":{"X-Real-IP":"$remote_addr", "X-Forwarded-Proto": "$scheme", "X-Forwarded-Host" : "$http_host"}}' > /dev/null && \
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/pcs -d '{"location":"/pcs","service":"pcs-service","redirect":"off", "headers":{"X-Real-IP":"$remote_addr", "X-Forwarded-Proto": "$scheme", "X-Forwarded-Host" : "$http_host"}}' > /dev/null && \
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/sud -d '{"location":"/sud","service":"docker-sud-service","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/sud-web -d '{"location":"/sud-web","service":"docker-sud-web-80","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/vistadataservices -d '{  "location":"/VistaDataServices",  "service":"vista-data-services_v1",  "redirect":"off",  "request_headers":"on",  "headers": { "X-Real-IP":"$remote_addr", "X-Forwarded-Host" : "$host" }}' > /dev/null && \
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/vista-scheduling-service -d '{ "location":"/VistaSchedulingService", "service":"vista-scheduling-service", "redirect":"off",  "request_headers":"on", "headers": { "X-Real-IP":"$remote_addr","X-Forwarded-Proto": "https", "X-Forwarded-Host" : "$http_host" }}'  > /dev/null && \
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/pns -d '{"location":"/pns","service":"vamf-notification-services","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/mobile-mvi-service -d '{"location":"/mobile-mvi-service/v1","service":"mobile-mvi-service-v1.ckm","redirect":"off","request_headers":"on","headers":{"X-Real-IP":"$remote_addr","X-Forwarded-For":"$proxy_add_x_forwarded_for","X-Forwarded-Server":"$http_host","X-Forwarded-Host":"$http_host"}}' > /dev/null

echo "********************* Register NextGen consul variables for mobile-mvi-service *********************"
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/mobile-mvi-service/v1/ENABLE_APPDYNAMICS -d 'false' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/mobile-mvi-service/v1/APPDYNAMICS_AGENT_APPLICATION_NAME -d 'mobile-mvi-service-v1' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/mobile-mvi-service/v1/APPDYNAMICS_NODE_NAME_PREFIX -d 'MAE-devint' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/mobile-mvi-service/v1/JWT_PUBLIC_KEY -d 'MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwRs7d+tRAuK4soDInuKp51lTTTXWN8okXR3/gSHiIB+q7vZeAGWA8ULjCYuECLde8tS6OxzZwSj/oM0kZBXuHKYq2ukWKjTEmOwlpQW4vBpX60bcx5rXwoEjbcWghK9oVWwY4OwbAJ4TYklkUHZC5buQ+8RU59u6FIWSv2N3D9VBkfYsHvp3O4aXVaE8dZ0dxldUdv/WePoLkCeUYgLsGyDg/zsZvDrX1+yvNsjNNmB/ksJSoptMv9CfyUpSXsfov+8GhBCtgzDvgn32kw79wPsrTkgnC0DRtuv+y3qCXX+ZJ6cbJ31tVus9AadUn2CrWJur6/KRYsniQaEnfA43hwIDAQAB' > /dev/null
# curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/mobile-mvi-service/v1/JWT_SECRET -d 'testtesttest' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/mobile-mvi-service/v1/MVI_URL -d 'http://mock-mvi:8080/mvi/mockVAIdMPort' > /dev/null






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
