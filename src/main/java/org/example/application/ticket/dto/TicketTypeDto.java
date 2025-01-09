package org.example.application.ticket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.domain.entity.KeyValueItem;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketTypeDto {
    private long id;
    private String name;
    private String price;
    private int quantity;
    private List<KeyValueItem> benefits;
}
