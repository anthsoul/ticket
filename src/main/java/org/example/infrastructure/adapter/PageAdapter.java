package org.example.infrastructure.adapter;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PageAdapter {
    public static  <T> org.example.domain.entity.Page<T> convertToCustomPage(Page<T> jpaPage) {
        return org.example.domain.entity.Page.<T>builder()
                .items(jpaPage.getContent())
                .totalItems((int) jpaPage.getTotalElements())
                .pageSize(jpaPage.getSize())
                .currentPage(jpaPage.getNumber() + 1)
                .build();
    }
}
