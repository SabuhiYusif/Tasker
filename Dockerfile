# An image configuration for SpringBoot application
FROM openjdk:8-jdk-alpine
RUN apk update && apk add bash
RUN mkdir /home/app
WORKDIR /home/app
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]