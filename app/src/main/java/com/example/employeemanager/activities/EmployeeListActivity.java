package com.example.employeemanager.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.employeemanager.R;

/**
 * [Người A – feature/auth-employee] sở hữu file này + layout/activity_employee_list.xml
 * TODO:
 * - ListView danh sách nhân viên (EmployeeDAO.getAll)
 * - Ô tìm kiếm theo mã / tên / email
 * - Nút Add -> EmployeeFormActivity (không truyền extra)
 * - Click item -> EmployeeFormActivity, putExtra(Constants.EXTRA_EMPLOYEE_ID, id) để sửa
 * - Long click -> AlertDialog xác nhận xoá
 * - onResume() load lại danh sách
 */
public class EmployeeListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);
        setTitle("Employee Management");
    }
}
