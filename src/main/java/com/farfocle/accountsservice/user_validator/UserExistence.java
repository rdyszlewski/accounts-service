package com.farfocle.accountsservice.user_validator;

public interface UserExistence {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
