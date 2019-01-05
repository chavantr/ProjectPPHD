package com.mywings.patients;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PatientActivity extends AppCompatActivity implements OnSensorDataListener {

    private RecyclerView lstPatients;
    ProgressDialogUtil progressDialogUtil;
    private PatientAdapter patientAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        progressDialogUtil = new ProgressDialogUtil(this);

        lstPatients = findViewById(R.id.lstPatients);
        lstPatients.setLayoutManager(new LinearLayoutManager(this));

        getPatients();


    }

    private void getPatients() {
        progressDialogUtil.show();
        GetUsers getUsers = new GetUsers();
        getUsers.setOnSensorDataListener(this);
    }

    @Override
    public void onSensorDataSuccess(JSONArray results) {
        progressDialogUtil.hide();
        if (null != results && results.length() > 0) {
            List<User> lstUsers = new ArrayList<>();
            for (int i = 0; i < results.length(); i++) {
                User user = new User();
                try {
                    JSONObject result = results.getJSONObject(i);
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
                    lstUsers.add(user);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            UserDataHolder.getInstance().setUsers(lstUsers);
            patientAdapter = new PatientAdapter(lstUsers);
            lstPatients.setAdapter(patientAdapter);
        }
    }
}
