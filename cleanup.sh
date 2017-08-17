#!/bin/bash

# cleanus up images, containers, and volumnes that are dangling
docker rmi -f $(docker images -f "dangling=true" -q)
docker rm $(docker ps -a -f status=exited -q)
docker volume rm $(docker volume ls -f dangling=true -q)