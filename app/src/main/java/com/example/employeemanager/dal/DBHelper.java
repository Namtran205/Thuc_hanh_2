package com.example.employeemanager.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.employeemanager.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * FILE DÙNG CHUNG – chỉ người dựng khung sửa. Các thành viên chỉ dùng hằng số tên bảng/cột ở đây.
 * Nếu bắt buộc đổi schema: tăng DB_VERSION + báo cả nhóm (gỡ app/clear data để tạo lại DB).
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "employee_manager.db";
    public static final int DB_VERSION = 1;

    // ---------- Account ----------
    public static final String TB_ACCOUNT = "Account";
    public static final String ACC_ID = "id";
    public static final String ACC_USERNAME = "username";
    public static final String ACC_PASSWORD = "password";
    public static final String ACC_ROLE = "role";

    // ---------- Employee ----------
    public static final String TB_EMPLOYEE = "Employee";
    public static final String EMP_ID = "id";
    public static final String EMP_CODE = "code";
    public static final String EMP_FULLNAME = "fullname";
    public static final String EMP_EMAIL = "email";
    public static final String EMP_PHONE = "phone";
    public static final String EMP_HOURLY_RATE = "hourlyRate";
    public static final String EMP_ACCOUNT_ID = "accountId";

    // ---------- WorkLog ----------
    public static final String TB_WORKLOG = "WorkLog";
    public static final String WL_ID = "id";
    public static final String WL_EMPLOYEE_ID = "employeeId";
    public static final String WL_WORK_DATE = "workDate";       // yyyy-MM-dd
    public static final String WL_CHECK_IN = "checkInTime";     // HH:mm:ss
    public static final String WL_CHECK_OUT = "checkOutTime";   // HH:mm:ss (NULL nếu chưa check-out)
    public static final String WL_TOTAL_HOURS = "totalHours";
    public static final String WL_STATUS = "status";

    // ---------- Salary ----------
    public static final String TB_SALARY = "Salary";
    public static final String SAL_ID = "id";
    public static final String SAL_EMPLOYEE_ID = "employeeId";
    public static final String SAL_MONTH = "month";
    public static final String SAL_YEAR = "year";
    public static final String SAL_TOTAL_HOURS = "totalHours";
    public static final String SAL_OVERTIME_HOURS = "overtimeHours";
    public static final String SAL_TOTAL_SALARY = "totalSalary";
    public static final String SAL_STATUS = "status";

    private static DBHelper instance;

    public static synchronized DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context.getApplicationContext());
        }
        return instance;
    }

    private DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TB_ACCOUNT + " ("
                + ACC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ACC_USERNAME + " TEXT NOT NULL UNIQUE, "
                + ACC_PASSWORD + " TEXT NOT NULL, "
                + ACC_ROLE + " TEXT NOT NULL)");

        db.execSQL("CREATE TABLE " + TB_EMPLOYEE + " ("
                + EMP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + EMP_CODE + " TEXT NOT NULL UNIQUE, "
                + EMP_FULLNAME + " TEXT NOT NULL, "
                + EMP_EMAIL + " TEXT, "
                + EMP_PHONE + " TEXT, "
                + EMP_HOURLY_RATE + " REAL NOT NULL DEFAULT 0, "
                + EMP_ACCOUNT_ID + " INTEGER, "
                + "FOREIGN KEY(" + EMP_ACCOUNT_ID + ") REFERENCES " + TB_ACCOUNT + "(" + ACC_ID + ") ON DELETE SET NULL)");

        // UNIQUE(employeeId, workDate): mỗi nhân viên chỉ có 1 bản ghi / ngày -> chống check-in trùng ở mức DB
        db.execSQL("CREATE TABLE " + TB_WORKLOG + " ("
                + WL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + WL_EMPLOYEE_ID + " INTEGER NOT NULL, "
                + WL_WORK_DATE + " TEXT NOT NULL, "
                + WL_CHECK_IN + " TEXT, "
                + WL_CHECK_OUT + " TEXT, "
                + WL_TOTAL_HOURS + " REAL NOT NULL DEFAULT 0, "
                + WL_STATUS + " TEXT NOT NULL, "
                + "UNIQUE(" + WL_EMPLOYEE_ID + ", " + WL_WORK_DATE + "), "
                + "FOREIGN KEY(" + WL_EMPLOYEE_ID + ") REFERENCES " + TB_EMPLOYEE + "(" + EMP_ID + ") ON DELETE CASCADE)");

        // UNIQUE(employeeId, month, year): mỗi nhân viên 1 bảng lương / tháng
        db.execSQL("CREATE TABLE " + TB_SALARY + " ("
                + SAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SAL_EMPLOYEE_ID + " INTEGER NOT NULL, "
                + SAL_MONTH + " INTEGER NOT NULL, "
                + SAL_YEAR + " INTEGER NOT NULL, "
                + SAL_TOTAL_HOURS + " REAL NOT NULL DEFAULT 0, "
                + SAL_OVERTIME_HOURS + " REAL NOT NULL DEFAULT 0, "
                + SAL_TOTAL_SALARY + " REAL NOT NULL DEFAULT 0, "
                + SAL_STATUS + " TEXT NOT NULL, "
                + "UNIQUE(" + SAL_EMPLOYEE_ID + ", " + SAL_MONTH + ", " + SAL_YEAR + "), "
                + "FOREIGN KEY(" + SAL_EMPLOYEE_ID + ") REFERENCES " + TB_EMPLOYEE + "(" + EMP_ID + ") ON DELETE CASCADE)");

        seedData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_SALARY);
        db.execSQL("DROP TABLE IF EXISTS " + TB_WORKLOG);
        db.execSQL("DROP TABLE IF EXISTS " + TB_EMPLOYEE);
        db.execSQL("DROP TABLE IF EXISTS " + TB_ACCOUNT);
        onCreate(db);
    }

    // ================= DỮ LIỆU MẪU (để cả nhóm test ngay) =================
    // admin/admin (ADMIN) | emp01/123 -> NV001 | emp02/123 -> NV002
    // NV001 có sẵn chấm công tháng trước (đủ ngày thường, 9.5h/ngày -> có OT) để test tính lương
    private void seedData(SQLiteDatabase db) {
        insertAccount(db, "admin", "admin", Constants.ROLE_ADMIN);

        long acc1 = insertAccount(db, "emp01", "123", Constants.ROLE_EMPLOYEE);
        long emp1 = insertEmployee(db, "NV001", "Nguyen Van A", "a@company.com", "0901111111", 50000, acc1);

        long acc2 = insertAccount(db, "emp02", "123", Constants.ROLE_EMPLOYEE);
        insertEmployee(db, "NV002", "Tran Thi B", "b@company.com", "0902222222", 45000, acc2);

        SimpleDateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT, Locale.getDefault());
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        int maxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        String lastDate = null;
        for (int d = 1; d <= maxDay; d++) {
            c.set(Calendar.DAY_OF_MONTH, d);
            int dow = c.get(Calendar.DAY_OF_WEEK);
            if (dow == Calendar.SATURDAY || dow == Calendar.SUNDAY) continue;
            lastDate = df.format(c.getTime());
            insertWorkLog(db, emp1, lastDate, "08:00:00", "17:30:00", 9.5, Constants.WL_STATUS_COMPLETED);
        }
        // Ngày làm cuối tháng: bản ghi thiếu check-out để test INCOMPLETE
        if (lastDate != null) {
            ContentValues v = new ContentValues();
            v.putNull(WL_CHECK_OUT);
            v.put(WL_TOTAL_HOURS, 0);
            v.put(WL_STATUS, Constants.WL_STATUS_INCOMPLETE);
            db.update(TB_WORKLOG, v, WL_EMPLOYEE_ID + "=? AND " + WL_WORK_DATE + "=?",
                    new String[]{String.valueOf(emp1), lastDate});
        }
    }

    private long insertAccount(SQLiteDatabase db, String u, String p, String role) {
        ContentValues v = new ContentValues();
        v.put(ACC_USERNAME, u);
        v.put(ACC_PASSWORD, p);
        v.put(ACC_ROLE, role);
        return db.insert(TB_ACCOUNT, null, v);
    }

    private long insertEmployee(SQLiteDatabase db, String code, String name, String email,
                                String phone, double rate, long accountId) {
        ContentValues v = new ContentValues();
        v.put(EMP_CODE, code);
        v.put(EMP_FULLNAME, name);
        v.put(EMP_EMAIL, email);
        v.put(EMP_PHONE, phone);
        v.put(EMP_HOURLY_RATE, rate);
        v.put(EMP_ACCOUNT_ID, accountId);
        return db.insert(TB_EMPLOYEE, null, v);
    }

    private void insertWorkLog(SQLiteDatabase db, long empId, String date, String in, String out,
                               double hours, String status) {
        ContentValues v = new ContentValues();
        v.put(WL_EMPLOYEE_ID, empId);
        v.put(WL_WORK_DATE, date);
        v.put(WL_CHECK_IN, in);
        if (out == null) v.putNull(WL_CHECK_OUT); else v.put(WL_CHECK_OUT, out);
        v.put(WL_TOTAL_HOURS, hours);
        v.put(WL_STATUS, status);
        db.insert(TB_WORKLOG, null, v);
    }
}
