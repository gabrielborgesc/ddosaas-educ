FROM maven:3.9.6-eclipse-temurin-21 AS build

COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-alpine

RUN apk --no-cache add curl

COPY --from=build target/*.jar /app.jar

WORKDIR /

EXPOSE 8080

CMD ["java","-jar","/app.jar"]
