package org.dbs.gapi.controllers;

import lombok.extern.slf4j.Slf4j;
import org.dbs.gapi.services.FundTransferService;
import org.dbs.gapi.utils.InputValidator;
import org.dbs.gapi.model.TransactionInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import static org.dbs.gapi.constants.GapiConstants.INVALID_TRANSACTION;

@RestController
@RequestMapping("api/v1")
@Slf4j
public class FundTransferController {

    @Autowired
    FundTransferService fundTransferService;

    @PostMapping(value = "/fundtransfer",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> makeTransfer(
            @Valid @RequestBody TransactionInput transactionInput) {
        if (InputValidator.isSearchTransactionValid(transactionInput)) {
            boolean isComplete = fundTransferService.makeTransfer(transactionInput);
            return new ResponseEntity<>(isComplete, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(INVALID_TRANSACTION, HttpStatus.BAD_REQUEST);
        }
    }
}
