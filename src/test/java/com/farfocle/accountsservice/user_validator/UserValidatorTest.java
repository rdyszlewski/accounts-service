package com.farfocle.accountsservice.user_validator;

import com.farfocle.accountsservice.user_validator.rules.EmailTakenRule;
import com.farfocle.accountsservice.user_validator.rules.MinUsernameLengthRule;
import com.farfocle.accountsservice.user_validator.rules.Rule;
import com.farfocle.accountsservice.user_validator.rules.UsernameTakenRule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class UserValidatorTest {

    private final MinUsernameLengthRule minUsernameLengthRule = Mockito.mock(MinUsernameLengthRule.class);
    private final EmailTakenRule emailTakenRule = Mockito.mock(EmailTakenRule.class);
    private final UsernameTakenRule usernameTakenRule = Mockito.mock(UsernameTakenRule.class);

    @Test
    public void shouldReturnFalseWhenDataInvalid() {
        UserData userData = new UserData("adasdas", "dasda");
        setupRuleMock(minUsernameLengthRule, false, UserValidationError.USERNAME_TOO_SHORT, false, userData);
        setupRuleMock(emailTakenRule, true, UserValidationError.EMAIL_TAKEN, false, userData);
        setupRuleMock(usernameTakenRule, false, UserValidationError.USERNAME_TAKEN, false, userData);

        UserValidationResult result = prepareValidatorAndValidate(userData);
        assertFalse(result.isValid());
        assertEquals(2, result.getErrorsCount());
        assertEquals(UserValidationError.USERNAME_TOO_SHORT, result.getError(0).getError());
        assertEquals(UserValidationError.USERNAME_TAKEN, result.getError(1).getError());
    }

    private UserValidationResult prepareValidatorAndValidate(UserData userData) {
        List<Rule> rules = Arrays.asList(
                minUsernameLengthRule,
                emailTakenRule,
                usernameTakenRule
        );
        UserValidator validator = new UserValidator(rules);

        return validator.validate(userData);
    }

    private void setupRuleMock(Rule rule, boolean valid, UserValidationError error, boolean interrupting, UserData userData){
        when(rule.validate(userData)).thenReturn(valid);
        when(rule.getErrorType()).thenReturn(new UserErrorDetails(error, null));
        when(rule.isInterrupting()).thenReturn(interrupting);
    }

    @Test
    public void shouldReturnTrueWhenDataIsCorrect() {
        UserData userData = new UserData("adasdas", "dasda");
        setupRuleMock(minUsernameLengthRule, true, UserValidationError.USERNAME_TOO_SHORT, true, userData);
        setupRuleMock(emailTakenRule, true, UserValidationError.EMAIL_TAKEN, false, userData);
        setupRuleMock(usernameTakenRule, true, UserValidationError.USERNAME_TAKEN, false, userData);

        UserValidationResult result = prepareValidatorAndValidate(userData);
        when(minUsernameLengthRule.validate(userData)).thenReturn(true);
        when(emailTakenRule.validate(userData)).thenReturn(true);
        when(usernameTakenRule.validate(userData)).thenReturn(true);

        assertTrue(result.isValid());
        assertEquals(0, result.getErrorsCount());
    }

    @Test
    public void shouldInterruptAfterFirstInvalidRuleWhenAllAreInterrupting() {
        UserData userData = new UserData("adasdas", "dasda");
        setupRuleMock(minUsernameLengthRule, true, UserValidationError.USERNAME_TOO_SHORT, true, userData);
        setupRuleMock(emailTakenRule, false, UserValidationError.EMAIL_TAKEN, true, userData);
        setupRuleMock(usernameTakenRule, false, UserValidationError.USERNAME_TAKEN, true, userData);

        UserValidationResult result = prepareValidatorAndValidate(userData);
        assertFalse(result.isValid());
        assertEquals(1, result.getErrorsCount());
        assertEquals(UserValidationError.EMAIL_TAKEN, result.getError(0).getError());
    }

    @Test
    public void shouldInterruptAfterFirstInterruptingInvalidRule() {
        UserData userData = new UserData("adasdas", "dasda");
        setupRuleMock(minUsernameLengthRule, false, UserValidationError.USERNAME_TOO_SHORT, false, userData);
        setupRuleMock(emailTakenRule, false, UserValidationError.EMAIL_TAKEN, true, userData);
        setupRuleMock(usernameTakenRule, false, UserValidationError.USERNAME_TAKEN, true, userData);

        UserValidationResult result = prepareValidatorAndValidate(userData);
        assertFalse(result.isValid());
        assertEquals(2, result.getErrorsCount());
        assertEquals(UserValidationError.USERNAME_TOO_SHORT, Objects.requireNonNull(result.getError(0)).getError());
        assertEquals(UserValidationError.EMAIL_TAKEN, Objects.requireNonNull(result.getError(1)).getError());
    }

    @Test
    public void shouldThrowExceptionWhenDataIsNull() {
        List<Rule> rules = Arrays.asList(
                minUsernameLengthRule,
                emailTakenRule,
                usernameTakenRule
        );
        UserValidator validator = new UserValidator(rules);
        assertThatThrownBy(() -> validator.validate(null)).isInstanceOf(NullPointerException.class);
    }
}
