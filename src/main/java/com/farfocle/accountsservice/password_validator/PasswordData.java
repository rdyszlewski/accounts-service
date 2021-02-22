package com.farfocle.accountsservice.password_validator;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PasswordData that = (PasswordData) o;
        return password.equals(that.password) &&
                Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, username);
    }
}
