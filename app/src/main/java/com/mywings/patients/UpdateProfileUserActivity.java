package com.mywings.patients;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class UpdateProfileUserActivity extends AppCompatActivity {


    private EditText txtName;
    private EditText txtPhone;
    private EditText txtEmail;
    private EditText txtUserName;
    private EditText txtPassword;
    private EditText txtLocalAddress;
    private EditText txtDob;
    private Spinner spnGender;
    private EditText txtHeight;
    private EditText txtWeight;
    private EditText txtAge;
    private Button btnRegister;
    private ProgressDialogUtil progressDialogUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile_user);

        txtName = findViewById(R.id.txtName);
        txtPhone = findViewById(R.id.txtPhone);
        txtEmail = findViewById(R.id.txtEmail);
        txtUserName = findViewById(R.id.txtUserName);
        txtPassword = findViewById(R.id.txtPassword);
        txtLocalAddress = findViewById(R.id.txtLocalAddress);
        txtDob = findViewById(R.id.txtDob);
        txtHeight = findViewById(R.id.txtHeight);
        txtWeight = findViewById(R.id.txtWeight);
        txtAge = findViewById(R.id.txtWeight);
        spnGender = findViewById(R.id.spnGender);
        btnRegister = findViewById(R.id.btnRegister);

        progressDialogUtil = new ProgressDialogUtil(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    JSONObject jRequest = new JSONObject();
                    JSONObject params = new JSONObject();
                    try {
                        params.put("Full_Name", txtName.getText().toString());
                        params.put("Mobile", txtPhone.getText().toString());
                        params.put("Email_Id", txtEmail.getText().toString());
                        params.put("Username", txtUserName.getText().toString());
                        params.put("Password", txtPassword.getText().toString());
                        params.put("Gender", spnGender.getSelectedItem().toString());
                        params.put("Address", txtLocalAddress.getText().toString());
                        params.put("DOB", txtDob.getText().toString());
                        params.put("Height", txtHeight.getText().toString());
                        params.put("Weight", txtWeight.getText().toString());
                        params.put("Age", txtAge.getText().toString());
                        jRequest.put("request", params);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    progressDialogUtil.show();
                    //PatientsRegistrationAsync patientsRegistrationAsync = new PatientsRegistrationAsync();
                    //patientsRegistrationAsync.setOnRegistrationListener(UpdateProfileUserActivity.this, jRequest);
                } else {
                    Toast.makeText(UpdateProfileUserActivity.this, "All fields required.", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private boolean validate() {
        if (txtName.getText().toString().isEmpty() ||
                txtPhone.getText().toString().isEmpty() ||
                txtEmail.getText().toString().isEmpty() ||
                txtUserName.getText().toString().isEmpty() ||
                txtPassword.getText().toString().isEmpty() ||
                txtLocalAddress.getText().toString().isEmpty() ||
                txtDob.getText().toString().isEmpty() ||
                txtHeight.getText().toString().isEmpty() ||
                txtWeight.getText().toString().isEmpty() ||
                txtAge.getText().toString().isEmpty()) {
            return false;
        }
        return true;
    }
}
