# Build
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Copy
COPY gradle .
COPY gradle gradle
COPY build.gradle settings.gradle ./
RUN chmod +x gradlew && ./gradlew dependencies --no-daemon

# 소스
COPY src src
RUN ./gradlew build -x test --no-daemon
# jdk 17기반의 이미지 사용
FROM eclipse-temurin:17-jdk
# 작업 디렉토리 설정
WORKDIR /app
# 빌드된 jar 파일 복사
COPY --from=build /app/build/libs/*-0.0.1-SNAPSHOT.war  app.war
# PORT 열기
EXPOSE 8080
# 실행
ENTRYPOINT ["java", "-jar", "app.war"]