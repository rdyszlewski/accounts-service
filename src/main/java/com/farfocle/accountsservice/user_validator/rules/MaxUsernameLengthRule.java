package com.farfocle.accountsservice.user_validator.rules;

import com.farfocle.accountsservice.user_validator.UserValidationError;

public class MaxUsernameLengthRule extends UsernameLengthRule {

    public MaxUsernameLengthRule(int length) {
        super(length, UserValidationError.USERNAME_TOO_LONG);
    }

    public MaxUsernameLengthRule(int length, boolean interrupting) {
        super(length, interrupting, UserValidationError.USERNAME_TOO_LONG);
    }

    @Override
    protected boolean checkLength(String username) {
        return username.length() <= length;
    }
}
