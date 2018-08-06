# Services are organized into buckets.  When maintained in a single location, the other service scripts can source this file to incorporate changes.

fixtures=(mock-roa var-mongo-db-mock var-oracle-db-mock video-visits-mongo-mock vvs-mongo-mock)

via=(via-mock-engine via-oracle via-vista via-weblogic)

#core=(registrator apigateway user-services mock-mvi mock-mvi-ablevets wayf-web redis )
core=(registrator apigateway user-services mock-mvi-ablevets wayf-web redis )

utilities=(swagger-ui zipkin)

ss=(var-messaging-microservice facility-service vmr-mock-service video-visits-service messaging-publisher vista-scheduling-service appointment-service)
# removed task-resources

vet=(roa-services roa-web eula-service eula-web veteran-video-connect-service iamssoe-proxy-mock iamssoe-db-mock)

staff=(patient-context mock-wstrust ssoi-mock sud-service sud-web pcs-web personal-preference-service vista-data-services mdws-via-adapter staff-video-connect-service)

var=(var-resources var-web)

sm=(scheduling-manager-resources scheduling-manager-web)

