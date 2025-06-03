#!/bin/sh

echo "Waiting for Kafka to be ready..."
sleep 10

/opt/kafka/bin/kafka-topics.sh \
  --bootstrap-server kafka1:29092 \
  --create \
  --topic orders \
  --partitions 3 \
  --replication-factor 3 \
  --if-not-exists

echo "Topic creation script completed."