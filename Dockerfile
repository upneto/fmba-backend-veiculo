# Maven
FROM maven:3-jdk-11
ADD . /fmba-backend-veiculo
WORKDIR /fmba-backend-veiculo
RUN ls -l
RUN mvn clean install

# Java App
FROM openjdk:11-jdk
VOLUME /tmp
COPY --from=0 "/fmba-backend-veiculo/target/fmba-backend-veiculo-*.jar" app.jar
CMD [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]