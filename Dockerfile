# Use the official Maven image to build the app
FROM maven:3.8.6-openjdk-17 AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and install dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the application source code
COPY src ./src

# Package the application
RUN mvn package -DskipTests

# Use the official OpenJDK image to run the app
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/cafe-employee-manager-backend-*.jar app.jar

# Expose the port the app runs on
EXPOSE 8000

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
