package org.dbs.gapi.utils;

import org.dbs.gapi.model.AccountInput;
import org.dbs.gapi.model.TransactionInput;

import static org.dbs.gapi.constants.GapiConstants.ACCOUNT_NUMBER_PATTERN;
import static org.dbs.gapi.constants.GapiConstants.PRODUCT_CODE_PATTERN;

public class InputValidator {

    public static boolean isSearchCriteriaValid(AccountInput accountInput) {
        return PRODUCT_CODE_PATTERN.matcher(accountInput.getProductCode()).find() &&
                ACCOUNT_NUMBER_PATTERN.matcher(accountInput.getAccountNumber()).find();
    }

    public static boolean isAccountNoValid(String accountNo) {
        return ACCOUNT_NUMBER_PATTERN.matcher(accountNo).find();
    }

    public static boolean isSearchTransactionValid(TransactionInput transactionInput) {
        if (!isSearchCriteriaValid(transactionInput.getSourceAccount()))
            return false;

        if (!isSearchCriteriaValid(transactionInput.getTargetAccount()))
            return false;

        if (transactionInput.getSourceAccount().equals(transactionInput.getTargetAccount()))
            return false;

        return true;
    }
}
