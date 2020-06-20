# build base image
FROM openjdk:14.0.1-slim

EXPOSE 9000

# set deployment directory
WORKDIR /articulorum/discovery

# copy over the built artifact
COPY ./target/discovery*.jar ./articulorum-discovery.jar

COPY ./wait-for-it.sh ./wait-for-it.sh
RUN chmod +x wait-for-it.sh

# set the startup command to run your binary
CMD ["./wait-for-it.sh", "-t", "90", "http://consul-server-bootstrap:8500", "--", "java", "-jar", "./articulorum-discovery.jar"]