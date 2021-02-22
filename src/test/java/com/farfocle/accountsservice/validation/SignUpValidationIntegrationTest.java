package com.farfocle.accountsservice.validation;

import com.farfocle.accountsservice.dto.SignUpData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SignUpValidationIntegrationTest {

    // TODO: dokończyć testy

    @MockBean
    private UserExistenceAdapter userExistenceAdapter;

    @Autowired
    private SignUpValidationService validationService;

    @Test
    public void shouldPassValidation(){
        SignUpData data = new SignUpData("kaszanka", "kaszanka@kaszanka.com", "Kaszanka2");
        when(userExistenceAdapter.existsByUsername(data.getUsername())).thenReturn(false);
        when(userExistenceAdapter.existsByEmail(data.getEmail())).thenReturn(false);
        SignUpValidationResult result = validationService.validate(data);

        assertTrue(result.isSuccess());
    }

    @Test
    public void shouldFailValidationWhenUsernameIsTaken(){
        SignUpData data = new SignUpData("kaszanka", "kaszanka@kaszanka.com", "Kaszanka2");
        when(userExistenceAdapter.existsByUsername(data.getUsername())).thenReturn(true);
        when(userExistenceAdapter.existsByEmail(data.getEmail())).thenReturn(false);
        SignUpValidationResult result = validationService.validate(data);

        assertFalse(result.isSuccess());
        assertEquals(SignUpError.USERNAME_TAKEN, result.getErrors().get(0).getCode());
    }

    @Test
    public void shouldFailValidationWhenEmailIsTaken(){
        SignUpData data = new SignUpData("kaszanka", "kaszanka@kaszanka.com", "Kaszanka2");
        when(userExistenceAdapter.existsByUsername(data.getUsername())).thenReturn(false);
        when(userExistenceAdapter.existsByEmail(data.getEmail())).thenReturn(true);
        SignUpValidationResult result = validationService.validate(data);

        assertFalse(result.isSuccess());
        assertEquals(SignUpError.EMAIL_TAKEN, result.getErrors().get(0).getCode());
    }

    @Test
    public void shouldFailValidationWhenUsernameIsTooShort(){
        SignUpData data = new SignUpData("k", "kaszanka@kaszanka.com", "Kaszanka2");
        when(userExistenceAdapter.existsByUsername(data.getUsername())).thenReturn(false);
        when(userExistenceAdapter.existsByEmail(data.getEmail())).thenReturn(false);
        SignUpValidationResult result = validationService.validate(data);

        assertFalse(result.isSuccess());
        assertEquals(SignUpError.USERNAME_TOO_SHORT, result.getErrors().get(0).getCode());
    }
}
