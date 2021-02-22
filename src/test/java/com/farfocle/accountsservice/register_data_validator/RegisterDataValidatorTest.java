package com.farfocle.accountsservice.register_data_validator;

import com.farfocle.accountsservice.register_data_validator.rules.EmailTakenRule;
import com.farfocle.accountsservice.register_data_validator.rules.MinUsernameLengthRule;
import com.farfocle.accountsservice.register_data_validator.rules.Rule;
import com.farfocle.accountsservice.register_data_validator.rules.UsernameTakenRule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class RegisterDataValidatorTest {

    private final MinUsernameLengthRule minUsernameLengthRule = Mockito.mock(MinUsernameLengthRule.class);
    private final EmailTakenRule emailTakenRule = Mockito.mock(EmailTakenRule.class);
    private final UsernameTakenRule usernameTakenRule = Mockito.mock(UsernameTakenRule.class);

    @Test
    public void shouldReturnFalseWhenDataInvalid() {
        List<Rule> rules = Arrays.asList(
                minUsernameLengthRule,
                emailTakenRule,
                usernameTakenRule
        );
        RegisterData registerData = new RegisterData("adasdas", "dasda");
        when(minUsernameLengthRule.validate(registerData)).thenReturn(false);
        when(minUsernameLengthRule.getErrorType()).thenReturn(RegisterError.USERNAME_TOO_SHORT);
        when(emailTakenRule.validate(registerData)).thenReturn(true);
        when(emailTakenRule.getErrorType()).thenReturn(RegisterError.EMAIL_TAKEN);
        when(usernameTakenRule.validate(registerData)).thenReturn(false);
        when(usernameTakenRule.getErrorType()).thenReturn(RegisterError.USERNAME_TAKEN);

        RegisterDataValidator validator = new RegisterDataValidator(rules);
        UserValidationResult result = validator.validate(registerData);
        assertFalse(result.isValid());
        assertEquals(2, result.getErrorsCount());
        assertEquals(RegisterError.USERNAME_TOO_SHORT, result.getError(0));
        assertEquals(RegisterError.USERNAME_TAKEN, result.getError(1));
    }

    @Test
    public void shouldReturnTrueWhenDataIsCorrect() {
        List<Rule> rules = Arrays.asList(
                minUsernameLengthRule,
                emailTakenRule,
                usernameTakenRule
        );
        RegisterData registerData = new RegisterData("adasdas", "dasda");
        when(minUsernameLengthRule.validate(registerData)).thenReturn(true);
        when(emailTakenRule.validate(registerData)).thenReturn(true);
        when(usernameTakenRule.validate(registerData)).thenReturn(true);

        RegisterDataValidator validator = new RegisterDataValidator(rules);
        UserValidationResult result = validator.validate(registerData);

        assertTrue(result.isValid());
        assertEquals(0, result.getErrorsCount());
    }

    @Test
    public void shouldInterruptAfterFirstInvalidRuleWhenAllAreInterrupting() {
        List<Rule> rules = Arrays.asList(
                minUsernameLengthRule,
                emailTakenRule,
                usernameTakenRule
        );
        RegisterData registerData = new RegisterData("adasdas", "dasda");
        when(minUsernameLengthRule.validate(registerData)).thenReturn(true);
        when(minUsernameLengthRule.getErrorType()).thenReturn(RegisterError.USERNAME_TOO_SHORT);
        when(minUsernameLengthRule.isInterrupting()).thenReturn(true);
        when(emailTakenRule.validate(registerData)).thenReturn(false);
        when(emailTakenRule.getErrorType()).thenReturn(RegisterError.EMAIL_TAKEN);
        when(emailTakenRule.isInterrupting()).thenReturn(true);
        when(usernameTakenRule.validate(registerData)).thenReturn(false);
        when(usernameTakenRule.getErrorType()).thenReturn(RegisterError.USERNAME_TAKEN);
        when(usernameTakenRule.isInterrupting()).thenReturn(true);

        RegisterDataValidator validator = new RegisterDataValidator(rules);
        UserValidationResult result = validator.validate(registerData);

        assertFalse(result.isValid());
        assertEquals(1, result.getErrorsCount());
        assertEquals(RegisterError.EMAIL_TAKEN, result.getError(0));
    }

    @Test
    public void shouldInterruptAfterFirstInterruptingInvalidRule() {
        List<Rule> rules = Arrays.asList(
                minUsernameLengthRule,
                emailTakenRule,
                usernameTakenRule
        );
        RegisterData registerData = new RegisterData("adasdas", "dasda");
        when(minUsernameLengthRule.validate(registerData)).thenReturn(false);
        when(minUsernameLengthRule.getErrorType()).thenReturn(RegisterError.USERNAME_TOO_SHORT);
        when(minUsernameLengthRule.isInterrupting()).thenReturn(false);
        when(emailTakenRule.validate(registerData)).thenReturn(false);
        when(emailTakenRule.getErrorType()).thenReturn(RegisterError.EMAIL_TAKEN);
        when(emailTakenRule.isInterrupting()).thenReturn(true);
        when(usernameTakenRule.validate(registerData)).thenReturn(false);
        when(usernameTakenRule.getErrorType()).thenReturn(RegisterError.USERNAME_TAKEN);
        when(usernameTakenRule.isInterrupting()).thenReturn(true);

        RegisterDataValidator validator = new RegisterDataValidator(rules);
        UserValidationResult result = validator.validate(registerData);

        assertFalse(result.isValid());
        assertEquals(2, result.getErrorsCount());
        assertEquals(RegisterError.USERNAME_TOO_SHORT, result.getError(0));
        assertEquals(RegisterError.EMAIL_TAKEN, result.getError(1));
    }

    @Test
    public void shouldThrowExceptionWhenDataIsNull() {
        List<Rule> rules = Arrays.asList(
                minUsernameLengthRule,
                emailTakenRule,
                usernameTakenRule
        );
        RegisterDataValidator validator = new RegisterDataValidator(rules);
        assertThatThrownBy(() -> validator.validate(null)).isInstanceOf(NullPointerException.class);
    }
}
