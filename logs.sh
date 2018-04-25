#!/usr/bin/env bash

function logs_core(){
    echo "\nOpening docker logs for NextGen core services... \n"
    docker-compose logs -f
}

function logs_fixtures(){
    echo "\nOpening docker logs for fixtures... \n"
    docker-compose -f docker-compose-fixtures.yml logs -f
}

function logs_data_seed(){
    echo "\nOpening docker logs for data seed... \n"
    docker-compose -f docker-compose-data-seed.yml logs -f
}

function logs_vet(){
    echo "\nOpening docker logs for vet services... \n"
    docker-compose -f docker-compose-vet.yml logs -f
}

function logs_staff(){
    echo "\nOpening docker logs for staff services... \n"
    docker-compose -f docker-compose-staff.yml logs -f
}

function logs_var(){
    echo "\nOpening docker logs for VAR... \n"
    docker-compose -f docker-compose-var.yml logs -f
}

function logs_sm(){
    echo "\nOpening docker logs for SM... \n"
    docker-compose -f docker-compose-sm.yml logs -f
}

while [[ "$#" > 0 ]]; do case $1 in
  c|core|-c|--core) logs_core; shift;;
  f|-f|--fixtures) logs_fixtures; shift;;
  s|ss|-s|--shared-services) logs_shared_services; shift;;
  d|data|-d|--data-seed) logs_data_seed; shift;;
  v|vet|-v|--vet) logs_vet; shift;;
  staff|--staff) logs_staff; shift;;
  var|--var) logs_var; shift;;
  sm|--sm) logs_sm; shift;;
  *) echo "Unknown parameter: $1"; exit 1;;
esac; shift; done