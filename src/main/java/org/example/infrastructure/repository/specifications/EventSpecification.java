package org.example.infrastructure.repository.specifications;

import org.example.infrastructure.entity.EventEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EventSpecification {
    public static Specification<EventEntity> hasTitle(String title) {
        return (root, query, criteriaBuilder) ->
                title != null ? criteriaBuilder.like(root.get("name"), "%" + title + "%") : null;
    }

    public static Specification<EventEntity> hasType(String type) {
        return (root, query, criteriaBuilder) ->
                type != null ? criteriaBuilder.equal(root.get("type"), type) : null;
    }

    public static Specification<EventEntity> hasTime(LocalDateTime startTime, LocalDateTime endTime) {
        return (root, query, criteriaBuilder) -> {
            if (startTime != null && endTime != null) {
                return criteriaBuilder.between(root.get("time"), startTime, endTime);
            } else if (startTime != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("time"), startTime);
            } else if (endTime != null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("time"), endTime);
            }
            return null;
        };
    }

    public Specification<EventEntity> buildSpecification(String title, String type, LocalDateTime startTime,
                                                         LocalDateTime endTime) {
        return Specification.where(EventSpecification.hasTitle(title))
                .and(EventSpecification.hasType(type))
                .and(EventSpecification.hasTime(startTime, endTime));
    }
}
