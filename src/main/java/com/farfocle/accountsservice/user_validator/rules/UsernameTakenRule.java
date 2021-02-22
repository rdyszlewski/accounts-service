package com.farfocle.accountsservice.user_validator.rules;

import com.farfocle.accountsservice.user_validator.UserData;
import com.farfocle.accountsservice.user_validator.UserValidationError;
import com.farfocle.accountsservice.user_validator.UserExistence;

public class UsernameTakenRule implements Rule {

    private final UserExistence existenceValidator;
    private final boolean interrupting;

    public UsernameTakenRule(UserExistence existenceValidator) {
        this.existenceValidator = existenceValidator;
        this.interrupting = false;
    }

    public UsernameTakenRule(UserExistence existenceValidator, boolean interrupting) {
        this.existenceValidator = existenceValidator;
        this.interrupting = interrupting;
    }

    @Override
    public boolean validate(UserData data) {
        if (data == null || data.getUsername() == null) {
            throw new NullPointerException();
        }
        return !existenceValidator.existsByUsername(data.getUsername());
    }

    @Override
    public UserValidationError getErrorType() {
        return UserValidationError.USERNAME_TAKEN;
    }

    @Override
    public boolean isInterrupting() {
        return interrupting;
    }
}
