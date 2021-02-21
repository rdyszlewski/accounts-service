package com.farfocle.accountsservice.password_validator.rules;

import com.farfocle.accountsservice.password_validator.PasswordError;

public class DigitsRule extends CharactersRule{


    public DigitsRule(int value) {
        super(value, PasswordError.DIGITS);
    }

    public DigitsRule(int value, boolean interrupting) {
        super(value, interrupting, PasswordError.DIGITS);
    }

    @Override
    protected boolean checkCharacter(int character) {
        return Character.isDigit(character);
    }
}
