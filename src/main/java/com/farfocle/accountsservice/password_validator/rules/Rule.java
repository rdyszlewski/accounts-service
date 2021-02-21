package com.farfocle.accountsservice.password_validator.rules;

import com.farfocle.accountsservice.password_validator.ErrorDetails;
import com.farfocle.accountsservice.password_validator.PasswordData;

import com.sun.istack.NotNull;

public interface Rule {
    boolean validate(@NotNull PasswordData password) throws NullPointerException;
    ErrorDetails getErrorDetails();
    boolean isInterrupting();
}
