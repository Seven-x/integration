FROM java:8

VOLUME /tmp

COPY target/in-redis-0.0.1-SNAPSHOT.jar in/in-redis.jar

RUN bash -c "touch /in-redis.jar"

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "-Duser.timezone=GMT+08", "/in/in-redis.jar"]