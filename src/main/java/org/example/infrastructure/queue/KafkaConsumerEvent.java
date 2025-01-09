package org.example.infrastructure.queue;

import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.example.application.common.queue.ConsumerEvent;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@AllArgsConstructor
@Service
public class KafkaConsumerEvent implements ConsumerEvent {
    private final Properties kafkaConsumerProperties;

    @Override
    public void consume(String topic, int consumerCount) {
        try (ExecutorService executorService = Executors.newFixedThreadPool(consumerCount)) {
            for (int i = 0; i < consumerCount; i++) {
                int consumerId = i;
                executorService.submit(() -> {
                    try (KafkaConsumer<String, String> localConsumer = new KafkaConsumer<>(kafkaConsumerProperties)) {
                        localConsumer.subscribe(Collections.singletonList(topic));
                        while (true) {
                            ConsumerRecords<String, String> records = localConsumer.poll(Duration.ofMillis(100));
                            for (ConsumerRecord<String, String> record : records) {
                                System.out.printf("Consumer %d received message: %s from partition: %d%n",
                                        consumerId, record.value(), record.partition());
                            }
                        }
                    } catch (Exception e) {
                        System.err.printf("Consumer %d encountered an error: %s%n", consumerId, e.getMessage());
                    }
                });
            }
        }
    }

    @Override
    public void handleError(String topic, Exception e) {
        System.err.printf("Error processing topic %s: %s%n", topic, e.getMessage());
    }
}
