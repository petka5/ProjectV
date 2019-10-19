# ProjectV - V for Vendeta.

Project integrated with RabbitMQ, Redis and MariaDB.  
ELK for logging and grafana/prometheus for monitoring and alerting.

Running application.
```
./gradlew clean build

docker-compose build

docker-compose up
```

**RabbitMQ**  
[http://localhost:15672/](http://localhost:15672/) - Management console, username: guest , password: guest.


**Swagger**  
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

**Spring Actuator endpoints:**
[http://localhost:8080/actuator/](http://localhost:8080/actuator/) - List of all endpoints.  
[http://localhost:8080/actuator/health](http://localhost:8080/actuator/health) - Health endpoint.

**Project endpoints:**  
[http://localhost:8080/](http://localhost:8080/) - send message to RabbitMQ and return greeting.  

[http://localhost:8080/cache/{id}](http://localhost:8080/cache/50) - cache id's that are less than 100 in Redis.  

[http://localhost:8080/jwttest](http://localhost:8080/jwttest) - test oauth2 security.  

**ELK stack for continer log collecting.**  
[http://localhost:5601](http://localhost:5601)

**Grafana monitoring and alerting**   
[http://localhost:3000](http://localhost:3000)  
The next step is to log on to Grafana. Grafana should be running on port 3000 if you used the same Docker configuration, so by visiting http://localhost:3000/login you should be able to log in as the administrator with the credentials we configured before.
The first step after logging in is to create a datasource, which in our case will be Prometheus. All you have to do is to configure the URL to be http://prometheus:9090  
Creating your own dashboard.