#!/bin/bash

source ./nextgen/set-env.sh
source ./nextgen/app.env

printf '\nShutting down vaos-export-service …\n'
docker-compose down || (echo "*** FAILED: Failed to stop running containers using docker-compose." && exit -1)
