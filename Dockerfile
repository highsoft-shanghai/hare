FROM openjdk:17-oracle
MAINTAINER Neil neil@scrumaster.org
ENV TZ "Asia/Shanghai"
ADD build/libs/hare-1.0.0.jar  /data/apps/hare.jar
WORKDIR /data/apps/
EXPOSE 8080
CMD ["java", "-jar", "hare.jar"]
