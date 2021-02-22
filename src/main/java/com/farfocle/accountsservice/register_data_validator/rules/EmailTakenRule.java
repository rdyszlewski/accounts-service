package com.farfocle.accountsservice.register_data_validator.rules;

import com.farfocle.accountsservice.register_data_validator.RegisterData;
import com.farfocle.accountsservice.register_data_validator.RegisterError;
import com.farfocle.accountsservice.register_data_validator.UserExistence;

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
    public boolean validate(RegisterData data) {
        if (data == null || data.getEmail() == null) {
            throw new NullPointerException();
        }
        return !existenceValidator.getByEmail(data.getEmail());
    }

    @Override
    public RegisterError getErrorType() {
        return RegisterError.EMAIL_TAKEN;
    }

    @Override
    public boolean isInterrupting() {
        return interrupting;
    }
}
