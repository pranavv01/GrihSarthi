package com.example.grihsarthi.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.grihsarthi.R;
import com.example.grihsarthi.data.local.SessionManager;
import com.example.grihsarthi.ui.auth.LoginActivity;
import com.example.grihsarthi.ui.device.AddDeviceFragment;
import com.example.grihsarthi.ui.device.ControlDeviceFragment;
import com.example.grihsarthi.ui.device.DeviceFragment;
import com.example.grihsarthi.ui.device.ManageDeviceFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private SessionManager sessionManager;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sessionManager = new SessionManager(this);
        if (!sessionManager.isLoggedIn()) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_main);

        // Drawer
        drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.navigationView);

        // Bottom Navigation
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);

        if (savedInstanceState == null) {
            loadFragment(new DeviceFragment());
        }

        navigationView.setNavigationItemSelectedListener(item -> {
            drawerLayout.closeDrawers();

            if (item.getItemId() == R.id.logout) {
                showLogoutDialog();
                return true;
            }
            return false;
        });


        bottomNav.setOnItemSelectedListener(item -> {
            Fragment fragment = null;

            int id = item.getItemId();
            if (id == R.id.nav_Device) {
                fragment = new DeviceFragment();
            } else if (id == R.id.nav_AddDevice) {
                fragment = new AddDeviceFragment();
            } else if (id == R.id.nav_ControlDevice) {
                fragment = new ControlDeviceFragment();
            } else if (id == R.id.nav_ManageDevice) {
                fragment = new ManageDeviceFragment();
            }

            if (fragment != null) {
                loadFragment(fragment);
            }
            return true;
        });
    }


    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }


    private void showLogoutDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, which) -> {

                    sessionManager.logout();

                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                .show();
    }
}
