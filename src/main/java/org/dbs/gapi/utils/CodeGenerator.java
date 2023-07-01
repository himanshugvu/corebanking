package org.dbs.gapi.utils;

import com.mifmif.common.regex.Generex;
import lombok.Data;

import static org.dbs.gapi.constants.GapiConstants.*;
@Data
public class CodeGenerator {
    Generex productCodeGenerex = new Generex(PRODUCT_CODE_PATTERN_STRING);
    Generex accountNumberGenerex = new Generex(ACCOUNT_NUMBER_PATTERN_STRING);

    public String generateProductCode() {
        return productCodeGenerex.random();
    }

    public String generateAccountNumber() {
        return accountNumberGenerex.random();
    }
}
