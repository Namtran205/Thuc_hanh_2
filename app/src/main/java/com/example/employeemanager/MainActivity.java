package com.example.employeemanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.employeemanager.activities.AdminHomeActivity;
import com.example.employeemanager.activities.EmployeeHomeActivity;
import com.example.employeemanager.activities.LoginActivity;
import com.example.employeemanager.utils.Constants;
import com.example.employeemanager.utils.SessionManager;

/**
 * Màn khởi động: đã đăng nhập (SharedPreferences) -> vào Home theo role, chưa -> Login.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SessionManager session = new SessionManager(this);
        Intent next = session.isLoggedIn()
                ? homeIntent(this, session.getRole())
                : new Intent(this, LoginActivity.class);
        startActivity(next);
        finish();
    }

    public static Intent homeIntent(Context context, String role) {
        return new Intent(context, Constants.ROLE_ADMIN.equals(role)
                ? AdminHomeActivity.class
                : EmployeeHomeActivity.class);
    }

    public static void logout(Activity activity) {
        new SessionManager(activity).logout();
        Intent i = new Intent(activity, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(i);
        activity.finish();
    }
}
