package org.example.infrastructure.repository.implement;

import org.example.application.ticket.dto.TicketTypeDto;
import org.example.application.ticket.repository.TicketTypeRepository;
import org.example.domain.entity.TicketType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TicketTypeRepositoryImpl implements TicketTypeRepository {
    @Override
    public List<TicketTypeDto> filterTicket() {
        return null;
    }

    @Override
    public Optional<TicketType> getTicketTypeById(long id) {
        return Optional.empty();
    }

    @Override
    public List<TicketTypeDto> getListTicketTypeByEventId(long eventId) {
        return null;
    }

    @Override
    public void saveTicketType(TicketType ticketType) {

    }
}
