package com.farfocle.accountsservice.password_validator;

public final class ErrorDetails {
    private final PasswordError error;
    private final String validValue;

    public ErrorDetails(PasswordError error, String validValue){
        this.error = error;
        this.validValue = validValue;
    }

    public PasswordError getError() {
        return error;
    }

    public String getValidValue() {
        return validValue;
    }
}
