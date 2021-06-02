#!/bin/bash

export CONSUL_SERVER="http://localhost:8500"
export JWT_PUBLIC_KEY=MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwRs7d+tRAuK4soDInuKp51lTTTXWN8okXR3/gSHiIB+q7vZeAGWA8ULjCYuECLde8tS6OxzZwSj/oM0kZBXuHKYq2ukWKjTEmOwlpQW4vBpX60bcx5rXwoEjbcWghK9oVWwY4OwbAJ4TYklkUHZC5buQ+8RU59u6FIWSv2N3D9VBkfYsHvp3O4aXVaE8dZ0dxldUdv/WePoLkCeUYgLsGyDg/zsZvDrX1+yvNsjNNmB/ksJSoptMv9CfyUpSXsfov+8GhBCtgzDvgn32kw79wPsrTkgnC0DRtuv+y3qCXX+ZJ6cbJ31tVus9AadUn2CrWJur6/KRYsniQaEnfA43hwIDAQAB

export CONSUL_PATH=/v1/kv/appconfig/local/pgd-fhir-services
export CONSUL_TOKEN=7BE784A4-7498-4469-BE2F-9C3B9444DFEF
export CONSUL_PATH_RBAC=/v1/kv/appconfig/local/pgd-rbac-services
export DTR_ORG=ckm
export DTR_PREFIX=https://mobileapps.vaftl.us:9250/
export CONSUL_HTTP_ADDR=localhost:8500
export VAMF_ENVIRONMENT=local
export CONSUL_MASTER_TOKEN=7BE784A4-7498-4469-BE2F-9C3B9444DFEF
export TOKEN="7BE784A4-7498-4469-BE2F-9C3B9444DFEF"

# Set minimum and maximum heap sizes on a service-by-service basis
export PGD_FHIR_HEAP_MIN=${PGD_FHIR_HEAP_MIN:-2048m}
export PGD_FHIR_HEAP_MAX=${PGD_FHIR_HEAP_MAX:-4096m}
export CONSUL_PATH=/v1/kv/appconfig/local/pgd-fhir-services
export CONSUL_TOKEN=7BE784A4-7498-4469-BE2F-9C3B9444DFEF
export CONSUL_PATH_RBAC=/v1/kv/appconfig/local/pgd-rbac-services
export REGISTER_PATH=vamf/local/apigateway/1.0/services


export CONSUL_SERVER=http://$CONSUL_HTTP_ADDR
export CONSUL_PATH=/v1/kv/appconfig/$VAMF_ENVIRONMENT/pgd-fhir-services
export CONSUL_TOKEN=$CONSUL_MASTER_TOKEN
export CONSUL_PATH_RBAC=/v1/kv/appconfig/$VAMF_ENVIRONMENT/pgd-rbac-services

echo "Setting PGD environment variables"
echo "--------------------------------------------"

#curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d "testtesttest" $CONSUL_SERVER$CONSUL_PATH/JWT_SECRET
#curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwzNSL1sillVk6Hr7c9C6NDAH1ZwX+DFVKhagN4LDLQXHvz8Xlj3zajroaPLTQwJ+dxBp8ADpxFnSpGS65sXLD0K7XHRdjM3uubWHpNdoQs4xiZLkBFBO6TJn3Q2i8CX55UtznTrrKItQQdwHkaYF1uZ1UZSMlXEwLwa6DId1esARg9Y/dlmXiFvtUlQDe+VuzOGNwXqEu6rVtEIgI/SDG7dBwamXXobQ31M7GkNPhe9+bQTRw3HrA191vCy84w963zUqEzuPmD+aJOjvIJTvzP8cOc1C4ffmCBoIUaUDRM5myQBgFqHJOFbPnrqmzSTXwd5kDC8103yXEJZMvaXTEQIDAQAB" $CONSUL_SERVER$CONSUL_PATH/JWT_PUBLIC_KEY

curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d "jdbc:oracle:thin:@database:1521:xe" $CONSUL_SERVER$CONSUL_PATH/ORACLE_URL > /dev/null
curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d "pgd_user" $CONSUL_SERVER$CONSUL_PATH/ORACLE_USER > /dev/null
curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d "1234" $CONSUL_SERVER$CONSUL_PATH/ORACLE_PASSWORD > /dev/null
curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d "http://example.com/edipid-system" $CONSUL_SERVER$CONSUL_PATH/EDIPID_IDENTIFIER_SYSTEM > /dev/null
curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d "urn:uuid:2.16.840.1.113883.4.349" $CONSUL_SERVER$CONSUL_PATH/ICN_IDENTIFIER_SYSTEM > /dev/null
curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d "urn:uuid:2.16.840.1.113883.4.349" $CONSUL_SERVER$CONSUL_PATH/EDIPID_IDENTIFIER_SYSTEM > /dev/null
curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d "urn:uuid:2.16.840.1.113883.4.349" $CONSUL_SERVER$CONSUL_PATH/PGD_ICN_IDENTIFIER_SYSTEM > /dev/null

echo "--------------------------------------------"
echo "Setting RBAC environment variables"

#curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d "testtesttest" $CONSUL_SERVER$CONSUL_PATH_RBAC/JWT_SECRET

curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d "@./roles-config-dstu2.json" $CONSUL_SERVER$CONSUL_PATH_RBAC/ROLES_CONFIG > /dev/null
curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d false $CONSUL_SERVER$CONSUL_PATH_RBAC/VERBOSE_ERRORS > /dev/null
curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d "@./jwtRSA512.key.pub" $CONSUL_SERVER$CONSUL_PATH_RBAC/JWT_KEY > /dev/null
curl -H "X-Consul-Token: ${CONSUL_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/pgd-fhir-services -d '{"location":"/pgd-fhir-services","service":"pgd-fhir-services","redirect":"off","headers":{"X-Real-IP":"$remote_addr", "Host": "$http_host"}}' > /dev/null
curl -H "X-Consul-Token: ${CONSUL_TOKEN}" -s -X PUT ${HOST_DOMAIN}:8500/v1/kv/vamf/${VAMF_ENVIRONMENT}/apigateway/1.0/services/pgd-rbac-services -d '{"location":"/rbac","service":"rbac-api","redirect":"off","headers":{"X-Real-IP":"$remote_addr", "Host": "$http_host"}}' > /dev/null

echo "--------------------------------------------"

#Set User service endpoint in the API Gateway
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT http://localhost:8500/v1/kv/vamf/apigateway/1.0/services/pgd-fhir-services -d '{"location":"/pgd-fhir-services","service":"pgd-fhir-services","redirect":"off","headers":{"X-Real-IP":"$remote_addr", "Host": "$http_host"}}' > /dev/null
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT http://localhost:8500/v1/kv/vamf/apigateway/1.0/services/pgd-rbac-services -d '{"location":"/rbac","service":"rbac-api","redirect":"off","headers":{"X-Real-IP":"$remote_addr", "Host": "$http_host"}}' > /dev/null
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT $CONSUL_ADDR/$APP_CONFIG_PATH/patient-context/APP_MONGODB_CONN -d "mongodb://mongo:27017/patientContext" > /dev/null && \
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT http://localhost:8500/v1/kv/vamf/apigateway/1.0/services/fit-heart -d '{"location":"/fit-heart","service":"fit-heart-web","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \

echo "Done.."