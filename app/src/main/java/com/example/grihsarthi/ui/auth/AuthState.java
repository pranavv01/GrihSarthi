package com.example.grihsarthi.ui.auth;

public class AuthState {
    public final boolean success;
    public final String error;

    public AuthState(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public static AuthState success(){
        return new AuthState(true, null);
    }

    public static AuthState error(String  message){
        return new AuthState(false, message);
    }

}
