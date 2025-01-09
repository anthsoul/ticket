package org.example.application.ticket.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TicketProcessorService {
    private final  VirtualWaitingService virtualWaitingService;

    public void processRequest() {

    }
}
