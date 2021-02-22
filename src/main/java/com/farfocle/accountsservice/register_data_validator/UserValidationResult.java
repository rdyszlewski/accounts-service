package com.farfocle.accountsservice.register_data_validator;

import java.util.LinkedList;
import java.util.List;

public final class UserValidationResult {

    private boolean valid;
    private final List<RegisterError> errors = new LinkedList<>();

    public UserValidationResult() {

    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public void addError(RegisterError error) {
        errors.add(error);
    }

    public int getErrorsCount() {
        return errors.size();
    }

    public RegisterError getError(int index) {
        if (index >= 0 && index < errors.size()) {
            return errors.get(index);
        }
        return null;
    }
}
