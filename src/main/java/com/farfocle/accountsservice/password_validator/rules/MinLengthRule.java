package com.farfocle.accountsservice.password_validator.rules;

import com.farfocle.accountsservice.password_validator.ErrorDetails;
import com.farfocle.accountsservice.password_validator.PasswordData;
import com.farfocle.accountsservice.password_validator.PasswordError;
import com.sun.istack.NotNull;

public class MinLengthRule implements Rule{

    private int value;
    private boolean interrupting;
    private ErrorDetails errorDetails;

    public MinLengthRule(int minLength){
       init(minLength);
    }

    public MinLengthRule(int minLength, boolean interrupting){
        init(minLength);
        this.interrupting = interrupting;
    }

    private void init(int minLength){
        this.value = minLength;
        this.errorDetails = new ErrorDetails(PasswordError.TOO_SHORT, String.valueOf(value));
    }

    @Override
    public boolean validate(@NotNull PasswordData password) throws NullPointerException {
        if(password == null || password.getPassword() == null){
            throw new NullPointerException();
        }
        return password.getPassword().length() >= value;
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
