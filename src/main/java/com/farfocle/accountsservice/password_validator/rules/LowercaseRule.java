package com.farfocle.accountsservice.password_validator.rules;

import com.farfocle.accountsservice.password_validator.PasswordError;

public class LowercaseRule extends CharactersRule {

    public LowercaseRule(int value) {
        super(value, PasswordError.LOWERCASE);
    }

    public LowercaseRule(int value, boolean interrupting) {
        super(value, interrupting, PasswordError.LOWERCASE);
    }

    @Override
    protected boolean checkCharacter(int character) {
        return Character.isLowerCase(character);
    }
}
