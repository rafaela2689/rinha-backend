### Build stage
FROM gradle:latest AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

### Package stage
FROM openjdk:latest
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/rinha-backend.jar
ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/rinha-backend.jar"]
