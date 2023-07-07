package org.dbs.gapi.controllers;


import lombok.extern.slf4j.Slf4j;
import org.dbs.gapi.constants.GapiConstants;
import org.dbs.gapi.entity.Account;
import org.dbs.gapi.services.AccountService;
import org.dbs.gapi.model.AccountInput;
import org.dbs.gapi.utils.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
@Slf4j
public class AccountRestController {
    @Autowired
    AccountService accountService;

    @PostMapping(value = "/accounts",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> checkAccountBalance(
            @Valid @RequestBody AccountInput accountInput) {
        log.info("Triggered AccountRestController.accountInput");
        if (InputValidator.isSearchCriteriaValid(accountInput)) {
            Account account = accountService.getAccount(
                    accountInput.getProductCode(), accountInput.getAccountNumber());
            if (account == null) {
                return new ResponseEntity<>(GapiConstants.NO_ACCOUNT_FOUND, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(account, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(GapiConstants.INVALID_SEARCH_CRITERIA, HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping(value = "/accounts",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAccount() {
        log.info("Triggered AccountRestController.createAccountInput");
            Account account = accountService.createAccount();
            if (account == null) {
                return new ResponseEntity<>(GapiConstants.CREATE_ACCOUNT_FAILED, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(account, HttpStatus.OK);
            }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}
