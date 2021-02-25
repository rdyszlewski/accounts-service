package com.farfocle.accountsservice.user_validator.rules;

import com.farfocle.accountsservice.user_validator.UserData;
import com.farfocle.accountsservice.user_validator.UserErrorDetails;
import com.farfocle.accountsservice.user_validator.UserValidationError;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class NotAllowedCharacterRule implements Rule{

    private final Set<Integer> notAllowedChars;
    private final boolean interrupting;
    private final UserErrorDetails errorDetails;

    public NotAllowedCharacterRule(List<Character> notAllowedChars, boolean interrupting){
        this.notAllowedChars = notAllowedChars.stream().map(x->(int)x).collect(Collectors.toSet());
        this.interrupting = interrupting;
        this.errorDetails = new UserErrorDetails(UserValidationError.NOT_ALLOWED_CHARACTER, null);
    }

    public NotAllowedCharacterRule(List<Character> notAllowedChars){
        this(notAllowedChars, false);
    }

    @Override
    public boolean validate(UserData data) {
        String username = data.getUsername();
        return username.chars().noneMatch(this.notAllowedChars::contains);
    }

    @Override
    public UserErrorDetails getErrorType() {
        return errorDetails;
    }

    @Override
    public boolean isInterrupting() {
        return interrupting;
    }
}
