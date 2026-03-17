# Java Kafka Consumer Nextgen

Simple Maven-based Kafka consumer that reads string key/value pairs from Kafka.

## Requirements

- Java 17+
- Maven 3.9+
- Kafka broker reachable from your machine

## Run

From the `consumer-nextgen` folder:

```bash
./mvnw compile exec:java
```

Environment variables:

- `BOOTSTRAP_SERVERS` (default: `localhost:9092`)
- `TOPIC_NAME` (default: `default-topic`)
- `GROUP_ID` (default: `nextgen-consumer-group`)
- `AUTO_OFFSET_RESET` (default: `earliest`)
- `POLL_TIMEOUT_MS` (default: `1000`)
- `MAX_POLL_RECORDS` (default: `100`)

Example:

```bash
BOOTSTRAP_SERVERS=localhost:9092 TOPIC_NAME=my-topic GROUP_ID=nextgen-consumer-group AUTO_OFFSET_RESET=earliest POLL_TIMEOUT_MS=1000 MAX_POLL_RECORDS=100 ./mvnw compile exec:java
```

The consumer is explicitly configured with the next-generation consumer group protocol (KIP-848) by setting `group.protocol=consumer`.

It also registers a rebalance listener that logs partition assignment, revocation, and loss events, and commits offsets before partitions are revoked.
