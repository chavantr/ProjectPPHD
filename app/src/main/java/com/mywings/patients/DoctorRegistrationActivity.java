package com.mywings.patients;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class DoctorRegistrationActivity extends AppCompatActivity implements OnRegistrationListener {

    private EditText txtName;
    private EditText txtDegree;
    private EditText txtClinicName;
    private EditText txtAddress;
    private EditText txtCity;
    private EditText txtPhone;
    private EditText txtEmail;
    private CheckBox chkMorning;
    private EditText txtStartTimeMor;
    private EditText txtEndTimeMor;
    private CheckBox chkMorningEve;
    private EditText txtStartTimeEve;
    private EditText txtEndTimeEve;
    private EditText txtUserName;
    private EditText txtPassword;
    private Button btnRegister;
    private ProgressDialogUtil progressDialogUtil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_registration);
        txtName = findViewById(R.id.txtName);
        txtDegree = findViewById(R.id.txtDegree);
        txtClinicName = findViewById(R.id.txtClinicName);
        txtAddress = findViewById(R.id.txtAddress);
        txtCity = findViewById(R.id.txtCity);
        txtPhone = findViewById(R.id.txtPhone);
        txtEmail = findViewById(R.id.txtEmail);
        chkMorning = findViewById(R.id.chkMorning);
        txtStartTimeMor = findViewById(R.id.txtStartTimeMor);
        txtEndTimeMor = findViewById(R.id.txtEndTimeMor);
        chkMorningEve = findViewById(R.id.chkMorningEve);
        txtStartTimeEve = findViewById(R.id.txtStartTimeEve);
        txtEndTimeEve = findViewById(R.id.txtEndTimeEve);
        txtUserName = findViewById(R.id.txtUserName);
        txtPassword = findViewById(R.id.txtPassword);
        btnRegister = findViewById(R.id.btnRegister);

        progressDialogUtil = new ProgressDialogUtil(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validate()) {

                    initRegister();

                } else {
                    Toast.makeText(DoctorRegistrationActivity.this, "All fields required.", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private boolean validate() {
        if (txtName.getText().toString().isEmpty() ||
                txtDegree.getText().toString().isEmpty() ||
                txtClinicName.getText().toString().isEmpty() ||
                txtAddress.getText().toString().isEmpty() ||
                txtCity.getText().toString().isEmpty() ||
                txtPhone.getText().toString().isEmpty() ||
                txtEmail.getText().toString().isEmpty() ||
                chkMorning.getText().toString().isEmpty() ||
                txtStartTimeMor.getText().toString().isEmpty() ||
                txtEndTimeMor.getText().toString().isEmpty() ||
                chkMorningEve.getText().toString().isEmpty() ||
                txtStartTimeEve.getText().toString().isEmpty() ||
                txtEndTimeEve.getText().toString().isEmpty() ||
                btnRegister.getText().toString().isEmpty()) {
            return false;
        }
        return true;
    }

    private void initRegister() {
        progressDialogUtil.show();
        JSONObject jRequest = new JSONObject();
        JSONObject params = new JSONObject();
        try {
            params.put("Name_of_Doctor", txtName.getText().toString());
            params.put("Degree", txtDegree.getText().toString());
            params.put("Clinic _Name", txtClinicName.getText().toString());
            params.put("Address", txtAddress.getText().toString());
            params.put("City", txtCity.getText().toString());
            params.put("Mob_No", txtPhone.getText().toString());
            params.put("Email_Id", txtEmail.getText().toString());
            params.put("Morning_OPD", chkMorning.isChecked());
            params.put("Start_Time", txtStartTimeMor.getText().toString());
            params.put("End_Time", txtEndTimeMor.getText().toString());
            params.put("Evening_OPD", chkMorningEve.isChecked());
            params.put("Eve_Start_Time", txtStartTimeEve.getText().toString());
            params.put("Eve_End_Time", txtEndTimeEve.getText().toString());
            params.put("Username", txtUserName.getText().toString());
            params.put("Password", txtPassword.getText().toString());
            params.put("Extra3", "");
            params.put("Extra4", "");
            params.put("Extra5", "");
            jRequest.put("register", params);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        DoctorRegistrationAsync doctorLoginAsync = new DoctorRegistrationAsync();
        doctorLoginAsync.setOnRegistrationListener(this, jRequest);


    }

    @Override
    public void onSuccess(JSONObject success) {
        progressDialogUtil.hide();
        if (null != success && success.toString().contains("1")) {
            Snackbar.make(btnRegister, "Registration completed.", Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            }).show();
        }
    }
}
