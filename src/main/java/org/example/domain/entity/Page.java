package org.example.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Page<T> {
    private List<T> items;
    private int totalItems;
    private int pageSize;
    private int currentPage;

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
