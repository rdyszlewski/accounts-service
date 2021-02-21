package com.farfocle.accountsservice.validation;

import com.farfocle.accountsservice.validation.exceptions.password.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordValidatorTest {

    // password requirements
    // length: min 8 signs, max

    @Value("${farfocle.password.min}")
    private int minPasswordLength;

    @Value("${farfocle.password.max}")
    private int maxPasswordLength;

    @Autowired
    private PasswordValidator passwordValidator;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    @DisplayName("Should Throw TooShortPasswordException")
    public void shouldThrowTooShortPasswordException() throws PasswordException {
        String emptyPassword = "";
        assertThatThrownBy(()->passwordValidator.validate(emptyPassword)).isInstanceOf(PasswordTooShortException.class);
        String password = new String(new char[minPasswordLength - 1]).replace('\0', 'a');
        assertThatThrownBy(()->passwordValidator.validate(password)).isInstanceOf(PasswordTooShortException.class);
    }

    @Test
    @DisplayName("Should Throw TooLongPasswordException")
    public void shouldThrowTooLongPasswordException() throws PasswordException {
        String veryLongPassword = "fjasdkljflasdjklfjsdljfklasdjfljasdlfjsdl;jflasdjflasdjfjsdfhjisdnvuinsvinasdonsd";
        assertThatThrownBy(()->passwordValidator.validate(veryLongPassword)).isInstanceOf(PasswordTooLongException.class);
        String password = new String(new char[maxPasswordLength + 1]).replace('\0', 'a');
        assertThatThrownBy(()->passwordValidator.validate(password)).isInstanceOf(PasswordTooLongException.class);
    }

    @Test
    public void shouldThrowPasswordRequiresNumbersException() throws PasswordException {
        String password = "ToJestHaslo";
        assertThatThrownBy(()->passwordValidator.validate(password)).isInstanceOf(PasswordRequiresNumbersException.class);
    }

    @Test
    public void shouldThrowPasswordRequiresUppercaseLettersException() throws PasswordException{
        String password = "tojesthaslo1";
        assertThatThrownBy(()->passwordValidator.validate(password)).isInstanceOf(PasswordRequiresUppercaseLettersException.class);
    }

    @Test
    public void shouldThrowPasswordRequiresSpecialCharactersException() throws PasswordException{
        String password = "ToJestHaslo1";
        assertThatThrownBy(()->passwordValidator.validate(password)).isInstanceOf(PasswordRequiresSpecialCharactersException.class);
    }

    @Test
    public void shouldValidPassword() throws PasswordException {
        String password = "Th1s@IsCorrectPassword2";
        boolean result = passwordValidator.validate(password);
        assertTrue(result);
    }


}
