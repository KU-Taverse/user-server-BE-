FROM openjdk:17-jdk

# build가 되는 시점에 JAR_FILE이라는 변수 명에 build/libs/*.jar 선언
# build/libs - gradle로 빌드했을 때 jar 파일이 생성되는 경로
ARG JAR_FILE=../../build/libs/*.jar

ARG GOOGLE_ID
ARG GOOGLE_SECRET
ARG KAKAO_ID
ARG KAKAO_SECRET

# JAR_FILE을 app.jar로 복사
COPY ${JAR_FILE} app.jar

ENV GOOGLE_ID=${GOOGLE_ID}
ENV GOOGLE_SECRET=${GOOGLE_SECRET}
ENV KAKAO_ID=${KAKAO_ID}
ENV KAKAO_SECRET=${KAKAO_SECRET}

# 운영 및 개발에서 사용되는 환경 설정을 분리
ENTRYPOINT ["java", "-jar", "/app.jar","--spring.profiles.active=local"]