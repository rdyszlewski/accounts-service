package com.farfocle.accountsservice.register_data_validator.rules;

import com.farfocle.accountsservice.register_data_validator.RegisterData;
import com.farfocle.accountsservice.register_data_validator.UserExistence;
import org.junit.Test;
import org.mockito.Mockito;

import static com.farfocle.accountsservice.register_data_validator.test_utils.ExceptionUtil.testException;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class UsernameTakenRuleTest {

    private UserExistence existenceValidator = Mockito.mock(UserExistence.class);

    @Test
    public void shouldReturnFalseWhenUsernameExists(){
        Rule rule = new UsernameTakenRule(existenceValidator);
        RegisterData data = new RegisterData("aaa", null);
        when(existenceValidator.existsByUsername(data.getUsername())).thenReturn(true);
        assertFalse(rule.validate(data));
    }

    @Test
    public void shouldReturnTrueWhenUsernameNotExists(){
        Rule rule = new UsernameTakenRule(existenceValidator);
        RegisterData data = new RegisterData("aaa", null);
        when(existenceValidator.existsByUsername(data.getUsername())).thenReturn(false);
        assertTrue(rule.validate(data));
    }

    @Test
    public void shouldThrowExceptionWhenUsernameIsNull(){
        Rule rule = new UsernameTakenRule(existenceValidator);
        testException(null, NullPointerException.class, rule);
        RegisterData nullUsername = new RegisterData(null, null);
        testException(nullUsername, NullPointerException.class, rule);
    }
}
