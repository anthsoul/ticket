package org.example.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity(name = "venues")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VenueEntity {
    @Id
    @GeneratedValue(generator = "snowflake-id")
    @GenericGenerator(name = "snowflake-id", strategy = "org.example.infrastructure.utils.SnowflakeIdGenerator")
    @Column(name = "v_id")
    private long vId;

    @Column(name = "v_name", nullable = false, length = 255)
    private String vName;

    @Column(name = "v_capacity", nullable = false)
    private int vCapacity;

    @Column(name = "v_position", nullable = false, length = 255)
    private String vPosition;

    @Column(name = "v_width")
    private Float vWidth;

    @Column(name = "v_height")
    private Float vHeight;

    @Column(name = "v_radius")
    private Float vRadius;

    @Column(name = "v_shape", nullable = false)
    private int vShape;

    @Column(name = "v_description", length = 255)
    private String vDescription;

    @ManyToOne
    @JoinColumn(name = "v_event_id", nullable = false)
    private EventEntity event;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
