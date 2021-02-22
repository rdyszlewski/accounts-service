package com.farfocle.accountsservice.register_data_validator.rules;

public class MinUsernameLengthRule extends UsernameLengthRule {


    public MinUsernameLengthRule(int length) {
        super(length);
    }

    public MinUsernameLengthRule(int length, boolean interrupting) {
        super(length, interrupting);
    }

    @Override
    protected boolean checkLength(String username) {
        return username.length() >= length;
    }
}
