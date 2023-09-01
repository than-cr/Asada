FROM maven:3-eclipse-temurin-20
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM azul/zulu-openjdk:20
MAINTAINER castro.asada
VOLUME /tmp
ADD target/Asada-0.0.1-SNAPSHOT.jar Asada-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/Asada-0.0.1-SNAPSHOT.jar"]
