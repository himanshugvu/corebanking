package org.dbs.gapi.model;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class TransactionHistoryRequest {
    @NotBlank(message = "Product code is mandatory")
    private String productCode;

    @NotBlank(message = "Account number is mandatory")
    private String accountNumber;
    private PageData page;
}
