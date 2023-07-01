package org.dbs.gapi.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
public class AccountInput {

    @NotBlank(message = "Product code is mandatory")
    private String productCode;

    @NotBlank(message = "Account number is mandatory")
    private String accountNumber;
}
