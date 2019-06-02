FROM openjdk:8-alpine

COPY target/uberjar/betting.jar /betting/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/betting/app.jar"]
