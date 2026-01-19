package com.example.grihsarthi.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.grihsarthi.data.local.SessionManager;
import com.example.grihsarthi.ui.device.AddDeviceFragment;
import com.example.grihsarthi.ui.device.ControlDeviceFragment;
import com.example.grihsarthi.ui.device.DeviceFragment;
import com.example.grihsarthi.ui.device.ManageDeviceFragment;
import com.example.grihsarthi.R;
import com.example.grihsarthi.ui.main.MainActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;

    private TextView SingUp;

    private TextInputEditText Enteremail;
    private TextInputEditText Enterpassword;

    private AuthViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SessionManager sessionManager = new SessionManager(this);

        // 1️⃣ Session check FIRST
        if (sessionManager.isLoggedIn()) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
            return;
        }

        // 2️⃣ Then load UI
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        Enteremail = findViewById(R.id.Enteremail);
        Enterpassword = findViewById(R.id.Enterpassword);
        SingUp = findViewById(R.id.SingUp);

        viewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        // 3️⃣ Observe login result
        viewModel.getAuthState().observe(this, state -> {
            if (state.success) {

                // ✅ SET LOGIN ONLY HERE
                sessionManager.setLoggedIn(true);

                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();

            } else if (state.error != null) {
                Toast.makeText(this, state.error, Toast.LENGTH_SHORT).show();
            }
        });

        // 4️⃣ Login button
        btnLogin.setOnClickListener(v -> {
            String email = Enteremail.getText().toString().trim();
            String password = Enterpassword.getText().toString().trim();

            viewModel.login(email, password);
        });

        // 5️⃣ Signup
        SingUp.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
        });
    }
}


