# Use an official OpenJDK image with JDK 23
FROM eclipse-temurin:23-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the Spring Boot JAR file to the container
COPY target/SpringBootProject_1-0.0.1-SNAPSHOT.jar app.jar

# Expose the port on which the app will run (default for Spring Boot is 8080)
EXPOSE 8080

# Set the entry point to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
