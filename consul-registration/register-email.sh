#!/usr/bin/env bash

. app.env






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
