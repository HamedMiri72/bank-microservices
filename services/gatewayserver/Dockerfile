FROM openjdk:17-jdk-slim

MAINTAINER "HAMED MIRI"

WORKDIR /app
# Install curl (for Docker healthcheck to work)
RUN apt-get update && apt-get install -y curl && apt-get clean

RUN mkdir -p /app/libs

COPY target/gatewayserver-0.0.1-SNAPSHOT.jar .

COPY opentelemetry-javaagent.jar /app/libs/opentelemetry-javaagent.jar


ENTRYPOINT ["java", "-jar", "gatewayserver-0.0.1-SNAPSHOT.jar"]