FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /build

COPY gradle/ gradle/
COPY gradlew build.gradle.kts settings.gradle.kts ./

RUN chmod +x ./gradlew
RUN ./gradlew dependencies --no-daemon

COPY src/ src/
RUN ./gradlew bootJar --no-daemon

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=builder /build/build/libs/backend-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]