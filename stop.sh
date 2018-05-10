#!/usr/bin/env bash

function stop_core(){
    docker-compose down
}

function stop_fixtures(){
    docker-compose -f docker-compose-fixtures.yml down
}

function stop_data_seed(){
    docker-compose -f docker-compose-data-seed.yml down
}

function stop_shared_services(){
    docker-compose -f docker-compose-shared-services.yml down
}

function stop_vet(){
    docker-compose -f docker-compose-services-vet.yml down
}

function stop_staff(){
    docker-compose -f docker-compose-services-staff.yml down
}

function stop_var(){
    docker-compose -f docker-compose-var.yml down
}

function stop_sm(){
    docker-compose -f docker-compose-sm.yml down
}

function stop_all(){
    stop_core;
    stop_fixtures;
    stop_data_seed;
    stop_shared_services;
    stop_vet;
    stop_staff;
    stop_var;
    stop_sm;
    stop_all;
}

while [[ "$#" > 0 ]]; do case $1 in
  a|all|-a|--all) stop_all; shift;;
  c|core|-c|--core) stop_core; shift;;
  f|fixtures|-f|--fixtures) stop_fixtures; shift;;
  s|ss|-s|--shared-services) stop_shared_services; shift;;
  v|vet|-v|--vet) stop_vet; shift;;
  staff|--staff) stop_staff; shift;;
  var|--var) stop_var; shift;;
  sm|--sm) stop_sm; shift;;
  *) echo "Unknown parameter: $1"; exit 1;;
esac; shift; done

echo -e "\nCleaning up dangling volumes"
docker volume rm $(docker volume ls -f dangling=true -q)

# removes cache file created by docker from var-oracle-db-mock
echo -e "\nCleaning up generated docker cache file from oracle-db"
rm ./var-oracle-db-mock/.cache