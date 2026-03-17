FROM maven:3.9.11-eclipse-temurin-17 as mavenBuild

WORKDIR /build

COPY pom.xml pom.xml
COPY src src
RUN ["mvn", "-q", "-DskipTests", "package"]

FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=mavenBuild /build/target/app.jar ./app.jar
ENV JAVA_OPTS ""
CMD ["sh", "-c", "java ${JAVA_OPTS} -jar app.jar"]
