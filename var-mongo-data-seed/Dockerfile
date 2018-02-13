FROM mongo:3.4.11

COPY ./certs/ /etc/ssl/certs/
COPY /data /data

USER mongodb


#CMD mongorestore --host var-mongo-db-mock --ssl --sslAllowInvalidCertificates --sslPEMKeyFile /etc/ssl/certs/mongodb.pem --dir /data/
CMD mongorestore --host var-mongo-db-mock --dir /data/