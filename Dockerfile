EXPOSE 8080 FROM amazoncorretto:17-alpine-jdk
MAINTAINER marina
COPY target/portfolio-0.0.1-SNAPSHOT.jar portfolio-0.0.1-SNAPSHOT.jar
EXPOSE 8080 ENTRYPOINT ["java","-jar","/portfolio-0.0.1-SNAPSHOT.jar"]
