package com.farfocle.accountsservice.password_validator.rules;

import com.farfocle.accountsservice.password_validator.PasswordData;
import com.farfocle.accountsservice.password_validator.exceptions.NullPasswordException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MinLengthRuleTest {

    @Test
    public void shouldReturnFalseWhenPasswordTooShort() throws NullPasswordException {
        MinLengthRule rule = new MinLengthRule(5);
        PasswordData emptyPassword = new PasswordData("");
        assertFalse(rule.validate(emptyPassword));

        PasswordData veryShortPassword = new PasswordData("a");
        assertFalse(rule.validate(veryShortPassword));

        PasswordData almostPassword = new PasswordData("aaaa");
        assertFalse(rule.validate(almostPassword));
    }

    @Test
    public void shouldReturnTrueWhenPasswordIsCorrect() throws NullPasswordException {
        MinLengthRule rule = new MinLengthRule(5);
        PasswordData exactlyPassword = new PasswordData("aaaaa");
        assertTrue(rule.validate(exactlyPassword));

        PasswordData longPassword = new PasswordData("aaaaaaaaaaaaaa");
        assertTrue(rule.validate(longPassword));

        PasswordData veryLongPassword = new PasswordData("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        assertTrue(rule.validate(veryLongPassword));
    }

    @Test
    public void shouldThrowNullPasswordException() {
        MinLengthRule rule = new MinLengthRule(5);
        PasswordData nullData = null;
        assertThatThrownBy(()->rule.validate(nullData)).isInstanceOf(NullPointerException.class);
        // TODO: można tutaj sprawdzić komunikat

        PasswordData nullPassword = new PasswordData(null);
        assertThatThrownBy(()->rule.validate(nullPassword)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void shouldReturnTrueWhenNotCharacters(){
        MinLengthRule rule = new MinLengthRule(5);
        PasswordData onlySpecialPassword = new PasswordData("#$%#$%");
        assertTrue(rule.validate(onlySpecialPassword));

        PasswordData nationalPassword = new PasswordData("śćśśśść");
        assertTrue(rule.validate(nationalPassword));

        PasswordData numberPassword = new PasswordData("1123131");
        assertTrue(rule.validate(numberPassword));

        PasswordData mixPassword = new PasswordData("1#śPo5>");
        assertTrue(rule.validate(mixPassword));
    }

}
