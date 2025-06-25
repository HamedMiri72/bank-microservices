
FROM openjdk:17-jdk-slim

MAINTAINER "hamedmiri72"

WORKDIR /app
# Install curl (for Docker healthcheck to work)
RUN apt-get update && apt-get install -y curl && apt-get clean

RUN mkdir -p /app/libs

COPY target/eurekaserver-0.0.1-SNAPSHOT.jar .


COPY opentelemetry-javaagent.jar /app/libs/opentelemetry-javaagent.jar


ENTRYPOINT ["java", "-jar", "eurekaserver-0.0.1-SNAPSHOT.jar"]