#!/bin/bash

if [ ! "$(docker network ls | grep pie_default)" ] ; then
    echo -e "\nCreating Docker Network pie_default.\n"
    docker network create pie_default
fi

docker login --username norcrossit --password 87645240-b46c-4867-999b-23d54f8c29f5
docker login dtr.mapsandbox.net

usage()
{
    cat << USAGE >&2
Usage:
    -m | --min-set      The minimum set of services required to replicate the NextGen JWT architecture
    			(API-Gateway, Consul, Vault, User-Service, ROA, SUD and WAYF)

    -l | --latest       Pull the latest images 
    -e | --example      Start the sample app, Mobile Blue Button (MBB), and the SSOE Mock
USAGE
    exit 1
}
export DTR_PREFIX_PGD_FHIR=mobileapps.vaftl.us:9250
export DTR_ORG_VAMIS=ckm
export DTR_PREFIX=mobileapps.vaftl.us:9250
export DTR_ORG=ckm
export CONSUL_HTTP_SSL=true
LATEST=false
MINSET=false
EXAMPLE=false




docker login dtr.mapsandbox.net
docker-cleanup.sh
### Defaults
export JWT_PUBLIC_KEY=MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwRs7d+tRAuK4soDInuKp51lTTTXWN8okXR3/gSHiIB+q7vZeAGWA8ULjCYuECLde8tS6OxzZwSj/oM0kZBXuHKYq2ukWKjTEmOwlpQW4vBpX60bcx5rXwoEjbcWghK9oVWwY4OwbAJ4TYklkUHZC5buQ+8RU59u6FIWSv2N3D9VBkfYsHvp3O4aXVaE8dZ0dxldUdv/WePoLkCeUYgLsGyDg/zsZvDrX1+yvNsjNNmB/ksJSoptMv9CfyUpSXsfov+8GhBCtgzDvgn32kw79wPsrTkgnC0DRtuv+y3qCXX+ZJ6cbJ31tVus9AadUn2CrWJur6/KRYsniQaEnfA43hwIDAQAB
export CONSUL_TOKEN=7BE784A4-7498-4469-BE2F-9C3B9444DFEF
export CONSUL_MASTER_TOKEN=7BE784A4-7498-4469-BE2F-9C3B9444DFEF
export CONSUL_HTTP_ADDR=http://localhost:8500
export CONSUL_ADDR=http://localhost:8500/v1/kv
export REGISTER_PATH=vamf/local/apigateway/1.0/services
export APP_CONFIG_PATH=appconfig/local
export CONSUL_HTTP_PATH=http://localhost:8500/
export VAMF_ENVIRONMENT=local
export INIT_ELASTICSEARCH=true
export INIT_DATABASE=true
export SAFEGUARD_SSL_ENABLED=true

# Set minimum and maximum heap sizes on a service-by-service basis
export PGD_FHIR_HEAP_MIN=${PGD_FHIR_HEAP_MIN:-2048m}
export PGD_FHIR_HEAP_MAX=${PGD_FHIR_HEAP_MAX:-4096m}

# Make sure there isn't anything running from previous deployments
docker-compose down



# JWT configuration
export USE_JWT_PUBLIC_KEY=false
export JWT_SECRET="testtesttest"
export JWT_ISSUER=""
export JWT_PUBLIC_KEY=MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwRs7d+tRAuK4soDInuKp51lTTTXWN8okXR3/gSHiIB+q7vZeAGWA8ULjCYuECLde8tS6OxzZwSj/oM0kZBXuHKYq2ukWKjTEmOwlpQW4vBpX60bcx5rXwoEjbcWghK9oVWwY4OwbAJ4TYklkUHZC5buQ+8RU59u6FIWSv2N3D9VBkfYsHvp3O4aXVaE8dZ0dxldUdv/WePoLkCeUYgLsGyDg/zsZvDrX1+yvNsjNNmB/ksJSoptMv9CfyUpSXsfov+8GhBCtgzDvgn32kw79wPsrTkgnC0DRtuv+y3qCXX+ZJ6cbJ31tVus9AadUn2CrWJur6/KRYsniQaEnfA43hwIDAQAB
export JWT_FAILBACK_TO_COOKIE=${JWT_FAILBACK_TO_COOKIE:-true}

