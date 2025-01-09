package org.example.infrastructure.repository.jpa;

import org.example.infrastructure.entity.TicketTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketTypeRepositoryJpa extends JpaRepository<TicketTypeEntity, Long> {
}
