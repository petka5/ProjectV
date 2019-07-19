#!/usr/bin/env bash

exec java $JVM_OPTS -Dspring.profiles.active=service,${ENV} -jar /opt/petka/libs/backend-1.0.jar
