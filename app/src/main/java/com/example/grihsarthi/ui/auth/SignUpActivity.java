package com.example.grihsarthi.ui.auth;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.grihsarthi.R;

public class SignUpActivity extends AppCompatActivity {

    private AuthViewModel viewModel;
    Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        btnSignup = findViewById(R.id.btnSignup);

        viewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        viewModel.getAuthState().observe(this, state -> {
            if(state.success){
                finish();
            } else if (state.error != null) {
                Toast.makeText(this, state.error, Toast.LENGTH_SHORT).show();
            }
        });
//
////        btnSignup.setOnClickListener(v -> {
////            viewModel.signup(
//////                    String email =
////            );
//        });
    }
}