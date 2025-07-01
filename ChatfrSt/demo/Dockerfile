FROM ubuntu:latest
LABEL authors="Bexultan"


RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    curl \
    && apt-get clean


ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH="$JAVA_HOME/bin:$PATH"

WORKDIR /app

COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
