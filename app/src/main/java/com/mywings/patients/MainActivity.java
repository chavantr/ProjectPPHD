package com.mywings.patients;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String CHK_PRESSURE = "chkPressure";
    public static final String CHK_SUGAR_RANDOM = "chkSugarRandom";
    public static final String CHK_CHOLESTROL = "chkCholestrol";
    public static final String CHK_STRESS = "chkStress";
    public static final String CHK_ECG = "chkECG";
    public static final String CHK_BMI = "chkBMI";
    public static final String CHK_HEMOGRAM = "chkHemogram";
    private CheckBox chkHemogram;
    private CheckBox chkBMI;
    private CheckBox chkECG;
    private CheckBox chkStress;
    private CheckBox chkCholestrol;
    private CheckBox chkSugarRandom;
    private CheckBox chkPressure;
    private String[] strPopUpData = {"ANN", "Fuzzy", "Linear Regression", "NB", "QLearning", "Random Forest"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        if (UserDataHolder.getInstance().isAdmin()) {
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.nav_camera).setVisible(false);
            menu.findItem(R.id.nav_questions).setVisible(false);
            // menu.findItem(R.id.nav_view).setVisible(false);
        }

        View view = navigationView.getHeaderView(0);

        TextView lblName = view.findViewById(R.id.lblName);

        lblName.setText(UserDataHolder.getInstance().getUser().getName());

        TextView lblTextView = view.findViewById(R.id.textView);

        lblTextView.setText(UserDataHolder.getInstance().getUser().getEmailId());


        chkHemogram = findViewById(R.id.chkHemogram);
        chkBMI = findViewById(R.id.chkBMI);
        chkECG = findViewById(R.id.chkECG);
        chkStress = findViewById(R.id.chkStress);
        chkCholestrol = findViewById(R.id.chkCholestrol);
        chkSugarRandom = findViewById(R.id.chkSugarRandom);
        chkPressure = findViewById(R.id.chkPressure);

        findViewById(R.id.btnRead).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(MainActivity.this, ReportDetailsActivity.class);
                //startActivity(intent);
                startActivity("");
            }
        });
    }

    private void startActivity(String type) {
        Intent intent = new Intent(MainActivity.this, ReportDetailsActivity.class);
        if (chkPressure.isChecked()) {

            intent.putExtra("1", chkPressure.getText());
            intent.putExtra(CHK_PRESSURE, true);
        }
        if (chkSugarRandom.isChecked()) {
            intent.putExtra("2", chkSugarRandom.getText());
            intent.putExtra(CHK_SUGAR_RANDOM, true);
        }
        if (chkCholestrol.isChecked()) {
            intent.putExtra("3", chkCholestrol.getText());
            intent.putExtra(CHK_CHOLESTROL, true);
        }
        if (chkStress.isChecked()) {
            intent.putExtra("4", chkStress.getText());
            intent.putExtra(CHK_STRESS, true);
        }
        if (chkECG.isChecked()) {
            intent.putExtra("5", chkECG.getText());
            intent.putExtra(CHK_ECG, true);
        }
        if (chkBMI.isChecked()) {
            intent.putExtra("6", chkBMI.getText());
            intent.putExtra(CHK_BMI, true);
        }
        if (chkHemogram.isChecked()) {
            intent.putExtra("7", chkHemogram.getText());
            intent.putExtra(CHK_HEMOGRAM, true);
        }
        intent.putExtra("type", type);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent intent = new Intent(MainActivity.this, UpdateProfileUserActivity.class);
            startActivity(intent);
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }

        if (id == R.id.nav_questions) {
            Intent intent = new Intent(MainActivity.this, QuestionariesActivity.class);
            startActivity(intent);
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void typeConfirmation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(
                MainActivity.this);
        builder.setTitle(R.string.select).setIcon(
                getResources().getDrawable(R.mipmap.ic_launcher));
        builder.setSingleChoiceItems(strPopUpData, 0,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String type = strPopUpData[which];
                        startActivity(type);
                        dialog.dismiss();
                    }
                });
        Dialog dialog = builder.create();
        dialog.show();
    }
}
