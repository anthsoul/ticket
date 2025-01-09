package org.example.application.event.service;

import lombok.AllArgsConstructor;
import org.example.application.common.queue.PublishEvent;
import org.example.application.common.repository.RedisRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StartEventService {
    private final PublishEvent publishEvent;
    private final RedisRepository redisRepository;
    private static final int batchSize= 100;

    public void processVirtualWaitingRoom() {

    }
}
