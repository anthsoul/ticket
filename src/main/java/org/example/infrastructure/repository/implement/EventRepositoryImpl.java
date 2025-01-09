package org.example.infrastructure.repository.implement;

import lombok.AllArgsConstructor;
import org.example.application.event.repository.EventRepository;
import org.example.domain.entity.Event;
import org.example.domain.entity.Page;
import org.example.infrastructure.adapter.PageAdapter;
import org.example.infrastructure.entity.EventEntity;
import org.example.infrastructure.repository.jpa.EventRepositoryJpa;
import org.example.infrastructure.repository.specifications.EventSpecification;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class EventRepositoryImpl implements EventRepository {
    private final EventRepositoryJpa eventRepositoryJpa;
    private final EventSpecification eventSpecification;
    @Override
    public Optional<Event> getEventById(long eventId) {
        return Optional.empty();
    }

    @Override
    public List<Event> getListEventByCompany(long companyId, int page, int perPage) {
        return null;
    }

    @Override
    public void updateEvent() {

    }

    @Override
    public void deleteEvent(long eventId) {

    }

    @Override
    public Page<Event> filterEvent(String name, String status, String type, LocalDateTime startTime, LocalDateTime endTime, int page, int perPage) {
        Specification<EventEntity> eventEntitySpecification = eventSpecification.buildSpecification(
                name, type, startTime, endTime
        );

        Pageable pageable = PageRequest.of(page, perPage);
        Page<EventEntity> events =PageAdapter.convertToCustomPage(eventRepositoryJpa.findAll(eventEntitySpecification, pageable));
        return null;
    }
}
