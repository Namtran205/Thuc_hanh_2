package com.example.employeemanager.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Lưu thông tin đăng nhập bằng SharedPreferences.
 * - Session: tài khoản đang đăng nhập (xoá khi logout)
 * - Remember me: username/password đã lưu (giữ lại khi logout)
 */
public class SessionManager {
    private static final String PREF_NAME = "EmployeeManagerPrefs";

    private static final String KEY_LOGGED_IN = "isLoggedIn";
    private static final String KEY_ACCOUNT_ID = "accountId";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_ROLE = "role";
    private static final String KEY_EMPLOYEE_ID = "employeeId";

    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_SAVED_USERNAME = "savedUsername";
    private static final String KEY_SAVED_PASSWORD = "savedPassword";

    private final SharedPreferences prefs;

    public SessionManager(Context context) {
        prefs = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    /** employeeId = -1 nếu là Admin */
    public void createSession(int accountId, String username, String role, int employeeId) {
        prefs.edit()
                .putBoolean(KEY_LOGGED_IN, true)
                .putInt(KEY_ACCOUNT_ID, accountId)
                .putString(KEY_USERNAME, username)
                .putString(KEY_ROLE, role)
                .putInt(KEY_EMPLOYEE_ID, employeeId)
                .apply();
    }

    public boolean isLoggedIn() { return prefs.getBoolean(KEY_LOGGED_IN, false); }
    public int getAccountId() { return prefs.getInt(KEY_ACCOUNT_ID, -1); }
    public String getUsername() { return prefs.getString(KEY_USERNAME, ""); }
    public String getRole() { return prefs.getString(KEY_ROLE, ""); }
    public int getEmployeeId() { return prefs.getInt(KEY_EMPLOYEE_ID, -1); }
    public boolean isAdmin() { return Constants.ROLE_ADMIN.equals(getRole()); }

    public void saveRemember(boolean remember, String username, String password) {
        SharedPreferences.Editor e = prefs.edit().putBoolean(KEY_REMEMBER, remember);
        if (remember) {
            e.putString(KEY_SAVED_USERNAME, username).putString(KEY_SAVED_PASSWORD, password);
        } else {
            e.remove(KEY_SAVED_USERNAME).remove(KEY_SAVED_PASSWORD);
        }
        e.apply();
    }

    public boolean isRemember() { return prefs.getBoolean(KEY_REMEMBER, false); }
    public String getSavedUsername() { return prefs.getString(KEY_SAVED_USERNAME, ""); }
    public String getSavedPassword() { return prefs.getString(KEY_SAVED_PASSWORD, ""); }

    public void logout() {
        prefs.edit()
                .remove(KEY_LOGGED_IN)
                .remove(KEY_ACCOUNT_ID)
                .remove(KEY_USERNAME)
                .remove(KEY_ROLE)
                .remove(KEY_EMPLOYEE_ID)
                .apply();
    }
}
