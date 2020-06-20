FROM openjdk:8-jdk-alpine
RUN apk update && apk add bash
RUN mkdir /home/app
WORKDIR /home/app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]