package org.example.application.ticket.service;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AllArgsConstructor;
import org.example.application.common.mapper.ObjectClassMapper;
import org.example.application.common.repository.RedisRepository;
import org.example.application.event.repository.EventRepository;
import org.example.application.ticket.dto.TicketTypeBuyRequestDTO;
import org.example.application.ticket.dto.TicketTypeBuyResponseDTO;
import org.example.application.ticket.dto.TicketTypeDto;
import org.example.application.ticket.repository.TicketTypeRepository;
import org.example.application.user.exception.InternalServerError;
import org.example.application.user.exception.ResourceNotFoundException;
import org.example.domain.entity.Event;
import org.example.domain.entity.TicketType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

@Service
@AllArgsConstructor
public class TicketTypeService {
    private final Logger logger = LoggerFactory.getLogger(TicketTypeService.class);
    private final ReentrantLock lock = new ReentrantLock();
    private final ObjectClassMapper objectClassMapper;
    private final TicketTypeRepository ticketTypeRepository;
    private final RedisRepository redisRepository;
    private final EventRepository eventRepository;
    private final String REDIS_EVENT_PREFIX = "EVENT";
    private final String REDIS_TICKET_TYPE_PREFIX = "TICKET_TYPE";

    public List<TicketTypeDto> getAllTicketTypeFromEvent(long eventId, int page, int perPage) {
        Event eventDTO = eventRepository.getEventById(eventId);

        String key = REDIS_EVENT_PREFIX + ":" + eventId;
        String redisValue = redisRepository.get(key);
        if (redisValue != null) {
            try {
                List<TicketTypeDto> list = objectClassMapper.mapToObject(redisValue, new TypeReference<List<TicketTypeDto>>() {});
                if (!list.isEmpty()) {
                    return list;
                }
            } catch (Exception e) {
                logger.error("Error parsing Redis value for key {}: {}", key, e.getMessage(), e);
            }
        }
        logger.info("Redis cache miss for event {}", eventId);
        lock.lock();
        try {
            redisValue = redisRepository.get(key);
            if (redisValue != null) {
                return objectClassMapper.mapToObject(redisValue, new TypeReference<List<TicketTypeDto>>() {});
            }
            List<TicketTypeDto> list = ticketTypeRepository.getListTicketTypeByEventId(eventId);
            if (!list.isEmpty()) {
                for (TicketTypeDto ticketTypeDto : list) {
                    try {
                        redisRepository.hashSet(key, buildTicketTypeKey(ticketTypeDto.getId()), objectClassMapper.mapToString(ticketTypeDto));
                    } catch (Exception e) {
                        logger.error("Error setting event to Redis for key {}: {}", key, e.getMessage(), e);
                    }
                }
            }
            return list;
        } finally {
            lock.unlock();
        }
    }

    private String buildRedisKey(long eventId) {
        return REDIS_EVENT_PREFIX + ":" + eventId;
    }
    private String buildTicketTypeKey(long ticketTypeId) {return  REDIS_TICKET_TYPE_PREFIX + ":" + ticketTypeId; };

    public TicketTypeBuyResponseDTO buyTicketType(TicketTypeBuyRequestDTO ticketTypeBuyRequestDTO) {
        TicketTypeDto ticketTypeDto = null;
        try {
            ticketTypeDto =objectClassMapper.mapToObject(
                    redisRepository.hashGet(buildRedisKey(ticketTypeBuyRequestDTO.getEventId()),buildTicketTypeKey(ticketTypeBuyRequestDTO.getTicketTypeId())),
                    TicketTypeDto.class
                    );
        } catch (Exception e) {
            logger.error("Error to get key from redis event id {} ticket type id {}", ticketTypeBuyRequestDTO.getEventId(), ticketTypeBuyRequestDTO.getTicketTypeId());
            throw new InternalServerError("Error to get key from redis");
        }
        if (ticketTypeDto == null) {
            logger.error("Ticket type in redis not found");
            throw new ResourceNotFoundException("Ticket type in redis not found");
        }
        Optional<TicketType> ticketType = ticketTypeRepository.getTicketTypeById(ticketTypeBuyRequestDTO.getTicketTypeId());
        if(ticketType.isEmpty()) {
            logger.error("Ticket type not found {}" , ticketTypeBuyRequestDTO.getTicketTypeId());
            throw  new ResourceNotFoundException("Ticket type not found");
        }
        ticketType.get().buyTicketType(ticketTypeBuyRequestDTO.getNumber());
        try {
            ticketTypeRepository.saveTicketType(ticketType.get());
        } catch (Exception e) {
            logger.error("Error to save ticket type {}",ticketTypeBuyRequestDTO.getTicketTypeId());
            throw new ResourceNotFoundException("Error to save ticket type");
        }
        return  null;
    }
}

