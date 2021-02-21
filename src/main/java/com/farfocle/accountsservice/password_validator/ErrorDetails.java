package com.farfocle.accountsservice.password_validator;

public class ErrorDetails {
    private PasswordError error;
    private String validValue;

    public ErrorDetails(PasswordError error, String validValue){
        this.error = error;
        this.validValue = validValue;
    }
}
