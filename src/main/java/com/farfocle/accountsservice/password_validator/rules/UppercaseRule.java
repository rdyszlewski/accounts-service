package com.farfocle.accountsservice.password_validator.rules;

import com.farfocle.accountsservice.password_validator.PasswordError;

public class UppercaseRule extends CharactersRule {

    public UppercaseRule(int value) {
        super(value, PasswordError.UPPERCASE);
    }

    public UppercaseRule(int value, boolean interrupting) {
        super(value, interrupting, PasswordError.UPPERCASE);
    }

    @Override
    protected boolean checkCharacter(int character) {
        return Character.isUpperCase(character);
    }
}
