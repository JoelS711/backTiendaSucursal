FROM openjdk:17-jdk-alpine
COPY "./target/back.report-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8086
ENTRYPOINT ["java","-jar","/app.jar"]