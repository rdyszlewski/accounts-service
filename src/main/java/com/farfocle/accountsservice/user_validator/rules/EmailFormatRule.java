package com.farfocle.accountsservice.user_validator.rules;

import com.farfocle.accountsservice.user_validator.UserData;
import com.farfocle.accountsservice.user_validator.UserValidationError;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailFormatRule implements Rule {

    //    private final String PATTERN = "(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$)";
    private final String PATTERN = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    private final Pattern pattern;

    private final boolean interrupting;

    public EmailFormatRule() {
        interrupting = false;
        pattern = Pattern.compile(PATTERN);
    }

    public EmailFormatRule(boolean interrupting) {
        this.interrupting = interrupting;
        pattern = Pattern.compile(PATTERN);
    }

    @Override
    public boolean validate(UserData data) {
        if (data == null || data.getEmail() == null) {
            throw new NullPointerException();
        }
        Matcher matcher = pattern.matcher(data.getEmail());
        if (matcher.find()) {
            return matcher.group(0).length() == data.getEmail().length();
        }
        return false;
    }

    @Override
    public UserValidationError getErrorType() {
        return UserValidationError.INVALID_EMAIL;
    }

    @Override
    public boolean isInterrupting() {
        return interrupting;
    }
}
