package com.farfocle.accountsservice.password_validator.rules;

import com.farfocle.accountsservice.password_validator.PasswordData;
import com.farfocle.accountsservice.password_validator.PasswordError;
import org.junit.Test;
import org.springframework.security.core.parameters.P;

import static com.farfocle.accountsservice.password_validator.test_utils.TestUtils.testException;
import static com.farfocle.accountsservice.password_validator.test_utils.TestUtils.testPasswordFail;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;

public class WhitespaceRuleTest {

    @Test
    public void shouldReturnFalseWhenWhitespace(){
        Rule rule = new WhitespaceRule();
        testPasswordFail("jdkla dajl", rule);
        testPasswordFail(" sadjklas", rule);
        testPasswordFail("das\tdas", rule);
        testPasswordFail("das\nsada", rule);

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
        testException(null, NullPointerException.class, rule);

        PasswordData nullPassword = new PasswordData(null);
        testException(nullPassword, NullPointerException.class, rule);
    }

    @Test
    public void shouldReturnCorrectErrorDetails(){
        Rule rule = new WhitespaceRule();
        assertEquals(PasswordError.WHITESPACE, rule.getErrorDetails().getError());
        assertNull(rule.getErrorDetails().getValidValue());
    }
}
