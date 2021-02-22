package com.farfocle.accountsservice.register_data_validator.test_utils;

import com.farfocle.accountsservice.register_data_validator.RegisterData;
import com.farfocle.accountsservice.register_data_validator.rules.Rule;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ExceptionUtil {

    public static void testException(RegisterData data, Class<?> type, Rule rule) {
        assertThatThrownBy(() -> rule.validate(data)).isInstanceOf(type);
    }

    public static void testUsernameFail(String username, Rule rule) {
        RegisterData data = new RegisterData(username, " ");
        assertFalse(rule.validate(data));
    }

    public static void testEmailFail(String email, Rule rule) {
        RegisterData data = new RegisterData("a", email);
        assertFalse(rule.validate(data));
    }

    public static void testUsernameSuccess(String username, Rule rule) {
        RegisterData data = new RegisterData(username, "a");
        assertTrue(rule.validate(data));
    }

    public static void testEmailSuccess(String email, Rule rule) {
        RegisterData data = new RegisterData("a", email);
        assertTrue(rule.validate(data));
    }
}
