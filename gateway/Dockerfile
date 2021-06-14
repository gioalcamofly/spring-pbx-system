FROM adoptopenjdk:11-jre-hotspot as builder
ARG JAR_FILE=target/gateway-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} phone_book.jar
ENTRYPOINT ["java","-jar","/gateway.jar"]