package org.dbs.gapi.services;


import org.dbs.gapi.entity.Account;
import org.dbs.gapi.entity.Transaction;
import org.dbs.gapi.model.PageData;
import org.dbs.gapi.model.TransactionHistory;
import org.dbs.gapi.model.TransactionHistoryResponse;
import org.dbs.gapi.repositories.AccountRepository;
import org.dbs.gapi.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionHistoryService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionRepository transactionRepository;
    public TransactionHistoryResponse getTransactionHistory(Account account, PageData page) {
        TransactionHistoryResponse transactionHistoryResponse = new TransactionHistoryResponse();
        transactionHistoryResponse.setPage(page);
        Pageable pageable = PageRequest.of(page.getPageNumber(), page.getPageSize(),
                Sort.by("id").descending());
        Page<Transaction> transactionPage = transactionRepository
                .findBySourceAccountId(account.getId(), pageable);
        List<Transaction> transactions = transactionPage.getContent();
        List<TransactionHistory> transactionHistoryList = new ArrayList<>();
        for(Transaction transaction: transactions){
            TransactionHistory transactionHistory = new TransactionHistory();
            transactionHistory.setAccountNumber(account.getAccountNumber());
            transactionHistory.setProductCode(account.getProductCode());
            transactionHistory.setAmount(transaction.getAmount());
            transactionHistory.setTransactionType(transaction.getTransactionType());
            transactionHistory.setTransactionDescription(transaction.getTransactionDescription());
            transactionHistory.setTransactionDate(transaction.getTransactionDate());
            Account targetAccount = accountRepository.findById(transaction.getTargetAccountId()).get();
            transactionHistory.setTransactionAccountNumber(targetAccount.getAccountNumber());
            transactionHistory.setTransactionProductCode(targetAccount.getProductCode());
            transactionHistoryList.add(transactionHistory);
        }
        transactionHistoryResponse.setTransactionHistory(transactionHistoryList);
        return transactionHistoryResponse;
    }
}
