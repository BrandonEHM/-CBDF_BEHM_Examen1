FROM amazoncorretto:17-alpine
COPY target/empleados-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
