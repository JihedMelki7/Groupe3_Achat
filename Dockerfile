FROM openjdk:11-jre-slim
EXPOSE 8089
WORKDIR /app

RUN apt-get update && apt-get install -y curl
RUN curl -o achat-2.0.jar -L "http://192.168.33.10:8081/repository/maven-releases/tn/esprit/rh/achat/2.0/achat-2.0.jar"

ENTRYPOINT ["java", "-jar", "achat-2.0.jar"]
