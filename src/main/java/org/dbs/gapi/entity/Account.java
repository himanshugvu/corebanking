package org.dbs.gapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_generator")
    private long id;

    private String productCode;

    private String accountNumber;

    private double balance;

    public Account(String productCode, String accountNumber, double balance) {
        this.productCode = productCode;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

}
