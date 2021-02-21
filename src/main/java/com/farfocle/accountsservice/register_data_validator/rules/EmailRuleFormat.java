package com.farfocle.accountsservice.register_data_validator.rules;

import com.farfocle.accountsservice.register_data_validator.RegisterData;
import com.farfocle.accountsservice.register_data_validator.RegisterError;

public class EmailRuleFormat implements Rule{

    @Override
    public boolean validate(RegisterData data) {
        return false;
    }

    @Override
    public RegisterError getErrorType() {
        return null;
    }

    @Override
    public boolean isInterrupting() {
        return false;
    }
}
