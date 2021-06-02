#!/bin/bash

echo -e "\nSourcing nextgen-stack/set-dev-envars.sh\n"
source ../nextgen-stack/set-dev-envars.sh

DTR_URL=${DTR_URL:-$DTR_URL_ECE}
DTR_DIR=${DTR_DIR:-/vamis}

export DTR_URL=$DTR_URL
export DTR_DIR=$DTR_DIR
export CONSUL_HTTP_ADDR=consul:8500
export CONSUL_MASTER_TOKEN=7BE784A4-7498-4469-BE2F-9C3B9444DFEF
export CONSUL_TOKEN=7BE784A4-7498-4469-BE2F-9C3B9444DFEF
export VAMF_ENVIRONMENT=local
export JWT_SECRET=testtesttest
export TRACE_URL=http://zipkin:9411/api/v1/spans
export HOST_DOMAIN=localhost
export SOURCE_BUILD_NUMBER=${SOURCE_BUILD_NUMBER:=latest}
export DEV_ZIPKIN_DISABLED="true"
export CDW_DB_NAME=OIA_MOBILEHEALTH
