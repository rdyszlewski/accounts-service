package com.farfocle.accountsservice.password_validator.rules;

import com.farfocle.accountsservice.password_validator.PasswordData;
import org.junit.Test;
import org.springframework.security.core.parameters.P;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WhitespaceRuleTest {

    @Test
    public void shouldReturnFalseWhenWhitespace(){
        Rule rule = new WhitespaceRule();
        PasswordData password1 = new PasswordData("jdkla dajl");
        assertFalse(rule.validate(password1));

        PasswordData password2 = new PasswordData(" sadjklas");
        assertFalse(rule.validate(password2));

        PasswordData password3 = new PasswordData("das\tdas");
        assertFalse(rule.validate(password3));

        PasswordData password4 = new PasswordData("das\nsada");
        assertFalse(rule.validate(password4));

        // TODO: dodać inne znaki
    }

    @Test
    public void shouldReturnTrueWhenNoWhitespaces(){
        Rule rule = new WhitespaceRule();
        PasswordData passwordData = new PasswordData("asdjkladh25eqwuwen");
        assertTrue(rule.validate(passwordData));
    }

    @Test
    public void shouldThrowNullPasswordException() {
        Rule rule = new WhitespaceRule();
        PasswordData nullData = null;
        assertThatThrownBy(()->rule.validate(nullData)).isInstanceOf(NullPointerException.class);
        // TODO: można tutaj sprawdzić komunikat

        PasswordData nullPassword = new PasswordData(null);
        assertThatThrownBy(()->rule.validate(nullPassword)).isInstanceOf(NullPointerException.class);
    }
}
