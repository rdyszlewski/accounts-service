package com.farfocle.accountsservice.register_data_validator;

import com.farfocle.accountsservice.register_data_validator.rules.Rule;

import java.util.List;

public class RegisterDataValidator {

    private final List<Rule> rules;

    public RegisterDataValidator(List<Rule> rules) {
        this.rules = rules;
    }

    public UserValidationResult validate(RegisterData data) {
        if (data == null) {
            throw new NullPointerException();
        }
        UserValidationResult validationResult = new UserValidationResult();
        for (Rule rule : rules) {
            if (!rule.validate(data)) {
                validationResult.addError(rule.getErrorType());
                if (rule.isInterrupting()) {
                    break;
                }
            }
        }
        validationResult.setValid(validationResult.getErrorsCount() == 0);
        return validationResult;
    }

}
