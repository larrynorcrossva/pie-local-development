echo -e "\nShutting down any NextGen running services…\n"
docker-compose down || (echo "*** FAILED: Failed to stop running containers using docker-compose." && exit -1)

echo -e "\nCleaning up dangling volumes"
docker volume rm $(docker volume ls -f dangling=true -q)