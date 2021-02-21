package com.farfocle.accountsservice.password_validator;

import com.farfocle.accountsservice.password_validator.rules.Rule;

import java.util.List;

public class PasswordValidator {

    private List<Rule> rules;

    public PasswordValidator(List<Rule> rules){
        this.rules = rules;
    }

    public ValidationResult validate(PasswordData password) {
        ValidationResult result = new ValidationResult();
        for (Rule rule : rules){
            if(!rule.validate(password)){
                result.addError(rule.getErrorDetails());
                if(rule.isInterrupting()){
                    break;
                }
            }
        }
        result.setValid(result.getErrors().isEmpty());
        return result;
    }

}
