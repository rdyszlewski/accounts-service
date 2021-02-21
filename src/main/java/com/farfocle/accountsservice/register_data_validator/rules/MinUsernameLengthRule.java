package com.farfocle.accountsservice.register_data_validator.rules;

import com.farfocle.accountsservice.register_data_validator.RegisterData;
import com.farfocle.accountsservice.register_data_validator.RegisterError;

public class MinUsernameLengthRule implements Rule{

    private int minLength;
    private boolean interrupting;

    public MinUsernameLengthRule(int minLength){
        this.minLength = minLength;
        this.interrupting = false;
    }

    public MinUsernameLengthRule(int minLength, boolean interrupting){
        this.minLength = minLength;
        this.interrupting = interrupting;
    }

    @Override
    public boolean validate(RegisterData data)throws NullPointerException {
        if(data == null || data.getUsername() == null){
            throw new NullPointerException();
        }
        return data.getUsername().length() >= minLength;
    }

    @Override
    public RegisterError getErrorType() {
        return RegisterError.USERNAME_TOO_SHORT;
    }

    @Override
    public boolean isInterrupting() {
        return interrupting;
    }
}
