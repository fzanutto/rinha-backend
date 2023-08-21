FROM openjdk:17

COPY build/libs/rinha-backend-1.0.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]