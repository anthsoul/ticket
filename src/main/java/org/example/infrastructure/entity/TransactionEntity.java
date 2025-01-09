package org.example.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.infrastructure.enums.TransactionStatus;
import org.hibernate.annotations.GenericGenerator;

@Entity(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransactionEntity {
    @Id
    @GeneratedValue(generator = "snowflake-id")
    @GenericGenerator(name = "snowflake-id", strategy = "org.example.infrastructure.utils.SnowflakeIdGenerator")
    private long TrId;
    private double TrAmount;
    private TransactionStatus TrStatus;
    private String TrPaymentMethod;
    private String TrTransactionCode;
}
