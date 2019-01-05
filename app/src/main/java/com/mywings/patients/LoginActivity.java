package com.mywings.patients;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements OnLoginListener, OnDoctorLoginListener {


    private RadioButton rdbUser;
    private RadioButton rdbDoctor;
    private EditText txtUserName;
    private EditText txtPassword;

    private ProgressDialogUtil progressDialogUtil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        rdbUser = findViewById(R.id.rdbUser);
        rdbDoctor = findViewById(R.id.rdbDoctor);

        txtUserName = findViewById(R.id.txtUserName);
        txtPassword = findViewById(R.id.txtPassword);

        progressDialogUtil = new ProgressDialogUtil(this);

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

                if (rdbUser.isChecked()) {
                    txtUserName.setText("abc");
                    txtPassword.setText("123456");

                } else if (rdbDoctor.isChecked()) {
                    txtUserName.setText("om1");
                    txtPassword.setText("123");

                }

                if (validate()) {
                    progressDialogUtil.show();

                    try {

                        if (rdbUser.isChecked()) {
                            initLoginUser();
                        } else if (rdbDoctor.isChecked()) {
                            initLoginDoctor();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    Toast.makeText(LoginActivity.this, "All fields required.", Toast.LENGTH_LONG).show();
                }
            }
        });

        findViewById(R.id.btnSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (rdbUser.isChecked()) {
                    // txtUserName.setText("abc");
                    // txtPassword.setText("123456");
                    Intent intent = new Intent(LoginActivity.this, PatientRegistrationActivity.class);
                    startActivity(intent);
                } else if (rdbDoctor.isChecked()) {
                    // txtUserName.setText("om1");
                    // txtPassword.setText("123");
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

    private void initLoginUser() throws JSONException {

        LoginAsync loginAsync = new LoginAsync();

        JSONObject jLogin = new JSONObject();

        JSONObject params = new JSONObject();

        params.put("UserName", txtUserName.getText().toString());
        params.put("Password", txtPassword.getText().toString());

        jLogin.put("login", params);

        loginAsync.setLoginListener(this, jLogin);

    }

    private void initLoginDoctor() throws JSONException {

        DoctorLoginAsync loginAsync = new DoctorLoginAsync();

        JSONObject jLogin = new JSONObject();

        JSONObject params = new JSONObject();

        params.put("UserName", txtUserName.getText().toString());
        params.put("Password", txtPassword.getText().toString());

        jLogin.put("login", params);

        loginAsync.setOnDoctorLoginListener(this, jLogin);

    }

    @Override
    public void onLoginListener(JSONObject result) {
        progressDialogUtil.hide();
        if (null != result && result.length() > 0) {

            try {
                result = result.getJSONObject("LoginUserResult");
                User user = new User();

                user.setId(result.getInt("Id"));
                user.setName(result.getString("Name"));
                user.setMobile(result.getString("Mobile"));
                user.setEmailId(result.getString("EmailId"));
                user.setUserName(result.getString("UserName"));
                user.setPassword(result.getString("Password"));
                user.setGender(result.getString("Gender"));
                user.setAddress(result.getString("Address"));
                user.setDOB(result.getString("DOB"));
                user.setHeight(result.getString("Height"));
                user.setWeight(result.getString("Weight"));
                user.setAge(result.getString("Age"));

                UserDataHolder.getInstance().setUser(user);

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

    @Override
    public void onDoctorLoginSuccess(JSONObject result) {
        progressDialogUtil.hide();
        if (null != result && result.length() > 0) {
            Doctor doctor = new Doctor();
            try {
                result = result.getJSONObject("LoginDoctorResult");
                doctor.setId(result.getInt("Id"));
                doctor.setDoctorName(result.getString("DoctorName"));
                doctor.setDegree(result.getString("Degree"));
                doctor.setClinicName(result.getString("ClinicName"));
                doctor.setAddress(result.getString("Address"));
                doctor.setCity(result.getString("City"));
                doctor.setMobNo(result.getString("MobNo"));
                doctor.setEmailId(result.getString("EmailId"));
                doctor.setMorOPD(result.getString("MorOPD"));
                doctor.setStartTimeMor(result.getString("StartTimeMor"));
                doctor.setEndTimeMor(result.getString("EndTimeMor"));
                doctor.setEveOPD(result.getString("EveOPD"));
                doctor.setStartTimeEve(result.getString("StartTimeEve"));
                doctor.setEndTimeEve(result.getString("EndTimeEve"));
                doctor.setUserName(result.getString("UserName"));
                doctor.setPassword(result.getString("Password"));
                UserDataHolder.getInstance().setDoctor(doctor);

                Intent intent = new Intent(LoginActivity.this, PatientActivity.class);
                startActivity(intent);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
