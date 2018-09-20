#!/bin/bash
test "x$(curl -s localhost:8500/v1/status/leader)" != 'x""'
if [ $? -eq 0 ]; then
    exit 0
else 
    echo -e "consul is not reachable. clearing configuration and restarting."

    # Really should keep track of a counter before doing this. 
    # As a proof of concept, we just kill the access.
    # rm -rf /consul/config/*
    # rm -rf /consul/data/*


    # kill -INT 1
    consul leave
    exit 1
fi

