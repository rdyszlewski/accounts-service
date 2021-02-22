package com.farfocle.accountsservice.user_validator.rules;

import com.farfocle.accountsservice.user_validator.UserValidationError;

public class MinUsernameLengthRule extends UsernameLengthRule {

    public MinUsernameLengthRule(int length) {
        super(length, UserValidationError.USERNAME_TOO_SHORT);
    }

    public MinUsernameLengthRule(int length, boolean interrupting) {
        super(length, interrupting, UserValidationError.USERNAME_TOO_SHORT);
    }

    @Override
    protected boolean checkLength(String username) {
        return username.length() >= length;
    }
}
