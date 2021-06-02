#!/bin/bash

printf '\nCompile vaos-export-service code\n'

. ./nextgen/set-env.sh

./nextgen/build.sh || { echo "FATAL: Failed to build vaos-export-service"; exit 1; }

. ./nextgen/app.env

printf '\nStop and remove running vaos-export-service containers\n'
docker-compose -f docker-compose.yml down || { echo "FATAL: Failed to stop and remove running containers"; exit 1; }

printf '\nSetting needed environment variables in Consul'
./nextgen/set_consul_vars.sh || { echo "FATAL: Failed to set needed environment variables in Consul"; exit 1; }

printf '\nStarting vaos-export-service containers\n'
docker-compose -f docker-compose.yml up -d --build || { echo "FATAL: Failed to start vaos-export-service containers"; exit 1; }

printf '\nOpening docker logs\n'
docker-compose logs -f
