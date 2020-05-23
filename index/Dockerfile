# build base image
FROM openjdk:14.0.1-slim

# set deployment directory
WORKDIR /articulorum/index

# copy over the built artifact
COPY ./target/index*.jar ./articulorum-index.jar

# set the startup command to run your binary
CMD ["java", "-jar", "./articulorum-index.jar"]