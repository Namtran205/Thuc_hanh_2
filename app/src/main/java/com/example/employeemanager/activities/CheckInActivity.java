package com.example.employeemanager.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.employeemanager.R;

/**
 * [Người B – feature/attendance] sở hữu file này + layout/activity_check_in.xml
 * TODO:
 * - Hiển thị ngày hôm nay + trạng thái chấm công hôm nay
 * - Nút Check in: chặn nếu hôm nay đã check-in
 * - Nút Check out: chặn nếu chưa check-in, đã check-out, hoặc giờ out <= giờ in
 * - Check-out xong: tự tính totalHours = (out - in) giờ, status = COMPLETED
 * - Bản ghi CHECKED_IN của ngày cũ -> INCOMPLETE
 * - employeeId lấy từ new SessionManager(this).getEmployeeId()
 */
public class CheckInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);
        setTitle("Check In / Check Out");
    }
}
