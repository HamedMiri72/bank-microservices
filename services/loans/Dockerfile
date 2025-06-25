FROM openjdk:17-jdk-slim

MAINTAINER https://github.com/hamedmiri72

WORKDIR /app

# Install curl (for Docker healthcheck to work)
RUN apt-get update && apt-get install -y curl && apt-get clean

RUN mkdir -p /app/libs

COPY target/loans-0.0.1-SNAPSHOT.jar .

COPY opentelemetry-javaagent.jar /app/libs/opentelemetry-javaagent.jar

ENTRYPOINT ["java", "-jar", "loans-0.0.1-SNAPSHOT.jar"]