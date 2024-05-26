FROM maven:3.9.4-amazoncorretto-20 AS MAVEN_BUILD
COPY pom.xml ./pom.xml
RUN --mount=type=cache,target=/root/.m2 mvn dependency:go-offline -B
COPY src ./src
RUN --mount=type=cache,target=/root/.m2 mvn package

FROM amazoncorretto:20-al2-generic AS CINEMA
WORKDIR /app
EXPOSE 8080
COPY --from=MAVEN_BUILD /target/*.jar /app/app.jar
RUN mkdir -p src/main/resources
ENTRYPOINT ["java","-jar","/app/app.jar","-Xms512M","-Xmx2G"]