package com.example.employeemanager.dal;

import android.content.Context;

/**
 * [Người C – feature/salary] sở hữu file này.
 * Cần đọc WorkLog thì viết query thẳng ở đây (bảng DBHelper.TB_WORKLOG), KHÔNG sửa WorkLogDAO của B.
 */
public class SalaryDAO {
    private final DBHelper dbHelper;

    public SalaryDAO(Context context) {
        dbHelper = DBHelper.getInstance(context);
    }

    // TODO (C):
    //  double sumCompletedHours(int employeeId, int month, int year)
    //      SELECT SUM(totalHours) FROM WorkLog WHERE employeeId=? AND status='COMPLETED' AND workDate LIKE 'yyyy-MM-%'
    //  long upsert(Salary s)                -> UNIQUE(employeeId, month, year): đã có thì update
    //  List<Salary> getByMonth(int month, int year)        (Admin)
    //  List<Salary> getByEmployee(int employeeId)          (Employee)
    //  Salary get(int employeeId, int month, int year)
    //  int updateStatus(int salaryId, String status)       (PENDING <-> PAID)
    //  int delete(int salaryId)
}
