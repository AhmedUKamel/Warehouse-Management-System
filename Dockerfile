FROM eclipse-temurin:20-jdk-alpine
VOLUME /tmp
ENTRYPOINT ["java","-jar","/Warehouse-Management-System.jar"]
EXPOSE 8080