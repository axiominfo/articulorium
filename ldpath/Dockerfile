# build base image
FROM openjdk:14.0.1-slim

# set deployment directory
WORKDIR /articulorum/ldpath

# copy over the built artifact
COPY ./target/ldpath*.jar ./articulorum-ldpath.jar

# set the startup command to run your binary
CMD ["java", "-jar", "./articulorum-ldpath.jar"]