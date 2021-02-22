package com.farfocle.accountsservice.register_data_validator.rules;

import com.farfocle.accountsservice.register_data_validator.RegisterData;
import com.farfocle.accountsservice.register_data_validator.RegisterError;

public abstract class UsernameLengthRule implements Rule {

    protected int length;
    private final boolean interrupting;

    public UsernameLengthRule(int length) {
        this.length = length;
        this.interrupting = false;
    }

    public UsernameLengthRule(int length, boolean interrupting) {
        this.length = length;
        this.interrupting = interrupting;
    }

    @Override
    public boolean validate(RegisterData data) throws NullPointerException {
        if (data == null || data.getUsername() == null) {
            throw new NullPointerException();
        }
        return checkLength(data.getUsername());
    }

    protected abstract boolean checkLength(String username);

    @Override
    public RegisterError getErrorType() {
        return RegisterError.USERNAME_TOO_SHORT;
    }

    @Override
    public boolean isInterrupting() {
        return interrupting;
    }
}
