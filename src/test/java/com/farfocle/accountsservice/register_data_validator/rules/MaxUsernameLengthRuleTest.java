package com.farfocle.accountsservice.register_data_validator.rules;

import com.farfocle.accountsservice.register_data_validator.RegisterData;
import org.junit.Test;

import static com.farfocle.accountsservice.register_data_validator.test_utils.ExceptionUtil.*;


public class MaxUsernameLengthRuleTest {

    @Test
    public void shouldReturnFalseWhenUsernameIsTooLong() {
        Rule rule = new MaxUsernameLengthRule(5);
        testUsernameFail("aaaaaaaaaaa", rule);
        testUsernameFail("aaaaaa", rule);
    }

    @Test
    public void shouldReturnTrueWhenUsernameIsNotTooLong() {
        Rule rule = new MaxUsernameLengthRule(5);
        testUsernameSuccess("a", rule);
        testUsernameSuccess("aaaaa", rule);
    }

    @Test
    public void shouldThrowException() {
        Rule rule = new MaxUsernameLengthRule(5);
        testException(null, NullPointerException.class, rule);

        RegisterData nullUsername = new RegisterData(null, null);
        testException(nullUsername, NullPointerException.class, rule);
    }
}
