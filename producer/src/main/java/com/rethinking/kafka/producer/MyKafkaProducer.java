package com.rethinking.kafka.producer;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.TopicExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

public class MyKafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyKafkaProducer.class);

    public static void main(String[] args) {
        String bootstrapServers = getEnvOrDefault("BOOTSTRAP_SERVERS", "localhost:9092");
        String topic = getEnvOrDefault("TOPIC_NAME", "default-topic");
        int topicPartitions = getEnvIntOrDefault("TOPIC_PARTITIONS", 1);
        long intervalMs = getEnvLongOrDefault("INTERVAL_MS", 1000L);

        createTopicIfNeeded(bootstrapServers, topic, topicPartitions);

        Properties properties = new Properties();
        properties.put("bootstrap.servers", bootstrapServers);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("acks", "all");
        properties.put("enable.idempotence", "true");
        properties.put("client.id", "my-kafka-producer");

        AtomicBoolean running = new AtomicBoolean(true);

        try (KafkaProducer<String, String> producer = new KafkaProducer<>(properties)) {
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                running.set(false);
                producer.flush();
                producer.close(Duration.ofSeconds(5));
                LOGGER.info("Producer stopped.");
            }));

            long sequence = 0L;
            while (running.get()) {
                String current = String.valueOf(sequence++);
                ProducerRecord<String, String> record = new ProducerRecord<>(topic, current, current);

                producer.send(record, (metadata, exception) -> {
                    if (exception != null) {
                        LOGGER.error("Failed to produce key={} value={}",
                                record.key(),
                                record.value(),
                                exception);
                        return;
                    }

                    LOGGER.info("Produced key={} value={} to {}-{} @ offset {}",
                            record.key(),
                            record.value(),
                            metadata.topic(),
                            metadata.partition(),
                            metadata.offset());
                });

                try {
                    Thread.sleep(intervalMs);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    running.set(false);
                }
            }
        }
    }

    private static void createTopicIfNeeded(String bootstrapServers, String topic, int partitions) {
        Properties adminProperties = new Properties();
        adminProperties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        try (AdminClient adminClient = AdminClient.create(adminProperties)) {
            NewTopic newTopic = new NewTopic(topic, partitions, (short) 1);
            adminClient.createTopics(Collections.singleton(newTopic)).all().get();
            LOGGER.info("Created topic {} with {} partition(s).", topic, partitions);
        } catch (Exception exception) {
            Throwable cause = exception.getCause();
            if (cause instanceof TopicExistsException) {
                LOGGER.info("Topic {} already exists.", topic);
                return;
            }
            throw new RuntimeException("Unable to create topic " + topic, exception);
        }
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
