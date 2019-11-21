FROM mariadb:10.4.8
# https://stackoverflow.com/questions/46060533/docker-container-postgres-connection-error
LABEL maintainer="petar.georgiev@gmail.com"
LABEL version="1.0.0"

ENV MYSQL_ROOT_PASSWORD petka
ENV MYSQL_DATABASE petka
ENV MYSQL_USER petka
ENV MYSQL_PASSWORD petka

COPY dbScript.sql /docker-entrypoint-initdb.d/

EXPOSE 3306

CMD ["mysqld"]
