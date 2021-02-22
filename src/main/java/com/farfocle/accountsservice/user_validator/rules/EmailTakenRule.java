package com.farfocle.accountsservice.user_validator.rules;

import com.farfocle.accountsservice.user_validator.UserData;
import com.farfocle.accountsservice.user_validator.UserErrorDetails;
import com.farfocle.accountsservice.user_validator.UserExistence;
import com.farfocle.accountsservice.user_validator.UserValidationError;

public class EmailTakenRule implements Rule {

    private final UserExistence existenceValidator;
    private final boolean interrupting;
    private final UserErrorDetails errorDetails;

    public EmailTakenRule(UserExistence existenceValidator) {
        this(existenceValidator, false);
    }

    public EmailTakenRule(UserExistence existenceValidator, boolean interrupting) {
        this.existenceValidator = existenceValidator;
        this.interrupting = interrupting;
        this.errorDetails = new UserErrorDetails(UserValidationError.EMAIL_TAKEN, null);
    }

    @Override
    public boolean validate(UserData data) {
        if (data == null || data.getEmail() == null) {
            throw new NullPointerException();
        }
        return !existenceValidator.existsByEmail(data.getEmail());
    }

    @Override
    public UserErrorDetails getErrorType() {
        return errorDetails;
    }

    @Override
    public boolean isInterrupting() {
        return interrupting;
    }
}
