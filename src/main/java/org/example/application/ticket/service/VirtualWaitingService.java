package org.example.application.ticket.service;

import lombok.AllArgsConstructor;
import org.example.application.common.repository.RedisRepository;
import org.example.application.user.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VirtualWaitingService {
    private final RedisRepository redisRepository;
    private static final String VIRTUAL_WAITING_ROOM_PREFIX = "virtual";
    private static final String VIRTUAL_WAITING_ROOM_INDEX = "virtual:index";
    private static final long MAX_SIZE = 10000;
    private String buildVirtualQueueName(long eventId) {
        return VIRTUAL_WAITING_ROOM_PREFIX + ":"+ eventId;
    }
    private String buildHashIndexVirtualQueueName(long eventId) {
        return VIRTUAL_WAITING_ROOM_INDEX+ ":"+ eventId;
    }
    public void pushUserToWaitingRoom(long eventId, long sessionId) {
        if(getVirtualWaitingRoomSize(eventId) < MAX_SIZE) {
            Long current = getVirtualWaitingRoomSize(eventId);
            redisRepository.LPush(buildVirtualQueueName(eventId), String.valueOf(sessionId));
            redisRepository.hashSet(buildHashIndexVirtualQueueName(eventId), String.valueOf(sessionId), current+1);
        } else {
            throw  new ResourceNotFoundException("Waiting room is full. Try later");
        }
    }
    public String getUserFromWaitingRoom(long eventId) {
        return  redisRepository.RPop(buildVirtualQueueName(eventId));
    }
    public Long getVirtualWaitingRoomSize(long eventId){
        return  redisRepository.getListSize(buildVirtualQueueName(eventId));
    }
    public Long getCurrentIndexUserInRoom(long eventId, long sessionId) {
        return Long.parseLong(redisRepository.hashGet(buildHashIndexVirtualQueueName(eventId), String.valueOf(sessionId)));
    }
}
