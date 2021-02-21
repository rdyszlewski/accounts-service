package com.farfocle.accountsservice.password_validator.rules;

public class LowercaseRule extends CharactersRule{

    public LowercaseRule(int value) {
        super(value);
    }

    public LowercaseRule(int value, boolean interrupting) {
        super(value, interrupting);
    }

    @Override
    protected boolean checkCharacter(int character) {
        return Character.isLowerCase(character);
    }
}
