package com.farfocle.accountsservice.password_validator.rules;

import com.farfocle.accountsservice.password_validator.PasswordData;
import com.farfocle.accountsservice.password_validator.PasswordError;
import org.junit.Test;

import static com.farfocle.accountsservice.password_validator.test_utils.TestUtils.*;
import static org.junit.Assert.assertEquals;

public class MaxLengthRuleTest {

    @Test
    public void shouldReturnFalseWhenPasswordTooLong() {
        Rule rule = new MaxLengthRule(5);
        testPasswordFail("aadadadaasfsdfsdjkflasdflasdjksfasfs", rule);
        testPasswordFail("aaaaaa", rule);
    }

    @Test
    public void shouldReturnTrueWhenPasswordCorrect() {
        MaxLengthRule rule = new MaxLengthRule(5);
        testPasswordSuccess("", rule);
        testPasswordSuccess("aaa", rule);
        testPasswordSuccess("aaaaa", rule);
    }

    @Test
    public void shouldThrowExceptionWhenPasswordIsNull() {
        MaxLengthRule rule = new MaxLengthRule(5);
        testException(null, NullPointerException.class, rule);

        PasswordData nullPassword = new PasswordData(null);
        testException(nullPassword, NullPointerException.class, rule);
    }

    @Test
    public void shouldReturnTrueWhenNotCharacters() {
        MaxLengthRule rule = new MaxLengthRule(8);
        testPasswordSuccess("#$%#$%", rule);
        testPasswordSuccess("śćśśśść", rule);
        testPasswordSuccess("1123131", rule);
        testPasswordSuccess("1#śPo5>", rule);
    }

    @Test
    public void shouldReturnCorrectErrorDetails() {
        Rule rule = new MaxLengthRule(8);
        assertEquals(PasswordError.TOO_LONG, rule.getErrorDetails().getError());
        assertEquals("8", rule.getErrorDetails().getValidValue());
    }
}
