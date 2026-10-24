package com.example.employeemanager.dal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.employeemanager.models.Account;

/**
 * [Người A – feature/auth-employee] sở hữu file này.
 */
public class AccountDAO {
    private final DBHelper dbHelper;

    public AccountDAO(Context context) {
        dbHelper = DBHelper.getInstance(context);
    }

    /** Trả về Account nếu đúng username/password, ngược lại null */
    public Account login(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try (Cursor c = db.query(DBHelper.TB_ACCOUNT, null,
                DBHelper.ACC_USERNAME + "=? AND " + DBHelper.ACC_PASSWORD + "=?",
                new String[]{username, password}, null, null, null)) {
            return c.moveToFirst() ? fromCursor(c) : null;
        }
    }

    private Account fromCursor(Cursor c) {
        return new Account(
                c.getInt(c.getColumnIndexOrThrow(DBHelper.ACC_ID)),
                c.getString(c.getColumnIndexOrThrow(DBHelper.ACC_USERNAME)),
                c.getString(c.getColumnIndexOrThrow(DBHelper.ACC_PASSWORD)),
                c.getString(c.getColumnIndexOrThrow(DBHelper.ACC_ROLE)));
    }

    // TODO (A):
    //  long insert(Account a)              -> trả về id mới, -1 nếu trùng username
    //  boolean isUsernameExists(String u)
    //  int update(Account a)
    //  int delete(int accountId)
}
