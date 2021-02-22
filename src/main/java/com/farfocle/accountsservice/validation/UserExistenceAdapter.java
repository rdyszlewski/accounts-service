package com.farfocle.accountsservice.validation;

import com.farfocle.accountsservice.user_validator.UserExistence;
import com.farfocle.accountsservice.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserExistenceAdapter implements UserExistence {

    private AccountsRepository repository;

    @Autowired
    public UserExistenceAdapter(AccountsRepository repository){
        this.repository = repository;
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
}
