package org.example.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(generator = "snowflake-id")
    @GenericGenerator(name = "snowflake-id", strategy = "org.example.infrastructure.utils.SnowflakeIdGenerator")
    @Column(name = "u_id")
    private long uId;

    @Column(name = "u_name")
    private String uName;

    @Column(name = "u_avatar")
    private String uImage;

    @Column(name = "u_username")
    private String uUsername;

    @Column(name = "u_email")
    private String uEmail;

    @Column(name = "u_password")
    private String uPassword;

    @Column(name = "u_role")
    private String uRole;

    @Column(name = "u_phone_number")
    private String uPhoneNumber;

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
