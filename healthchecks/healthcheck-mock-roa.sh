#!/usr/bin/env bash

export ORACLE_USERNAME="${ORACLE_USERNAME:-system}"
export ORACLE_PASSWORD="${ORACLE_PASSWORD:-oracle}"

validate_fixture_database() {
    local db=$1

    rm -f /tmp/validatedb.log
    sqlplus -L -S ${ORACLE_USERNAME}/${ORACLE_PASSWORD} > /tmp/validatedb.log <<-EOF
    desc ${db};
EOF
    grep -s "does not exist" /tmp/validatedb.log
    if [[ $0 -ne 0 ]]; then
        echo "Database does not contain table ${db}.  The database will need to be seeded."
        exit 1
    fi
}

echo -e "Validating databases. . ."
exit 0

