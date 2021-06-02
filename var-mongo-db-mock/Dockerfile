FROM mongo:3.6.11-xenial

COPY ssl/certs/ /etc/ssl/certs/
COPY conf/mongod.conf /etc/mongod.conf

RUN mkdir -p /var/lib/mongodb
RUN chown mongodb /var/lib/mongodb

USER mongodb

CMD ["mongod", "-f", "/etc/mongod.conf"]
#, "--sslMode", "requireSSL", "--sslPEMKeyFile", "/etc/ssl/certs/mongodb.pem", "--sslCAFile", "/etc/ssl/certs/mongodb-cert.crt", "--sslAllowInvalidCertificates", "--sslAllowInvalidHostnames",  "--sslAllowConnectionsWithoutCertificates", "-