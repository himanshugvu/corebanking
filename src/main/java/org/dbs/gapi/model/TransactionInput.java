package org.dbs.gapi.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
@Data
public class TransactionInput {

    private AccountInput sourceAccount;

    private AccountInput targetAccount;

    @Positive(message = "Transfer amount must be positive")
    @Min(value = 1, message = "Amount must be larger than 1")
    private double amount;
    private String transactionDescription;
}
