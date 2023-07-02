package org.dbs.gapi.controllers;

import lombok.extern.slf4j.Slf4j;
import org.dbs.gapi.constants.GapiConstants;
import org.dbs.gapi.entity.Account;
import org.dbs.gapi.model.TransactionHistoryRequest;
import org.dbs.gapi.model.TransactionHistoryResponse;
import org.dbs.gapi.services.AccountService;
import org.dbs.gapi.model.AccountInput;
import org.dbs.gapi.services.TransactionHistoryService;
import org.dbs.gapi.utils.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1")
@Slf4j
public class TransactionHistoryController {

    @Autowired
    AccountService accountService;
    @Autowired
    TransactionHistoryService transactionHistoryService;

    @PostMapping(value = "/transactions",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> checkAccountBalance(
            @Valid @RequestBody TransactionHistoryRequest transactionHistoryRequest) {
        log.debug("Triggered AccountRestController.accountInput");
        Account account = accountService.getAccount(
                transactionHistoryRequest.getProductCode(), transactionHistoryRequest.getAccountNumber());
        TransactionHistoryResponse transactionHistoryResponse =
                transactionHistoryService.getTransactionHistory(account,transactionHistoryRequest.getPage());
        if (transactionHistoryResponse == null) {
            return new ResponseEntity<>(GapiConstants.NO_TRANSACTION_FOUND, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(transactionHistoryResponse, HttpStatus.OK);
        }

    }
}
