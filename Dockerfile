# Use a base image with Java
FROM openjdk:17-jdk-alpine

# Copy the built JAR file from the target folder into the container
COPY target/*.jar app.jar

# Expose the port 
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java","-jar","/app.jar"]
