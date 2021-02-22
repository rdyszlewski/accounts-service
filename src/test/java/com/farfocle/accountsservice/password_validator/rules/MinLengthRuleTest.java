package com.farfocle.accountsservice.password_validator.rules;

import com.farfocle.accountsservice.password_validator.PasswordData;
import com.farfocle.accountsservice.password_validator.PasswordError;
import org.junit.Test;

import static com.farfocle.accountsservice.password_validator.test_utils.TestUtils.*;
import static org.junit.Assert.*;

public class MinLengthRuleTest {

    @Test
    public void shouldReturnFalseWhenPasswordTooShort()  {
        Rule rule = new MinLengthRule(5);
        testPasswordFail("", rule);
        testPasswordFail("a", rule);
        testPasswordFail("aaaa", rule);
    }

    @Test
    public void shouldReturnTrueWhenPasswordIsCorrect() {
        MinLengthRule rule = new MinLengthRule(5);
        testPasswordSuccess("aaaaa", rule);
        testPasswordSuccess("aaaaaaaaaaaaaa", rule);
        testPasswordSuccess("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", rule);
    }

    @Test
    public void shouldThrowNullPasswordException() {
        MinLengthRule rule = new MinLengthRule(5);
        testException(null, NullPointerException.class, rule);

        PasswordData nullPassword = new PasswordData(null);
        testException(nullPassword, NullPointerException.class, rule);
    }

    @Test
    public void shouldReturnTrueWhenNotCharacters(){
        MinLengthRule rule = new MinLengthRule(5);
        testPasswordSuccess("#$%#$%", rule);
        testPasswordSuccess("śćśśśść", rule);
        testPasswordSuccess("1123131", rule);
        testPasswordSuccess("1#śPo5", rule);
    }

    @Test
    public void shouldReturnCorrectErrorDetails(){
        Rule rule = new MinLengthRule(5);
        assertEquals(PasswordError.TOO_SHORT, rule.getErrorDetails().getError());
        assertEquals("5", rule.getErrorDetails().getValidValue());
    }

}
