package com.farfocle.accountsservice.register_data_validator;

public interface UserExistence {
    boolean getByUsername(String username);
    boolean getByEmail(String email);
}
