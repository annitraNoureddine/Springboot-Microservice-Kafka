# Springboot-Microservice-Kafka

SpringBoot 3.0.7
Java 17
BDD: Mysql

services:
  - Hello world : http://localhost:8060/
    --> url/port configurer dans api-gareway
  - Get exemple Message Json : http://localhost:8081/courses/
    --> url/port configurer dans config-server (ressources/config/course-service.yaml)
  - Create Course(Post) : http://localhost:8081/courses/
    --> url/port configurer dans config-server (ressources/config/course-service.yaml)
