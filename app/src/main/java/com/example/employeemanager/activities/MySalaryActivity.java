package com.example.employeemanager.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.employeemanager.R;

/**
 * [Người C – feature/salary] sở hữu file này + layout/activity_my_salary.xml
 * TODO:
 * - Lấy employeeId từ SessionManager
 * - Chọn tháng/năm -> hiển thị totalHours, overtimeHours, totalSalary, status
 * - Danh sách lương các tháng của mình
 * - Chưa có bản ghi Salary -> hiện 'Not calculated yet' (hoặc tính tạm để xem)
 */
public class MySalaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_salary);
        setTitle("My Salary");
    }
}
