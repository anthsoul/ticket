package org.example.infrastructure.adapter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domain.entity.Event;
import org.example.domain.entity.KeyValueItem;
import org.example.infrastructure.entity.EventEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventAdapter {
    private final ObjectMapper objectMapper  = new ObjectMapper();
    public Event convertToEventDomain(EventEntity event) {
        List<KeyValueItem> list = objectMapper.convertValue(
                event.getEIntroduction(),
                new TypeReference<List<KeyValueItem>>() {}
        );
        return  Event.builder()
                .eId(event.getEId())
                .eName(event.getEName())
                .eAddress(event.getEAddress())
                .eDescription(event.getEDescription())
                .eType(event.getEType())
                .eStatus(event.getEStatus())
                .eEndTime(event.getEEndTime())
                .build();
    }
}
