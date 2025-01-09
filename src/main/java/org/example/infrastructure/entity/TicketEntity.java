package org.example.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.infrastructure.enums.TicketStatus;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity(name = "tickets")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TicketEntity {
    @Id
    @GeneratedValue(generator = "snowflake-id")
    @GenericGenerator(name = "snowflake-id", strategy = "org.example.infrastructure.utils.SnowflakeIdGenerator")
    @Column(name = "t_id")
    private long TId;

    @Column(name = "t_status")
    private TicketStatus TStatus;

    @ManyToOne
    @JoinColumn(name = "t_event_id")
    private EventEntity eventEntity;

    @ManyToOne
    @JoinColumn(name = "t_buyer_id")
    private UserEntity userEntity;

    @OneToOne
    @JoinColumn(name = "t_transaction_id")
    private TransactionEntity transactionEntity;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
