FROM openjdk:17-jdk-slim

MAINTAINER "https://github.com/hamedmiri72"


# Install curl (for Docker healthcheck to work)
RUN apt-get update && apt-get install -y curl && apt-get clean

COPY target/configserver-0.0.1-SNAPSHOT.jar configserver-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "configserver-0.0.1-SNAPSHOT.jar"]