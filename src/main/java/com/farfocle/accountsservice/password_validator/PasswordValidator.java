package com.farfocle.accountsservice.password_validator;

import com.farfocle.accountsservice.password_validator.rules.Rule;

import java.util.List;
import java.util.stream.Collectors;

public class PasswordValidator {

    private final List<Rule> rules;

    public PasswordValidator(List<Rule> rules) {
        this.rules = rules;
    }

    public ValidationResult validate(PasswordData password) {
        ValidationResult result = new ValidationResult();
        for (Rule rule : rules) {
            if (!rule.validate(password)) {
                result.addError(rule.getErrorDetails());
                if (rule.isInterrupting()) {
                    break;
                }
            }
        }
        result.setValid(result.getErrors().isEmpty());
        return result;
    }

    public List<ErrorDetails> getAvailableErrorDetails(){
        return rules.stream().map(Rule::getErrorDetails).collect(Collectors.toList());
    }

}
