package com.example.employeemanager.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.employeemanager.R;

/**
 * [Người A – feature/auth-employee] sở hữu file này + layout/activity_employee_form.xml
 * TODO:
 * - Nhận Constants.EXTRA_EMPLOYEE_ID (-1 = thêm mới, khác -1 = sửa)
 * - Các ô: code, fullname, email, phone, hourlyRate, username, password
 * - Validate: không rỗng, email hợp lệ, hourlyRate > 0, code/username không trùng
 * - Thêm mới: tạo Account (ROLE_EMPLOYEE) trước, rồi Employee với accountId đó
 * - Lưu xong finish()
 */
public class EmployeeFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_form);
        setTitle("Employee Form");
    }
}
