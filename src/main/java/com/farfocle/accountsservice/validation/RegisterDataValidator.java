package com.farfocle.accountsservice.validation;

import com.farfocle.accountsservice.dto.RegisterRequest;
import com.farfocle.accountsservice.repository.AccountsRepository;
import com.farfocle.accountsservice.validation.exceptions.register.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RegisterDataValidator {

    private AccountsRepository repository;


    private static int minUsernameLength;
    private static int maxUsernameLength;

    @Value("${farfocle.username.min}")
    private void setMinUsernameLength(int value){
        minUsernameLength = value;
    }

    @Value("${farfocle.username.max}")
    private void setMaxUsernameLength(int value){
        maxUsernameLength = value;
    }

    @Autowired
    public RegisterDataValidator(AccountsRepository repository){
        this.repository = repository;
    }

    public boolean validate(RegisterRequest data) throws InvalidLoginException{
        if(data.getUsername() == null || data.getUsername().isEmpty()){
            throw new EmptyUsernameException();
        }
        if(data.getEmail() == null || data.getEmail().isEmpty()){
            throw new EmptyEmailException();
        }
        if(repository.existsByUsername(data.getUsername())){
            throw new UsernameIsTakenException();
        }
        if(repository.existsByEmail(data.getEmail())){
            throw new EmailIsTakenException();
        }
        if(data.getUsername().length() < minUsernameLength){
            throw new UsernameIsTooShortException();
        }
        if(data.getUsername().length() > maxUsernameLength){
            throw new UsernameIsTooLongException();
        }

        return true;
    }
}
