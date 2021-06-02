#!/bin/bash
set -eo pipefail

## Display warning messages if required environment variables are not set:
if [ -z ${CONSUL_HTTP_ADDR} ]; then echo "Warning; CONSUL_HTTP_ADDR not set"; fi
if [ -z ${CONSUL_TOKEN} ]; then echo "Warning; CONSUL_TOKEN not set"; fi
if [ -z ${CONSUL_PATH} ]; then echo "Warning; CONSUL_PATH not set"; fi
if [ -z ${VAMF_ENVIRONMENT} ]; then echo "Warning; VAMF_ENVIRONMENT not set"; fi

run_service_with_envconsul () {
  local config
  if [ ! -z "$1" ]; then
      config="-config $1"
  fi

  ENVCONSUL_OPTS="-consul-addr $CONSUL_HTTP_ADDR -consul-token $CONSUL_TOKEN -prefix $CONSUL_PATH"
  # Splay Option
  if [ -n "$SPLAY_DURATION" ]; then
      ENVCONSUL_OPTS="$ENVCONSUL_OPTS -exec-splay=$SPLAY_DURATION"
  else
      ENVCONSUL_OPTS="$ENVCONSUL_OPTS -exec-splay=1s"
  fi

  if [ -n "$CONSUL_APPDYNAMICS_PATH" ]; then
    echo "Reading App Dynamics global configs from Consul on: $CONSUL_APPDYNAMICS_PATH"
    ENVCONSUL_OPTS="$ENVCONSUL_OPTS -prefix $CONSUL_APPDYNAMICS_PATH"
  fi

  # Run the service under the control of envconsul to ensure that configuration
  # changes in Consul or Vault cause auto-restart
  envconsul $ENVCONSUL_OPTS \
            $config \
            /run-service.sh
}

# We first check consul for keys that need to be imported to the java store (this will change to vault)
# If a vault address is set we use vault to pull in our secrets as environment variables
# if there is no vault address we use consul
# the USE_ENVCONSUL is for local development when no consul or vault servers are present and should not be set beyond local dev
if [ "${USE_ENVCONSUL}" == "false" ]; then
  echo "Consul not enabled"
  /run-service.sh
elif [ -n "${VAULT_ADDR}" ]; then
  echo "Using Vault for Secrets and Consul for Configs"
  ### get vault token from approle, assumes the app role id is set to the environment name
  export VAULT_TOKEN=$(curl -X POST -d '{"role_id":"'$VAMF_ENVIRONMENT'-read" }' $VAULT_ADDR/v1/auth/approle/login | jq -r '.auth.client_token')
  #### the vault address has to be set in a config file, cannot be passed as a CLI paramater
  echo vault '{ address = "'$VAULT_ADDR'" token = "'$VAULT_TOKEN'" renew_token = true }' secret '{ path = "'$SECRET_PATH'" no_prefix = true }' > vault_config.hcl

  if [ -n "$VAULT_APPDYNAMICS_PATH" ]; then
    echo "Reading App Dynamics global configs from Vault on: $VAULT_APPDYNAMICS_PATH"
    echo secret '{ path = "'$VAULT_APPDYNAMICS_PATH'" no_prefix = true }' >> vault_config.hcl
  fi

  run_service_with_envconsul "vault_config.hcl"
else
  echo "Vault address not present, using Consul for app configs"
  run_service_with_envconsul
fi
