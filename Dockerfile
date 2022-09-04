FROM openjdk:11
LABEL verion="1.0.0"
COPY ./target/kafka-avro-tutorial-0.0.1-SNAPSHOT.jar kafka-avro-tutorial.jar
CMD ["java","-jar","kafka-avro-tutorial.jar"]
