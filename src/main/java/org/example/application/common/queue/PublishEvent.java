package org.example.application.common.queue;

import org.springframework.stereotype.Service;

@Service
public interface PublishEvent {
    void publishEvent(String topic, String payload);
    void sendToDeadLetterQueue(String topic, String payload);
}
