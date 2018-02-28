#!/usr/bin/env bash

./run-nextgen-infrastructure.sh
./run-fixtures.sh
sleep 10
./run-data-seed.sh
./run-shared-services.sh
sleep 10
./run-var.sh
./run-sm.sh