package com.farfocle.accountsservice.register_data_validator.rules;

import com.farfocle.accountsservice.register_data_validator.RegisterData;
import com.farfocle.accountsservice.register_data_validator.RegisterError;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailFormatRule implements Rule{

//    private final String PATTERN = "(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$)";
    private final String PATTERN = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    private Pattern pattern;

    private boolean interrupting;

    public EmailFormatRule(){
        interrupting = false;
        pattern = Pattern.compile(PATTERN);
    }

    public EmailFormatRule(boolean interrupting){
        this.interrupting = interrupting;
        pattern = Pattern.compile(PATTERN);
    }

    @Override
    public boolean validate(RegisterData data) {
        if(data == null || data.getEmail() == null){
            throw new NullPointerException();
        }
        Matcher matcher = pattern.matcher(data.getEmail());
        if(matcher.find()){
            return matcher.group(0).length() == data.getEmail().length();
        }
        return false;
    }

    @Override
    public RegisterError getErrorType() {
        return RegisterError.INVALID_EMAIL;
    }

    @Override
    public boolean isInterrupting() {
        return interrupting;
    }
}
