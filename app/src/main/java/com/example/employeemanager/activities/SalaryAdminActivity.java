package com.example.employeemanager.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.employeemanager.R;

/**
 * [Người C – feature/salary] sở hữu file này + layout/activity_salary_admin.xml
 * TODO:
 * - Chọn tháng/năm
 * - Nút Calculate: với mỗi nhân viên -> tổng giờ COMPLETED trong tháng -> SalaryCalculator -> upsert Salary (PENDING)
 * - overtime = max(0, total - STANDARD_HOURS_PER_MONTH)
 * - salary = (total - overtime) * rate + overtime * rate * OVERTIME_RATE
 * - ListView bảng lương tháng; click -> đổi PENDING <-> PAID; long click -> xoá
 */
public class SalaryAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary_admin);
        setTitle("Salary Management");
    }
}
