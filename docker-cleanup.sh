#!/bin/bash

# Clean up docker volumes
echo -e "Clean up dangling volumes\n"
docker volume ls -qf dangling=true | xargs docker volume rm

# Clean up old docker images
echo -e "Clean up dangling images\n"
docker images --filter "dangling=true" -q --no-trunc | xargs docker rmi