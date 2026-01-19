package com.example.grihsarthi.data.repository;

public class AuthRepository {
    public boolean login(String email, String password) {
        return email.equals("admin") && password.equals("12345");
    }

    public boolean signup(String email, String password) {
        return true;
    }
}
