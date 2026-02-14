# Stage 1: Build
FROM maven:3.8.5-openjdk-17 AS build
# Yahan 'BookApp' wahi naam likhna jo tere GitHub folder ka hai
WORKDIR /app
COPY BookApp/ . 
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
# Target folder bhi ussi folder ke andar banega
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]