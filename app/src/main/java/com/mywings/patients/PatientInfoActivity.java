package com.mywings.patients;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mywings.patients.prescription.AddPrescriptionActivity;

import java.util.ArrayList;

public class PatientInfoActivity extends AppCompatActivity implements OnUpdateUserListener {


    ProgressDialogUtil progressDialogUtil;
    private LinearLayout lnrPressure;
    private LinearLayout lnrSugarRandom;
    private LinearLayout lnrCholestrol;
    private LinearLayout lnrStress;
    private LinearLayout lnrECG;
    private LinearLayout lnrBMI;
    private LinearLayout lnrHemogram;

    private TextView lblPressure;
    private TextView lblRandomSugar;
    private TextView lblCholestrol;
    private TextView lblStress;
    private TextView lblECG;
    private TextView lblBMI;
    private TextView lblHemogram;
    private ArrayList<String> lstFetchData;
    private String[] strPopUpData = {"ANN", "Fuzzy", "Linear Regression", "NB", "QLearning", "Random Forest"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);
        lnrPressure = findViewById(R.id.lnrPressure);
        lnrSugarRandom = findViewById(R.id.lnrSugarRandom);
        lnrCholestrol = findViewById(R.id.lnrCholestrol);
        lnrStress = findViewById(R.id.lnrStress);
        lnrECG = findViewById(R.id.lnrECG);
        lnrBMI = findViewById(R.id.lnrBMI);
        lnrHemogram = findViewById(R.id.lnrHemogram);

        lblPressure = findViewById(R.id.lblPressure);
        lblRandomSugar = findViewById(R.id.lblRandomSugar);
        lblCholestrol = findViewById(R.id.lblCholestrol);
        lblStress = findViewById(R.id.lblStress);
        lblECG = findViewById(R.id.lblECG);
        lblBMI = findViewById(R.id.lblBMI);
        lblHemogram = findViewById(R.id.lblHemogram);
        findViewById(R.id.btnAddPres).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientInfoActivity.this, AddPrescriptionActivity.class);
                startActivity(intent);
            }
        });
        initUpdate();
    }


    @Override
    public void onUpdateUser(String result) {
        if (!TextUtils.isEmpty(result)) {
            Intent intent = new Intent(PatientInfoActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void initUpdate() {
        UpdateUser updateUser = new UpdateUser();
        updateUser.setUpdateUserListener(this, String.valueOf(getIntent().getExtras().getInt("id")));
    }

    public void typeConfirmation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(
                PatientInfoActivity.this);
        builder.setTitle(R.string.select).setIcon(
                getResources().getDrawable(R.mipmap.ic_launcher));
        builder.setSingleChoiceItems(strPopUpData, 0,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String type = strPopUpData[which];

                        dialog.dismiss();
                    }
                });
        Dialog dialog = builder.create();
        dialog.show();
    }
}
