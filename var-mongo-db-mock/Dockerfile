FROM mongo:3.6.6

ENV DEBIAN_FRONTEND noninteractive
RUN apt-get update \
    && apt-get install -y --no-install-recommends ssl-cert \
    && apt-get clean && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

COPY ssl/certs/ /etc/ssl/certs/
COPY conf/mongod.conf /etc/mongod.conf

RUN mkdir -p /var/lib/mongodb
RUN chown mongodb /var/lib/mongodb

USER mongodb

CMD ["mongod", "-f", "/etc/mongod.conf"]
#, "--sslMode", "requireSSL", "--sslPEMKeyFile", "/etc/ssl/certs/mongodb.pem", "--sslCAFile", "/etc/ssl/certs/mongodb-cert.crt", "--sslAllowInvalidCertificates", "--sslAllowInvalidHostnames",  "--sslAllowConnectionsWithoutCertificates", "--auth"]
