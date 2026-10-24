package com.example.employeemanager.dal;

import android.content.Context;

/**
 * [Người B – feature/attendance] sở hữu file này.
 */
public class WorkLogDAO {
    private final DBHelper dbHelper;

    public WorkLogDAO(Context context) {
        dbHelper = DBHelper.getInstance(context);
    }

    // TODO (B):
    //  WorkLog getByDate(int employeeId, String workDate)
    //  long checkIn(int employeeId, String workDate, String time)
    //      -> chặn nếu đã có bản ghi hôm nay (DB đã có UNIQUE(employeeId, workDate))
    //  int checkOut(int workLogId, String time, double totalHours)
    //      -> chặn nếu chưa check-in / đã check-out / giờ out <= giờ in
    //  List<WorkLog> getByMonth(int employeeId, int month, int year)   (workDate LIKE 'yyyy-MM-%')
    //  double getTotalHoursByMonth(int employeeId, int month, int year) (chỉ status COMPLETED)
    //  void markIncompleteBefore(String today)
    //      -> các bản ghi CHECKED_IN của ngày cũ chuyển thành INCOMPLETE
}
