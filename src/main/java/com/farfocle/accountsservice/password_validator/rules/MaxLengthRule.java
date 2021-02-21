package com.farfocle.accountsservice.password_validator.rules;

import com.farfocle.accountsservice.password_validator.ErrorDetails;
import com.farfocle.accountsservice.password_validator.PasswordData;
import com.farfocle.accountsservice.password_validator.PasswordError;

public class MaxLengthRule implements Rule{

    private int value;
    private boolean interrupting;
    private ErrorDetails errorDetails;

    public MaxLengthRule(int minLength){
        init(minLength);
    }

    public MaxLengthRule(int minLength, boolean interrupting){
        init(minLength);
        this.interrupting = interrupting;
    }

    private void init(int minLength){
        this.value = minLength;
        this.errorDetails = new ErrorDetails(PasswordError.TOO_LONG, String.valueOf(value));
    }

    @Override
    public boolean validate(PasswordData password) {
//        return password.length() <= value;
        return false;
    }

    @Override
    public ErrorDetails getErrorDetails() {
        return errorDetails;
    }

    @Override
    public boolean isInterrupting() {
        return interrupting;
    }

}
