# build base image
FROM openjdk:14.0.1-slim

# set deployment directory
WORKDIR /articulorum/api

# copy over the built artifact
COPY ./target/api*.jar ./articulorum-api.jar

COPY ./wait-for-it.sh ./wait-for-it.sh
RUN chmod +x wait-for-it.sh

# set the startup command to run your binary
CMD ["./wait-for-it.sh", "http://eureka:8761/eureka", "--", "java", "-jar", "./articulorum-api.jar"]