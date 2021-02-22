package com.farfocle.accountsservice.validation;


public class SignUpErrorDetails {

    private final SignUpError code;
    private final String validValue;
    private final String message;

    public SignUpErrorDetails(SignUpError code, String validValue, String message) {
        this.code = code;
        this.validValue = validValue;
        this.message = message;
    }

    public SignUpError getCode() {
        return code;
    }

    public String getValidValue() {
        return validValue;
    }

    public String getMessage() {
        return message;
    }
}
