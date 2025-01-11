FROM ubuntu:latest AS build
RUN apt-get update && apt-get install -y
RUN apt-get instsall openjdk-21-jdk -y
COPY . .
RUN ./gradlew bootJar --no-daemon

FROM openjdk:21-jdk-slim
EXPOSE 8080
COPY --from=build /build/build/libs/telemed-1.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]