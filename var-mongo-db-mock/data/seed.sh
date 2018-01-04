#!/bin/bash

mongorestore --ssl --sslAllowInvalidCertificates --sslPEMKeyFile /etc/ssl/certs/client.pem --dir /docker-entrypoint-initdb.d

