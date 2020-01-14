FROM openjdk:8-jdk-alpine
ENV SPRING_DATASOURCE_URL=jdbc:mysql://192.168.99.100:3306/facturacion?serverTimezone=UTC&useLegacyDatetimeCode=false
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=root
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","/app.jar"]