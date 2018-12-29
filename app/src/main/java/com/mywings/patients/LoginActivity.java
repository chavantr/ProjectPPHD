package com.mywings.patients;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    private RadioButton rdbUser;
    private RadioButton rdbDoctor;
    private EditText txtUserName;
    private EditText txtPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        rdbUser = findViewById(R.id.rdbUser);
        rdbDoctor = findViewById(R.id.rdbDoctor);

        txtUserName = findViewById(R.id.txtUserName);
        txtPassword = findViewById(R.id.txtPassword);


        findViewById(R.id.btnForgotPassword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, FortgotActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnSignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validate()) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "All fields required.", Toast.LENGTH_LONG).show();
                }
            }
        });

        findViewById(R.id.btnSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (rdbUser.isChecked()) {
                    Intent intent = new Intent(LoginActivity.this, PatientRegistrationActivity.class);
                    startActivity(intent);
                } else if (rdbDoctor.isChecked()) {
                    Intent intent = new Intent(LoginActivity.this, DoctorRegistrationActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Select user type", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean validate() {

        if (!rdbUser.isChecked() && !rdbDoctor.isChecked()) {
            return false;
        } else if (txtUserName.getText().toString().isEmpty() && txtPassword.getText().toString().isEmpty()) {
            return false;
        }

        return true;
    }

}
