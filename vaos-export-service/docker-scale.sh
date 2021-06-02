#!/bin/bash

#Run this command from the location of docker-compose file using this command:  ./nextgen/docker-scale.sh NUMBER
docker-compose -f docker-compose.yml up -d --scale vaos-export-service=$1