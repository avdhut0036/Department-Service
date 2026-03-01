FROM openjdk:22-jdk
COPY target/spring-boot-tutorial-0.0.1-SNAPSHOT.jar crud.jar
ENV SPRING_PROFILES_ACTIVE=PROD
ENTRYPOINT ["java","-jar","crud.jar"]