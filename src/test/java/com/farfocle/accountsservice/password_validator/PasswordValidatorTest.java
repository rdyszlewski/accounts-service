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
        PasswordData passwordData = new PasswordData("Sho4hsauin");
        setupRule(minLengthRule, true, false, getTooShortError(), passwordData);
        setupRule(maxLengthRule, true, false, getTooLongError(), passwordData);
        setupRule(uppercaseRule, true, false, getUppercaseError(), passwordData);
        setupRule(digitsRule, true, false, getDigitsError(), passwordData);

        ValidationResult validationResult = prepareValidatorAndValidate(passwordData);
        assertTrue(validationResult.isValid());
        assertEquals(0, validationResult.getErrors().size());
    }

    @Test
    public void shouldReturnInvalid(){
        PasswordData passwordData = new PasswordData("Sho4hsauin");
        setupRule(minLengthRule, true, false, getTooShortError(), passwordData);
        setupRule(maxLengthRule, false, false, getTooLongError(), passwordData);
        setupRule(uppercaseRule, true, false, getUppercaseError(), passwordData);
        setupRule(digitsRule, false, false, getDigitsError(), passwordData);

        ValidationResult result = prepareValidatorAndValidate(passwordData);
        assertFalse(result.isValid());
        assertEquals(2, result.getErrors().size());
        assertEquals(PasswordError.TOO_LONG, result.getErrors().get(0).getError());
        assertEquals(PasswordError.DIGITS, result.getErrors().get(1).getError());
    }

    @Test
    public void shouldInterrupt(){
        PasswordData passwordData = new PasswordData("Sho4hsauin");
        setupRule(minLengthRule, true, false, getTooShortError(), passwordData);
        setupRule(maxLengthRule, false, true, getTooLongError(), passwordData);
        setupRule(uppercaseRule, true, false, getUppercaseError(), passwordData);
        setupRule(digitsRule, false, false, getDigitsError(), passwordData);

        ValidationResult result = prepareValidatorAndValidate(passwordData);
        assertFalse(result.isValid());
        assertEquals(1, result.getErrors().size());
        assertEquals(PasswordError.TOO_LONG, result.getErrors().get(0).getError());
    }

    @Test
    public void shouldNotInterruptWhenValid(){
        PasswordData passwordData = new PasswordData("Sho4hsauin");
        setupRule(minLengthRule, true,  true, getTooShortError(), passwordData);
        setupRule(maxLengthRule, true,  true, getTooLongError(), passwordData);
        setupRule(uppercaseRule, true, true, getUppercaseError(), passwordData);
        setupRule(digitsRule, true,  true, getDigitsError(), passwordData);

        ValidationResult result = prepareValidatorAndValidate(passwordData);
        assertTrue(result.isValid());
        assertEquals(0, result.getErrors().size());
    }

    private ValidationResult prepareValidatorAndValidate(PasswordData passwordData) {
        List<Rule> rules = Arrays.asList(
                minLengthRule,
                maxLengthRule,
                uppercaseRule,
                digitsRule
        );
        PasswordValidator validator = new PasswordValidator(rules);


        ValidationResult result = validator.validate(passwordData);
        return result;
    }

    private void setupRule(Rule rule, boolean valid,  boolean interrupting, ErrorDetails errorDetails, PasswordData passwordData){
        when(rule.validate(passwordData)).thenReturn(valid);
        when(rule.getErrorDetails()).thenReturn(errorDetails);
        when(rule.isInterrupting()).thenReturn(interrupting);
    }

    private ErrorDetails getTooShortError(){
        return new ErrorDetails(PasswordError.TOO_SHORT, "1");
    }

    private ErrorDetails getTooLongError(){
        return new ErrorDetails(PasswordError.TOO_LONG, "1");
    }

    private ErrorDetails getUppercaseError(){
        return new ErrorDetails(PasswordError.UPPERCASE, "1");
    }

    private ErrorDetails getDigitsError(){
        return new ErrorDetails(PasswordError.DIGITS, "1");
    }
}
