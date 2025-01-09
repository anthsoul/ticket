package org.example.application.ticket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketTypeBuyRequestDTO {
    private long ticketTypeId;
    private int number;
    private long userId;
    private long eventId;
}
