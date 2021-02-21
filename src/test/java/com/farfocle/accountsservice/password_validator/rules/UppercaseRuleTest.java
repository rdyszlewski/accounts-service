package com.farfocle.accountsservice.password_validator.rules;

import com.farfocle.accountsservice.password_validator.PasswordData;
import com.farfocle.accountsservice.password_validator.PasswordError;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;

public class UppercaseRuleTest {

    // TODO: dodać testy z przyjmowanymi znakami

    @Test
    public void shouldReturnFalseWhenNotEnoughUppercase(){
        UppercaseRule rule = new UppercaseRule(2);
        PasswordData noUppercasePassword = new PasswordData("aaaa");
        assertFalse(rule.validate(noUppercasePassword));

        PasswordData notEnoughUppercasePassword1 = new PasswordData("aaAaa");
        assertFalse(rule.validate(notEnoughUppercasePassword1));

        PasswordData notEnoughUppercasePassword2 = new PasswordData("Aaaaa");
        assertFalse(rule.validate(notEnoughUppercasePassword2));

        PasswordData notEnoughUppercasePassword3 = new PasswordData("aaaaZ");
        assertFalse(rule.validate(notEnoughUppercasePassword3));
    }

    @Test
    public void shouldReturnTrueWhenEnoughUppercase(){
        UppercaseRule rule = new UppercaseRule(2);
        PasswordData manyUppercasePassword = new PasswordData("ACdddADP");
        assertTrue(rule.validate(manyUppercasePassword));

        PasswordData onlyUppercasePassword = new PasswordData("AADJKJDLAJD");
        assertTrue(rule.validate(onlyUppercasePassword));

        PasswordData exactlyUppercasePassword = new PasswordData("aAaAa");
        assertTrue(rule.validate(exactlyUppercasePassword));
    }

    @Test
    public void shouldThrowNullPasswordException() {
        UppercaseRule rule = new UppercaseRule(2);
        PasswordData nullData = null;
        assertThatThrownBy(()->rule.validate(nullData)).isInstanceOf(NullPointerException.class);
        // TODO: można tutaj sprawdzić komunikat

        PasswordData nullPassword = new PasswordData(null);
        assertThatThrownBy(()->rule.validate(nullPassword)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void shouldReturnTrueWhenNationalCharacters(){
        UppercaseRule rule = new UppercaseRule(2);
        PasswordData password1 = new PasswordData("kŚoĆte");
        assertTrue(rule.validate(password1));

        PasswordData password2 = new PasswordData("kjiĘOs");
        assertTrue(rule.validate(password2));

        PasswordData password3 = new PasswordData("ÖOppppp");
        assertTrue(rule.validate(password3));
    }

    @Test
    public void shouldReturnCorrectErrorDetails(){
        Rule rule = new UppercaseRule(2);
        assertEquals(PasswordError.UPPERCASE, rule.getErrorDetails().getError());
        assertEquals("2", rule.getErrorDetails().getValidValue());
    }
}
