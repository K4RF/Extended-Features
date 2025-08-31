# ---------- Build stage ----------
FROM eclipse-temurin:17-jdk AS builder
WORKDIR /app

# Gradle 캐시 최적화: 래퍼/빌드 스크립트만 먼저 복사
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle settings.gradle ./
# 필요 시 gradle.properties도 복사
# COPY gradle.properties ./

# 권한 및 종속성 프리페치(테스트 생략)
RUN chmod +x gradlew && ./gradlew dependencies --no-daemon || true

# 소스 전체 복사 및 빌드
COPY . .
RUN ./gradlew clean bootJar -x test --no-daemon

# ---------- Runtime stage ----------
FROM eclipse-temurin:17-jre
WORKDIR /app

# 컨테이너 실행시 필요한 환경변수(예: 프로파일)
ENV SPRING_PROFILES_ACTIVE=prod
# 필요 시 JVM 옵션
# ENV JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=75.0 -XX:+UseG1GC"

# 빌드 산출물 복사
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
