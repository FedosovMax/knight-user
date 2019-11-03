FROM openjdk:11.0.4-jre-slim

WORKDIR /usr/src/myapp

COPY target/knight-user*.jar /knight-user.jar

CMD ["java", "-jar", "/knight-user.jar"]