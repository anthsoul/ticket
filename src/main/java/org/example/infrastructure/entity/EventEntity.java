package org.example.infrastructure.entity;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.domain.entity.enums.EventType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Entity(name = "events")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class  EventEntity {
    @Id
    @GeneratedValue(generator = "snowflake-id")
    @GenericGenerator(name = "snowflake-id", strategy = "org.example.infrastructure.utils.SnowflakeIdGenerator")
    @Column(name = "e_id")
    private long eId;

    @Column(name = "e_name")
    private String eName;

    @Column(name = "e_description")
    private String eDescription;

    @Column(name = "e_start_time")
    private LocalDateTime eStartTime;

    @Column(name = "e_end_time")
    private LocalDateTime eEndTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "e_type")
    private EventType eType;

    @Column(name = "e_address")
    private String eAddress;

    @Column(name = "e_map_url")
    private String eMapUrl;

    @Column(name = "e_status")
    private String eStatus;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "e_introduction", columnDefinition = "jsonb")
    private JsonNode eIntroduction;

    @Column(name = "e_thumbnail")
    private String eThumbnail;

    @ManyToOne
    @JoinColumn(name = "e_company_id")
    private CompanyEntity companyEntity;

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