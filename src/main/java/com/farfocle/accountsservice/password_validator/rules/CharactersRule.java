package com.farfocle.accountsservice.password_validator.rules;

import com.farfocle.accountsservice.password_validator.ErrorDetails;
import com.farfocle.accountsservice.password_validator.PasswordData;
import com.farfocle.accountsservice.password_validator.PasswordError;

public abstract class CharactersRule implements Rule{

    private int value;
    private boolean interrupting;
    private ErrorDetails errorDetails;

    public CharactersRule(int value){
        init(value);
    }

    public CharactersRule(int value, boolean interrupting){
        init(value);
        this.interrupting = interrupting;
    }

    private void init(int validValue){
        this.value = validValue;
        this.errorDetails = new ErrorDetails(PasswordError.UPPERCASE, String.valueOf(validValue));
    }

    @Override
    public boolean validate(PasswordData password) throws NullPointerException {
        if(password == null || password.getPassword() == null){
            throw new NullPointerException();
        }
        long count =  password.getPassword().chars().filter(this::checkCharacter).limit(value).count();
        return count >= value;
    }

    protected abstract boolean checkCharacter(int character);

    @Override
    public ErrorDetails getErrorDetails() {
        return errorDetails;
    }

    @Override
    public boolean isInterrupting() {
        return interrupting;
    }
}
