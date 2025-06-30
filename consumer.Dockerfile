FROM maven:3.9.10-eclipse-temurin-24 AS build
WORKDIR /app
COPY . .
RUN --mount=type=cache,target=/root/.m2 mvn clean package -DskipTests

FROM openjdk:24-jdk-slim
WORKDIR /app
COPY --from=build /app/event-consumer/target/*.jar app.jar
COPY ./docker/scripts/consumer-entrypoint.sh entrypoint.sh
RUN chmod +x /app/entrypoint.sh
ENTRYPOINT ["/bin/sh", "-c", "/app/entrypoint.sh"]