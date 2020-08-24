# Camping Fire

## Prerequisites

```
Java Development Kit 8
Docker and Docker Compose
Browser
```

## Technologies

```
Spring Boot
Swagger
Apache Maven 3
```

## Instructions

- Start the MySQL Database with Docker Compose:

```
$ docker-compose up
```

> You need to be in the project root directory to execute.

- To start the server execute:

```
$ ./mvnw spring-boot:run
```

> If you are using **Windows** execute ```.\mvnw clean package``` in the terminal.

- See the endpoints of the application in http://localhost:8080/swagger-ui.html.

> You can access http://localhost phpMyAdmin for manage the MySQL Database.
