FROM amazoncorretto:16-alpine-jdk
WORKDIR /usr/local/dockerkek
EXPOSE 9999
COPY target/cryptolyf-0.0.1-SNAPSHOT.jar Dockerfile ./
#CMD ["java","-jar","/app.jar"]
CMD ["java", "-jar", "cryptolyf-0.0.1-SNAPSHOT.jar"]
