
#start with a base imagecontaining java runtime
FROM openjdk:17-jdk-slim

#Information around who maintains the image
MAINTAINER https://github.com/hamedmiri72

WORKDIR /app

# Install curl (for Docker healthcheck to work)
RUN apt-get update && apt-get install -y curl && apt-get clean

RUN mkdir -p /app/libs

#add the application's jar file to the image
COPY target/accounts-0.0.1-SNAPSHOT.jar .

COPY opentelemetry-javaagent.jar /app/libs/opentelemetry-javaagent.jar

#execute the application
ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]




