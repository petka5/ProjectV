#!/bin/bash

docker build -t petka/redis:latest .
docker rm petka-redis

docker run -ti --name "petka-redis" petka/redis:latest
