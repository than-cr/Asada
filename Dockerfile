FROM azul/zulu-openjdk:20
MAINTAINER castro.asada
VOLUME /tmp
ADD target/Asada-0.0.1-SNAPSHOT.jar Asada-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/Asada-0.0.1-SNAPSHOT.jar"]
