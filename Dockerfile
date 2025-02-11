FROM openjdk:17-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} userservice.jar
ENTRYPOINT ["java","-jar","/userservice.jar"]