package com.farfocle.accountsservice.validation;

import java.util.LinkedList;
import java.util.List;

public class SignUpValidationResult {

    private final List<SignUpErrorDetails> errors = new LinkedList<>();

    public boolean isSuccess(){
        return errors.isEmpty();
    }

    public List<SignUpErrorDetails> getErrors() {
        return errors;
    }

    public void addError(SignUpErrorDetails error){
        if(error != null){
            this.errors.add(error);
        }
    }
}
