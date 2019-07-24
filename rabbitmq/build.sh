#!/bin/bash

docker build -t petka/rabbitmq:latest .
docker rm petka-rabbitmq
docker run -ti --name "petka-rabbitmq" petka/rabbitmq:latest
