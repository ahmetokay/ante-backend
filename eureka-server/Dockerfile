FROM openjdk:17
MAINTAINER it@antegrup.com.tr
COPY target/eureka-server.jar eureka-server.jar
ARG KULLANICI_ADI
ARG SIFRE
ENV KULLANICI_ADI $KULLANICI_ADI
ENV SIFRE $SIFRE
ENTRYPOINT ["java","-jar","/eureka-server.jar"]