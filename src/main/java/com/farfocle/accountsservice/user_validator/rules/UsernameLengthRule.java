package com.farfocle.accountsservice.user_validator.rules;

import com.farfocle.accountsservice.user_validator.UserData;
import com.farfocle.accountsservice.user_validator.UserErrorDetails;
import com.farfocle.accountsservice.user_validator.UserValidationError;

public abstract class UsernameLengthRule implements Rule {

    protected final int length;
    private final boolean interrupting;
    private final UserErrorDetails errorDetails;

    public UsernameLengthRule(int length, UserValidationError errorType) {
        this(length, false, errorType);
    }

    public UsernameLengthRule(int length, boolean interrupting, UserValidationError errorType) {
        this.length = length;
        this.interrupting = interrupting;
        this.errorDetails = new UserErrorDetails(errorType, String.valueOf(length));
    }

    @Override
    public boolean validate(UserData data) throws NullPointerException {
        if (data == null || data.getUsername() == null) {
            throw new NullPointerException();
        }
        return checkLength(data.getUsername());
    }

    protected abstract boolean checkLength(String username);

    @Override
    public UserErrorDetails getErrorType(){
        return errorDetails;
    }

    @Override
    public boolean isInterrupting() {
        return interrupting;
    }
}
