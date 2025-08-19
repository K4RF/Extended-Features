# =================
# 1단계: 빌드(Build) 스테이지 - 소스코드를 컴파일해서 JAR 파일을 만듭니다.
# =================
FROM openjdk:17-jdk-alpine AS builder

# 작업 디렉토리 설정
WORKDIR /workspace/app

# Gradle 관련 파일들을 먼저 복사 (Gradle 의존성 캐싱을 위함)
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# 소스코드 복사
COPY src src

# gradlew 파일에 실행 권한 부여 (리눅스 환경이므로)
RUN chmod +x ./gradlew

# Gradle 빌드 실행. --no-daemon 옵션은 CI/CD 환경에서 권장됩니다.
RUN ./gradlew build --no-daemon


# =================
# 2단계: 실행(Final) 스테이지 - 빌드된 JAR 파일만 가지고 최종 이미지를 만듭니다.
# =================
FROM bellsoft/liberica-openjdk-alpine:17

# 작업 디렉토리 설정
WORKDIR /app

# 빌드 스테이지(builder)에서 생성된 JAR 파일만 복사해옵니다.
COPY --from=builder /workspace/app/build/libs/*.jar app.jar

# 8081 포트 노출
EXPOSE 8081

# 컨테이너 시작 시 JAR 파일 실행
ENTRYPOINT ["java","-jar","app.jar"]