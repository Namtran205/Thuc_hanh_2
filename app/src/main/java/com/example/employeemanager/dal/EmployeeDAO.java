package com.example.employeemanager.dal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.employeemanager.models.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * [Người A – feature/auth-employee] sở hữu file này.
 * B và C được GỌI các hàm get* có sẵn, nhưng KHÔNG sửa file này.
 */
public class EmployeeDAO {
    private final DBHelper dbHelper;

    public EmployeeDAO(Context context) {
        dbHelper = DBHelper.getInstance(context);
    }

    public Employee getById(int id) {
        return getOne(DBHelper.EMP_ID + "=?", String.valueOf(id));
    }

    public Employee getByAccountId(int accountId) {
        return getOne(DBHelper.EMP_ACCOUNT_ID + "=?", String.valueOf(accountId));
    }

    public List<Employee> getAll() {
        List<Employee> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try (Cursor c = db.query(DBHelper.TB_EMPLOYEE, null, null, null, null, null,
                DBHelper.EMP_CODE + " ASC")) {
            while (c.moveToNext()) list.add(fromCursor(c));
        }
        return list;
    }

    private Employee getOne(String where, String arg) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try (Cursor c = db.query(DBHelper.TB_EMPLOYEE, null, where, new String[]{arg},
                null, null, null)) {
            return c.moveToFirst() ? fromCursor(c) : null;
        }
    }

    public Employee fromCursor(Cursor c) {
        return new Employee(
                c.getInt(c.getColumnIndexOrThrow(DBHelper.EMP_ID)),
                c.getString(c.getColumnIndexOrThrow(DBHelper.EMP_CODE)),
                c.getString(c.getColumnIndexOrThrow(DBHelper.EMP_FULLNAME)),
                c.getString(c.getColumnIndexOrThrow(DBHelper.EMP_EMAIL)),
                c.getString(c.getColumnIndexOrThrow(DBHelper.EMP_PHONE)),
                c.getDouble(c.getColumnIndexOrThrow(DBHelper.EMP_HOURLY_RATE)),
                c.getInt(c.getColumnIndexOrThrow(DBHelper.EMP_ACCOUNT_ID)));
    }

    // TODO (A):
    //  long insert(Employee e)                 -> -1 nếu trùng code
    //  int update(Employee e)
    //  int delete(int id)                      -> nhớ xoá luôn Account của nhân viên
    //  List<Employee> search(String keyword)   -> LIKE theo code / fullname / email
    //  boolean isCodeExists(String code, int excludeId)
}
