package org.dbs.gapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_generator")
    private long id;

    private long sourceAccountId;

    private long targetAccountId;
    private double amount;
    private String transactionType;
    private String transactionDescription;
    private LocalDateTime transactionDate;
}
