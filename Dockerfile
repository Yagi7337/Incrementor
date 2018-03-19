FROM maven:jdk8 AS BUILD_IMAGE
USER root
RUN mkdir /apps
WORKDIR /apps
ADD . .
RUN gradle package

FROM openjdk:8-jre
COPY --from=BUILD_IMAGE /apps/target/incrementor-0.0.1-SNAPSHOT.jar .