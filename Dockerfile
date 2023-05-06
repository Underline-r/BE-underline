FROM openjdk:11
WORKDIR /usr/src/app
ARG JAR_PATH=./build/libs/underline-0.0.1-SNAPSHOT.jar
COPY ${JAR_PATH} app.jar
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=prod", "-Duser.timezone=Asia/Seoul", "app.jar"]
