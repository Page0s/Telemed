FROM ubuntu:latest AS build
RUN apt-get update && apt-get install -y openjdk-21-jdk
WORKDIR /build
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew bootJar --no-daemon

# Debugging step to verify the JAR file is present
RUN ls -la /build/build/libs

FROM openjdk:21-jdk-slim
EXPOSE 8080
COPY --from=build /build/build/libs/telemed-0.0.3-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]