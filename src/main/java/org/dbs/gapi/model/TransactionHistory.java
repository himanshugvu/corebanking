package org.dbs.gapi.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionHistory {
    private String accountNumber;
    private String productCode;
    private String transactionAccountNumber;
    private String transactionProductCode;
    private double amount;
    private String transactionType;
    private String transactionDescription;
    private LocalDateTime transactionDate;
}
