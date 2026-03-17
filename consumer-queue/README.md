# Java Kafka Consumer Queue (KIP-932)

Maven-based Kafka share-group consumer demonstrating **KIP-932 queue semantics**.
Multiple instances of this consumer can all receive records from the same topic,
with each record delivered to exactly one consumer at a time — no partition assignment needed.

## KIP-932 Share Groups

Share groups implement queue-style consumption on top of Kafka:

- **Any consumer** in the group can receive any record from any partition.
- Records are **acknowledged per-message** using `AcknowledgeType`:
  - `ACCEPT` — processing succeeded; record is removed from the share group.
  - `RELEASE` — processing failed; record is returned to the group for redelivery.
  - `REJECT` — record is permanently discarded (dead-letter).
- `deliveryCount` on each record shows how many times it has been attempted.
- No `auto.offset.reset` — share groups maintain their own share-offset state on the broker.
- Requires **Kafka clients 4.0+** and a broker with share-coordinator support.

## Requirements

- Java 17+
- Maven 3.9+
- Kafka 4.x broker with share coordinator enabled

## Run

```bash
./mvnw compile exec:java
```

Environment variables:

- `BOOTSTRAP_SERVERS` (default: `localhost:9092`)
- `TOPIC_NAME` (default: `default-topic`)
- `GROUP_ID` (default: `queue-consumer-group`)
- `POLL_TIMEOUT_MS` (default: `1000`)
- `MAX_POLL_RECORDS` (default: `100`)

Example:

```bash
BOOTSTRAP_SERVERS=localhost:9092 TOPIC_NAME=my-topic GROUP_ID=queue-consumer-group POLL_TIMEOUT_MS=1000 MAX_POLL_RECORDS=100 ./mvnw compile exec:java
```
