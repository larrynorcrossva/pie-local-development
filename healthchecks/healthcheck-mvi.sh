#!/bin/bash

if test "$(curl -sL -w '%{http_code}' -o /dev/null --connect-timeout 1000 --retry 10 --retry-delay 10  http://localhost:8080/mvi/mockVAIdMPort)" = 200; then
    echo -e "mvi ready."
    exit 0
else
    echo -e "mvi is not ready."
    exit 1
fi

