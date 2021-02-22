package com.farfocle.accountsservice.user_validator.rules;

import com.farfocle.accountsservice.user_validator.UserData;
import com.farfocle.accountsservice.user_validator.UserExistence;
import org.junit.Test;
import org.mockito.Mockito;

import static com.farfocle.accountsservice.user_validator.test_utils.ExceptionUtil.testException;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class EmailTakenRuleTest {

    private final UserExistence existenceValidator = Mockito.mock(UserExistence.class);

    @Test
    public void shouldReturnFalseWhenEmailExists() {
        Rule rule = new EmailTakenRule(existenceValidator);
        UserData data = new UserData("dadad", "dada");
        when(existenceValidator.existsByEmail(data.getEmail())).thenReturn(true);
        assertFalse(rule.validate(data));
    }

    @Test
    public void shouldReturnTrueWhenEmailNotExists() {
        Rule rule = new EmailTakenRule(existenceValidator);
        UserData data = new UserData("adada", "dada");
        when(existenceValidator.existsByEmail(data.getEmail())).thenReturn(false);
        assertTrue(rule.validate(data));
    }

    @Test
    public void shouldThrowExceptionWhenEmailIsNull() {
        Rule rule = new EmailTakenRule(existenceValidator);

        testException(null, NullPointerException.class, rule);

        UserData nullEmail = new UserData("dada", null);
        testException(nullEmail, NullPointerException.class, rule);
    }
}
