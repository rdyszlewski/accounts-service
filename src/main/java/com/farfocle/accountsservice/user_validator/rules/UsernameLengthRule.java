package com.farfocle.accountsservice.user_validator.rules;

import com.farfocle.accountsservice.user_validator.UserData;
import com.farfocle.accountsservice.user_validator.UserValidationError;

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
    public boolean validate(UserData data) throws NullPointerException {
        if (data == null || data.getUsername() == null) {
            throw new NullPointerException();
        }
        return checkLength(data.getUsername());
    }

    protected abstract boolean checkLength(String username);

    public abstract UserValidationError getErrorType();

    @Override
    public boolean isInterrupting() {
        return interrupting;
    }
}
