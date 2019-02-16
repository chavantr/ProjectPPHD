package com.mywings.patients.prescription;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.mywings.patients.OnUpdateUserListener;
import com.mywings.patients.ProgressDialogUtil;
import com.mywings.patients.R;
import com.mywings.patients.UserDataHolder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class AddPrescriptionActivity extends AppCompatActivity implements OnUpdateUserListener {

    private EditText txtName;
    private EditText txtQty;
    private CheckBox chkMor;
    private CheckBox chkNoon;
    private CheckBox chkEve;
    private ProgressDialogUtil progressDialogUtil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prescription);
        txtName = findViewById(R.id.txtName);
        txtQty = findViewById(R.id.txtQty);
        chkMor = findViewById(R.id.chkMorning);
        chkNoon = findViewById(R.id.chkNoon);
        chkEve = findViewById(R.id.chkEve);

        progressDialogUtil = new ProgressDialogUtil(this);

        findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    initPres();
                } else {
                    Snackbar.make(txtName, "enter value", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean validate() {

        if (TextUtils.isEmpty(txtName.getText()) && TextUtils.isEmpty(txtQty.getText()) && (!chkMor.isChecked() && !chkNoon.isChecked() && !chkEve.isChecked())) {
            return false;
        }

        return true;
    }

    private void initPres() {
        try {
            progressDialogUtil.show();
            JSONObject jPref = new JSONObject();
            JSONObject param = new JSONObject();
            param.put("Descs", txtName.getText());
            param.put("Quty", txtQty.getText());
            param.put("CDate", Calendar.getInstance().getTimeInMillis());
            param.put("Morning", chkMor.isChecked());
            param.put("Noon", chkNoon.isChecked());
            param.put("Eve", chkEve.isChecked());
            if (UserDataHolder.getInstance().isAdmin()) {
                param.put("UId", UserDataHolder.getInstance().getSelectedUser().getId());
            } else {
                param.put("UId", UserDataHolder.getInstance().getUser().getId());
            }
            jPref.put("pres", param);
            AddPrescription pres = new AddPrescription();
            pres.setOnUpdateUserListener(this, jPref);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpdateUser(String result) {
        progressDialogUtil.hide();
        if (!TextUtils.isEmpty(result) && result.contains("1")) {
            Snackbar.make(txtName, "Prescription saved", Snackbar.LENGTH_LONG).setAction("OK", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            }).show();

        }
    }
}
