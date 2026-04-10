<<<<<<< HEAD
﻿FROM maven:3.9.6-eclipse-temurin-17 AS build
=======
FROM maven:3.9.6-eclipse-temurin-17 AS build
>>>>>>> 21f175a2d7df3dd6d9d732ae4c6d5b97313d4a60
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 9090
<<<<<<< HEAD
ENTRYPOINT ["java", "-jar", "app.jar"]
=======
ENTRYPOINT ["java", "-jar", "app.jar"]
>>>>>>> 21f175a2d7df3dd6d9d732ae4c6d5b97313d4a60
