FROM openjdk:11-jre-slim

EXPOSE 8087

WORKDIR /app

RUN apt-get update && apt-get install -y curl

# Add a stage to check if curl works
RUN curl --version

# Check the exit code of the curl command
RUN if [ $? -ne 0 ]; then exit 1; fi

# Download the JAR file
RUN curl -o achat-1.0.jar -L "http://192.168.33.10:8081/repository/maven-releases/tn/esprit/rh/achat/1.0/achat-1.0.jar"

ENTRYPOINT ["java", "-jar", "achat-1.0.jar"]
