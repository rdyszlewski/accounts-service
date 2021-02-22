package com.farfocle.accountsservice.register_data_validator.rules;

public class MaxUsernameLengthRule extends UsernameLengthRule{

    public MaxUsernameLengthRule(int length) {
        super(length);
    }

    public MaxUsernameLengthRule(int length, boolean interrupting) {
        super(length, interrupting);
    }

    @Override
    protected boolean checkLength(String username) {
        return username.length() <= length;
    }
}
