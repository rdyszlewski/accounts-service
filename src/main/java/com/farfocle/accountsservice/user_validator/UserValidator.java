package com.farfocle.accountsservice.user_validator;

import com.farfocle.accountsservice.user_validator.rules.Rule;

import java.util.List;

public class UserValidator {

    private final List<Rule> rules;

    public UserValidator(List<Rule> rules) {
        this.rules = rules;
    }

    public UserValidationResult validate(UserData data) {
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
        return validationResult;
    }

}
