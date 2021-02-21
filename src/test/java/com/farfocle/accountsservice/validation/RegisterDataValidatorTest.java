package com.farfocle.accountsservice.validation;

import com.farfocle.accountsservice.dto.RegisterRequest;
import com.farfocle.accountsservice.repository.AccountsRepository;
import com.farfocle.accountsservice.validation.exceptions.register.*;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegisterDataValidatorTest {

    private AccountsRepository repository = Mockito.mock(AccountsRepository.class);

    private RegisterDataValidator validator = new RegisterDataValidator(repository);

    @Value("${farfocle.username.min}")
    private int minUsernameLength;

    @Value("${farfocle.username.max}")
    private int maxUsernameLength;

    @Test
    public void shouldReturnTrueWhenCorrectData(){

    }

    @Test
    public void shouldThrowUsernameIsTakenException() throws InvalidLoginException {
        RegisterRequest registerRequest = getTestRegisterRequest();
        when(repository.existsByUsername(registerRequest.getUsername())).thenReturn(true);
        assertThatThrownBy(()->validator.validate(registerRequest)).isInstanceOf(UsernameIsTakenException.class);
    }

    private RegisterRequest getTestRegisterRequest(){
        return new RegisterRequest("Antek","antek@gmail.com", "Passw0rd");
    }

    @Test
    public void shouldThrowEmailIsTakenException(){
        RegisterRequest registerRequest = getTestRegisterRequest();
        when(repository.existsByEmail(registerRequest.getEmail())).thenReturn(true);
        assertThatThrownBy(()->validator.validate(registerRequest)).isInstanceOf(EmailIsTakenException.class);
    }

    @Test
    public void shouldThrowUsernameIsTooShortException() throws InvalidLoginException {
        RegisterRequest registerRequest = getTestRegisterRequest();
        registerRequest.setUsername("a");
        configureMockUserIsNotTaken(registerRequest);
        assertThatThrownBy(()->validator.validate(registerRequest)).isInstanceOf(UsernameIsTooShortException.class);

        registerRequest.setUsername(new String(new char[minUsernameLength - 1]).replace('\0','a'));
        assertThatThrownBy(()->validator.validate(registerRequest)).isInstanceOf(UsernameIsTooShortException.class);

        registerRequest.setUsername(new String(new char[minUsernameLength]).replace('\0','a'));
        Assertions.assertTrue(validator.validate(registerRequest));

        // TODO: można tutaj jeszcze dodać testy które obsłuża
    }

    private void configureMockUserIsNotTaken(RegisterRequest registerRequest){
        when(repository.existsByUsername(registerRequest.getUsername())).thenReturn(false);
        when(repository.existsByEmail(registerRequest.getEmail())).thenReturn(false);
    }

    @Test
    public void shouldThrowUsernameIsTooLongException() throws InvalidLoginException {
        RegisterRequest registerRequest = getTestRegisterRequest();
        configureMockUserIsNotTaken(registerRequest);
        registerRequest.setUsername(new String(new char[maxUsernameLength * 5]).replace('\0', 'a'));
        assertThatThrownBy(()->validator.validate(registerRequest)).isInstanceOf(UsernameIsTooLongException.class);
        registerRequest.setUsername(new String(new char[maxUsernameLength + 1]).replace('\0','a'));
        assertThatThrownBy(()->validator.validate(registerRequest)).isInstanceOf(UsernameIsTooLongException.class);
        registerRequest.setUsername(new String(new char[maxUsernameLength]).replace('\0','a'));
        boolean result = validator.validate(registerRequest);
        Assertions.assertTrue(result);
    }

    @Test
    public void shouldThrowEmptyUsernameException(){
        RegisterRequest registerRequest = getTestRegisterRequest();
        configureMockUserIsNotTaken(registerRequest);
        registerRequest.setUsername(null);
        assertThatThrownBy(()->validator.validate(registerRequest)).isInstanceOf(EmptyUsernameException.class);
        registerRequest.setUsername("");
        assertThatThrownBy(()->validator.validate(registerRequest)).isInstanceOf(EmptyUsernameException.class);


    }

    @Test
    public void shouldThrowEmptyEmailException(){
        RegisterRequest registerRequest = getTestRegisterRequest();
        configureMockUserIsNotTaken(registerRequest);
        registerRequest.setEmail(null);
        assertThatThrownBy(()->validator.validate(registerRequest)).isInstanceOf(EmptyEmailException.class);
        registerRequest.setEmail("");
        assertThatThrownBy(()->validator.validate(registerRequest)).isInstanceOf(EmptyEmailException.class);
    }

    @Test
    public void shouldThrowInvalidEmailException(){
        // TODO: zrobić to później
    }


}
