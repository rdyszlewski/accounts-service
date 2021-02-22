package com.farfocle.accountsservice.register_data_validator;

public interface UserExistence {
    boolean existsByUsername(String username);

    boolean getByEmail(String email);
}
