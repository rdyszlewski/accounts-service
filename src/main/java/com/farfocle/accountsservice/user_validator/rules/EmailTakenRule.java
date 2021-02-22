package com.farfocle.accountsservice.user_validator.rules;

import com.farfocle.accountsservice.user_validator.UserData;
import com.farfocle.accountsservice.user_validator.UserValidationError;
import com.farfocle.accountsservice.user_validator.UserExistence;

public class EmailTakenRule implements Rule {

    private final UserExistence existenceValidator;
    private final boolean interrupting;

    public EmailTakenRule(UserExistence existenceValidator) {
        this.existenceValidator = existenceValidator;
        this.interrupting = false;
    }

    public EmailTakenRule(UserExistence existenceValidator, boolean interrupting) {
        this.existenceValidator = existenceValidator;
        this.interrupting = interrupting;
    }

    @Override
    public boolean validate(UserData data) {
        if (data == null || data.getEmail() == null) {
            throw new NullPointerException();
        }
        return !existenceValidator.getByEmail(data.getEmail());
    }

    @Override
    public UserValidationError getErrorType() {
        return UserValidationError.EMAIL_TAKEN;
    }

    @Override
    public boolean isInterrupting() {
        return interrupting;
    }
}
