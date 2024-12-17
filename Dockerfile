FROM gradle:8.2.1-jdk17 AS build
WORKDIR /app
COPY build.gradle settings.gradle ./
COPY src ./src
RUN gradle build -x test --no-daemon

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 9898
ENTRYPOINT ["java", "-jar", "app.jar"]