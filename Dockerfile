FROM maven:3.9.6-eclipse-temurin-17

WORKDIR /app

COPY demo/pom.xml .
COPY demo/src ./src

RUN mvn clean package -DskipTests

CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]
