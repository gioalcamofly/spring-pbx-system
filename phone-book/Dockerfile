FROM adoptopenjdk:11-jre-hotspot as builder
ARG JAR_FILE=target/phone-book-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} phone_book.jar
ENTRYPOINT ["java","-jar","/phone_book.jar"]