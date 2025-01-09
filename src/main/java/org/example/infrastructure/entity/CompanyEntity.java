package org.example.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.infrastructure.enums.CompanyVerifyStatus;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity(name = "companies")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CompanyEntity {
    @Id
    @GeneratedValue(generator = "snowflake-id")
    @GenericGenerator(name = "snowflake-id", strategy = "org.example.infrastructure.utils.SnowflakeIdGenerator")
    @Column(name = "c_id")
    private long cId;

    @Column(name = "c_name")
    private String cName;

    @Column(name = "c_tax_number")
    private String cTaxNumber;

    @Column(name = "c_registration_number")
    private String cRegistrationNumber;

    @Column(name = "c_bank_account_number")
    private String cBankAccountNumber;

    @Column(name = "c_bank_name")
    private String cBankName;

    @Column(name = "c_email")
    private String cEmail;

    @Column(name = "c_phone_number")
    private String cPhoneNumber;

    @Column(name = "c_address")
    private String cAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "c_verify_status")
    private CompanyVerifyStatus cVerifyStatus;

    @OneToOne
    @JoinColumn(name = "c_user_id")
    private UserEntity userEntity;

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