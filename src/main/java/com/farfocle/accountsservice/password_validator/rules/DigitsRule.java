package com.farfocle.accountsservice.password_validator.rules;

public class DigitsRule extends CharactersRule{


    public DigitsRule(int value) {
        super(value);
    }

    public DigitsRule(int value, boolean interrupting) {
        super(value, interrupting);
    }

    @Override
    protected boolean checkCharacter(int character) {
        return Character.isDigit(character);
    }
}
