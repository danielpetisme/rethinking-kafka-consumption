package com.rethinking.kafka.consumer;

import org.apache.kafka.clients.consumer.AcknowledgeType;
import org.apache.kafka.clients.consumer.AcknowledgementCommitCallback;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaShareConsumer;
import org.apache.kafka.common.TopicIdPartition;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * KIP-932 Share Group consumer.
 *
 * <p>
 * Share groups provide queue semantics: messages from a topic are distributed
 * across all consumers in the group (any consumer can receive any message).
 * Each message is delivered to exactly one consumer at a time. If not
 * acknowledged
 * within the delivery timeout, the broker redelivers the message automatically.
 *
 * <p>
 * Acknowledgement types:
 * <ul>
 * <li>ACCEPT – message processed successfully, will not be redelivered.</li>
 * <li>RELEASE – return the message to the group for redelivery.</li>
 * <li>REJECT – discard the message (moved to dead-letter handling if
 * configured).</li>
 * </ul>
 */
public class MyKafkaConsumerShare {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyKafkaConsumerShare.class);
    private static final Random RANDOM = new Random();
    // Broker-side constant — not exposed in kafka-clients, defined locally for
    // readability
    private static final String SHARE_GROUP_DELIVERY_COUNT_LIMIT_CONFIG = "group.share.delivery.count.limit";

    public static void main(String[] args) {
        String bootstrapServers = getEnvOrDefault("BOOTSTRAP_SERVERS", "localhost:9092");
        String topic = getEnvOrDefault("TOPIC_NAME", "default-topic");
        String groupId = getEnvOrDefault("GROUP_ID", "queue-consumer-group");
        long pollTimeoutMs = getEnvLongOrDefault("POLL_TIMEOUT_MS", 1000L);
        int maxPollRecords = getEnvIntOrDefault("MAX_POLL_RECORDS", 100);

        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPollRecords);
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, "consumer-share");

        properties.put(ConsumerConfig.SHARE_ACKNOWLEDGEMENT_MODE_CONFIG, "explicit");

        AtomicBoolean running = new AtomicBoolean(true);

        KafkaShareConsumer<String, String> consumer = new KafkaShareConsumer<>(properties);

        consumer.setAcknowledgementCommitCallback((offsetsMap, exception) -> {
            if (exception != null) {
                LOGGER.error("Acknowledgement commit failed for {} partition(s).", offsetsMap.size(), exception);
            } else {
                offsetsMap.forEach((partition, offsets) -> LOGGER.debug(
                        "Acknowledgements committed — topic={} partition={} offsets={}",
                        partition.topic(), partition.partition(), offsets));
            }
        });

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            running.set(false);
            consumer.wakeup();
            LOGGER.info("Consumer stopped.");
        }));

        try {
            consumer.subscribe(Collections.singletonList(topic));
            LOGGER.info("Subscribed to topic {} with share group {} (KIP-932).", topic, groupId);

            while (running.get()) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(pollTimeoutMs));

                for (ConsumerRecord<String, String> record : records) {
                    try {
                        LOGGER.info(
                                "Consumed key={} value={} from {}-{} @ offset {} (delivery #{})",
                                record.key(),
                                record.value(),
                                record.topic(),
                                record.partition(),
                                record.offset(),
                                record.deliveryCount().orElse((short) 1));

                        AcknowledgeType ackType = process(record);
                        LOGGER.info("Acknowledging key={} with {}.", record.key(), ackType);
                        consumer.acknowledge(record, ackType);
                    } catch (Exception e) {
                        LOGGER.error("Error processing record key={} — releasing for redelivery.", record.key(), e);
                        consumer.acknowledge(record, AcknowledgeType.RELEASE);
                    }
                }

                if (!records.isEmpty()) {
                    consumer.commitSync();
                }
            }
        } catch (WakeupException exception) {
            if (running.get()) {
                throw exception;
            }
        } finally {
            consumer.close();
        }
    }

    /**
     * Simulate a message processing function.
     * Randomly returns ACCEPT (50%), RELEASE (25%), or REJECT (25%)
     */
    private static AcknowledgeType process(ConsumerRecord<String, String> record) {
        int roll = RANDOM.nextInt(100);
        if (roll < 50)
            return AcknowledgeType.ACCEPT;
        if (roll < 75)
            return AcknowledgeType.RELEASE;
        return AcknowledgeType.REJECT;
    }

    private static String getEnvOrDefault(String key, String defaultValue) {
        String value = System.getenv(key);
        return value == null || value.isBlank() ? defaultValue : value;
    }

    private static int getEnvIntOrDefault(String key, int defaultValue) {
        String value = System.getenv(key);
        if (value == null || value.isBlank()) {
            return defaultValue;
        }
        return Integer.parseInt(value);
    }

    private static long getEnvLongOrDefault(String key, long defaultValue) {
        String value = System.getenv(key);
        if (value == null || value.isBlank()) {
            return defaultValue;
        }
        return Long.parseLong(value);
    }
}
