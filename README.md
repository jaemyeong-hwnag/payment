# 결제

실행 명령어
```
   chmod +x gradlew && 
   ./gradlew clean &&
   ./gradlew build -x test &&
   docker compose up
```

### 스펙
JDK 17, spring-boot 3.1, mysql 8.0, querydsl, kotlin 1.8

### 기능
1. 유저
    - 생성
    - 로그인
    - 조회
2. 결제
    - 결제 내역 생성
    - 결제 취소 내역 생성
    - 결제 조회
    - 결제 내역 조회
