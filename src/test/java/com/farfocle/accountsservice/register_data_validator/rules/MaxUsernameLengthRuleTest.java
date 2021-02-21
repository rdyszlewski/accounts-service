package com.farfocle.accountsservice.register_data_validator.rules;

import com.farfocle.accountsservice.register_data_validator.RegisterData;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MaxUsernameLengthRuleTest {

    @Test
    public void shouldReturnFalseWhenUsernameIsTooLong(){
        Rule rule = new MaxUsernameLengthRule(5);
        RegisterData data1 = new RegisterData("aaaaaaaaaaa", null);
        assertFalse(rule.validate(data1));

        RegisterData data2 = new RegisterData("aaaaaa", null);
        assertFalse(rule.validate(data2));
    }

    @Test
    public void shouldReturnTrueWhenUsernameIsNotTooLong(){
        Rule rule = new MaxUsernameLengthRule(5);
        RegisterData data1 = new RegisterData("a", null);
        assertTrue(rule.validate(data1));

        RegisterData data2 = new RegisterData("aaaaa", null);
        assertTrue(rule.validate(data2));
    }

    @Test
    public void shouldThrowException(){
        Rule rule = new MaxUsernameLengthRule(5);
        RegisterData nullData = null;
        assertThatThrownBy(()->rule.validate(nullData)).isInstanceOf(NullPointerException.class);

        RegisterData nullUsername = new RegisterData(null, null);
        assertThatThrownBy(()->rule.validate(nullUsername)).isInstanceOf(NullPointerException.class);
    }
}
