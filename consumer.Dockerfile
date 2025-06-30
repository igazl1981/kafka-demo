FROM maven:3.9.10-eclipse-temurin-24 AS build
WORKDIR /app
COPY . .
RUN --mount=type=cache,target=/root/.m2 mvn clean package -DskipTests

FROM openjdk:24-jdk-slim
WORKDIR /app
COPY --from=build /app/event-consumer/target/*.jar app.jar
ENTRYPOINT ["java", "-XX:UseSVE=0", "-jar", "app.jar"]