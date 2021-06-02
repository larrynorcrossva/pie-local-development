#!/bin/bash
set -e
set -o pipefail

printf '\n\n\n\n**** STARTING BUILD PROCESS ********************\n\n'

if [ -z $APP_NAME ] || [ -z $APP_VERSION ]; then
    source ./nextgen/app.env
fi

./gradlew clean build :bootJar

cp build/libs/vaos-export-service*.jar nextgen/vaos-export-service.jar


printf '\n\n**** Package Build Artifacts ********************\n'

tar -zcvf $APP_NAME.BUILD-$BUILD_NUMBER.tar.gz nextgen/  docker-compose.yml

printf '\n\n**** COMPLETED build.sh ********************\n\n\n\n'
