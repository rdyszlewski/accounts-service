package com.farfocle.accountsservice.password_validator.rules;

import com.farfocle.accountsservice.password_validator.PasswordData;
import com.farfocle.accountsservice.password_validator.PasswordError;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;

public class LowercaseRuleTest {

    // TODO: dodać testy z przyjmowanymi znakami

    @Test
    public void shouldReturnFalseWhenNotEnoughLowercase(){
        Rule rule = new LowercaseRule(2);
        PasswordData noLowercasePassword = new PasswordData("AAAA");
        assertFalse(rule.validate(noLowercasePassword));

        PasswordData notEnoughLowercasePassword1 = new PasswordData("AAaAA");
        assertFalse(rule.validate(notEnoughLowercasePassword1));

        PasswordData notEnoughLowercasePassword2 = new PasswordData("aAAAA");
        assertFalse(rule.validate(notEnoughLowercasePassword2));

        PasswordData notEnoughLowercasePassword3 = new PasswordData("AAAAa");
        assertFalse(rule.validate(notEnoughLowercasePassword3));
    }

    @Test
    public void shouldReturnTrueWhenEnoughLowercase(){
        Rule rule = new LowercaseRule(2);
        PasswordData manyLowercasePassword = new PasswordData("AaaAaa");
        assertTrue(rule.validate(manyLowercasePassword));

        PasswordData onlyLowercasePassword = new PasswordData("aaaaaa");
        assertTrue(rule.validate(onlyLowercasePassword));

        PasswordData exactlyLowercasePassword = new PasswordData("AaAaA");
        assertTrue(rule.validate(exactlyLowercasePassword));
    }

    @Test
    public void shouldThrowNullPasswordException() {
        Rule rule = new LowercaseRule(2);
        PasswordData nullData = null;
        assertThatThrownBy(()->rule.validate(nullData)).isInstanceOf(NullPointerException.class);
        // TODO: można tutaj sprawdzić komunikat

        PasswordData nullPassword = new PasswordData(null);
        assertThatThrownBy(()->rule.validate(nullPassword)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void shouldReturnCorrectErrorDetails(){
        Rule rule = new LowercaseRule(2);
        assertEquals(PasswordError.LOWERCASE, rule.getErrorDetails().getError());
        assertEquals("2", rule.getErrorDetails().getValidValue());
    }
}
