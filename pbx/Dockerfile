FROM adoptopenjdk:11-jre-hotspot as builder
ARG JAR_FILE=target/pbx-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} pbx.jar
ENTRYPOINT ["java","-jar","/pbx.jar"]