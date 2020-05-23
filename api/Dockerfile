# build base image
FROM openjdk:14.0.1-slim

# set deployment directory
WORKDIR /articulorum/api

# copy over the built artifact
COPY ./target/api*.jar ./articulorum-api.jar

# set the startup command to run your binary
CMD ["java", "-jar", "./articulorum-api.jar"]