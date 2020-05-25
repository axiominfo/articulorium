# build base image
FROM openjdk:14.0.1-slim

# set deployment directory
WORKDIR /articulorum/eureka

# copy over the built artifact
COPY ./target/eureka*.jar ./articulorum-eureka.jar

# set the startup command to run your binary
CMD ["java", "-jar", "./articulorum-eureka.jar"]