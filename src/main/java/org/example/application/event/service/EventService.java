package org.example.application.event.service;

import lombok.AllArgsConstructor;
import org.example.application.event.repository.EventRepository;
import org.example.domain.entity.Event;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public List<Event> getAllEvent() {
        return  null;
    }
    public Event getEventById() {
        return null;
    }
    public List<Event> filterEvent(String name,String status, String eventType,double price, LocalDateTime startTime, LocalDateTime endTime) {
        return  null;
    }
}
