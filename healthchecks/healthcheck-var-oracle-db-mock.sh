#!/bin/bash

validate_fixture_database() {
    local db=$1

    rm -f /tmp/validatedb.log
    sqlplus -L -S system/oracle > /tmp/validatedb.log <<-EOF
    desc ${db};
EOF
    grep -s "does not exist" /tmp/validatedb.log
    if [[ $0 -ne 0 ]]; then
        echo "Database does not contain table ${db}.  The database will need to be seeded."
        exit 1
    fi
}

echo -e "Validating databases. . ."

validate_fixture_database "VARDB.FACILITY"
validate_fixture_database "MOCKDB.MOCK_USERS"
validate_fixture_database "HADB.FACILITY"
validate_fixture_database "VARDB.USER_HISTORY"

echo -e "success."
exit 0

