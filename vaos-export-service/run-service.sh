#!/bin/bash

#
# Set up key store and trust store and run the service.
#
# This script may be run under the control of envconsul
# to ensure that the key/trust store files are rebuilt automatically
# and that the service is then restarted whenever changes are made in Consul.
# Alternatively, the environment variables required for configuration may
# be set manually and the script executed directly without running under envconsul.
#
# See the entrypoint.sh script for details on how this script is invoked.
#

# Define default service-specific variables here
serviceJar=/opt/service/vaos-export-service.jar

# AppDynamics
addAppDynamics (){
  # Check if App Dynamics should be enabled, the default is set to false
  if [[ $ENABLE_APPDYNAMICS != "false" ]]; then
      # Ensure the Node Name prefix is set, node names must be unique and this setting will generate unique names
      if [[ -z $APPDYNAMICS_NODE_NAME_PREFIX ]]; then
          echo "APPDYNAMICS_NODE_NAME_PREFIX not set, App Dynamics will not be enabled."
      else
          echo "App Dynamics is enabled, Node Name Prefix set to: ${APPDYNAMICS_NODE_NAME_PREFIX}"
          JAVA_OPTS="$JAVA_OPTS -javaagent:/appdynamics/AppServerAgent/javaagent.jar -Dappdynamics.agent.auto.node.prefix=${APPDYNAMICS_NODE_NAME_PREFIX}"
      fi
  else
      echo "App Dynamics is not enabled, set the environment variable: ENABLE_APPDYNAMICS=true"
  fi
}

import_pkcs12 () {
  # Load SVC_KEY_P12 contents into a local p12 keystore
  if [[ -n $SVC_KEY_P12 ]]; then
    echo $SVC_KEY_P12 | base64 -d > svc_keystore.p12
  else
    echo "SVC_KEY_P12 environment variables were not set, so not importing keys."
  fi

  # Optional, load svc_truststore.p12 here by checking appropriate env variable
  # and duplicating the commands for creating the svc_keystore.p12

  # If a p12 keystore file was created then adjust JAVA_OPTS
  if [[ -e svc_keystore.p12 ]]; then
    JAVA_OPTS="$JAVA_OPTS -Djavax.net.ssl.keyStore=./svc_keystore.p12"
    JAVA_OPTS="$JAVA_OPTS -Djavax.net.ssl.keyStoreType=PKCS12"

    if [[ -n $SVC_KEY_P12_PWD ]]; then
      JAVA_OPTS="$JAVA_OPTS -Djavax.net.ssl.keyStorePassword=${SVC_KEY_P12_PWD}"
    fi
  fi

  # If an optional p12 truststore file was created then adjust JAVA_OPTS
  if [[ -e svc_truststore.p12 ]]; then
    JAVA_OPTS="$JAVA_OPTS -Djavax.net.ssl.trustStore=./svc_truststore.p12"
    JAVA_OPTS="$JAVA_OPTS -Djavax.net.ssl.trustStoreType=PKCS12"
  fi
}

# adding AppDynamics to service
addAppDynamics

# Import PKCS#12 contents from environment variables
import_pkcs12

echo "Starting service"

echo $serviceJar


# Run the Java service using exec to ensure that process signals from envconsul are handled properly
exec java $JAVA_OPTS -jar $serviceJar

