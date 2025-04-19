FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8051
ENTRYPOINT ["java", "-jar", "app.jar"]
