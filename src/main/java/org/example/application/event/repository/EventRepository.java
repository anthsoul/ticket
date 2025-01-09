package org.example.application.event.repository;

import org.example.domain.entity.Event;
import org.example.domain.entity.Page;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository {
    Optional<Event> getEventById(long eventId);
    List<Event> getListEventByCompany(long companyId, int page, int perPage);
    void updateEvent();
    void deleteEvent(long eventId);
    Page<Event> filterEvent(String name,String status, String type, LocalDateTime startTime, LocalDateTime endTime, int page, int perPage);
}
