package com.farfocle.accountsservice.password_validator.rules;

import com.farfocle.accountsservice.password_validator.ErrorDetails;
import com.farfocle.accountsservice.password_validator.PasswordData;
import com.farfocle.accountsservice.password_validator.PasswordError;

public class MaxLengthRule implements Rule{

    private int value;
    private boolean interrupting;
    private ErrorDetails errorDetails;

    public MaxLengthRule(int maxLength){
        init(maxLength);
    }

    public MaxLengthRule(int maxLength, boolean interrupting){
        init(maxLength);
        this.interrupting = interrupting;
    }

    private void init(int maxLength){
        this.value = maxLength;
        this.errorDetails = new ErrorDetails(PasswordError.TOO_LONG, String.valueOf(value));
    }

    @Override
    public boolean validate(PasswordData password) {
        if(password == null || password.getPassword() == null){
            throw new NullPointerException();
        }
        return password.getPassword().length() <= value;
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
