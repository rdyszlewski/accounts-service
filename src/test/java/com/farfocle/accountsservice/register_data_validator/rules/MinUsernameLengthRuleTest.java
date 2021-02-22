package com.farfocle.accountsservice.register_data_validator.rules;

import com.farfocle.accountsservice.register_data_validator.RegisterData;
import org.junit.Test;

import static com.farfocle.accountsservice.register_data_validator.test_utils.ExceptionUtil.*;

public class MinUsernameLengthRuleTest {

    @Test
    public void shouldReturnFalseWhenUsernameIsTooShort(){
        Rule rule = new MinUsernameLengthRule(4);
        testUsernameFail("a", rule);
        testUsernameFail("aaa", rule);
        testUsernameFail("", rule);
    }


    @Test
    public void shouldReturnTrueWhenEnoughLong(){
        Rule rule = new MinUsernameLengthRule(4);
        testUsernameSuccess("aaaa", rule);
        testUsernameSuccess("aaaaadasda", rule);
    }


    @Test
    public void shouldThrowExceptionWhenNull(){
        Rule rule = new MinUsernameLengthRule(4);
        testException(null, NullPointerException.class, rule);
        RegisterData nullUsername = new RegisterData(null, null);
        testException(nullUsername, NullPointerException.class, rule);
    }
}
