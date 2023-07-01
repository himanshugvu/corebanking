package org.dbs.gapi.model;

import lombok.Data;

import java.util.List;

@Data
public class TransactionHistoryResponse {
    private List<TransactionHistory> transactionHistory;
    private PageData page;
}
