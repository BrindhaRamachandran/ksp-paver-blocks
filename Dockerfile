# Stage 1: Build the application using Maven
FROM maven:3.9.3-eclipse-temurin-17 AS build

# Set working directory inside the container
WORKDIR /app

# Copy only the Maven configuration first to leverage caching
COPY pom.xml .

# Download dependencies (this step is cached if pom.xml doesn't change)
RUN mvn dependency:go-offline -B

# Copy the full project
COPY src ./src

# Build the project and package it as a JAR
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jdk-alpine

# Set working directory for runtime
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]