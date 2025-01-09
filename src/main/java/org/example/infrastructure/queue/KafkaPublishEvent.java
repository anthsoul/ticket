package org.example.infrastructure.queue;

import lombok.AllArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.application.common.queue.PublishEvent;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class KafkaPublishEvent implements PublishEvent {
    private final  String DEAD_LETTER_QUEUE_TOPIC = "";
    private final KafkaProducer<String, String> kafkaProducer;

    @Override
    public void publishEvent(String topic, String payload) {
        kafkaProducer.send(new ProducerRecord<>(topic, payload), (metadata, exception) -> {
            if (exception != null) {
                System.err.println("Error sending message to Kafka: " + exception.getMessage());
                sendToDeadLetterQueue(DEAD_LETTER_QUEUE_TOPIC, payload);
            } else {
                System.out.printf("Message sent to topic %s, partition %d, offset %d%n",
                        metadata.topic(), metadata.partition(), metadata.offset());
            }
        });
    }

    @Override
    public void sendToDeadLetterQueue(String dlqTopic, String payload) {
        try {
            kafkaProducer.send(new ProducerRecord<>(dlqTopic, payload), (metadata, exception) -> {
                if (exception != null) {
                    System.err.println("Failed to send to DLQ: " + exception.getMessage());
                } else {
                    System.out.println("Message sent to DLQ: " + dlqTopic);
                }
            }).get();
        } catch (Exception e) {
            System.err.println("Critical failure: Could not send to DLQ either. " + e.getMessage());
        }
    }
}
