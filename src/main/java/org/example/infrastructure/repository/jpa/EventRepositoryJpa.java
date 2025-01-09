package org.example.infrastructure.repository.jpa;

import org.example.domain.entity.enums.EventType;
import org.example.infrastructure.entity.EventEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepositoryJpa extends JpaRepository<EventEntity, Long>, JpaSpecificationExecutor<EventEntity> {
    Optional<EventEntity> findByeId(long eId);
//    List<EventEntity> findByECompanyId(long id, Pageable pageable);
    List<EventEntity> findEventByEType(EventType eType, Pageable pageable);
//    List<EventEntity> filterBy(String search);
}
