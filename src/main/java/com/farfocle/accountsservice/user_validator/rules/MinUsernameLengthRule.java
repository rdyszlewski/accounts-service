package com.farfocle.accountsservice.user_validator.rules;

import com.farfocle.accountsservice.user_validator.UserValidationError;

public class MinUsernameLengthRule extends UsernameLengthRule {


    public MinUsernameLengthRule(int length) {
        super(length);
    }

    public MinUsernameLengthRule(int length, boolean interrupting) {
        super(length, interrupting);
    }

    @Override
    protected boolean checkLength(String username) {
        return username.length() >= length;
    }

    @Override
    public UserValidationError getErrorType() {
        return UserValidationError.USERNAME_TOO_SHORT;
    }
}
