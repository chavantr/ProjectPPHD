package com.mywings.patients;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class QuestionariesActivity extends AppCompatActivity implements OnStressListener {


    private RadioButton rdbFirstYes;
    private RadioButton rdbsecondtYes;
    private RadioButton rdbThirdYes;
    private RadioButton rdbFourthYes;
    private RadioButton rdbFifthYes;
    private RadioButton rdbSixthYes;
    private RadioButton rdbSeventhYes;
    private RadioButton rdbEighthYes;
    private RadioButton rdbNinethYes;
    private RadioButton rdbThenthYes;

    private ProgressDialogUtil progressDialogUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionaries);
        rdbFirstYes = findViewById(R.id.firstYes);
        rdbsecondtYes = findViewById(R.id.secondYes);
        rdbThirdYes = findViewById(R.id.thirdYes);
        rdbFourthYes = findViewById(R.id.fourthYes);
        rdbFifthYes = findViewById(R.id.fifthYes);
        rdbSixthYes = findViewById(R.id.sixthYes);
        rdbSeventhYes = findViewById(R.id.seventhYes);
        rdbEighthYes = findViewById(R.id.eighthYes);
        rdbNinethYes = findViewById(R.id.ninethYes);
        rdbThenthYes = findViewById(R.id.tenthYes);

        progressDialogUtil = new ProgressDialogUtil(this);

        findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int i = 0;

                if (rdbFirstYes.isChecked()) {
                    i = i + 1;
                }


                if (rdbsecondtYes.isChecked()) {
                    i = i + 1;
                }

                if (rdbThirdYes.isChecked()) {
                    i = i + 1;
                }

                if (rdbFourthYes.isChecked()) {
                    i = i + 1;
                }

                if (rdbFifthYes.isChecked()) {
                    i = i + 1;
                }

                if (rdbSixthYes.isChecked()) {
                    i = i + 1;
                }

                if (rdbSeventhYes.isChecked()) {
                    i = i + 1;
                }

                if (rdbEighthYes.isChecked()) {
                    i = i + 1;
                }

                if (rdbNinethYes.isChecked()) {
                    i = i + 1;
                }

                if (rdbThenthYes.isChecked()) {
                    i = i + 1;
                }

                progressDialogUtil.show();
                UpdateStress updateStress = new UpdateStress();
                int id = UserDataHolder.getInstance().getUser().getId();
                updateStress.setOnStressListener(QuestionariesActivity.this, i + "#" + id);


            }
        });

    }

    @Override
    public void onStressSuccess(String inserted) {
        progressDialogUtil.hide();
        Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();
        finish();
    }
}
