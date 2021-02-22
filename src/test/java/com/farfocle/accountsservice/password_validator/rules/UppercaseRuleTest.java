package com.farfocle.accountsservice.password_validator.rules;

import com.farfocle.accountsservice.password_validator.PasswordData;
import com.farfocle.accountsservice.password_validator.PasswordError;
import org.junit.Test;

import static com.farfocle.accountsservice.password_validator.test_utils.TestUtils.*;
import static org.junit.Assert.assertEquals;

public class UppercaseRuleTest {

    // TODO: dodać testy z przyjmowanymi znakami

    @Test
    public void shouldReturnFalseWhenNotEnoughUppercase() {
        UppercaseRule rule = new UppercaseRule(2);
        testPasswordFail("aaaa", rule);
        testPasswordFail("aaAaa", rule);
        testPasswordFail("Aaaaa", rule);
        testPasswordFail("aaaaZ", rule);
    }

    @Test
    public void shouldReturnTrueWhenEnoughUppercase() {
        UppercaseRule rule = new UppercaseRule(2);
        testPasswordSuccess("ACdddADP", rule);
        testPasswordSuccess("AADJKJDLAJD", rule);
        testPasswordSuccess("aAaAa", rule);
    }

    @Test
    public void shouldThrowNullPasswordException() {
        UppercaseRule rule = new UppercaseRule(2);
        testException(null, NullPointerException.class, rule);

        PasswordData nullPassword = new PasswordData(null);
        testException(nullPassword, NullPointerException.class, rule);
    }

    @Test
    public void shouldReturnTrueWhenNationalCharacters() {
        UppercaseRule rule = new UppercaseRule(2);
        testPasswordSuccess("kŚoĆte", rule);
        testPasswordSuccess("kjiĘOs", rule);
        testPasswordSuccess("ÖOppppp", rule);
    }

    @Test
    public void shouldReturnCorrectErrorDetails() {
        Rule rule = new UppercaseRule(2);
        assertEquals(PasswordError.UPPERCASE, rule.getErrorDetails().getError());
        assertEquals("2", rule.getErrorDetails().getValidValue());
    }
}
