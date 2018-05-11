function start_all_shared(){
    start_core
    sleep 15
    start_fixtures
    sleep 15
    start_data_seed
    start_shared_services
}

function start_all(){
    start_all_shared
    sleep 15
    start_vet
    start_staff
    sleep 15
    start_var
    start_sm
}

function start_all_vet(){
    start_all_shared
    sleep 15
    start_vet
    sleep 15
    start_var
}

function start_all_staff(){
    start_all_shared
    sleep 15
    start_staff
    sleep 15
    start_sm
}

function start_fixtures(){
    echo "\nStarting Fixtures\n"
    docker-compose stop mock-roa var-mongo-db-mock var-oracle-db-mock video-visits-mongo-mock mongo
    docker-compose pull mock-roa var-mongo-db-mock var-oracle-db-mock video-visits-mongo-mock mongo
    docker-compose up -d mock-roa var-mongo-db-mock var-oracle-db-mock video-visits-mongo-mock mongo
}

function start_data_seed(){
    echo "\nStarting Data Seed\n"
    docker-compose stop var-mongo-data-seed
    docker-compose pull var-mongo-data-seed
    docker-compose up -d var-mongo-data-seed
}

function start_core(){
    ## Start Consul first
    echo "\nStarting Consul and Vault\n"
    # docker-compose up -d consul vault || (echo "*** FAILED: Could not start Consul or Vault using docker-compose." && exit -1)
    docker-compose pull consul
    docker-compose up -d consul || (echo "*** FAILED: Could not start Consul or Vault using docker-compose." && exit -1)

    echo "\nWaiting 15s for Consul and Vault...\n"
    sleep 15s

    echo "\nRegistering core services to Consul... \n"
    ./consul-registration/register-core.sh

    docker-compose pull registrator apigateway mock-mvi user-services swagger-ui wayf-web redis zipkin
    docker-compose up -d registrator apigateway mock-mvi user-services swagger-ui wayf-web redis zipkin || (echo "*** FAILED: Could not start core containers using docker-compose." && exit -1)
}

function start_shared_services(){
    ./consul-registration/register-shared-services.sh
    docker-compose stop var-messaging-microservice facility-service vmr-mock-service video-visits-service #messaging-publisher
    docker-compose pull var-messaging-microservice facility-service vmr-mock-service video-visits-service #messaging-publisher
    docker-compose up -d var-messaging-microservice facility-service vmr-mock-service video-visits-service #messaging-publisher
}

function start_vet(){
    docker-compose stop roa-services roa-web veteran-video-connect-service iamssoe-proxy-mock iamssoe-db-mock
    docker-compose pull roa-services roa-web veteran-video-connect-service iamssoe-proxy-mock iamssoe-db-mock
    docker-compose up -d roa-services roa-web veteran-video-connect-service iamssoe-proxy-mock iamssoe-db-mock
}

function start_staff(){
    docker-compose stop patient-context mock-wstrust ssoi-mock mock-saml-idp saml-sts sud-service sud-web pcs-web personal-preference-service vista-data-services mdws-via-adapter
    docker-compose pull patient-context mock-wstrust ssoi-mock mock-saml-idp saml-sts sud-service sud-web pcs-web personal-preference-service vista-data-services mdws-via-adapter
    docker-compose up -d patient-context mock-wstrust ssoi-mock mock-saml-idp saml-sts sud-service sud-web pcs-web personal-preference-service vista-data-services mdws-via-adapter
}

function start_var(){
    ./consul-registration/register-var.sh
    docker-compose stop var-resources var-web
    docker-compose pull var-resources var-web
    docker-compose up -d var-resources var-web
}

function start_sm(){
    ./consul-registration/register-sm.sh
    docker-compose stop scheduling-manager-resources scheduling-manager-web
    docker-compose pull scheduling-manager-resources scheduling-manager-web
    docker-compose up -d scheduling-manager-resources scheduling-manager-web
}

