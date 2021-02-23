package com.farfocle.accountsservice.validation;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SignUpMessageCreatorTest {

    @Test
    public void shouldWork() throws IOException {
        SignUpMessageCreator messageCreator = new SignUpMessageCreator("validation-messages-pl.properties");
        String message = messageCreator.getMessage(SignUpError.USERNAME_TOO_SHORT, "200");
        String expectedMessage = "Kaszanka jest za krótka. Minimalna długość to 200 znaków";
        assertEquals(expectedMessage, message);
    }
}
