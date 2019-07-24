#!/bin/bash -e

exec java ${JVM_OPTS} -Dspring.profiles.active=${ENV} -jar /opt/petka/libs/backend-1.0.jar
