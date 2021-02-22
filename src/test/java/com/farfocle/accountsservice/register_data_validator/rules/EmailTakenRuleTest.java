package com.farfocle.accountsservice.register_data_validator.rules;

import com.farfocle.accountsservice.register_data_validator.RegisterData;
import com.farfocle.accountsservice.register_data_validator.UserExistence;
import org.junit.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class EmailTakenRuleTest {

    private UserExistence existenceValidator = Mockito.mock(UserExistence.class);

    @Test
    public void shouldReturnFalseWhenEmailExists(){
        Rule rule = new EmailTakenRule(existenceValidator);
        RegisterData data = new RegisterData("dadad", "dada");
        when(existenceValidator.getByEmail(data.getEmail())).thenReturn(true);
        assertFalse(rule.validate(data));
    }

    @Test
    public void shouldReturnTrueWhenEmailNotExists(){
        Rule rule = new EmailTakenRule(existenceValidator);
        RegisterData data = new RegisterData("adada", "dada");
        when(existenceValidator.getByEmail(data.getEmail())).thenReturn(false);
        assertTrue(rule.validate(data));
    }

    @Test
    public void shouldThrowExceptionWhenEmailIsNull(){
        Rule rule = new EmailTakenRule(existenceValidator);
        RegisterData nullData = null;
        assertThatThrownBy(()->rule.validate(nullData)).isInstanceOf(NullPointerException.class);

        RegisterData nullEmail = new RegisterData("dada", null);
        assertThatThrownBy(()->rule.validate(nullEmail)).isInstanceOf(NullPointerException.class);
    }
}
