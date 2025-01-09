package org.example.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TicketType {
    private long id;
    private String name;
    private String price;
    private int quantity;
    private List<KeyValueItem> benefits;
    private Event event;
    private Section section;
    public void buyTicketType(int number) {
        this.quantity = this.quantity - number;
    }
}
