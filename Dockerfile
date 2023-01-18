FROM openjdk:17-jdk
COPY target/ocitaskserv-1.0-SNAPSHOT.jar ocitaskserv-1.0.0.jar
ENTRYPOINT ["java","-jar","/ocitaskserv-1.0.0.jar","--spring.profiles.active=test"]
