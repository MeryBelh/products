FROM openjdk:17-jdk-slim
VOLUME /tmp
EXPOSE 8080
ADD target/products-0.0.1-SNAPSHOT.jar products.jar
ENTRYPOINT ["java","-jar","/products.jar"]
