FROM openjdk:17.0

EXPOSE 8095

ADD build/libs/e-auction-gateway.jar e-auction-gateway.jar

ENTRYPOINT ["java","-jar", "e-auction-gateway.jar"]