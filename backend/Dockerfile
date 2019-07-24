FROM openjdk:8u222-jre

LABEL maintainer="petar.georgiev@gmail.com"
LABEL version="1.0.0"

RUN mkdir -p /opt/petka/libs
COPY build/libs/* /opt/petka/libs

COPY entrypoint.sh ./

ENTRYPOINT ["./entrypoint.sh"]

EXPOSE 8080
EXPOSE 9000