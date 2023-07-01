FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY target/corebanking-*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]