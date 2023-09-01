FROM eclipse-temurin:20-jdk-alpine
VOLUME /tmp
COPY /*.jar executable.jar
ENTRYPOINT ["java","-jar","executable.jar"]
EXPOSE 8080