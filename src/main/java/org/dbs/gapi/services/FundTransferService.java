package org.dbs.gapi.services;

import org.dbs.gapi.constants.Action;
import org.dbs.gapi.entity.Account;
import org.dbs.gapi.helper.FundTransferTransactionHelper;
import org.dbs.gapi.repositories.AccountRepository;
import org.dbs.gapi.model.TransactionInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.dbs.gapi.constants.Action.CREDIT;
import static org.dbs.gapi.constants.Action.DEBIT;

@Service
public class FundTransferService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    FundTransferTransactionHelper fundTransferTransactionHelper;
    public boolean makeTransfer(TransactionInput transactionInput) {
        String sourceProductCode = transactionInput.getSourceAccount().getProductCode();
        String sourceAccountNumber = transactionInput.getSourceAccount().getAccountNumber();
        Optional<Account> sourceAccountOpt = accountRepository
                .findByProductCodeAndAccountNumber(sourceProductCode, sourceAccountNumber);

        String targetProductCode = transactionInput.getTargetAccount().getProductCode();
        String targetAccountNumber = transactionInput.getTargetAccount().getAccountNumber();
        Optional<Account> targetAccountOpt = accountRepository
                .findByProductCodeAndAccountNumber(targetProductCode, targetAccountNumber);

        if (sourceAccountOpt.isPresent() && targetAccountOpt.isPresent()) {
            if (isAmountAvailable(transactionInput.getAmount(), sourceAccountOpt.get().getBalance())) {
                Account sourceAccount = updateAccountBalance(sourceAccountOpt.get(), transactionInput.getAmount(), DEBIT);
                Account targetAccount = updateAccountBalance(targetAccountOpt.get(), transactionInput.getAmount(), CREDIT);
                fundTransferTransactionHelper.fundTransfer(transactionInput, sourceAccount, targetAccount);

                return true;
            }
        }
        return false;
    }

    public Account updateAccountBalance(Account account, double amount, Action action) {
        if (action == DEBIT) {
            account.setBalance((account.getBalance() - amount));
        } else if (action == CREDIT) {
            account.setBalance((account.getBalance() + amount));
        }
        return account;
    }

    public boolean isAmountAvailable(double amount, double accountBalance) {
        return (accountBalance - amount) > 0;
    }
}
