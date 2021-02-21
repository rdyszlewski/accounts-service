package com.farfocle.accountsservice.password_validator;

import com.farfocle.accountsservice.password_validator.rules.*;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class PasswordValidatorTest {

    private MinLengthRule minLengthRule = Mockito.mock(MinLengthRule.class);
    private MaxLengthRule maxLengthRule = Mockito.mock(MaxLengthRule.class);
    private UppercaseRule uppercaseRule = Mockito.mock(UppercaseRule.class);
    private DigitsRule digitsRule = Mockito.mock(DigitsRule.class);

    @Test
    public void shouldReturnValidationSuccess(){
        List<Rule> rules = Arrays.asList(
                minLengthRule,
                maxLengthRule,
                uppercaseRule,
                digitsRule
        );
        PasswordData passwordData = new PasswordData("Sho4hsauin");
        when(minLengthRule.validate(passwordData)).thenReturn(true);
        when(maxLengthRule.validate(passwordData)).thenReturn(true);
        when(uppercaseRule.validate(passwordData)).thenReturn(true);
        when(digitsRule.validate(passwordData)).thenReturn(true);

        PasswordValidator validator = new PasswordValidator(rules);
        ValidationResult validationResult = validator.validate(passwordData);
        assertTrue(validationResult.isValid());
        assertEquals(0, validationResult.getErrors().size());
    }

    @Test
    public void shouldReturnInvalid(){
        List<Rule> rules = Arrays.asList(
                minLengthRule,
                maxLengthRule,
                uppercaseRule,
                digitsRule
        );
        PasswordData passwordData = new PasswordData("Sho4hsauin");
        when(minLengthRule.validate(passwordData)).thenReturn(true);
        when(maxLengthRule.validate(passwordData)).thenReturn(false);
        when(maxLengthRule.getErrorDetails()).thenReturn(new ErrorDetails(PasswordError.TOO_LONG, "1"));
        when(uppercaseRule.validate(passwordData)).thenReturn(true);
        when(digitsRule.validate(passwordData)).thenReturn(false);
        when(digitsRule.getErrorDetails()).thenReturn(new ErrorDetails(PasswordError.DIGITS, "1"));

        PasswordValidator validator = new PasswordValidator(rules);
        ValidationResult result = validator.validate(passwordData);
        assertFalse(result.isValid());
        assertEquals(2, result.getErrors().size());
        assertEquals(PasswordError.TOO_LONG, result.getErrors().get(0).getError());
        assertEquals(PasswordError.DIGITS, result.getErrors().get(1).getError());
    }

    @Test
    public void shouldInterrupt(){
        List<Rule> rules = Arrays.asList(
                minLengthRule,
                maxLengthRule,
                uppercaseRule,
                digitsRule
        );
        PasswordData passwordData = new PasswordData("Sho4hsauin");
        when(minLengthRule.validate(passwordData)).thenReturn(true);
        when(maxLengthRule.validate(passwordData)).thenReturn(false);
        when(maxLengthRule.isInterrupting()).thenReturn(true);
        when(maxLengthRule.getErrorDetails()).thenReturn(new ErrorDetails(PasswordError.TOO_LONG, "1"));
        when(uppercaseRule.validate(passwordData)).thenReturn(true);
        when(digitsRule.validate(passwordData)).thenReturn(false);
        when(digitsRule.getErrorDetails()).thenReturn(new ErrorDetails(PasswordError.DIGITS, "1"));

        PasswordValidator validator = new PasswordValidator(rules);
        ValidationResult result = validator.validate(passwordData);
        assertFalse(result.isValid());
        assertEquals(1, result.getErrors().size());
        assertEquals(PasswordError.TOO_LONG, result.getErrors().get(0).getError());
    }

    @Test
    public void shouldNotInterruptWhenValid(){
        List<Rule> rules = Arrays.asList(
                minLengthRule,
                maxLengthRule,
                uppercaseRule,
                digitsRule
        );
        PasswordData passwordData = new PasswordData("Sho4hsauin");
        when(minLengthRule.validate(passwordData)).thenReturn(true);
        when(minLengthRule.isInterrupting()).thenReturn(true);
        when(minLengthRule.getErrorDetails()).thenReturn(new ErrorDetails(PasswordError.TOO_SHORT, "1"));
        when(maxLengthRule.validate(passwordData)).thenReturn(true);
        when(maxLengthRule.isInterrupting()).thenReturn(true);
        when(maxLengthRule.getErrorDetails()).thenReturn(new ErrorDetails(PasswordError.TOO_LONG, "1"));
        when(uppercaseRule.validate(passwordData)).thenReturn(true);
        when(uppercaseRule.isInterrupting()).thenReturn(true);
        when(uppercaseRule.getErrorDetails()).thenReturn(new ErrorDetails(PasswordError.UPPERCASE, "1"));
        when(digitsRule.validate(passwordData)).thenReturn(true);
        when(digitsRule.isInterrupting()).thenReturn(true);
        when(digitsRule.getErrorDetails()).thenReturn(new ErrorDetails(PasswordError.DIGITS, "1"));

        PasswordValidator validator = new PasswordValidator(rules);
        ValidationResult result = validator.validate(passwordData);
        assertTrue(result.isValid());
        assertEquals(0, result.getErrors().size());
    }

}
