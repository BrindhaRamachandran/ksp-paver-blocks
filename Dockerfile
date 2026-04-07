FROM maven:3.9.6-eclipse-temurin-17
WORKDIR /app
COPY demo /app
RUN mvn -f /app/pom.xml clean package -DskipTests
CMD ["sh", "-c", "java -jar target/*.jar"]