# Set a default DOMAIN_REGEX (API Gateway) for local development. Allow the value to be overridden
export DOMAIN_REGEX="${DOMAIN_REGEX:-^http(s)?://((.*.va.gov)(:[0-9]*)?|(localhost)(:[0-9]*)?|(.*.vaftl.us))}"

#
# Configuration for Identity Providers that are supported in NextGen
#
# Properties:
#   - idpId
#   - friendlyName
#   - description
#   - versionId
#   - scope -> Used by User Services to filter the IDPs that are shown to the user
#   - logoUrl
#   - loginUrl
#   - logoutUrl
#   - landingPage -> location to redirect to after logout. Note that currently this is only relevant for Veteran apps. Staff app logout stops at the logoutUrl
#
IDP_CONFIG_JSON=$(cat <<EOF
[
  {
    "idpId": "gov.va.iam.ssoe.v1",
    "friendlyName": "VAAFI SSOe",
    "description": "Veteran login supporting DSLogon, Symantec, and other CSPs.",
    "versionId": "v1",
    "scope": "VETERAN",
    "logoUrl": "img/dslogon-logo.png",
    "loginUrl": "http://${HOST_DOMAIN}:9000/ssoeproxy/veteran/authorize",
    "logoutUrl": "http://${HOST_DOMAIN}:9000/ssoeproxy/logout",
    "landingPage": "https://mobile.va.gov/appstore"
  },
  {
    "idpId": "gov.va.iam.ssoi.v1",
    "friendlyName": "VAAFI SSOi",
    "description": "Staff login supporting PIV authentication.",
    "versionId": "v1",
    "scope": "STAFF",
    "logoUrl": "img/logo_navbar_ipad.png",
    "loginUrl": "http://${HOST_DOMAIN}:8080/ssoiproxy/login",
    "logoutUrl": "http://${HOST_DOMAIN}:8080/ssoiproxy/logout",
    "landingPage": "http://${HOST_DOMAIN}:8080/users/v1/landing"
  },
  {
    "idpId": "gov.va.mhv.v1",
    "friendlyName": "My HealtheVet",
    "description": "Staff login supporting PIV authentication.",
    "versionId": "v1",
    "scope": "VETERAN",
    "logoUrl": "img/mhv-logo.png",
    "loginUrl": "http://${HOST_DOMAIN}:8080/ssoiproxy/login",
    "logoutUrl": "http://${HOST_DOMAIN}:8080/ssoiproxy/logout",
    "landingPage": "http://${HOST_DOMAIN}:8080/users/v1/landing"
  }
]
EOF
)
export IDP_CONFIG_JSON

JWTISSUER_CONFIG_JSON=$(cat <<EOF
[
  {
    "rsaPublicKeyB64":"${JWT_PUBLIC_KEY}",
    "iss":"gov.va.mhv.idp.v1",
    "userType":"VETERAN"
  },
  {
    "rsaPublicKeyB64":"${JWT_PUBLIC_KEY}",
    "iss":"external.idp.test1",
    "userType":"VETERAN"
  }
]
EOF
)
export JWTISSUER_CONFIG_JSON

