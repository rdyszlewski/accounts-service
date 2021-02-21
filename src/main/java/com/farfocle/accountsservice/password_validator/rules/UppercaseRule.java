package com.farfocle.accountsservice.password_validator.rules;

import com.farfocle.accountsservice.password_validator.ErrorDetails;
import com.farfocle.accountsservice.password_validator.PasswordData;
import com.farfocle.accountsservice.password_validator.PasswordError;
import org.passay.CharacterRule;

public class UppercaseRule extends CharactersRule {

    private int value;
    private boolean interrupting;
    private ErrorDetails errorDetails;

    public UppercaseRule(int value){
        super(value);
    }

    public UppercaseRule(int value, boolean interrupting){
        super(value, interrupting);
    }

    @Override
    protected boolean checkCharacter(int character) {
        return Character.isUpperCase(character);
    }
}
