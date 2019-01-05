package com.mywings.patients;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONArray;

public class ReportDetailsActivity extends AppCompatActivity implements OnSensorDataListener {


    ProgressDialogUtil progressDialogUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_details);
        progressDialogUtil = new ProgressDialogUtil(this);
    }

    @Override
    public void onSensorDataSuccess(JSONArray result) {
        progressDialogUtil.hide();
        if (null != result) {

        }
    }

    private void initGetSensors() {
        progressDialogUtil.show();
        GetSensorData getSensorData = new GetSensorData();
        getSensorData.setOnSensorDataListener(this);
    }
}
