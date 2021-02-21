package com.farfocle.accountsservice.register_data_validator.rules;

import com.farfocle.accountsservice.register_data_validator.RegisterData;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;

public class MinUsernameLengthRuleTest {

    @Test
    public void shouldReturnFalseWhenUsernameIsTooShort(){
        Rule rule = new MinUsernameLengthRule(4);
        RegisterData data1 = new RegisterData("a", null);
        assertFalse(rule.validate(data1));

        RegisterData data2 = new RegisterData("aaa", null);
        assertFalse(rule.validate(data2));

        RegisterData data3 = new RegisterData("", null);
        assertFalse(rule.validate(data3));
    }

    @Test
    public void shouldReturnTrueWhenEnoughLong(){
        Rule rule = new MinUsernameLengthRule(4);
        RegisterData data1 = new RegisterData("aaaa", null);
        assertTrue(rule.validate(data1));

        RegisterData data2 = new RegisterData("aaaaadasda", null);
        assertTrue(rule.validate(data2));
    }

    @Test
    public void shouldThrowExceptionWhenNull(){
        Rule rule = new MinUsernameLengthRule(4);
        RegisterData nullData = null;
        assertThatThrownBy(()->rule.validate(nullData)).isInstanceOf(NullPointerException.class);

        RegisterData nullUsername = new RegisterData(null, null);
        assertThatThrownBy(()->rule.validate(nullUsername)).isInstanceOf(NullPointerException.class);
    }
}
