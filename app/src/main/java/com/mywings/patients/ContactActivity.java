package com.mywings.patients;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mywings.patients.emergencycontact.EmergencyContactAsync;
import com.mywings.patients.emergencycontact.OnEmergencyContactListener;

import org.json.JSONException;
import org.json.JSONObject;

public class ContactActivity extends AppCompatActivity implements OnEmergencyContactListener {


    private ProgressDialogUtil progressDialogUtil;

    private EditText txtPrimary;
    private EditText txtSecondary;
    private EditText txtSecondary0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        progressDialogUtil = new ProgressDialogUtil(this);

        txtPrimary = findViewById(R.id.txtPrimaryNumber);
        txtSecondary = findViewById(R.id.txtSecondaryNumber0);
        txtSecondary0 = findViewById(R.id.txtSecondaryNumber00);

        findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validate()) {
                    try {
                        init();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(ContactActivity.this, "Enter contact number", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private boolean validate() {
        if (txtPrimary.getText().toString().isEmpty() && txtSecondary.getText().toString().isEmpty() && txtSecondary0.getText().toString().isEmpty()) {
            return false;
        }
        return true;
    }


    private void init() throws JSONException {
        progressDialogUtil.show();
        EmergencyContactAsync emergencyContactAsync = new EmergencyContactAsync();
        JSONObject request = new JSONObject();
        JSONObject param = new JSONObject();
        param.put("first", txtPrimary.getText().toString());
        param.put("first0", txtSecondary.getText().toString());
        param.put("first00", txtSecondary0.getText().toString());
        request.put("request", param);
        emergencyContactAsync.setOnEmergencyContactListener(this, request);
    }


    @Override
    public void onEmergencySuccess(String result) {

        progressDialogUtil.hide();

    }
}
