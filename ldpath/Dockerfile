# build base image
FROM openjdk:14.0.1-slim

EXPOSE 8000
EXPOSE 9000

# set deployment directory
WORKDIR /articulorum/ldpath

# copy over the built artifact
COPY ./target/ldpath*.jar ./articulorum-ldpath.jar

COPY ./wait-for-it.sh ./wait-for-it.sh
RUN chmod +x wait-for-it.sh

# set the startup command to run your binary
CMD ["./wait-for-it.sh", "-t", "60", "http://api:9000", "--", "./wait-for-it.sh", "-t", "60", "http://consul-server-bootstrap:8500", "--", "java", "-jar", "./articulorum-ldpath.jar"]