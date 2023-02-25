FROM eclipse-temurin:17-alpine

LABEL mentainer = "erickhc.dev@gmail.com"

WORKDIR /app

COPY ./target/spring-hibernate-jpa-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "spring-hibernate-jpa-0.0.1-SNAPSHOT.jar"]