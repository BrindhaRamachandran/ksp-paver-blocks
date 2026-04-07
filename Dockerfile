# Stage 1: Build the Spring Boot JAR
FROM maven:3.9.3-eclipse-temurin-17 AS build

WORKDIR /app

# Copy Maven config first to cache dependencies
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline -B

# Copy the source code
COPY src ./src

# Build the project (skip tests)
RUN mvn clean package -DskipTests

# Stage 2: Run the Spring Boot application
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy the JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose application port
EXPOSE 9090

# Pass the MAIL_PASSWORD environment variable at runtime
ENV MAIL_PASSWORD=${MAIL_PASSWORD}

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
