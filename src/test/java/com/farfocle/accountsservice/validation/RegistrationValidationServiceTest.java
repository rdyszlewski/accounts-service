package com.farfocle.accountsservice.validation;

import com.farfocle.accountsservice.dto.SignUpData;
import com.farfocle.accountsservice.password_validator.*;
import com.farfocle.accountsservice.user_validator.*;
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

    private void setupUserValidation(SignUpData data, UserErrorDetails... errors){
        UserData userData = new UserData(data.getUsername(), data.getEmail());
        UserValidationResult expectedUserValidationResult = new UserValidationResult();
        for(UserErrorDetails error: errors){
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

        setupUserValidation(data,
                new UserErrorDetails(UserValidationError.USERNAME_TOO_SHORT, "5"),
                new UserErrorDetails(UserValidationError.EMAIL_TAKEN, null));
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
        setupPasswordValidation(data, false,
                new ErrorDetails(PasswordError.TOO_SHORT, "5"),
                new ErrorDetails(PasswordError.UPPERCASE, "1"),
                new ErrorDetails(PasswordError.DIGITS, "1"));
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
    public void shouldReturnAllErrors(){
        SignUpData data = new SignUpData("username","email@email.com","Password1");

        setupUserValidation(data,
                new UserErrorDetails(UserValidationError.EMAIL_TAKEN, null),
                new UserErrorDetails(UserValidationError.USERNAME_TAKEN, null));
        setupPasswordValidation(data,false, new ErrorDetails(PasswordError.TOO_SHORT, "5"), new ErrorDetails(PasswordError.UPPERCASE, "1"));

        SignUpValidationResult result = service.validate(data);
        assertFalse(result.isSuccess());
        assertEquals(4, result.getErrors().size());
        assertEquals(SignUpError.EMAIL_TAKEN, result.getErrors().get(0).getCode());
        assertEquals(SignUpError.USERNAME_TAKEN, result.getErrors().get(1).getCode());
        assertEquals(SignUpError.PASS_TOO_SHORT, result.getErrors().get(2).getCode());
        assertEquals(SignUpError.PASS_NO_UPPERCASE, result.getErrors().get(3).getCode());
    }
}
