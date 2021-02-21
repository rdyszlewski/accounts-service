package com.farfocle.accountsservice.password_validator.rules;

import com.farfocle.accountsservice.password_validator.ErrorDetails;
import com.farfocle.accountsservice.password_validator.PasswordData;
import com.farfocle.accountsservice.password_validator.PasswordError;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WhitespaceRule implements Rule{

    private ErrorDetails errorDetails;
    private final String PATTERN = "(\\s)";
    private Pattern pattern;
    private boolean interrupting;


    public WhitespaceRule(){
        init();
    }

    public WhitespaceRule(boolean interrupting){
        init();
        this.interrupting = interrupting;
    }

    private void init(){
        errorDetails = new ErrorDetails(PasswordError.WHITESPACE,"");
        pattern = Pattern.compile(PATTERN);
    }

    @Override
    public boolean validate(PasswordData password) throws NullPointerException {
        Matcher matcher = pattern.matcher(password.getPassword());
        return !matcher.find();
    }

    @Override
    public ErrorDetails getErrorDetails() {
        return errorDetails;
    }

    @Override
    public boolean isInterrupting() {
        return interrupting;
    }
}
