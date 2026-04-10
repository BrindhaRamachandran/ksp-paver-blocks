FROM maven:3.9.6-eclipse-temurin-17
WORKDIR /app
COPY . /app
RUN mvn -f /app/pom.xml clean package -DskipTests
CMD ["java", "-jar", "/app/target/*.jar"]