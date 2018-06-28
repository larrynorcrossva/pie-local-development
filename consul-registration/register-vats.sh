#!/usr/bin/env bash

. app.env

echo "********************* Register VATS with API Gateway *********************"
curl -H "X-Consul-Token: ${CONSUL_MASTER_TOKEN}" -s -X PUT http://${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/var-utility-resources -d '{"location":"/VarUtilityServices","service":"var-utility-resources-8080","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null

echo "********************* Register NextGen consul variables for var-utility-resources *********************"
