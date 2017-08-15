#!/bin/bash

#make sure there isn't anything running from previous deployments
docker-compose down

echo "\nCheck for and Pull the latest docker images..\n"
docker-compose pull --ignore-pull-failures 

#start consul first
echo "\nStarting Consul…\n"
docker-compose up -d consul || (echo "*** FAILED: Could not start Consul using docker-compose." && exit -1)

# Wait 10s for consul to start up, then register endpoints
sleep 10
echo  "\nRegister NextGen services in consul...\n"

curl -H "X-Consul-Token: 7BE784A4-7498-4469-BE2F-9C3B9444DFEF" -s -X PUT localhost:8500/v1/kv/appconfig/local/apigateway/USERSVC_URL -d 'http://user-services:8080/users/v1/session/jwt' > /dev/null && \

curl -H "X-Consul-Token: 7BE784A4-7498-4469-BE2F-9C3B9444DFEF" -s -X PUT localhost:8500/v1/kv/vamf/local/apigateway/1.0/services/roa -d '{"location":"/roa","service":"docker-roa-service","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \

curl -H "X-Consul-Token: 7BE784A4-7498-4469-BE2F-9C3B9444DFEF" -s -X PUT localhost:8500/v1/kv/vamf/local/apigateway/1.0/services/users -d '{"location":"/users","service":"docker-user-service","redirect":"off","headers":{"X-Real-IP":"$remote_addr", "Host" : "$http_host"}}' > /dev/null && \

curl -H "X-Consul-Token: 7BE784A4-7498-4469-BE2F-9C3B9444DFEF" -s -X PUT localhost:8500/v1/kv/vamf/local/apigateway/1.0/services/roa-web -d '{"location":"/roa-web","service":"roa-web-80","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \

curl -H "X-Consul-Token: 7BE784A4-7498-4469-BE2F-9C3B9444DFEF" -s -X PUT localhost:8500/v1/kv/vamf/local/apigateway/1.0/services/wayf -d '{"location":"/wayf","service":"wayf-web","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \

curl -H "X-Consul-Token: 7BE784A4-7498-4469-BE2F-9C3B9444DFEF" -s -X PUT localhost:8500/v1/kv/vamf/local/apigateway/1.0/services/var -d '{"location":"/veteran-appointment-requests","service":"var-web","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \

curl -H "X-Consul-Token: 7BE784A4-7498-4469-BE2F-9C3B9444DFEF" -s -X PUT localhost:8500/v1/kv/vamf/local/apigateway/1.0/services/var-resources -d '{"location":"/VeteranAppointmentRequestService","service":"var-resources-8080","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null

echo "*** Standing up images..."

docker-compose up -d || (echo "*** FAILED: Could not start containers using docker-compose." && exit -1)

echo "\nOpening docker logs…\n"
docker-compose logs -f

