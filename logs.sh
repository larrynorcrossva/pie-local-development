#!/usr/bin/env bash

function logs_core(){
    echo "\nOpening docker logs for NextGen core services... \n"
    docker-compose logs -f consul registrator apigateway mock-mvi user-services swagger-ui wayf-web redis zipkin
}

function logs_fixtures(){
    echo "\nOpening docker logs for fixtures... \n"
    docker-compose logs -f mock-roa var-mongo-db-mock var-oracle-db-mock video-visits-mongo-mock mongo
}

function logs_data_seed(){
    echo "\nOpening docker logs for data seed... \n"
    docker-compose logs -f var-mongo-data-seed
}

function logs_vet(){
    echo "\nOpening docker logs for vet services... \n"
    docker-compose logs -f roa-services roa-web veteran-video-connect-service iamssoe-proxy-mock iamssoe-db-mock
}

function logs_staff(){
    echo "\nOpening docker logs for staff services... \n"
    docker-compose logs -f patient-context mock-wstrust ssoi-mock mock-saml-idp saml-sts sud-service sud-web pcs-web personal-preference-service vista-data-services mdws-via-adapter staff-video-connect-service
}

function logs_var(){
    echo "\nOpening docker logs for VAR... \n"
    docker-compose logs -f var-resources var-web
}

function logs_sm(){
    echo "\nOpening docker logs for SM... \n"
    docker-compose logs -f scheduling-manager-resources scheduling-manager-web
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