FROM openjdk:11
EXPOSE 8089
WORKDIR /app

RUN apt-get update && apt-get install -y curl
RUN curl -o achat-1.0.jar -L "http://172.30.235.161:8081/repository/deploymentRepo/tn/esprit/rh/achat/1.0/achat-1.0.jar"

ENTRYPOINT ["java", "-jar", "achat-1.0.jar"]

