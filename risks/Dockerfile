#build stage
FROM openjdk:17-jdk-slim AS build

RUN apt-get update && apt-get install -y maven

WORKDIR /app

COPY pom.xml .

COPY src src
RUN mvn package -DskipTests

#runtime stage
FROM bellsoft/liberica-openjre-debian

WORKDIR /app

COPY --from=build /app/target/risks-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8084

CMD ["java", "-jar", "app.jar"]