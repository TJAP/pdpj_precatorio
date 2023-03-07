FROM openjdk:11-jre
COPY app.jar /srv/snmc.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/srv/snmc.jar" ]
