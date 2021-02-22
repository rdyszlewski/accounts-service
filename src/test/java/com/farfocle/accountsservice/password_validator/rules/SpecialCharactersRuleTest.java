package com.farfocle.accountsservice.password_validator.rules;

import com.farfocle.accountsservice.password_validator.PasswordData;
import com.farfocle.accountsservice.password_validator.PasswordError;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.farfocle.accountsservice.password_validator.test_utils.TestUtils.*;
import static org.junit.Assert.assertEquals;

public class SpecialCharactersRuleTest {

    @Test
    public void shouldReturnFalseWhenNotEnoughSpecialCharacters() {
        Rule rule = new SpecialCharactersRule(2);
        testPasswordFail("aaaa", rule);
        testPasswordFail("aa}aa", rule);
        testPasswordFail("[aaaa", rule);
        testPasswordFail("aaaa_", rule);
    }

    @Test
    public void shouldReturnTrueWhenEnoughSpecialCharacters() {
        Rule rule = new SpecialCharactersRule(2);
        testPasswordSuccess("//ddd#)(", rule);
        testPasswordSuccess("#_.,()", rule);
        testPasswordSuccess("a#a[a", rule);
    }

    @Test
    public void shouldThrowNullPasswordException() {
        Rule rule = new SpecialCharactersRule(2);
        testException(null, NullPointerException.class, rule);

        PasswordData nullPassword = new PasswordData(null);
        testException(nullPassword, NullPointerException.class, rule);
    }

    @Test
    public void shouldReturnTrueWhenGetSpecialsFromList() {
        List<Character> list1 = Arrays.asList('#', '/', '_');
        Rule rule = new SpecialCharactersRule(2, list1);

        testPasswordSuccess("das#das_", rule);
        testPasswordFail("das#dj-", rule);
    }

    @Test
    public void shouldReturnCorrectErrorDetails() {
        Rule rule = new SpecialCharactersRule(2);
        assertEquals(PasswordError.SPECIAL_CHARACTERS, rule.getErrorDetails().getError());
        assertEquals("2", rule.getErrorDetails().getValidValue());
    }
}
