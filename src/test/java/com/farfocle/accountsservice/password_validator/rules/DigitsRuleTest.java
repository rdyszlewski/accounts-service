package com.farfocle.accountsservice.password_validator.rules;

import com.farfocle.accountsservice.password_validator.PasswordData;
import com.farfocle.accountsservice.password_validator.PasswordError;
import org.junit.Test;

import static com.farfocle.accountsservice.password_validator.test_utils.TestUtils.*;
import static org.junit.Assert.assertEquals;

public class DigitsRuleTest {
    @Test
    public void shouldReturnFalseWhenNotEnoughDigits() {
        Rule rule = new DigitsRule(2);
        testPasswordFail("aaaa", rule);
        testPasswordFail("aa4aa", rule);
        testPasswordFail("2aaaa", rule);
        testPasswordFail("aaaa4", rule);

    }

    @Test
    public void shouldReturnTrueWhenEnoughDigits() {
        Rule rule = new DigitsRule(2);
        testPasswordSuccess("13ddd543", rule);
        testPasswordSuccess("3948392", rule);
        testPasswordSuccess("a4a4a", rule);
    }

    @Test
    public void shouldThrowNullPasswordException() {
        Rule rule = new DigitsRule(2);
        testException(null, NullPointerException.class, rule);

        PasswordData nullPassword = new PasswordData(null);
        testException(nullPassword, NullPointerException.class, rule);
    }

    @Test
    public void shouldReturnDigitError() {
        Rule rule = new DigitsRule(2);
        assertEquals(PasswordError.DIGITS, rule.getErrorDetails().getError());
        assertEquals("2", rule.getErrorDetails().getValidValue());
    }
}
