package com.farfocle.accountsservice.user_validator.test_utils;

import com.farfocle.accountsservice.user_validator.UserData;
import com.farfocle.accountsservice.user_validator.rules.Rule;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ExceptionUtil {

    public static void testException(UserData data, Class<?> type, Rule rule) {
        assertThatThrownBy(() -> rule.validate(data)).isInstanceOf(type);
    }

    public static void testUsernameFail(String username, Rule rule) {
        UserData data = new UserData(username, " ");
        assertFalse(rule.validate(data));
    }

    public static void testEmailFail(String email, Rule rule) {
        UserData data = new UserData("a", email);
        assertFalse(rule.validate(data));
    }

    public static void testUsernameSuccess(String username, Rule rule) {
        UserData data = new UserData(username, "a");
        assertTrue(rule.validate(data));
    }

    public static void testEmailSuccess(String email, Rule rule) {
        UserData data = new UserData("a", email);
        assertTrue(rule.validate(data));
    }
}
