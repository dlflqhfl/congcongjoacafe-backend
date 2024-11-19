FROM gradle:7.6-jdk as builder

WORKDIR /app
COPY . ./
RUN gradle clean build --no-daemon

# 실행을 위한 OpenJDK 이미지 사용
FROM openjdk:17-alpine

# 작업 디렉토리 설정
WORKDIR /app

# 빌드된 JAR 파일 복사
COPY --from=builder /app/build/libs/congcongjoa-0.0.1-SNAPSHOT.jar .

# 필요한 디렉토리 복사
#COPY nginx/ssl/accounts /etc/nginx/ssl/accounts

# 소유자 변경 및 권한 수정
#RUN chown -R root:root /etc/nginx/ssl/accounts && chmod -R 755 /etc/nginx/ssl/accounts

# 포트 노출
EXPOSE 9090

# 어플리케이션 실행
ENTRYPOINT ["java", "-jar", "congcongjoa-0.0.1-SNAPSHOT.jar"]
