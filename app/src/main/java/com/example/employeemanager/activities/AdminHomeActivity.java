package com.example.employeemanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.employeemanager.MainActivity;
import com.example.employeemanager.R;
import com.example.employeemanager.utils.SessionManager;

/**
 * FILE DÙNG CHUNG – menu Admin. Đã nối sẵn tới màn của A và C, không cần sửa.
 */
public class AdminHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SessionManager session = new SessionManager(this);
        if (!session.isLoggedIn() || !session.isAdmin()) {
            MainActivity.logout(this);
            return;
        }
        setContentView(R.layout.activity_admin_home);
        setTitle("Admin");

        TextView tvWelcome = findViewById(R.id.tvWelcome);
        tvWelcome.setText("Hello, " + session.getUsername() + " (Admin)");

        findViewById(R.id.btnManageEmployees).setOnClickListener(v ->
                startActivity(new Intent(this, EmployeeListActivity.class)));          // A
        findViewById(R.id.btnManageSalaries).setOnClickListener(v ->
                startActivity(new Intent(this, SalaryAdminActivity.class)));           // C
        findViewById(R.id.btnLogout).setOnClickListener(v -> MainActivity.logout(this));
    }
}
