package com.farfocle.accountsservice.user_validator;

public enum UserValidationError {
    USERNAME_TAKEN,
    EMAIL_TAKEN,
    USERNAME_TOO_SHORT,
    USERNAME_TOO_LONG,
    INVALID_EMAIL,
    NOT_ALLOWED_CHARACTER
}
