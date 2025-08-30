# 1단계: 빌드용 이미지 (JDK)
FROM eclipse-temurin:17-jdk as builder

WORKDIR /app
COPY . ./
RUN ./gradlew build -x test

# 2단계: 실행용 이미지 (JRE)
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
