#!/usr/bin/env bash

. app.env

function stop_core(){
    docker-compose stop registrator apigateway mock-mvi user-services swagger-ui wayf-web redis zipkin consul
}

function stop_fixtures(){
    docker-compose stop mock-roa var-mongo-db-mock var-oracle-db-mock video-visits-mongo-mock mongo
}

function stop_data_seed(){
    docker-compose stop var-mongo-data-seed
}

function stop_shared_services(){
    docker-compose stop var-messaging-microservice facility-service vmr-mock-service video-visits-service #messaging-publisher
}

function stop_vet(){
    docker-compose stop roa-services roa-web veteran-video-connect-service iamssoe-proxy-mock iamssoe-db-mock
}

function stop_staff(){
    docker-compose stop patient-context mock-wstrust ssoi-mock mock-saml-idp saml-sts sud-service sud-web pcs-web personal-preference-service vista-data-services mdws-via-adapter staff-video-connect-service
}

function stop_var(){
    docker-compose stop var-resources var-web
}

function stop_sm(){
    docker-compose stop scheduling-manager-resources scheduling-manager-web
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