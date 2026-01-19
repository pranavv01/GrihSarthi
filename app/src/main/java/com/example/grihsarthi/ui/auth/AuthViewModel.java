package com.example.grihsarthi.ui.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.grihsarthi.data.repository.AuthRepository;

public class AuthViewModel extends ViewModel {

    private final AuthRepository repository = new AuthRepository();
    private final MutableLiveData<AuthState> authState = new MutableLiveData<>();
    public LiveData<AuthState> getAuthState() {
        return authState;
    }

    public void login (String email, String password) {
        if(email.isEmpty() || password.isEmpty()) {
            authState.setValue(AuthState.error("Fields cannot be empty"));
            return;
        }

        boolean success = repository.login(email, password);
        if(success) {
            authState.setValue(AuthState.success());
            return;
        }
        else {
            authState.setValue(AuthState.error("Invalid Credentials"));
        }
    }

    public void signup(String email, String password){
        if(email.isEmpty() || password.isEmpty()){
            authState.setValue(AuthState.success());
        }
        else {
            authState.setValue(AuthState.error("Sign failed"));
        }
    }
}