STAFF_USERS=$(cat <<EOF
{
  "users":
  [
    {
      "userId": "vhastaff01",
      "headers":
      {
        "ADDOMAIN": "vha",
        "ADEMAIL": "test01.Staff01@va.gov",
        "LASTNAME": "Staff01",
        "FIRSTNAME": "Test01",
        "SESSIONSCOPE": "B",
        "VAUID": "1984",
        "ADSAMACCOUNTNAME": "staff01t",
        "ACCESSROLES": "CBSS_Access",
        "DODEDIPNID": "1020002350",
        "VISTAID": "508|22228439^PN^508^USVHA|A,590|11128439^PN^590^USVHA|A,533|33328439^PN^533^USVHA|A,516|33328439^PN^516^USVHA|A,598|42409^PN^598^USVHA|A",
        "SECID": "0000028347",
        "MVIICN": "1008693670V746966",
        "VISTAIDRAW": "508|22228439^PN^508^USVHA|A^590|11128439^PN^590^USVHA|A,533|33328439^PN^533^USVHA|A,516|33328439^PN^516^USVHA|A,598|42409^PN^598^USVHA|A",
        "ORGANIZATIONID": "urn:oid:2.16.840.1.113883.4.349",
        "ASSURLEVEL": "2",
        "ORGANIZATION": "Department of Veterans Affairs",
        "PROOFINGAUTH": "VA",
        "ROLE": "role1",
        "ISSUEINSTANT": "2017-06-19T13:37:16Z",
        "AUTHNTYPE": "Direct",
        "ADUPN": "tstaff01@iam.va.local",
        "TRANSACTIONID": "00000000000000000000000036d0e30a-0ad4-5947d38c-3688-00fa4fca",
        "SSOI_LANDING_URL": "http://not_set",
        "SSOI_LOGGEDOUT_URL": "http://not_set",
        "IAMSESSION": "test"
      }
    },
    {
      "userId": "vhastaff02",
      "headers":
      {
        "LASTNAME": "Staff02",
        "FIRSTNAME": "Test02",
        "ADUPN": "tstaff01@iam.va.local",
        "VISTAID": "590|10300252^PN^590^USVHA|A,500|12744^PN^500^USVHA|A"
      }
    }
  ]
}
EOF
)

####################################################################################
# Option to pull latest, the script will no longer pull the latest version by default
# use the command sh run.sh latest to pull the latest images
#####################################################################################
if [ "$LATEST" = true ]; then
	echo -e "\nPulling the latest docker images..\n"
	docker-compose pull
fi

#start Consul first
echo -e "\nStarting Consul…\n"
docker compose up -d consul  || (echo "*** FAILED: Could not start Consul using docker-compose." && exit -1)
sleep 5s

echo "Waiting for Consul to come online"
while true; do
    RESPONSE=$(curl -s localhost:8500/v1/status/leader)
if [ "$RESPONSE" != '""' ];
    then
      break;

fi
printf "."
sleep 1s
done
echo " Consul is Ready!\n"


echo  -e "\nRegister NextGen services in consul...\n"

echo "********************* Register NextGen consul variables for var-utility-resources *********************"
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_TOKEN}" -X PUT -d "sa" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-utility-resources/cdw_user > /dev/null
curl -H "Content-Type: application/json" -H "X-Consul-Token: ${CONSUL_TOKEN}" -X PUT -d "tk1ZXVsKmafZFt8p" http://${HOST_DOMAIN}:8500/v1/kv/appconfig/${VAMF_ENVIRONMENT}/var-utility-resources/cdw_password > /dev/null


curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT $CONSUL_ADDR/$APP_CONFIG_PATH/apigateway/USERSVC_URL -d 'http://user-services:8080/users/v1/session/jwt' > /dev/null


# Expose Services via API Gateway
#curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT $CONSUL_ADDR/$REGISTER_PATH/cdw -d '{"location":"/cdw","service":"docker-cdw-service","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT $CONSUL_ADDR/$REGISTER_PATH/adr -d '{"location":"/adr","service":"docker-adr-service","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT $CONSUL_ADDR/$REGISTER_PATH/roa -d '{"location":"/roa","service":"docker-roa-service","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT $CONSUL_ADDR/$REGISTER_PATH/users -d '{"location":"/users","service":"docker-user-service","redirect":"off","headers":{"X-Forwarded-Host" : "$http_host", "X-Forwarded-proto" : "$scheme", "Host" : "$http_host"}}' > /dev/null && \
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT $CONSUL_ADDR/$REGISTER_PATH/fit-heart -d '{"location":"/fit-heart","service":"fit-heart-web","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \

curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT $CONSUL_ADDR/$REGISTER_PATH/roa-web -d '{"location":"/roa-web","service":"roa-web-80","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT $CONSUL_ADDR/$REGISTER_PATH/wayf -d '{"location":"/wayf","service":"wayf-web","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT $CONSUL_ADDR/$REGISTER_PATH/eula-web -d '{"location":"/eula-web","service":"eula-web","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT $CONSUL_ADDR/$REGISTER_PATH/pcs -d '{"location":"/pcs","service":"pcs-web","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT $CONSUL_ADDR/$REGISTER_PATH/hdr -d '{"location":"/hdr","service":"hdr-service","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT $CONSUL_ADDR/$REGISTER_PATH/sud -d '{"location":"/sud","service":"docker-sud-service","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT $CONSUL_ADDR/$REGISTER_PATH/sud-web -d '{"location":"/sud-web","service":"docker-sud-web-80","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null && \
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT $CONSUL_ADDR/$REGISTER_PATH/samlsts -d '{"location":"/samlsts","service":"saml-sts","redirect":"off","headers":{"X-Real-IP":"$remote_addr", "Host" : "$http_host"}}' > /dev/null && \
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT $CONSUL_ADDR/$REGISTER_PATH/vistadataservices -d '{  "location":"/VistaDataServices",  "service":"docker-vista-data-services",  "redirect":"off",  "request_headers":"on",  "headers": { "X-Real-IP":"$remote_addr", "X-Forwarded-Host" : "$http_host", "X-Forwarded-proto" : "$scheme" }}' > /dev/null && \
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT $CONSUL_ADDR/$REGISTER_PATH/vista-emr-services -d '{  "location":"/VistaEmrService",  "service":"vista-emr-service_v1",  "redirect":"off",  "request_headers":"on",  "headers": { "X-Real-IP":"$remote_addr", "X-Forwarded-Host" : "$http_host", "X-Forwarded-proto" : "$scheme" }}' > /dev/null && \
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT $CONSUL_ADDR/$REGISTER_PATH/trs -d '{"location":"/trs","service":"task-resources","redirect":"off","headers":{"X-Real-IP":"$remote_addr", "X-Forwarded-Host" : "$http_host", "X-Forwarded-proto" : "$scheme"}}' > /dev/null && \
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT $CONSUL_ADDR/$REGISTER_PATH/pns -d '{"location":"/pns","service":"vamf-notification-services","redirect":"off","headers":{"X-Real-IP":"$remote_addr", "X-Forwarded-Host" : "$http_host", "X-Forwarded-proto" : "$scheme" }}' > /dev/null && \
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT $CONSUL_ADDR/$REGISTER_PATH/eula -d '{"location":"/eula","service":"eula-services_v1","redirect":"off","headers":{"X-Real-IP":"$remote_addr", "X-Forwarded-Host" : "$http_host", "X-Forwarded-proto" : "$scheme" }}' > /dev/null && \
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT $CONSUL_ADDR/$REGISTER_PATH/patient-context -d '{"location":"/patient-context","service":"patient-context","redirect":"off","headers":{"X-Real-IP":"$remote_addr"}}' > /dev/null

## App configs for patient context
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT $CONSUL_ADDR/$APP_CONFIG_PATH/patient-context/APP_MONGODB_CONN -d "mongodb://mongo:27017/patientContext" > /dev/null && \
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT $CONSUL_ADDR/$APP_CONFIG_PATH/patient-context/APP_JWT_SECRET  -d "testtesttest" > /dev/null

### Add Public Keys for JWT
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT $CONSUL_ADDR/$APP_CONFIG_PATH/roa-service/JWT_PUBLIC_KEY -d $JWT_PUBLIC_KEY > /dev/null && \#curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT $CONSUL_ADDR/$APP_CONFIG_PATH/cdw-service/JWT_PUBLIC_KEY -d $JWT_PUBLIC_KEY > /dev/null && \
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT $CONSUL_ADDR/$APP_CONFIG_PATH/adr-service/JWT_PUBLIC_KEY -d $JWT_PUBLIC_KEY > /dev/null && \
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT $CONSUL_ADDR/$APP_CONFIG_PATH/hdr-service/JWT_PUBLIC_KEY -d $JWT_PUBLIC_KEY > /dev/null && \
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT $CONSUL_ADDR/$APP_CONFIG_PATH/sud-service/JWT_PUBLIC_KEY -d $JWT_PUBLIC_KEY > /dev/null && \
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT $CONSUL_ADDR/$APP_CONFIG_PATH/task-resources/JWT_PUBLIC_KEY -d $JWT_PUBLIC_KEY > /dev/null && \
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT $CONSUL_ADDR/$APP_CONFIG_PATH/pns/JWT_PUBLIC_KEY -d $JWT_PUBLIC_KEY > /dev/null && \
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT $CONSUL_ADDR/$APP_CONFIG_PATH/eula/JWT_PUBLIC_KEY -d $JWT_PUBLIC_KEY > /dev/null && \
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT $CONSUL_ADDR/$APP_CONFIG_PATH/mdws-via-adapter/JWT_PUBLIC_KEY -d $JWT_PUBLIC_KEY > /dev/null && \
curl -H "X-Consul-Token: $CONSUL_TOKEN" -s -X PUT $CONSUL_ADDR/$APP_CONFIG_PATH/user-services/JWT_PUBLIC_KEY -d $JWT_PUBLIC_KEY > /dev/null && \

export CONSUL_PATH=/v1/kv/appconfig/local/pgd-fhir-services
export CONSUL_TOKEN=7BE784A4-7498-4469-BE2F-9C3B9444DFEF
export CONSUL_PATH_RBAC=/v1/kv/appconfig/local/pgd-rbac-services

echo "Setting PGD environment variables"

#curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d "testtesttest" $CONSUL_SERVER$CONSUL_PATH/JWT_SECRET

# curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwzNSL1sillVk6Hr7c9C6NDAH1ZwX+DFVKhagN4LDLQXHvz8Xlj3zajroaPLTQwJ+dxBp8ADpxFnSpGS65sXLD0K7XHRdjM3uubWHpNdoQs4xiZLkBFBO6TJn3Q2i8CX55UtznTrrKItQQdwHkaYF1uZ1UZSMlXEwLwa6DId1esARg9Y/dlmXiFvtUlQDe+VuzOGNwXqEu6rVtEIgI/SDG7dBwamXXobQ31M7GkNPhe9+bQTRw3HrA191vCy84w963zUqEzuPmD+aJOjvIJTvzP8cOc1C4ffmCBoIUaUDRM5myQBgFqHJOFbPnrqmzSTXwd5kDC8103yXEJZMvaXTEQIDAQAB" $CONSUL_SERVER$CONSUL_PATH/JWT_PUBLIC_KEY

curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d "jdbc:oracle:thin:@database:1521:xe" $CONSUL_SERVER$CONSUL_PATH/ORACLE_URL


echo "\nSetting RBAC environment variables"



echo "\nDone.."

############  BEGIN VAULT CONFIG #####################
# start Vault
echo -e "\nStarting Vault…\n"
docker compose up -d vault  || (echo "*** FAILED: Could not start Vault using docker-compose." && exit -1)

# Check that Vault is up
echo "Waiting for Vault to come online"
while true; do
    RESPONSE=$(curl --write-out %{http_code} -s -o /dev/null localhost:8202/v1/sys/health)
if [ $RESPONSE = "200" ];
    then
      break;
fi
printf "."
sleep 1s
done
echo "Vault is Ready!\n"
echo $(curl localhost:8202/v1/sys/health)

echo "Setup Vault"
# allow time to bootstrap AppRole
sleep 20s

### Set up Vault
export VAMF_ENVIRNOMENT=local
export ADMIN_VAULT_TOKEN=92389390-D796-490A-A91F-44CA582AA661
export VAULT_ADDR=http://localhost:8202

## Create Rule
echo '{"rules":"{\"path\": {\"secret/'${VAMF_ENVIRNOMENT}'/*\":{\"policy\":\"read\"}}}"}' > ${VAMF_ENVIRNOMENT}.json

## Create a Policy and assign the Rule
curl -s -X POST -H "X-Vault-Token: $ADMIN_VAULT_TOKEN" -d @${VAMF_ENVIRNOMENT}.json $VAULT_ADDR/v1/sys/policy/${VAMF_ENVIRNOMENT}-read > /dev/null

### MODIFY THE CIDR BLOCK TO MATCH THE ENVIROMMENT ######
## Create a Role and add the Policy
#########################################################
curl -s -X POST -H "X-Vault-Token: $ADMIN_VAULT_TOKEN" -d '{ "policies":"'${VAMF_ENVIRNOMENT}'-read", "bind_secret_id":false,"bound_cidr_list":"0.0.0.0/0", "role_id":"'${VAMF_ENVIRNOMENT}'-read"}' $VAULT_ADDR/v1/auth/approle/role/${VAMF_ENVIRNOMENT}-read > /dev/null

### Add JWT secrets for microservice
curl -s -X POST -H "X-Vault-Token: $ADMIN_VAULT_TOKEN" -d '{"DB_MOBILEHEALTH_USERNAME" : "sa", "DB_MOBILEHEALTH_PASSWORD" : "IS4skwnfuglx928361V"}' $VAULT_ADDR/v1/secret/${VAMF_ENVIRNOMENT}/cdw-service > /dev/null && \
curl -s -X POST -H "X-Vault-Token: $ADMIN_VAULT_TOKEN" -d '{"DB_ROA_USERNAME" : "HADB", "DB_ROA_PASSWORD" : "m0ckpass01"}' $VAULT_ADDR/v1/secret/${VAMF_ENVIRNOMENT}/roa-service > /dev/null && \
curl -s -X POST -H "X-Vault-Token: $ADMIN_VAULT_TOKEN" -d '{"DB_SUD_USERNAME" : "HADB", "DB_SUD_PASSWORD" : "m0ckpass01"}' $VAULT_ADDR/v1/secret/${VAMF_ENVIRNOMENT}/sud-service > /dev/null && \
curl -s -X POST -H "X-Vault-Token: $ADMIN_VAULT_TOKEN" -d '{"ORACLEDB_USER" : "HADB", "ORACLEDB_PASSWORD" : "m0ckpass01"}' $VAULT_ADDR/v1/secret/${VAMF_ENVIRNOMENT}/quartz-scheduler > /dev/null && \
curl -s -X POST -H "X-Vault-Token: $ADMIN_VAULT_TOKEN" -d '{"TRUSTSTORE_PASSWORD" : "password"}' $VAULT_ADDR/v1/secret/${VAMF_ENVIRNOMENT}/mdws-via-adapter > /dev/null && \
curl -s -X POST -H "X-Vault-Token: $ADMIN_VAULT_TOKEN" -d @jwt-private.json $VAULT_ADDR/v1/secret/${VAMF_ENVIRNOMENT}/user-services > /dev/null
curl -s -X POST -H "X-Vault-Token: $ADMIN_VAULT_TOKEN" -d @jwt-private.json $VAULT_ADDR/v1/secret/${VAMF_ENVIRNOMENT}/pgd-fhir-services> /dev/null


set-pgd-consul-vars.sh

echo "Setting PGD environment variables"

export JWT_PUBLIC_KEY=MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwRs7d+tRAuK4soDInuKp51lTTTXWN8okXR3/gSHiIB+q7vZeAGWA8ULjCYuECLde8tS6OxzZwSj/oM0kZBXuHKYq2ukWKjTEmOwlpQW4vBpX60bcx5rXwoEjbcWghK9oVWwY4OwbAJ4TYklkUHZC5buQ+8RU59u6FIWSv2N3D9VBkfYsHvp3O4aXVaE8dZ0dxldUdv/WePoLkCeUYgLsGyDg/zsZvDrX1+yvNsjNNmB/ksJSoptMv9CfyUpSXsfov+8GhBCtgzDvgn32kw79wPsrTkgnC0DRtuv+y3qCXX+ZJ6cbJ31tVus9AadUn2CrWJur6/KRYsniQaEnfA43hwIDAQAB
export CONSUL_TOKEN=7BE784A4-7498-4469-BE2F-9C3B9444DFEF
export CONSUL_MASTER_TOKEN=7BE784A4-7498-4469-BE2F-9C3B9444DFEF
export CONSUL_HTTP_ADDR=http://localhost:8500
export CONSUL_ADDR=http://localhost:8500/v1/kv
export REGISTER_PATH=vamf/local/apigateway/1.0/services
export APP_CONFIG_PATH=appconfig/local
export CONSUL_HTTP_PATH=http://localhost:8500/
export VAMF_ENVIRONMENT=local
export INIT_ELASTICSEARCH=true
export INIT_DATABASE=true
export SAFEGUARD_SSL_ENABLED=true

# Set minimum and maximum heap sizes on a service-by-service basis

export CONSUL_PATH=/v1/kv/appconfig/local/pgd-fhir-services
export CONSUL_TOKEN=7BE784A4-7498-4469-BE2F-9C3B9444DFEF
export CONSUL_PATH_RBAC=/v1/kv/appconfig/local/pgd-rbac-services

echo "Setting PGD environment variables"

#curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d "testtesttest" $CONSUL_SERVER$CONSUL_PATH/JWT_SECRET

# curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwzNSL1sillVk6Hr7c9C6NDAH1ZwX+DFVKhagN4LDLQXHvz8Xlj3zajroaPLTQwJ+dxBp8ADpxFnSpGS65sXLD0K7XHRdjM3uubWHpNdoQs4xiZLkBFBO6TJn3Q2i8CX55UtznTrrKItQQdwHkaYF1uZ1UZSMlXEwLwa6DId1esARg9Y/dlmXiFvtUlQDe+VuzOGNwXqEu6rVtEIgI/SDG7dBwamXXobQ31M7GkNPhe9+bQTRw3HrA191vCy84w963zUqEzuPmD+aJOjvIJTvzP8cOc1C4ffmCBoIUaUDRM5myQBgFqHJOFbPnrqmzSTXwd5kDC8103yXEJZMvaXTEQIDAQAB" $CONSUL_SERVER$CONSUL_PATH/JWT_PUBLIC_KEY

curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d "jdbc:oracle:thin:@database:1521:xe" $CONSUL_SERVER$CONSUL_PATH/ORACLE_URL

curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d "pgd_user" $CONSUL_SERVER$CONSUL_PATH/ORACLE_USER

curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d "1234" $CONSUL_SERVER$CONSUL_PATH/ORACLE_PASSWORD

curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d "urn:uuid:2.16.840.1.113883.4.349" $CONSUL_SERVER$CONSUL_PATH/EDIPID_IDENTIFIER_SYSTEM

curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d "http://example.com/icn-system" $CONSUL_SERVER$CONSUL_PATH/ICN_IDENTIFIER_SYSTEM

echo "\nSetting RBAC environment variables"

#curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d "testtesttest" $CONSUL_SERVER$CONSUL_PATH_RBAC/JWT_SECRET

curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d "@./roles-config-dstu2.json" $CONSUL_SERVER$CONSUL_PATH_RBAC/ROLES_CONFIG

curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d false $CONSUL_SERVER$CONSUL_PATH_RBAC/VERBOSE_ERRORS

echo "\nDone.."




DTR_ORG="ckm"
DTR_PREFIX="https://mobileapps.vaftl.us:9250"
CONSUL_HTTP_ADDR="localhost:8500"
VAMF_ENVIRONMENT="local"
CONSUL_MASTER_TOKEN="7BE784A4-7498-4469-BE2F-9C3B9444DFEF"
TOKEN="7BE784A4-7498-4469-BE2F-9C3B9444DFEF"
ENV="local"

CONSUL_SERVER=http://$CONSUL_HTTP_ADDR
CONSUL_PATH=/v1/kv/appconfig/$VAMF_ENVIRONMENT/pgd-fhir-services
CONSUL_TOKEN=$CONSUL_MASTER_TOKEN
CONSUL_PATH_RBAC=/v1/kv/appconfig/$VAMF_ENVIRONMENT/pgd-rbac-services

echo "Setting PGD environment variables"

#curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d "testtesttest" $CONSUL_SERVER$CONSUL_PATH/JWT_SECRET

# curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwzNSL1sillVk6Hr7c9C6NDAH1ZwX+DFVKhagN4LDLQXHvz8Xlj3zajroaPLTQwJ+dxBp8ADpxFnSpGS65sXLD0K7XHRdjM3uubWHpNdoQs4xiZLkBFBO6TJn3Q2i8CX55UtznTrrKItQQdwHkaYF1uZ1UZSMlXEwLwa6DId1esARg9Y/dlmXiFvtUlQDe+VuzOGNwXqEu6rVtEIgI/SDG7dBwamXXobQ31M7GkNPhe9+bQTRw3HrA191vCy84w963zUqEzuPmD+aJOjvIJTvzP8cOc1C4ffmCBoIUaUDRM5myQBgFqHJOFbPnrqmzSTXwd5kDC8103yXEJZMvaXTEQIDAQAB" $CONSUL_SERVER$CONSUL_PATH/JWT_PUBLIC_KEY


curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d "jdbc:oracle:thin:@database:1521:xe" $CONSUL_SERVER$CONSUL_PATH/ORACLE_URL

curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d "pgd_user" $CONSUL_SERVER$CONSUL_PATH/ORACLE_USER

curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d "1234" $CONSUL_SERVER$CONSUL_PATH/ORACLE_PASSWORD

curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d "http://example.com/edipid-system" $CONSUL_SERVER$CONSUL_PATH/EDIPID_IDENTIFIER_SYSTEM

curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d "urn:uuid:2.16.840.1.113883.4.349" $CONSUL_SERVER$CONSUL_PATH/ICN_IDENTIFIER_SYSTEM
curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d "urn:uuid:2.16.840.1.113883.4.349" $CONSUL_SERVER$CONSUL_PATH/EDIPID_IDENTIFIER_SYSTEM
curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d "urn:uuid:2.16.840.1.113883.4.349" $CONSUL_SERVER$CONSUL_PATH/PGD_ICN_IDENTIFIER_SYSTEM
echo "\nSetting RBAC environment variables"

#curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d "testtesttest" $CONSUL_SERVER$CONSUL_PATH_RBAC/JWT_SECRET

curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d "@./roles-config-dstu2.json" $CONSUL_SERVER$CONSUL_PATH_RBAC/ROLES_CONFIG

curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d false $CONSUL_SERVER$CONSUL_PATH_RBAC/VERBOSE_ERRORS
curl -H "X-Consul-Token : $CONSUL_TOKEN" -X PUT -d "@./jwtRSA512.key.pub" $CONSUL_SERVER$CONSUL_PATH_RBAC/JWT_KEY
echo "\nDone.."


### Start the mocks first and then wait 20s for them to come up before starting the services
echo -e "\n*** Starting Mocks ***\n"
docker-compose up -d pie-cdw-mock mock-roa mock-via mock-mvi qss-mock redis mock-wstrust || (echo "*** FAILED: Could not start mocks using docker-compose." && exit -1)
sleep 25s

## Start the services based on the provided CLI parameters ##################
# The min set us used by the example app even if it's not selected explicitly
##############################################################################
echo -e "\nStarting API Gateway\n\n>User Services\n*ROA\n*SUD\n*WAYF\n\nEULA and example Apps\n\n*Fit Heart\n*Mobile-Kidney\n\nand SSOE Mock"
docker-compose up -d adr-services patient-context apigateway user-services roa-services roa-web wayf-web eula-services_v1 eula-web iamssoe-proxy-mock iamssoe-db-mock  || (echo "*** FAILED: Could not start containers using docker-compose." && exit -1)
sleep 10s
echo -e "\n*** Opening docker logs… ***\n"
docker-create-json.sh
docker-compose logs -f