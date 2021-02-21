package com.farfocle.accountsservice.register_data_validator.rules;

import com.farfocle.accountsservice.register_data_validator.RegisterData;
import com.farfocle.accountsservice.register_data_validator.RegisterError;

public interface Rule {
    boolean validate(RegisterData data);
    RegisterError getErrorType();
    boolean isInterrupting();
}
