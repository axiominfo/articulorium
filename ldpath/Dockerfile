# build base image
FROM openjdk:14.0.1-slim

# set deployment directory
WORKDIR /articulorum/ldpath

# copy over the built artifact
COPY ./target/ldpath*.jar ./articulorum-ldpath.jar

COPY ./wait-for-it.sh ./wait-for-it.sh
RUN chmod +x wait-for-it.sh

# set the startup command to run your binary
CMD ["./wait-for-it.sh", "http://api:8080", "--", "java", "-jar", "./articulorum-ldpath.jar"]