#!/bin/bash

mongorestore --host var-mongod-db-mock --dir /data/

JSON_DIR=/opt/mongo-init/json

for path in $JSON_DIR/*
do
    if [ -d "${path}" ]; then
        db=$(basename $path)
        for json in $path/*.json
        do
            echo "Importing $json into $db DB"
            mongoimport -h var-mongo-db-mock -d "$db" --jsonArray --mode upsert --file $json
        done
    fi
done
