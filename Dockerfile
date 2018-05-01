FROM hub.c.163.com/wuxukun/maven-aliyun:3-jdk-8

ADD ./*  /tmp/build/

RUN cd /tmp/build && mvn clean package \
        && mv wechat/target/*.jar /wechat.jar \
        && cd / && rm -rf /tmp/build

VOLUME /tmp
EXPOSE 80 18899
ENTRYPOINT ["java","-jar","/wechat.jar"]
