package org.example.application.ticket.repository;

import org.example.application.ticket.dto.TicketTypeDto;
import org.example.domain.entity.TicketType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketTypeRepository {
    List<TicketTypeDto> filterTicket();
    Optional<TicketType> getTicketTypeById(long id);
    public List<TicketTypeDto> getListTicketTypeByEventId(long eventId);
    void saveTicketType(TicketType ticketType);
}
