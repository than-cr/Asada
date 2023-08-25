FROM azul/zulu-openjdk:20
MAINTAINER castro.asada
COPY target/Asada-0.0.1-SNAPSHOT.jar Asada-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/Asada-0.0.1-SNAPSHOT.jar"]
