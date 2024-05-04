FROM openjdk:17
EXPOSE 8050
ADD target/buildlive-userservice.jar buildlive-userservice.jar
ENTRYPOINT ["java","-jar","/buildlive-userservice.jar"]