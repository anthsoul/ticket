package org.example.application.common.queue;

import org.springframework.stereotype.Service;

@Service
public interface ConsumerEvent {
    void consume(String topic, int consumer);
    void handleError(String topic, Exception e);
}
