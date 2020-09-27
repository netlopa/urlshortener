
# Pokemon Challenge

## Problem

Expose a REST API for URL Shortening

### URL Documentation

See the API calls following this link: https://documenter.getpostman.com/view/8137404/TVKHVG32

## Technology

This project was made with Java 8 with the Spring Boot framework (no storage engine is required since, for the purpose of this challenge, I am using H2 DB)

WARNING: Since the H2 DB is not persistent, all data is lost when you shutdown the server

## How to run the application (standalone)

1. Make sure you have Java 8 installed and properly configured
2. Run the following command
```
git clone https://github.com/netlopa/pokechallenge.git
```
3. Inside the created folder you need to run 
```
mvn spring-boot:run
```

## How to run the application with Docker

1. Make sure you have Java 8 installed and properly configured and clone the project
2. First of all you need to create the JAR package of the application: you need to go in the main folder of the project that you cloned and then execute this command
```
./mvnw package
```
3. Now, you need to create the Docker image, using this command
```
docker build -t netlopa/urlshortener .
```
4. If you want to run the Docker image, run this command
```
docker run -p 8080:8080 -t netlopa/urlshortener
```



