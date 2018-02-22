#!/bin/bash

source app.env

#make sure there isn't anything running from previous deployments
docker-compose down

# removes cache file created by docker from var-oracle-db-mock
#rm ./var-oracle-db-mock/.cache

echo "\nCheck for and Pull the latest docker images..\n"
docker-compose pull --ignore-pull-failures

#start consul first
echo "\nStarting Consul and Vault\n"
docker-compose up -d consul vault || (echo "*** FAILED: Could not start Consul using docker-compose." && exit -1)

# Wait 10s for consul to start up, then register endpoints
sleep 10
echo  "\nRegister NextGen services in consul...\n"

curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/apigateway/USERSVC_URL -d 'http://user-services:8080/users/v1/session/jwt' > /dev/null && \
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/roa -d '{"location":"/roa","service":"docker-roa-service","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/users -d '{"location":"/users","service":"docker-user-service","redirect":"off","headers":{"X-Forwarded-Host" : "$http_host", "X-Forwarded-proto" : "$scheme"}}' > /dev/null && \
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/roa-web -d '{"location":"/roa-web","service":"roa-web-80","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/wayf -d '{"location":"/wayf","service":"wayf-web","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \

echo "\nBootstrap Vault...\n"

## Alow time for Vault to start
sleep 30s

echo '{"rules":"{\"path\": {\"secret/'${VAMF_ENVIRONMENT}'/*\":{\"policy\":\"read\"}}}"}' > ${VAMF_ENVIRONMENT}.json

## Create a Policy and assign the Rule
curl -X POST -H "X-Vault-Token: $ADMIN_VAULT_TOKEN" -d @${VAMF_ENVIRONMENT}.json $VAULT_ADDR/v1/sys/policy/${VAMF_ENVIRONMENT}-read

### MODIFY THE CIDR BLOCK TO MATCH THE ENVIROMMENT ######
## Create a Role and add the Policy
#########################################################
curl -X POST -H "X-Vault-Token: $ADMIN_VAULT_TOKEN" -d '{ "policies":"'${VAMF_ENVIRONMENT}'-read", "bind_secret_id":false,"bound_cidr_list":"0.0.0.0/0", "role_id":"'${VAMF_ENVIRONMENT}'-read"}' $VAULT_ADDR/v1/auth/approle/role/${VAMF_ENVIRONMENT}-read

### Add JWT secrets for microservice
curl -X POST -H "X-Vault-Token: $ADMIN_VAULT_TOKEN" -d '{"JWT_SECRET" : "testtesttest"}' $VAULT_ADDR/v1/secret/${VAMF_ENVIRONMENT}/user-services
curl -X POST -H "X-Vault-Token: $ADMIN_VAULT_TOKEN" -d '{"JWT_SECRET" : "testtesttest"}' $VAULT_ADDR/v1/secret/${VAMF_ENVIRONMENT}/roa

echo "*** Standing up images..."

docker-compose up -d || (echo "*** FAILED: Could not start containers using docker-compose." && exit -1)

echo "\nOpening docker logs for NextGen infrastructure... \n"
docker-compose logs -f
