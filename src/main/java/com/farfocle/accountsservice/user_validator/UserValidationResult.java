package com.farfocle.accountsservice.user_validator;

import java.util.LinkedList;
import java.util.List;

public final class UserValidationResult {


    private final List<UserValidationError> errors = new LinkedList<>();

    public UserValidationResult() {

    }

    public boolean isValid() {
        return errors.isEmpty();
    }

    public void addError(UserValidationError error) {
        errors.add(error);
    }

    public int getErrorsCount() {
        return errors.size();
    }

    public List<UserValidationError> getErrors(){
        return this.errors;
    }

    public UserValidationError getError(int index) {
        if (index >= 0 && index < errors.size()) {
            return errors.get(index);
        }
        return null;
    }
}
