#!/usr/bin/env sh

mvn clean package
docker stop kafka-avro-tutorial
docker rm kafka-avro-tutorial
docker rmi -f viniciussf/kafka-avro-tutorial:latest
docker build . -t viniciussf/kafka-avro-tutorial:latest