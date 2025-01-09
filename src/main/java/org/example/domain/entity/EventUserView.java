package org.example.domain.entity;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.infrastructure.entity.EventEntity;
import org.example.infrastructure.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EventUserView {
    private UserEntity user;
    private EventEntity event;
    private Integer times;
    private LocalDateTime lastModify;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
