package org.dbs.gapi.services;


import org.dbs.gapi.entity.Account;
import org.dbs.gapi.repositories.AccountRepository;
import org.dbs.gapi.utils.CodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public Account getAccount(String productCode, String accountNumber) {
        Optional<Account> account = accountRepository
                .findByProductCodeAndAccountNumber(productCode, accountNumber);

        return account.orElse(null);
    }

    public Account createAccount() {
        CodeGenerator codeGenerator = new CodeGenerator();
        Account newAccount = new Account(codeGenerator.generateProductCode(),
                codeGenerator.generateAccountNumber(), 1000.00);
        return accountRepository.save(newAccount);
    }
}
