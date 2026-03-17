# Java Kafka Producer

Simple Maven-based Kafka producer that publishes monotonic string key/value pairs.

## Requirements

- Java 17+
- Maven 3.9+
- Kafka broker reachable from your machine

## Run

From the `producer` folder:

```bash
./mvnw compile exec:java
```

Environment variables:

- `BOOTSTRAP_SERVERS` (default: `localhost:9092`)
- `TOPIC_NAME` (default: `monotonic-sequence`)
- `TOPIC_PARTITIONS` (default: `1`)
- `INTERVAL_MS` (default: `1000`)

Example:

```bash
BOOTSTRAP_SERVERS=localhost:9092 TOPIC_NAME=monotonic-sequence TOPIC_PARTITIONS=3 INTERVAL_MS=500 ./mvnw compile exec:java
```

On startup, the producer creates the topic if it does not already exist, using the configured partition count and a replication factor of `1`.

Each message uses the same monotonic sequence string for both key and value:

- key = `"0"`, value = `"0"`
- key = `"1"`, value = `"1"`
- key = `"2"`, value = `"2"`
- ...
