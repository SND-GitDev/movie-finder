FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar  "app.jar"
ENV TMDB_API_KEY=Add-Key-Here-But-Never-Commit
ENTRYPOINT ["java","-jar","/app.jar"]