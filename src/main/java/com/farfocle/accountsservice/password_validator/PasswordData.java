package com.farfocle.accountsservice.password_validator;

public class PasswordData {
    private String password;
    private String username;

    public PasswordData(String password){
        this.password = password;
    }

    public PasswordData(String password, String username){
        this.password = password;
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }
}