FROM hub.c.163.com/wuxukun/maven-aliyun:3-jdk-8

ADD ./*  /tmp/build/

RUN cd /tmp/build && mvn clean package \
        && mv TKkindle/target/*.jar /kindle.jar \
        && cd / && rm -rf /tmp/build

VOLUME /tmp
EXPOSE 8080
ENTRYPOINT ["java","-jar","/kindle.jar"]
