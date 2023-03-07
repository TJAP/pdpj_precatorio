FROM openjdk:11-jre-slim

COPY target/precatorio.jar /opt/precatorio/precatorio.jar

WORKDIR /opt/precatorio

EXPOSE 8080

ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/opt/precatorio/precatorio.jar" ]