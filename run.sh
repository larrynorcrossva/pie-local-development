#!/usr/bin/env bash

source app.env

docker network create varnextgenlocalenvironment_default

function start_all(){
    ./run-nextgen-infrastructure.sh
    ./run-fixtures.sh
    sleep 10
    ./run-data-seed.sh
    ./run-shared-services.sh
    sleep 10
    ./run-var.sh
    ./run-sm.sh
}

function start_core(){
    ## Start Consul first
    echo "\nStarting Consul and Vault\n"
    docker-compose up -d consul vault || (echo "*** FAILED: Could not start Consul or Vault using docker-compose." && exit -1)

    echo "\nWaiting 15s for Consul and Vault...\n"
    sleep 15s

    echo "\nRegistering core services to Consul... \n"
    ./consul-registration/register-core.sh

    docker-compose up -d || (echo "*** FAILED: Could not start core containers using docker-compose." && exit -1)
}

function start_fixtures(){
    docker-compose -f docker-compose-fixtures.yml down
    docker-compose -f docker-compose-fixtures.yml pull
    docker-compose -f docker-compose-fixtures.yml up -d
}

function start_data_seed(){
    docker-compose -f docker-compose-data-seed.yml down
    docker-compose -f docker-compose-data-seed.yml pull
    docker-compose -f docker-compose-data-seed.yml up -d
}

function start_shared_services(){
    ./consul-registration/register-shared-services.sh
    docker-compose -f docker-compose-shared-services.yml down
    docker-compose -f docker-compose-shared-services.yml pull
    docker-compose -f docker-compose-shared-services.yml up -d
}

function start_vet(){
    docker-compose -f docker-compose-services-vet.yml down
    docker-compose -f docker-compose-services-vet.yml pull
    docker-compose -f docker-compose-services-vet.yml up -d
}

function start_staff(){
    docker-compose -f docker-compose-staff.yml down
    docker-compose -f docker-compose-staff.yml pull
    docker-compose -f docker-compose-staff.yml up -d
}

function start_var(){
    ./consul-registration/register-var.sh
    docker-compose -f docker-compose-var.yml down
    docker-compose -f docker-compose-var.yml pull
    docker-compose -f docker-compose-var.yml up -d
}

function start_sm(){
    ./consul-registration/register-sm.sh
    docker-compose -f docker-compose-sm.yml down
    docker-compose -f docker-compose-sm.yml pull
    docker-compose -f docker-compose-sm.yml up -d
}

while [[ "$#" > 0 ]]; do case $1 in
  a|all|-a|--all) start_all; shift;;
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