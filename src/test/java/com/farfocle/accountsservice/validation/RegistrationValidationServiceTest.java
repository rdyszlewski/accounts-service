package com.farfocle.accountsservice.validation;

import com.farfocle.accountsservice.dto.SignUpData;
import com.farfocle.accountsservice.password_validator.*;
import com.farfocle.accountsservice.user_validator.UserValidationError;
import com.farfocle.accountsservice.user_validator.UserData;
import com.farfocle.accountsservice.user_validator.UserValidationResult;
import com.farfocle.accountsservice.user_validator.UserValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class RegistrationValidationServiceTest {

    // TODO: dokończyć testy
    // TODO: dodać testy z wartościami
    // TODO: dodać testy z wiadomościami
    // TODO: prawdopodobnie zmienić PasswordErrorDetails, żeby nie było pola isValid

    private PasswordValidator passwordValidator = Mockito.mock(PasswordValidator.class);
    private UserValidator userValidator = Mockito.mock(UserValidator.class);
    private SignUpMessageCreator messageCreator = Mockito.mock(SignUpMessageCreator.class);
    // TODO: prawdopodobnie tutaj będzie trzeba jeszcze dodać coś, co tworzy komentarze

    private SignUpValidationService service;

    @Before
    public void initService(){
        service = new SignUpValidationService(passwordValidator, userValidator, messageCreator);
    }

    @Test
    public void shouldReturnTrueWhenDataAreValid(){
        SignUpData data = new SignUpData("username","email@email.com","Password1");

        // TODO: sprawdzić, czy passwordValidation wymaga podawania true
        setupUserValidation(data);
        setupPasswordValidation(data, true);

        SignUpValidationResult result = service.validate(data);
        assertTrue(result.isSuccess());
        assertEquals(0, result.getErrors().size());
    }

    private void setupUserValidation(SignUpData data, UserValidationError... errors){
        UserData userData = new UserData(data.getUsername(), data.getEmail());
        UserValidationResult expectedUserValidationResult = new UserValidationResult();
        for(UserValidationError error: errors){
            expectedUserValidationResult.addError(error);
        }
        when(userValidator.validate(userData)).thenReturn(expectedUserValidationResult);
    }

    private void setupPasswordValidation(SignUpData data, boolean success, ErrorDetails... errorDetails){
        PasswordData password = new PasswordData(data.getPassword(), data.getUsername());
        ValidationResult expectedPasswordValidationResult = new ValidationResult();
        expectedPasswordValidationResult.setValid(success);
        for(ErrorDetails details: errorDetails){
            expectedPasswordValidationResult.addError(details);
        }
        when(passwordValidator.validate(password)).thenReturn(expectedPasswordValidationResult);
    }

    @Test
    public void shouldReturnFalseWhenUserValidationIsFailed(){
        SignUpData data = new SignUpData("username","email@email.com","Password1");

        setupUserValidation(data, UserValidationError.USERNAME_TOO_SHORT, UserValidationError.EMAIL_TAKEN);
        setupPasswordValidation(data, true);

        SignUpValidationResult result = service.validate(data);
        assertFalse(result.isSuccess());
        assertEquals(2, result.getErrors().size());
        assertEquals(SignUpError.USERNAME_TOO_SHORT, result.getErrors().get(0).getCode());
        assertEquals(SignUpError.EMAIL_TAKEN, result.getErrors().get(1).getCode());
    }

    @Test
    public void shouldReturnFalseWhenPasswordValidationIsFailed(){
        SignUpData data = new SignUpData("username","email@email.com","Password1");

        setupUserValidation(data);
        setupPasswordValidation(data, false, new ErrorDetails(PasswordError.TOO_SHORT, "5"), new ErrorDetails(PasswordError.UPPERCASE, "1"), new ErrorDetails(PasswordError.DIGITS, "1"));
        SignUpValidationResult result = service.validate(data);
        assertFalse(result.isSuccess());
        assertEquals(3, result.getErrors().size());
        assertEquals(SignUpError.PASS_TOO_SHORT, result.getErrors().get(0).getCode());
        assertEquals(SignUpError.PASS_NO_UPPERCASE, result.getErrors().get(1).getCode());
        assertEquals(SignUpError.PASS_NO_DIGITS, result.getErrors().get(2).getCode());
    }

    @Test
    public void shouldThrowErrorWhenDataIsNull(){
        // TODO: zmienić ten błąd na jakiś inny
        assertThatThrownBy(()->service.validate(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void shouldNotExecutePasswordValidationWhenUserValidationFailed(){
        SignUpData data = new SignUpData("username","email@email.com","Password1");

        setupUserValidation(data, UserValidationError.EMAIL_TAKEN, UserValidationError.USERNAME_TAKEN);
        setupPasswordValidation(data,false, new ErrorDetails(PasswordError.TOO_SHORT, "5"), new ErrorDetails(PasswordError.UPPERCASE, "1"));

        SignUpValidationResult result = service.validate(data);
        assertFalse(result.isSuccess());
        assertEquals(2, result.getErrors().size());
        assertEquals(SignUpError.EMAIL_TAKEN, result.getErrors().get(0).getCode());
        assertEquals(SignUpError.USERNAME_TAKEN, result.getErrors().get(1).getCode());
    }
}
