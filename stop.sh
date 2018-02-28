
if [ -z $1 ] || [ "$1" == "all" ]; then
    echo -e "\nShutting down NextGen platform services...\n"
    docker-compose down || (echo "*** FAILED: Failed to stop running containers using docker-compose." && exit -1)

    echo -e "\nCleaning up dangling volumes"
    docker volume rm $(docker volume ls -f dangling=true -q)
fi

if [ "$1" == "fixtures" ] || [ "$1" == "all" ]; then
    echo -e "\nShutting down fixture services...\n"
    docker-compose -f docker-compose-fixtures.yml down
fi

if [ "$1" == "shared-services" ] || [ "$1" == "all" ]; then
    echo -e "\nShutting down shared services...\n"
    docker-compose -f docker-compose-shared-services.yml down
fi

if [ "$1" == "var" ] || [ "$1" == "all" ]; then
    echo -e "\nShutting down var services and apps...\n"
    docker-compose -f docker-compose-var.yml down
fi

if [ "$1" == "sm" ] || [ "$1" == "all" ]; then
    echo -e "\nShutting down scheduling manager services and apps...\n"
    docker-compose -f docker-compose-sm.yml down
fi

# removes cache file created by docker from var-oracle-db-mock
echo -e "\nCleaning up generated docker cache file from oracle-db"
rm ./var-oracle-db-mock/.cache