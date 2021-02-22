package com.farfocle.accountsservice.register_data_validator;

public class RegisterData {

    private final String username;
    private final String email;

    public RegisterData(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
