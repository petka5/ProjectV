#!/bin/bash

docker build -t petka/backend:latest .
docker rm petka-backend
docker run -ti --name "petka-backend" petka/backend:latest
