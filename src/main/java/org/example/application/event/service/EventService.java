package org.example.application.event.service;

import lombok.AllArgsConstructor;
import org.example.application.event.repository.EventRepository;
import org.example.application.ticket.repository.EventViewUserViewRepository;
import org.example.domain.entity.Event;
import org.example.domain.entity.EventUserView;
import org.example.domain.entity.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final EventViewUserViewRepository eventViewUserViewRepository;

    public Event getEventById(long eventId) {
        return eventRepository.getEventById(eventId);
    }
    public Page<EventUserView> getHotEvent(int page, int perPage) {
        return eventViewUserViewRepository.findHotEventByTimes(PageRequest.of(page, perPage));
    }
    public Page<Event> getRecentEvent(int page, int perPage) {
        return eventRepository.getRecentEvent(page, perPage);
    }
    public Page<Event> filterEvent(String name, String status, String eventType , LocalDateTime startTime, LocalDateTime endTime, int page, int perPage) {
        return  eventRepository.filterEvent(name, status, eventType, startTime, endTime, page, perPage);
    }
}
