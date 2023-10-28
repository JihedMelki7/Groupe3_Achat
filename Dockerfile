FROM openjdk:11-jre-slim
EXPOSE 8082
WORKDIR /app
#zz
RUN apt-get update && apt-get install -y curl
Run curl -o achat-1.0.jar -L "http://192.168.33.10:8081/repository/maven-releases/tn/esprit/rh/achat/1.0/achat-1.0.jar"
ENTRYPOINT["java", "-jar", "achat-1.0.jar"]
