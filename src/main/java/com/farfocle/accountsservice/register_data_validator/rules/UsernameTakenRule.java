package com.farfocle.accountsservice.register_data_validator.rules;

import com.farfocle.accountsservice.register_data_validator.RegisterData;
import com.farfocle.accountsservice.register_data_validator.RegisterError;
import com.farfocle.accountsservice.register_data_validator.UserExistence;

public class UsernameTakenRule implements Rule{

    private UserExistence existenceValidator;
    private boolean interrupting;

    public UsernameTakenRule(UserExistence existenceValidator){
        this.existenceValidator = existenceValidator;
        this.interrupting = false;
    }

    public UsernameTakenRule(UserExistence existenceValidator, boolean interrupting){
        this.existenceValidator = existenceValidator;
        this.interrupting = interrupting;
    }

    @Override
    public boolean validate(RegisterData data) {
        if(data == null || data.getUsername() == null){
            throw new NullPointerException();
        }
        return !existenceValidator.getByUsername(data.getUsername());
    }

    @Override
    public RegisterError getErrorType() {
        return RegisterError.USERNAME_TAKEN;
    }

    @Override
    public boolean isInterrupting() {
        return interrupting;
    }
}
