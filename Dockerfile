FROM adoptopenjdk:11-jre-hotspot

ARG VERSION
ARG VCS_REF
ARG BUILD_DATE

VOLUME /logs

RUN echo $VCS_REF
RUN echo $BUILD_DATE

ADD  application/build/libs/ApartmentMonitoring-$VERSION.jar application.jar

ENTRYPOINT ["java", "-jar", "/application.jar"]