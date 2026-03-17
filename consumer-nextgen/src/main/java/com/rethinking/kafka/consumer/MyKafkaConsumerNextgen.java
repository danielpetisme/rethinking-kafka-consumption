package com.rethinking.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

public class MyKafkaConsumerNextgen {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyKafkaConsumerNextgen.class);

    public static void main(String[] args) {
        String bootstrapServers = getEnvOrDefault("BOOTSTRAP_SERVERS", "localhost:9092");
        String topic = getEnvOrDefault("TOPIC_NAME", "default-topic");
        String groupId = getEnvOrDefault("GROUP_ID", "nextgen-consumer-group");
        String autoOffsetReset = getEnvOrDefault("AUTO_OFFSET_RESET", "earliest");
        long pollTimeoutMs = getEnvLongOrDefault("POLL_TIMEOUT_MS", 1000L);
        int maxPollRecords = getEnvIntOrDefault("MAX_POLL_RECORDS", 100);

        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.put(ConsumerConfig.GROUP_PROTOCOL_CONFIG, "consumer");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPollRecords);
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, "consumer-nextgen");

        AtomicBoolean running = new AtomicBoolean(true);

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties)) {
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                running.set(false);
                consumer.wakeup();
                LOGGER.info("Consumer stopped.");
            }));

            consumer.subscribe(Collections.singletonList(topic), new LoggingRebalanceListener(consumer));
            LOGGER.info("Subscribed to topic {} with group.id={} using next-gen group protocol (KIP-848).", topic,
                    groupId);

            while (running.get()) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(pollTimeoutMs));

                for (ConsumerRecord<String, String> record : records) {
                    LOGGER.info(
                            "Consumed key={} value={} from {}-{} @ offset {}",
                            record.key(),
                            record.value(),
                            record.topic(),
                            record.partition(),
                            record.offset());
                }

                if (!records.isEmpty()) {
                    LOGGER.info("Committing polled records...");
                    consumer.commitSync();
                }
            }
        } catch (WakeupException exception) {
            if (running.get()) {
                throw exception;
            }
        }
    }

    private static String getEnvOrDefault(String key, String defaultValue) {
        String value = System.getenv(key);
        return value == null || value.isBlank() ? defaultValue : value;
    }

    private static final class LoggingRebalanceListener implements ConsumerRebalanceListener {

        private final KafkaConsumer<String, String> consumer;

        private LoggingRebalanceListener(KafkaConsumer<String, String> consumer) {
            this.consumer = consumer;
        }

        @Override
        public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
            LOGGER.info("Partitions revoked: {}", partitions);
            if (!partitions.isEmpty()) {
                consumer.commitSync();
                LOGGER.info("Offsets committed before rebalance.");
            }
        }

        @Override
        public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
            LOGGER.info("Partitions assigned: {}", partitions);
        }

        @Override
        public void onPartitionsLost(Collection<TopicPartition> partitions) {
            LOGGER.warn("Partitions lost: {}", partitions);
        }
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