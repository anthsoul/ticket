package org.example.application.ticket.repository;

import org.example.domain.entity.EventUserView;
import org.example.domain.entity.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;

public interface EventViewUserViewRepository {
    Optional<EventUserView> findByUserIdAndEventId(Long userId, Long eventId);
    Page<EventUserView> findByUserIdRecentTime(Long userId, LocalDateTime timeLimit, int page, int perPage);
    Page<EventUserView> findHotEventByTimes(Pageable pageable);
    Integer countViewEvent(Long eventId);
}
