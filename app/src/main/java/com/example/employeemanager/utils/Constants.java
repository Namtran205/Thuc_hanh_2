package com.example.employeemanager.utils;

/**
 * Hằng số dùng chung cho cả nhóm. KHÔNG sửa giá trị cũ, chỉ thêm mới ở cuối file.
 */
public final class Constants {
    private Constants() {
    }

    // Role
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_EMPLOYEE = "EMPLOYEE";

    // WorkLog.status
    public static final String WL_STATUS_CHECKED_IN = "CHECKED_IN"; // đã check-in, chưa check-out
    public static final String WL_STATUS_COMPLETED = "COMPLETED";   // đủ check-in + check-out
    public static final String WL_STATUS_INCOMPLETE = "INCOMPLETE"; // ngày cũ bị thiếu check-out

    // Salary.status
    public static final String SAL_STATUS_PENDING = "PENDING";
    public static final String SAL_STATUS_PAID = "PAID";

    // Quy tắc tính lương
    public static final double STANDARD_HOURS_PER_MONTH = 160.0; // vượt mức này = overtime
    public static final double OVERTIME_RATE = 1.5;              // hệ số lương OT

    // Định dạng ngày giờ lưu trong DB
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";

    // Intent extras
    public static final String EXTRA_EMPLOYEE_ID = "employeeId";
}
