package com.farfocle.accountsservice.user_validator;

public final class UserErrorDetails {

    private final UserValidationError error;
    private final String value;

    public UserErrorDetails(UserValidationError error, String value) {
        this.error = error;
        this.value = value;
    }

    public UserValidationError getError() {
        return error;
    }

    public String getValue() {
        return value;
    }
}
