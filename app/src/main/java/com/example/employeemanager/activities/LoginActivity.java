package com.example.employeemanager.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.employeemanager.MainActivity;
import com.example.employeemanager.R;
import com.example.employeemanager.dal.AccountDAO;
import com.example.employeemanager.dal.EmployeeDAO;
import com.example.employeemanager.models.Account;
import com.example.employeemanager.models.Employee;
import com.example.employeemanager.utils.Constants;
import com.example.employeemanager.utils.SessionManager;

/**
 * [Người A – feature/auth-employee] sở hữu file này (đã chạy được, A có thể cải thiện thêm).
 */
public class LoginActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private CheckBox cbRemember;
    private SessionManager session;
    private AccountDAO accountDAO;
    private EmployeeDAO employeeDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        cbRemember = findViewById(R.id.cbRemember);
        Button btnLogin = findViewById(R.id.btnLogin);

        session = new SessionManager(this);
        accountDAO = new AccountDAO(this);
        employeeDAO = new EmployeeDAO(this);

        if (session.isRemember()) {
            etUsername.setText(session.getSavedUsername());
            etPassword.setText(session.getSavedPassword());
            cbRemember.setChecked(true);
        }

        btnLogin.setOnClickListener(v -> doLogin());
    }

    private void doLogin() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (username.isEmpty()) { etUsername.setError("Required"); return; }
        if (password.isEmpty()) { etPassword.setError("Required"); return; }

        Account acc = accountDAO.login(username, password);
        if (acc == null) {
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            return;
        }

        int employeeId = -1;
        if (Constants.ROLE_EMPLOYEE.equals(acc.getRole())) {
            Employee e = employeeDAO.getByAccountId(acc.getId());
            if (e == null) {
                Toast.makeText(this, "This account is not linked to any employee", Toast.LENGTH_SHORT).show();
                return;
            }
            employeeId = e.getId();
        }

        session.createSession(acc.getId(), acc.getUsername(), acc.getRole(), employeeId);
        session.saveRemember(cbRemember.isChecked(), username, password);

        startActivity(MainActivity.homeIntent(this, acc.getRole()));
        finish();
    }
}
