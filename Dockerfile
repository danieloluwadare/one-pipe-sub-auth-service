FROM openjdk:8-jdk-alpine

LABEL maintainer="danieldada123@gmail.com"

VOLUME /tmp

EXPOSE 8080/tcp

COPY target/one-pipe-project-0.0.1-SNAPSHOT.jar one-pipe-project-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","one-pipe-project-0.0.1-SNAPSHOT.jar"]
