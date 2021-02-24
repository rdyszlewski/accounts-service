package com.farfocle.accountsservice.user_validator.rules;

import com.farfocle.accountsservice.user_validator.UserData;
import com.farfocle.accountsservice.user_validator.UserErrorDetails;

import java.util.List;

public class NotAllowedCharacterRule implements Rule{

    private final List<Character> notAllowedChars;

    public NotAllowedCharacterRule(List<Character> notAllowedChars){
        this.notAllowedChars=notAllowedChars;
    }

    @Override
    public boolean validate(UserData data) {
        for(char character : notAllowedChars){
            if(data.getUsername().indexOf(character) >= 0){
                return false;
            }
        }
        return true;
    }

    @Override
    public UserErrorDetails getErrorType() {
        return null;
    }

    @Override
    public boolean isInterrupting() {
        return false;
    }
}
