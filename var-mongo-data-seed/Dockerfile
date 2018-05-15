FROM mongo:3.4.11

COPY ./certs/ /etc/ssl/certs/
COPY /data /data

RUN mkdir /opt/mongo-init

COPY ./mongo-init.sh /opt/mongo-init
COPY ./json/ /opt/mongo-init/json/

USER mongodb

CMD /opt/mongo-init/mongo-init.sh
