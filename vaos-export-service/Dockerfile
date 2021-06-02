ARG DTR_URL
ARG DTR_DIR
# FROM ${DTR_URL}/${DTR_DIR}/oraclejdk8-spring-appd:2.2.4
FROM dtr.mapsandbox.net/images/oraclejdk8-spring-appd:2.2.4

LABEL maintainer="Sustainment Team"

COPY ./vaos-export-service/entrypoint.sh /entrypoint.sh

COPY ./vaos-export-service/run-service.sh /run-service.sh
COPY ./vaos-export-service/vaos-export-service.jar  /opt/service/vaos-export-service.jar


WORKDIR /opt/service/

ENV JAVA_OPTS=${JAVA_OPTS}

RUN chmod a+x /entrypoint.sh /run-service.sh /opt/service/vaos-export-service.jar

ENTRYPOINT ["/run-service.sh"]
