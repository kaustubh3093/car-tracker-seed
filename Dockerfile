FROM openjdk:8-jdk-alpine
VOLUME /tmp

COPY ./target/car-tracker-seed-0.0.1.jar /api.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/api.jar"]