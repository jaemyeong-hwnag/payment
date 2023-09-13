FROM openjdk:17

WORKDIR /usr/src/app

# Copy Application
COPY build/libs/*.jar app.jar

# Set Timezone
RUN ln -fs /usr/share/zoneinfo/Asia/Seoul /etc/localtime

CMD java -jar app.jar