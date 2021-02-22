package com.farfocle.accountsservice.user_validator;

import java.util.LinkedList;
import java.util.List;

public final class UserValidationResult {


    private final List<UserErrorDetails> errors = new LinkedList<>();

    public UserValidationResult() {

    }

    public boolean isValid() {
        return errors.isEmpty();
    }

    public void addError(UserErrorDetails error) {
        errors.add(error);
    }

    public int getErrorsCount() {
        return errors.size();
    }

    public List<UserErrorDetails> getErrors() {
        return this.errors;
    }

    public UserErrorDetails getError(int index) {
        if (index >= 0 && index < errors.size()) {
            return errors.get(index);
        }
        return null;
    }
}
