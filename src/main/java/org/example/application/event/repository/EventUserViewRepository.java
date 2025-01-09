package org.example.application.event.repository;

import org.example.domain.entity.EventUserView;
import org.example.domain.entity.Page;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventUserViewRepository {
    Optional<EventUserView> findEventByUserIdAndEventId(Long userId, Long eventId);
    Page<EventUserView> findByUserIdRecentTime(Long userId, Long seconds);
    Page<EventUserView> findHotEventByTimes(Long eventId, int page, int perPage);
}
