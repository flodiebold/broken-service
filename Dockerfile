FROM openjdk:8-jdk-alpine
VOLUME /tmp
RUN apk --no-cache --update add dumb-init && \
rm -rf /var/cache/apk/* 

ADD ./build/libs/gs-rest-service-0.1.0.jar app.jar
ENTRYPOINT ["dumb-init","java","-Djava.security.egd=file:/dev/./urandom","-Dcom.sun.management.jmxremote","-Dcom.sun.management.jmxremote.port=9010","-Dcom.sun.management.jmxremote.local.only=false","-Dcom.sun.management.jmxremote.authenticate=false","-Dcom.sun.management.jmxremote.ssl=false","-XX:NativeMemoryTracking=summary","-verbose:gc","-XX:+PrintGCDetails","-XX:+PrintGCTimeStamps","-XX:+PrintCodeCache","-Xloggc:/tmp/gc.log","-XX:+UseGCLogFileRotation","-XX:NumberOfGCLogFiles=5","-XX:GCLogFileSize=2M" ,"-jar","/app.jar"]

