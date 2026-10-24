package com.example.employeemanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.employeemanager.MainActivity;
import com.example.employeemanager.R;
import com.example.employeemanager.dal.EmployeeDAO;
import com.example.employeemanager.models.Employee;
import com.example.employeemanager.utils.SessionManager;

/**
 * FILE DÙNG CHUNG – menu Employee. Đã nối sẵn tới màn của B và C, không cần sửa.
 */
public class EmployeeHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SessionManager session = new SessionManager(this);
        if (!session.isLoggedIn() || session.isAdmin()) {
            MainActivity.logout(this);
            return;
        }
        setContentView(R.layout.activity_employee_home);
        setTitle("Employee");

        Employee me = new EmployeeDAO(this).getById(session.getEmployeeId());
        TextView tvWelcome = findViewById(R.id.tvWelcome);
        tvWelcome.setText(me != null
                ? "Hello, " + me.getFullname() + " (" + me.getCode() + ")"
                : "Hello, " + session.getUsername());

        findViewById(R.id.btnCheckIn).setOnClickListener(v ->
                startActivity(new Intent(this, CheckInActivity.class)));               // B
        findViewById(R.id.btnHistory).setOnClickListener(v ->
                startActivity(new Intent(this, AttendanceHistoryActivity.class)));     // B
        findViewById(R.id.btnMySalary).setOnClickListener(v ->
                startActivity(new Intent(this, MySalaryActivity.class)));              // C
        findViewById(R.id.btnLogout).setOnClickListener(v -> MainActivity.logout(this));
    }
}
