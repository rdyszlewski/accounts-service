package com.farfocle.accountsservice.user_validator.rules;

import com.farfocle.accountsservice.user_validator.UserValidationError;

public class MaxUsernameLengthRule extends UsernameLengthRule {

    public MaxUsernameLengthRule(int length) {
        super(length);
    }

    public MaxUsernameLengthRule(int length, boolean interrupting) {
        super(length, interrupting);
    }

    @Override
    protected boolean checkLength(String username) {
        return username.length() <= length;
    }

    @Override
    public UserValidationError getErrorType() {
        return UserValidationError.USERNAME_TOO_LONG;
    }
}
