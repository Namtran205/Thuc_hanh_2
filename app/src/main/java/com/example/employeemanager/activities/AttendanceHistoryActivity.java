package com.example.employeemanager.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.employeemanager.R;

/**
 * [Người B – feature/attendance] sở hữu file này + layout/activity_attendance_history.xml
 * TODO:
 * - Chọn tháng/năm (Spinner hoặc nút < >)
 * - ListView các bản ghi: ngày, giờ vào, giờ ra, tổng giờ, status
 * - Tổng kết tháng: số ngày công, tổng giờ (COMPLETED), số bản ghi INCOMPLETE
 */
public class AttendanceHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_history);
        setTitle("Attendance History");
    }
}
