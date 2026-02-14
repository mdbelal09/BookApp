# Step 1: Build stage
# Maven image wahi rehne de sakte hain ya stable version use karein
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Step 2: Run stage
# Yahan 'openjdk' ki jagah 'eclipse-temurin' use kar raha hoon (Best alternative)
FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /target/*.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.jar"]