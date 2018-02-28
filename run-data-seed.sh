#!/usr/bin/env bash

source app.env

docker-compose -f docker-compose-data-seed.yml down

docker-compose -f docker-compose-data-seed.yml pull

docker-compose -f docker-compose-data-seed.yml up -d

echo "\nOpening docker logs for data seed... \n"
docker-compose -f docker-compose-data-seed.yml logs -f