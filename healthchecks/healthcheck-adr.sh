#!/bin/bash

if test "$(curl -sL -w '%{http_code}' -o /dev/null --connect-timeout 1000 --retry 10 --retry-delay 10  http://localhost:8080/adr/mockeeSummaryPortSoap11)" = 200; then
    echo -e "adr ready."
    exit 0
else
    echo -e "adr is not ready."
    exit 1
fi

