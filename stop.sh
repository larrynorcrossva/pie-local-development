echo -e "\nShutting down any NextGen running services…\n"
docker-compose down || (echo "*** FAILED: Failed to stop running containers using docker-compose." && exit -1)
