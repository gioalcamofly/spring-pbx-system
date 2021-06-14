FROM adoptopenjdk:11-jre-hotspot as builder
ARG JAR_FILE=target/call_detailed_record-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} cdr.jar
ENTRYPOINT ["java","-jar","/cdr.jar"]