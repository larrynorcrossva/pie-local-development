#!/bin/bash

#make sure there isn't anything running from previous deployments
docker-compose down

echo "\nCheck for and Pull the latest docker images..\n"
docker-compose pull --ignore-pull-failures 

#start consul first
echo "\nStarting Consul and Vault\n"
docker-compose up -d consul vault || (echo "*** FAILED: Could not start Consul using docker-compose." && exit -1)

# Wait 10s for consul to start up, then register endpoints
sleep 10
echo  "\nRegister NextGen services in consul...\n"

curl -H "X-Consul-Token: 7BE784A4-7498-4469-BE2F-9C3B9444DFEF" -s -X PUT localhost:8500/v1/kv/appconfig/local/apigateway/USERSVC_URL -d 'http://user-services:8080/users/v1/session/jwt' > /dev/null && \

curl -H "X-Consul-Token: 7BE784A4-7498-4469-BE2F-9C3B9444DFEF" -s -X PUT localhost:8500/v1/kv/appconfig/local/var-resources/APPDYNAMICS_MONITORING_ENABLED -d 'true' > /dev/null && \

curl -H "X-Consul-Token: 7BE784A4-7498-4469-BE2F-9C3B9444DFEF" -s -X PUT localhost:8500/v1/kv/appconfig/local/var-resources/APPDYNAMICS_APPLICATION_NAME -d 'VAMF LOCAL VAR-RESOURCES' > /dev/null && \

curl -H "X-Consul-Token: 7BE784A4-7498-4469-BE2F-9C3B9444DFEF" -s -X PUT localhost:8500/v1/kv/appconfig/local/var-resources/APPDYNAMICS_NODE_PREFIX -d 'VAR-RESOURCES' > /dev/null && \

curl -H "X-Consul-Token: 7BE784A4-7498-4469-BE2F-9C3B9444DFEF" -s -X PUT localhost:8500/v1/kv/appconfig/local/var-messaging-microservice/APPDYNAMICS_MONITORING_ENABLED -d 'true' > /dev/null && \

curl -H "X-Consul-Token: 7BE784A4-7498-4469-BE2F-9C3B9444DFEF" -s -X PUT localhost:8500/v1/kv/appconfig/local/var-messaging-microservice/APPDYNAMICS_APPLICATION_NAME -d 'VAMF LOCAL VAR-MESSAGING-MICROSERVICE' > /dev/null && \

curl -H "X-Consul-Token: 7BE784A4-7498-4469-BE2F-9C3B9444DFEF" -s -X PUT localhost:8500/v1/kv/appconfig/local/var-messaging-microservice/APPDYNAMICS_NODE_PREFIX -d 'VAR-MESSAGING-MICROSERVICE' > /dev/null && \

curl -H "X-Consul-Token: 7BE784A4-7498-4469-BE2F-9C3B9444DFEF" -s -X PUT localhost:8500/v1/kv/vamf/local/apigateway/1.0/services/roa -d '{"location":"/roa","service":"docker-roa-service","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \

curl -H "X-Consul-Token: 7BE784A4-7498-4469-BE2F-9C3B9444DFEF" -s -X PUT localhost:8500/v1/kv/vamf/local/apigateway/1.0/services/users -d '{"location":"/users","service":"docker-user-service","redirect":"off","headers":{"X-Forwarded-Host" : "$http_host", "X-Forwarded-proto" : "$scheme"}}' > /dev/null && \

curl -H "X-Consul-Token: 7BE784A4-7498-4469-BE2F-9C3B9444DFEF" -s -X PUT localhost:8500/v1/kv/vamf/local/apigateway/1.0/services/roa-web -d '{"location":"/roa-web","service":"roa-web-80","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \

curl -H "X-Consul-Token: 7BE784A4-7498-4469-BE2F-9C3B9444DFEF" -s -X PUT localhost:8500/v1/kv/vamf/local/apigateway/1.0/services/wayf -d '{"location":"/wayf","service":"wayf-web","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \

curl -H "X-Consul-Token: 7BE784A4-7498-4469-BE2F-9C3B9444DFEF" -s -X PUT localhost:8500/v1/kv/vamf/local/apigateway/1.0/services/var -d '{"location":"/var","service":"var-web","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \

curl -H "X-Consul-Token: 7BE784A4-7498-4469-BE2F-9C3B9444DFEF" -s -X PUT localhost:8500/v1/kv/vamf/local/apigateway/1.0/services/var-resources -d '{"location":"/var/VeteranAppointmentRequestService","service":"var-resources-8080","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null

echo "\nBootstrap Vault...\n"

## Alow time for Vault to start
sleep 15s

VAMF_ENVIRNOMENT=local
ADMIN_VAULT_TOKEN=92389390-D796-490A-A91F-44CA582AA661
VAULT_ADDR=http://localhost:8202

echo '{"rules":"{\"path\": {\"secret/'${VAMF_ENVIRNOMENT}'/*\":{\"policy\":\"read\"}}}"}' > ${VAMF_ENVIRNOMENT}.json

## Create a Policy and assign the Rule
curl -X POST -H "X-Vault-Token: $ADMIN_VAULT_TOKEN" -d @${VAMF_ENVIRNOMENT}.json $VAULT_ADDR/v1/sys/policy/${VAMF_ENVIRNOMENT}-read

### MODIFY THE CIDR BLOCK TO MATCH THE ENVIROMMENT ######
## Create a Role and add the Policy
#########################################################
curl -X POST -H "X-Vault-Token: $ADMIN_VAULT_TOKEN" -d '{ "policies":"'${VAMF_ENVIRNOMENT}'-read", "bind_secret_id":false,"bound_cidr_list":"0.0.0.0/0", "role_id":"'${VAMF_ENVIRNOMENT}'-read"}' $VAULT_ADDR/v1/auth/approle/role/${VAMF_ENVIRNOMENT}-read

### Add JWT secrets for microservice
curl -X POST -H "X-Vault-Token: $ADMIN_VAULT_TOKEN" -d '{"JWT_SECRET" : "testtesttest"}' $VAULT_ADDR/v1/secret/${VAMF_ENVIRNOMENT}/user-services
curl -X POST -H "X-Vault-Token: $ADMIN_VAULT_TOKEN" -d '{"JWT_SECRET" : "testtesttest"}' $VAULT_ADDR/v1/secret/${VAMF_ENVIRNOMENT}/roa

echo "*** Standing up images..."

docker-compose up -d || (echo "*** FAILED: Could not start containers using docker-compose." && exit -1)

echo "\nOpening docker logs…\n"
docker-compose logs -f

