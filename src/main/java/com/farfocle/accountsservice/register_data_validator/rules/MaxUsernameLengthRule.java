package com.farfocle.accountsservice.register_data_validator.rules;

import com.farfocle.accountsservice.register_data_validator.RegisterData;
import com.farfocle.accountsservice.register_data_validator.RegisterError;

public class MaxUsernameLengthRule implements Rule{

    private int maxLength;
    private boolean interrupting;

    public MaxUsernameLengthRule(int maxLength){
        this.maxLength = maxLength;
        this.interrupting = false;
    }

    public MaxUsernameLengthRule(int maxLength, boolean interrupting){
        this.maxLength = maxLength;
        this.interrupting = interrupting;
    }

    @Override
    public boolean validate(RegisterData data) {
        if(data == null || data.getUsername() == null){
            throw new NullPointerException();
        }
        return data.getUsername().length() <= maxLength;
    }

    @Override
    public RegisterError getErrorType() {
        return RegisterError.USERNAME_TOO_LONG;
    }

    @Override
    public boolean isInterrupting() {
        return interrupting;
    }
}
