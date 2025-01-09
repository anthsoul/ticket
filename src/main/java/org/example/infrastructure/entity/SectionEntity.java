package org.example.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity(name = "sections")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SectionEntity {
    @Id
    @GeneratedValue(generator = "snowflake-id")
    @GenericGenerator(name = "snowflake-id", strategy = "org.example.infrastructure.utils.SnowflakeIdGenerator")
    @Column(name = "s_id")
    private long sId;

    @ManyToOne
    @JoinColumn(name = "s_venue_id", nullable = false)
    private VenueEntity venueEntity;

    @Column(name = "s_name", nullable = false)
    private String sName;

    @Column(name = "s_capacity", nullable = false)
    private int sCapacity;

    @Column(name = "s_shape", nullable = false)
    private int sShape;

    @Column(name = "s_x_position", nullable = false)
    private float sXPosition;

    @Column(name = "s_y_position", nullable = false)
    private float sYPosition;

    @Column(name = "s_width")
    private Float sWidth;

    @Column(name = "s_height")
    private Float sHeight;

    @Column(name = "s_radius")
    private Float sRadius;

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