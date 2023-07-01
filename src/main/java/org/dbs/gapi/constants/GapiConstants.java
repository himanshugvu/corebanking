package org.dbs.gapi.constants;

import java.util.regex.Pattern;

public class GapiConstants {

    public static final String SUCCESS =
            "Operation completed successfully";
    public static final String NO_ACCOUNT_FOUND =
            "Unable to find an account matching this product code and account number";

    public static final String NO_TRANSACTION_FOUND =
            "Unable to find any transaction for the product code and account number";
    public static final String INVALID_SEARCH_CRITERIA =
            "The provided product code or account number did not match the expected format";

    public static final String INSUFFICIENT_ACCOUNT_BALANCE =
            "Your account does not have sufficient balance";

    public static final String PRODUCT_CODE_PATTERN_STRING = "[0-9]{4}";

    public static final String ACCOUNT_NUMBER_PATTERN_STRING = "[0-9]{14}";
    public static final Pattern PRODUCT_CODE_PATTERN = Pattern.compile("^[0-9]{4}$");
    public static final Pattern ACCOUNT_NUMBER_PATTERN = Pattern.compile("^[0-9]{14}$");

    public static final String INVALID_TRANSACTION =
            "Account information is invalid or transaction has been denied for your protection. Please try again.";
    public static final String CREATE_ACCOUNT_FAILED =
            "Error happened during creating new account";
}
