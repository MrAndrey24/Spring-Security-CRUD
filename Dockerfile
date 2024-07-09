FROM amazoncorretto:21-alpine
ARG JAR_FILE=build/libs/spring-security-crud-1.0.0.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]