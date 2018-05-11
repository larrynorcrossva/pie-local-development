#!/usr/bin/env bash

. app.env
. start.sh

docker network create varnextgenlocalenvironment_default

function use_vpn(){
    # URLs for CDW, MDWS, and VIA will reference internal URLs based on this setting.  Note that VPN use is required when
    #   working outside of AbleVets HQ
    echo "\nSetting VPN configuration\n"
    CDW_HOST=${CDW_HOST_AV_VPN}
    MDWS_HOST=${MDWS_HOST_AV_VPN}
}

function set_stack_mode_dev(){
    # In dev mode, all applications will pull from dev as the DTR PREFIX/ORG
    export STACK_MODE=dev
}

function set_stack_mode_test(){
    # In test mode, all applications will pull from the ECR based on the version tagged in this dev/release branch.
    export STACK_MODE=test
}

while [[ "$#" > 0 ]]; do case $1 in
  ### VPN Mode
  --vpn) use_vpn; shift;;

  --test|--test-mode) set_stack_mode_test; shift;;
  --dev|--dev-mode) set_stack_mode_dev; shift;;

  a|all|-a|--all) start_all; shift;;
  av|all-vet|-av|--all-vet) start_all_vet; shift;;
  as|all-staff|-as|--all-staff) start_all_staff; shift;;
  c|core|-c|--core) start_core; shift;;
  f|fixtures|-f|--fixtures) start_fixtures; shift;;
  s|ss|-s|--shared-services) start_shared_services; shift;;
  d|data|-d|--data-seed) start_data_seed; shift;;
  v|vet|-v|--vet) start_vet; shift;;
  staff|--staff) start_staff; shift;;
  var|--var) start_var; shift;;
  sm|--sm) start_sm; shift;;
  *) echo "Unknown parameter: $1"; exit 1;;
esac; shift; done
