#!/usr/bin/env bash

source app.env

docker-compose -f docker-compose-fixtures.yml down

docker-compose -f docker-compose-fixtures.yml up -d

echo "\nOpening docker logs for fixtures... \n"
docker-compose -f docker-compose-fixtures.yml logs -f
