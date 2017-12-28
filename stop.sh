echo -e "\nShutting down any NextGen running services…\n"
docker-compose down || (echo "*** FAILED: Failed to stop running containers using docker-compose." && exit -1)

echo -e "\nCleaning up dangling volumes"
docker volume rm $(docker volume ls -f dangling=true -q)

# removes cache file created by docker from var-oracle-db-mock
echo -e "\nCleaning up generated docker cache file from oracle-db"
rm ./var-oracle-db-mock/.cache