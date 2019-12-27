#!/bin/bash

docker build -t petka/db:latest .
docker rm petka-mariadb
docker run -ti --name "petka-mariadb" petka/db:latest
