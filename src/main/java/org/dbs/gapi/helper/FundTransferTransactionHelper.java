package org.dbs.gapi.helper;

import org.dbs.gapi.entity.Account;
import org.dbs.gapi.entity.Transaction;
import org.dbs.gapi.repositories.AccountRepository;
import org.dbs.gapi.repositories.TransactionRepository;
import org.dbs.gapi.model.TransactionInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.dbs.gapi.constants.Action.CREDIT;
import static org.dbs.gapi.constants.Action.DEBIT;

@Component
public class FundTransferTransactionHelper {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Transactional(rollbackFor = Exception.class)
    public void fundTransfer(TransactionInput transactionInput, Account sourceAccount,
                             Account targetAccount) {
        LocalDateTime transactionDate = LocalDateTime.now();

        accountRepository.save(sourceAccount);

        var debitTransaction = new Transaction();
        debitTransaction.setAmount(transactionInput.getAmount());
        debitTransaction.setSourceAccountId(sourceAccount.getId());
        debitTransaction.setTargetAccountId(targetAccount.getId());
        debitTransaction.setTransactionDate(transactionDate);
        debitTransaction.setTransactionType(DEBIT.name());
        debitTransaction.setTransactionDescription(transactionInput.getTransactionDescription());
        transactionRepository.save(debitTransaction);

        accountRepository.save(targetAccount);

        var creditTransaction = new Transaction();
        creditTransaction.setAmount(transactionInput.getAmount());
        creditTransaction.setSourceAccountId(targetAccount.getId());
        creditTransaction.setTargetAccountId(sourceAccount.getId());
        creditTransaction.setTransactionDate(transactionDate);
        creditTransaction.setTransactionType(CREDIT.name());
        creditTransaction.setTransactionDescription(transactionInput.getTransactionDescription());
        transactionRepository.save(creditTransaction);
    }
}
