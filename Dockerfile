# Use OpenJDK 11 as the base image
FROM openjdk:11

# Expose port 8089 for the application
EXPOSE 8089

# Set the working directory to /app
WORKDIR /app

# Add an ARG and ENV for the SonarQube login token
ARG SONAR_LOGIN
ENV SONAR_LOGIN=${SONAR_LOGIN}

# Install curl
RUN apt-get update && apt-get install -y curl

# Download the application JAR file
RUN curl -o achat-1.0.jar -L "http://10.10.10.130:32323/repository/deploymentRepo/tn/esprit/rh/achat/1.0/achat-1.0.jar"

# Run the application with the SonarQube token passed as an environment variable
ENTRYPOINT ["java", "-jar", "achat-1.0.jar"]
