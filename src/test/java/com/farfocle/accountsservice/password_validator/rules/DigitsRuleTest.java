package com.farfocle.accountsservice.password_validator.rules;

import com.farfocle.accountsservice.password_validator.PasswordData;
import com.farfocle.accountsservice.password_validator.PasswordError;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;

public class DigitsRuleTest {
    @Test
    public void shouldReturnFalseWhenNotEnoughDigits(){
        Rule rule = new DigitsRule(2);
        PasswordData noDigitsPassword = new PasswordData("aaaa");
        assertFalse(rule.validate(noDigitsPassword));

        PasswordData notEnoughDigitsPassword1 = new PasswordData("aa4aa");
        assertFalse(rule.validate(notEnoughDigitsPassword1));

        PasswordData notEnoughDigitsPassword2 = new PasswordData("2aaaa");
        assertFalse(rule.validate(notEnoughDigitsPassword2));

        PasswordData notEnoughDigitsPassword3 = new PasswordData("aaaa4");
        assertFalse(rule.validate(notEnoughDigitsPassword3));
    }

    @Test
    public void shouldReturnTrueWhenEnoughDigits(){
        Rule rule = new DigitsRule(2);
        PasswordData manyDigitsPassword = new PasswordData("13ddd543");
        assertTrue(rule.validate(manyDigitsPassword));

        PasswordData onlyDigitsPassword = new PasswordData("3948392");
        assertTrue(rule.validate(onlyDigitsPassword));

        PasswordData exactlyDigitsPassword = new PasswordData("a4a4a");
        assertTrue(rule.validate(exactlyDigitsPassword));
    }

    @Test
    public void shouldThrowNullPasswordException() {
        Rule rule = new DigitsRule(2);
        PasswordData nullData = null;
        assertThatThrownBy(()->rule.validate(nullData)).isInstanceOf(NullPointerException.class);
        // TODO: można tutaj sprawdzić komunikat

        PasswordData nullPassword = new PasswordData(null);
        assertThatThrownBy(()->rule.validate(nullPassword)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void shouldReturnDigitError(){
        Rule rule = new DigitsRule(2);
        assertEquals(PasswordError.DIGITS, rule.getErrorDetails().getError());
        assertEquals("2", rule.getErrorDetails().getValidValue());
    }
}
