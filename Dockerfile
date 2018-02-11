FROM java:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/uberjar/sqlite-app.jar /sqlite-app/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/sqlite-app/app.jar"]
