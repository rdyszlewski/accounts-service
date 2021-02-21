package com.farfocle.accountsservice.password_validator.rules;

import com.farfocle.accountsservice.password_validator.PasswordData;
import com.farfocle.accountsservice.password_validator.PasswordError;
import org.junit.Assert;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;

public class MaxLengthRuleTest {

    @Test
    public void shouldReturnFalseWhenPasswordTooLong(){
        MaxLengthRule rule = new MaxLengthRule(5);
        PasswordData veryLongPassword = new PasswordData("aadadadaasfsdfsdjkflasdflasdjksfasfs");
        assertFalse(rule.validate(veryLongPassword));

        PasswordData almostPassword = new PasswordData("aaaaaa");
        assertFalse(rule.validate(almostPassword));

    }

    @Test
    public void shouldReturnTrueWhenPasswordCorrect(){
        MaxLengthRule rule = new MaxLengthRule(5);
        PasswordData emptyPassword = new PasswordData("");
        assertTrue(rule.validate(emptyPassword));

        PasswordData shortPassword = new PasswordData("aaa");
        assertTrue(rule.validate(shortPassword));

        PasswordData exactlyPassword = new PasswordData("aaaaa");
        assertTrue(rule.validate(exactlyPassword));
    }

    @Test
    public void shouldThrowExceptionWhenPasswordIsNull(){
        MaxLengthRule rule = new MaxLengthRule(5);
        PasswordData nullData = null;
        assertThatThrownBy(()->rule.validate(nullData)).isInstanceOf(NullPointerException.class);
        // TODO: można tutaj sprawdzić komunikat

        PasswordData nullPassword = new PasswordData(null);
        assertThatThrownBy(()->rule.validate(nullPassword)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void shouldReturnTrueWhenNotCharacters(){
        MaxLengthRule rule = new MaxLengthRule(8);
        PasswordData onlySpecialPassword = new PasswordData("#$%#$%");
        assertTrue(rule.validate(onlySpecialPassword));

        PasswordData nationalPassword = new PasswordData("śćśśśść");
        assertTrue(rule.validate(nationalPassword));

        PasswordData numberPassword = new PasswordData("1123131");
        assertTrue(rule.validate(numberPassword));

        PasswordData mixPassword = new PasswordData("1#śPo5>");
        assertTrue(rule.validate(mixPassword));
    }

    @Test
    public void shouldReturnCorrectErrorDetails(){
        Rule rule = new MaxLengthRule(8);
        assertEquals(PasswordError.TOO_LONG, rule.getErrorDetails().getError());
        assertEquals("8", rule.getErrorDetails().getValidValue());
    }
}
