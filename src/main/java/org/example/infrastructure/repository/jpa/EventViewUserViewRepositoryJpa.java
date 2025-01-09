package org.example.infrastructure.repository.jpa;

import org.example.infrastructure.entity.EventUserViewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface EventViewUserViewRepositoryJpa extends JpaRepository<EventUserViewEntity, Long> {

    @Query("SELECT e FROM events_user_view e WHERE e.user.uId = :userId AND e.event.eId = :eventId")
    Optional<EventUserViewEntity> findByUserIdAndEventId(Long userId, Long eventId);

    @Query("SELECT e FROM events_user_view e WHERE e.user.uId = :userId AND e.lastModify >= :timeLimit")
    Page<EventUserViewEntity> findByUserIdRecentTime(@Param("userId") Long userId,
                                                     @Param("timeLimit") LocalDateTime timeLimit,
                                                     Pageable pageable);

    @Query("SELECT e FROM events_user_view e " +
            "JOIN e.event ev " +
            "WHERE ev.eId = :eventId " +
            "GROUP BY ev.eId " +
            "ORDER BY SUM(e.times) DESC")
    Page<EventUserViewEntity> findHotEventByTimes(@Param("eventId") Long eventId, Pageable pageable);

    @Query("SELECT SUM(e.times) FROM events_user_view e WHERE e.event.eId = :eventId")
    Integer countViewEvent(@Param("eventId") Long eventId);
}
