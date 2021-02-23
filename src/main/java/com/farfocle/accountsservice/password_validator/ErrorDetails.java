package com.farfocle.accountsservice.password_validator;

import java.util.Objects;

public final class ErrorDetails {
    private final PasswordError error;
    private final String validValue;

    public ErrorDetails(PasswordError error, String validValue) {
        this.error = error;
        this.validValue = validValue;
    }

    public PasswordError getError() {
        return error;
    }

    public String getValidValue() {
        return validValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorDetails details = (ErrorDetails) o;
        return error == details.error &&
                Objects.equals(validValue, details.validValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(error, validValue);
    }
}
