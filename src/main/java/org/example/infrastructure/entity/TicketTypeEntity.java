package org.example.infrastructure.entity;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Entity(name = "ticket_types")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TicketTypeEntity {
    @Id
    @GeneratedValue(generator = "snowflake-id")
    @GenericGenerator(name = "snowflake-id", strategy = "org.example.infrastructure.utils.SnowflakeIdGenerator")
    @Column(name = "tt_id")
    private long ttId;

    @Column(name = "tt_name")
    private String ttName;

    @Column(name = "tt_price")
    private double ttPrice;

    @Column(name = "tt_quantity")
    private int ttQuantity;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb", name = "tt_benefit")
    private JsonNode TTBenefit;

    @ManyToOne
    @JoinColumn(name =  "tt_event_id")
    private EventEntity eventEntity;

    @ManyToOne
    @JoinColumn(name = "tt_section_id")
    private SectionEntity sectionEntity;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
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
