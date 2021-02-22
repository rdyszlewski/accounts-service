package com.farfocle.accountsservice.user_validator.rules;

import com.farfocle.accountsservice.user_validator.UserData;
import com.farfocle.accountsservice.user_validator.UserErrorDetails;
import com.farfocle.accountsservice.user_validator.UserValidationError;

public interface Rule {
    boolean validate(UserData data);

    UserErrorDetails getErrorType();

    boolean isInterrupting();
}
