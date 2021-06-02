# Services are organized into buckets.  When maintained in a single location, the other service scripts can source this file to incorporate changes.

fixtures=(mock-roa var-mongo-db-mock var-oracle-db-mock video-visits-mongo-mock vvs-mongo-mock mock-adr mongo var-cdw-mock mock-mvi)

vista=(vaos-vista) # start vista independently for proof-of-concept...

via=(via-oracle via-weblogic)

core=(registrator apigateway user-services mobile-mvi-service wayf-web authorization-rules-service redis)

utilities=(swagger-ui zipkin)

ss=(var-messaging-microservice facility-service vmr-mock-service video-visits-service messaging-publisher vista-scheduling-service appointment-service cdw-service task-resources adr-service)

vet=(vamf-quartz-scheduler vamf-notification-services patient-context pgd-ccda fit-heart-web database pgd-fhir-services rbac-api roa-services roa-web eula-service eula-web veteran-video-connect-service iamssoe-proxy-mock iamssoe-db-mock)

staff=(patient-context mock-wstrust ssoi-mock sud-service sud-web pcs-service pcs-web personal-preference-service vista-data-services mdws-via-adapter staff-video-connect-service vista-emr-service)

var=()

sm=()

vats=()


# Comment the following to disable the regular URLs
ss+=(var-messaging-microservice facility-service)
var+=(var-resources var-web)
sm+=(scheduling-manager-resources scheduling-manager-web)
vats+=(var-utility-resources var-utility-web)

# Uncomment the following to enable beta deployments
# vats+=(var-utility-resources-beta var-utility-web-beta)
# sm+=(scheduling-manager-resources-beta scheduling-manager-web-beta)
# var+=(var-resources-beta var-web-beta)
# ss+=(var-messaging-microservice-beta facility-service-beta)

