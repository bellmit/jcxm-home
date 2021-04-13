FROM java:8
RUN mkdir -p /tmp
WORKDIR /tmp
COPY target/jcxm-home.jar ./app.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","app.jar", "--spring.profiles.active=prod"]
