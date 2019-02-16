package com.mywings.patients;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mywings.patients.algorithms.ANN;
import com.mywings.patients.algorithms.Fuzzy;
import com.mywings.patients.algorithms.LinierRegression;
import com.mywings.patients.algorithms.NB;
import com.mywings.patients.algorithms.QLearning;
import com.mywings.patients.algorithms.RandomForest;
import com.mywings.patients.algorithms.iHeart;
import com.mywings.patients.prescription.AddPrescriptionActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ReportDetailsActivity extends AppCompatActivity implements OnSensorDataListener, OnAlgoListener {

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
    private User user;
    private Button btnPres;
    private Button btnEmer;
    private Button btnPDF;
    private static final int EXTERNAL_REQUEST = 1000;

    private Font catFont = new Font(Font.FontFamily.TIMES_ROMAN,
            20, Font.BOLD, BaseColor.RED);

    private Font catFontf = new Font(Font.FontFamily.TIMES_ROMAN,
            25, Font.BOLD);

    private Handler handlePdfResponse;
    private TextView lblVAH;
    private TextView lblCI;
    private TextView lblCVD;

    private String[] result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_report_details);
        lnrPressure = findViewById(R.id.lnrPressure);
        lnrSugarRandom = findViewById(R.id.lnrSugarRandom);
        lnrCholestrol = findViewById(R.id.lnrCholestrol);
        lnrStress = findViewById(R.id.lnrStress);
        lnrECG = findViewById(R.id.lnrECG);
        lnrBMI = findViewById(R.id.lnrBMI);
        lnrHemogram = findViewById(R.id.lnrHemogram);
        lblVAH = findViewById(R.id.lblVAH);
        lblCI = findViewById(R.id.lblCI);
        lblCVD = findViewById(R.id.lblCVD);
        if (UserDataHolder.getInstance().isAdmin()) {
            user = UserDataHolder.getInstance().getSelectedUser();
        } else {
            user = UserDataHolder.getInstance().getUser();
        }
        lblPressure = findViewById(R.id.lblPressure);
        lblRandomSugar = findViewById(R.id.lblRandomSugar);
        lblCholestrol = findViewById(R.id.lblCholestrol);
        lblStress = findViewById(R.id.lblStress);
        lblECG = findViewById(R.id.lblECG);
        lblBMI = findViewById(R.id.lblBMI);
        lblHemogram = findViewById(R.id.lblHemogram);


        btnEmer = findViewById(R.id.btnEmer);
        btnPres = findViewById(R.id.btnAddPres);
        btnPDF = findViewById(R.id.btnPDF);

        if (UserDataHolder.getInstance().isAdmin()) {
            btnPres.setVisibility(View.VISIBLE);
            btnPDF.setVisibility(View.GONE);
            btnEmer.setVisibility(View.GONE);
        } else {
            btnPres.setVisibility(View.GONE);
            btnPDF.setVisibility(View.VISIBLE);
            btnEmer.setVisibility(View.VISIBLE);
        }

        btnPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generatePDF();
            }
        });

        btnPres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReportDetailsActivity.this, AddPrescriptionActivity.class);
                startActivity(intent);
            }
        });

        btnEmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendSMS sendSMS = new SendSMS();
                sendSMS.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:" + Constant.PHONE));
                startActivity(dialIntent);
            }
        });


        if (getIntent().hasExtra("1")) {
            lnrPressure.setVisibility(View.VISIBLE);

        }

        if (getIntent().hasExtra("2")) {
            lnrSugarRandom.setVisibility(View.VISIBLE);

        }

        if (getIntent().hasExtra("3")) {
            lnrCholestrol.setVisibility(View.VISIBLE);
        }

        if (getIntent().hasExtra("4")) {
            lnrStress.setVisibility(View.VISIBLE);
        }
        if (getIntent().hasExtra("5")) {
            lnrECG.setVisibility(View.VISIBLE);
        }
        if (getIntent().hasExtra("6")) {
            lnrBMI.setVisibility(View.VISIBLE);
        }
        if (getIntent().hasExtra("7")) {
            lnrHemogram.setVisibility(View.VISIBLE);
        }


        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    EXTERNAL_REQUEST);


        }


        progressDialogUtil = new ProgressDialogUtil(this);
        // initGetSensors();
        initGetAlgo();

        handlePdfResponse = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                document.close();

                File file = new File("mnt/sdcard/IOT/report.pdf");
                Uri path = Uri.fromFile(file);
                Intent pdfOpenintent = new Intent(Intent.ACTION_VIEW);
                pdfOpenintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                pdfOpenintent.setDataAndType(path, "application/pdf");
                try {
                    startActivity(pdfOpenintent);
                } catch (ActivityNotFoundException e) {

                }
            }
        };
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case EXTERNAL_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (!UserDataHolder.getInstance().isAdmin())
                        btnPDF.setVisibility(View.VISIBLE);
                } else {
                    btnPDF.setVisibility(View.GONE);
                }
                break;
        }
    }

    @Override
    public void onSensorDataSuccess(JSONArray result) {
        progressDialogUtil.hide();
        if (null != result && result.length() > 0) {
            lstFetchData = new ArrayList<>();
            for (int i = 0; i < result.length(); i++) {
                try {
                    JSONObject node = result.getJSONObject(i);
                    String strNode = node.getInt("Id") +
                            "," + node.getString("SystolicBp") +
                            "," + node.getString("DiastolicBp") +
                            "," + node.getString("HeartRate") +
                            "," + node.getString("TotalCholestroral") +
                            "," + node.getString("CholesterolLDL") +
                            "," + node.getString("CholesterolHDL") +
                            "," + node.getString("Stress") +
                            "," + node.getString("RandomSugar") +
                            "," + node.getString("QTInterval") +
                            "," + node.getString("PRInterval") +
                            "," + node.getString("OxySaturation") +
                            "," + node.getString("HB") +
                            "," + node.getString("TimeStamp");
                    lstFetchData.add(strNode);

                    String[] strNodes = {"", "", "", "", "", "", ""};

                    switch (getIntent().getExtras().getString("type")) {
                        case "ANN":
                            strNodes = ANN.FeedForward(lstFetchData, user.getGender()).split(",");
                            break;
                        case "Fuzzy":
                            strNodes = Fuzzy.GenerateProbability(lstFetchData, user.getGender()).split(",");
                            break;
                        case "Linear Regression":
                            strNodes = LinierRegression.Regression(lstFetchData, user.getGender()).split(",");
                            break;
                        case "NB":
                            strNodes = NB.ProcessData(lstFetchData, user.getGender()).split(",");
                            break;
                        case "QLearning":
                            strNodes = QLearning.ProcessData(lstFetchData, user.getGender()).split(",");
                            break;
                        case "Random Forest":
                            strNodes = RandomForest.GenerateTrees(lstFetchData, user.getGender()).split(",");
                            break;
                    }
                    lblPressure.setText(strNodes[0]);
                    lblRandomSugar.setText(strNodes[1]);
                    lblCholestrol.setText(strNodes[2]);
                    lblStress.setText(strNodes[3]);
                    lblECG.setText(strNodes[4]);
                    lblBMI.setText(strNodes[5]);
                    lblHemogram.setText(strNodes[6]);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initGetSensors() {
        progressDialogUtil.show();
        GetSensorData getSensorData = new GetSensorData();
        getSensorData.setOnSensorDataListener(this);
    }

    File headerFile;
    Document document;

    private void createFile(String fileName) throws IOException {
        File file = new File("mnt/sdcard/IOT");
        if (!file.exists()) {
            file.mkdirs();
        }

        headerFile = new File(file, fileName);
        if (!headerFile.exists()) {
            headerFile.createNewFile();
        }
    }


    private void generatePDF() {


        try {
            createFile("report.pdf");

            document = new Document();

            document.setMargins(5, 5, 5, 5);

            PdfWriter.getInstance(document,
                    new FileOutputStream(headerFile.getAbsoluteFile()));

            document.open();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    createImage();

                }
            }).start();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }


    }

    private void createImage() {

        try {
            InputStream ims = getAssets().open("biglogo.jpg");
            Bitmap bmp = BitmapFactory.decodeStream(ims);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            Image image = null;

            image = Image.getInstance(stream.toByteArray());

            image.setAlignment(Element.ALIGN_CENTER);


            Paragraph empty1 = new Paragraph();
            addEmptyLine(empty1, 1);
            document.add(empty1);

            Chunk underline = new Chunk("PATIENT CLINICAL REPORT");
            underline.setUnderline(0.8f, -4);
            underline.setFont(catFont);

            Paragraph paragraph = new Paragraph("");
            paragraph.add(underline);

            paragraph.setAlignment(Element.ALIGN_CENTER);

            document.add(paragraph);

            Paragraph empty2 = new Paragraph();
            addEmptyLine(empty2, 1);
            document.add(empty2);

            PdfPTable table = new PdfPTable(2);

            PdfPCell cellId = new PdfPCell(new Phrase("Patient Id : " + user.getId()));

            cellId.setBorder(Rectangle.NO_BORDER);

            table.addCell(cellId);


            PdfPCell dob = new PdfPCell(new Phrase("DOB : " + user.getDOB()));

            dob.setBorder(Rectangle.NO_BORDER);


            table.addCell(dob);


            PdfPCell name = new PdfPCell(new Phrase("Patient Name : " + user.getName()));

            name.setBorder(Rectangle.NO_BORDER);

            table.addCell(name);


            PdfPCell weight = new PdfPCell(new Phrase("Weight : " + user.getWeight()));

            weight.setBorder(Rectangle.NO_BORDER);

            table.addCell(weight);


            PdfPCell address = new PdfPCell(new Phrase("Address : " + user.getAddress()));

            address.setBorder(Rectangle.NO_BORDER);

            table.addCell(address);

            PdfPCell height = new PdfPCell(new Phrase("Height : " + user.getHeight()));

            height.setBorder(Rectangle.NO_BORDER);

            table.addCell(height);

            PdfPCell mobile = new PdfPCell(new Phrase("Mobile No : " + user.getMobile()));

            mobile.setBorder(Rectangle.NO_BORDER);

            table.addCell(mobile);


            PdfPCell gender = new PdfPCell(new Phrase("Gender : " + user.getGender()));

            gender.setBorder(Rectangle.NO_BORDER);

            table.addCell(gender);


            PdfPCell age = new PdfPCell(new Phrase("Age : " + user.getAge()));

            age.setBorder(Rectangle.NO_BORDER);

            table.addCell(age);


            PdfPCell emaild = new PdfPCell(new Phrase("Email : " + user.getEmailId()));

            emaild.setBorder(Rectangle.NO_BORDER);

            table.addCell(emaild);

            PdfPCell date = new PdfPCell(new Phrase("Date : " + ""));

            date.setBorder(Rectangle.NO_BORDER);

            table.addCell(date);


            PdfPCell empty = new PdfPCell(new Phrase(""));

            empty.setBorder(Rectangle.NO_BORDER);

            table.addCell(empty);

            document.add(table);


            Paragraph empty3 = new Paragraph();
            addEmptyLine(empty3, 3);
            document.add(empty3);

            PdfPTable tableNew = new PdfPTable(4);

            PdfPCell headerTest = new PdfPCell(new Phrase("TEST "));

            tableNew.addCell(headerTest);


            PdfPCell headerResult = new PdfPCell(new Phrase("RESULT"));

            headerResult.setColspan(2);

            tableNew.addCell(headerResult);

            PdfPCell headerRange = new PdfPCell(new Phrase("REF. RANGE"));

            tableNew.addCell(headerRange);


            //---------------------

            if (getIntent().hasExtra("1")) {

                PdfPCell bloodPressure = new PdfPCell(new Phrase("BLOOD PRESSURE"));

                bloodPressure.setColspan(4);

                tableNew.addCell(bloodPressure);


                PdfPCell systolic = new PdfPCell(new Phrase("Systolic"));


                tableNew.addCell(systolic);


                PdfPCell systolicvalue = new PdfPCell(new Phrase(result[0].split("#")[1]));
                tableNew.addCell(systolicvalue);


                PdfPCell sysacvalue = new PdfPCell(new Phrase(result[0].split("#")[0] + " (mmHg)"));
                tableNew.addCell(sysacvalue);

                PdfPCell sysrange = new PdfPCell(new Phrase("120"));
                tableNew.addCell(sysrange);


                PdfPCell diatolic = new PdfPCell(new Phrase("Diastolic"));
                tableNew.addCell(diatolic);

                PdfPCell diatolicvalue = new PdfPCell(new Phrase(result[1].split("#")[1]));
                tableNew.addCell(diatolicvalue);

                PdfPCell diaacvalue = new PdfPCell(new Phrase(result[0].split("#")[0] + " (mmHg)"));
                tableNew.addCell(diaacvalue);

                PdfPCell diarange = new PdfPCell(new Phrase("80"));
                tableNew.addCell(diarange);


//------------------

                PdfPCell heartRate = new PdfPCell(new Phrase("HEART RATE"));

                heartRate.setColspan(4);

                tableNew.addCell(heartRate);


                PdfPCell hearrate = new PdfPCell(new Phrase("Heart Rate"));
                tableNew.addCell(hearrate);


                PdfPCell heartRateValue = new PdfPCell(new Phrase(result[2].split("#")[1]));
                tableNew.addCell(heartRateValue);


                PdfPCell ratevalue = new PdfPCell(new Phrase(result[2].split("#")[0] + " (1/min)"));
                tableNew.addCell(ratevalue);

                PdfPCell heartRange = new PdfPCell(new Phrase("60 to 100"));
                tableNew.addCell(heartRange);

            }

            if (getIntent().hasExtra("3")) {

                PdfPCell cholestrol = new PdfPCell(new Phrase("CHOLESTEROL"));

                cholestrol.setColspan(4);

                tableNew.addCell(cholestrol);


                PdfPCell totcol = new PdfPCell(new Phrase("Total Cholesterol"));
                tableNew.addCell(totcol);


                PdfPCell colrvalue = new PdfPCell(new Phrase(result[3].split("#")[1]));
                tableNew.addCell(colrvalue);


                PdfPCell colravalue = new PdfPCell(new Phrase(result[3].split("#")[0]));
                tableNew.addCell(colravalue);

                PdfPCell colrefvalue = new PdfPCell(new Phrase("< 200"));
                tableNew.addCell(colrefvalue);


                PdfPCell totcolldl = new PdfPCell(new Phrase("Cholesterol_LDL"));
                tableNew.addCell(totcolldl);


                PdfPCell colrvalueldl = new PdfPCell(new Phrase(result[4].split("#")[1]));
                tableNew.addCell(colrvalueldl);


                PdfPCell colravalueldl = new PdfPCell(new Phrase(result[4].split("#")[0]));
                tableNew.addCell(colravalueldl);

                PdfPCell colrefvalueldl = new PdfPCell(new Phrase("100 to 129"));
                tableNew.addCell(colrefvalueldl);


                PdfPCell totcolhdl = new PdfPCell(new Phrase("Cholesterol_HDL"));
                tableNew.addCell(totcolhdl);


                PdfPCell colrvaluehdl = new PdfPCell(new Phrase(result[5].split("#")[1]));
                tableNew.addCell(colrvaluehdl);


                PdfPCell colravaluehdl = new PdfPCell(new Phrase(result[5].split("#")[0]));
                tableNew.addCell(colravaluehdl);

                PdfPCell colrefvaluehdl = new PdfPCell(new Phrase("40 to 59"));
                tableNew.addCell(colrefvaluehdl);

                //---------------------------


                PdfPCell heartRatio = new PdfPCell(new Phrase("LDL/HDL Ratio"));

                heartRatio.setColspan(4);

                tableNew.addCell(heartRatio);


                PdfPCell totcolratio = new PdfPCell(new Phrase("LDL/HDL Ratio"));
                totcolratio.setColspan(2);
                tableNew.addCell(totcolratio);


            /*PdfPCell totcolratioe = new PdfPCell(new Phrase(" "));
            //totcolratio.setColspan(2);
            tableNew.addCell(totcolratioe);*/


                PdfPCell colrvalueratio = new PdfPCell(new Phrase(result[6].split("#")[1]));

                tableNew.addCell(colrvalueratio);


                PdfPCell colrefvalueratio = new PdfPCell(new Phrase("40 to 59"));
                tableNew.addCell(colrefvalueratio);

            }
//-------------------------------------

            if (getIntent().hasExtra("4")) {

                PdfPCell stress = new PdfPCell(new Phrase("STRESS"));

                stress.setColspan(4);

                tableNew.addCell(stress);


                PdfPCell stresslbl = new PdfPCell(new Phrase("Stress"));

                tableNew.addCell(stresslbl);


                PdfPCell stressType = new PdfPCell(new Phrase(result[7].split("#")[1]));

                tableNew.addCell(stressType);


                PdfPCell stressrange = new PdfPCell(new Phrase(result[7].split("#")[0]));
                stressrange.setColspan(2);
                tableNew.addCell(stressrange);
            }

//----------------

            if (getIntent().hasExtra("2")) {

                PdfPCell random_sugar = new PdfPCell(new Phrase("RANDOM SUGAR"));

                random_sugar.setColspan(4);

                tableNew.addCell(random_sugar);


                PdfPCell ransuglbl = new PdfPCell(new Phrase("Random Sugar"));

                tableNew.addCell(ransuglbl);


                PdfPCell ransugType = new PdfPCell(new Phrase(result[8].split("#")[1]));

                tableNew.addCell(ransugType);


                PdfPCell ransugrange = new PdfPCell(new Phrase(result[8].split("#")[0]));
                ransugrange.setColspan(2);
                tableNew.addCell(ransugrange);

            }

            //----------------------------

            if (getIntent().hasExtra("5")) {


                PdfPCell ecg = new PdfPCell(new Phrase("ECG"));

                ecg.setColspan(4);

                tableNew.addCell(ecg);


                PdfPCell qt_interval = new PdfPCell(new Phrase("QT_Interval"));
                tableNew.addCell(qt_interval);


                PdfPCell qt_type = new PdfPCell(new Phrase(result[11].split("#")[0]));
                tableNew.addCell(qt_type);


                PdfPCell qt_intrange = new PdfPCell(new Phrase(result[11].split("#")[1]));
                tableNew.addCell(qt_intrange);

                PdfPCell qt_interrange = new PdfPCell(new Phrase("0.36to0.44"));
                tableNew.addCell(qt_interrange);


                //----------

                PdfPCell rr_interval = new PdfPCell(new Phrase("RR_Interval"));
                tableNew.addCell(rr_interval);


                PdfPCell rr_type = new PdfPCell(new Phrase(result[10].split("#")[1]));
                tableNew.addCell(rr_type);


                PdfPCell rr_intrange = new PdfPCell(new Phrase(result[10].split("#")[0]));
                tableNew.addCell(rr_intrange);

                PdfPCell rr_interrange = new PdfPCell(new Phrase("0.12to0.20(sec)"));
                tableNew.addCell(rr_interrange);


            }

            //------------------------
            //


            PdfPCell oxy_sat = new PdfPCell(new Phrase("OXYGEN SATURATION"));

            oxy_sat.setColspan(4);

            tableNew.addCell(oxy_sat);


            PdfPCell oxy_san = new PdfPCell(new Phrase("Oxygen_saturation"));
            tableNew.addCell(oxy_san);


            PdfPCell oxy_san_res = new PdfPCell(new Phrase(result[8].split("#")[1]));
            tableNew.addCell(oxy_san_res);


            PdfPCell oxy_san_per = new PdfPCell(new Phrase(result[8].split("#")[0]));
            tableNew.addCell(oxy_san_per);

            PdfPCell oxy_san_range = new PdfPCell(new Phrase("90 - 100 %"));
            tableNew.addCell(oxy_san_range);


            //-----------------------


            //HEMOGLOBIN

            if (getIntent().hasExtra("7")) {

                PdfPCell hemo = new PdfPCell(new Phrase("HEMOGLOBIN"));

                hemo.setColspan(4);

                tableNew.addCell(hemo);


                PdfPCell hemotitle = new PdfPCell(new Phrase("Hemoglobin"));
                tableNew.addCell(hemotitle);


                PdfPCell hemoresul = new PdfPCell(new Phrase(result[9].split("#")[1]));
                tableNew.addCell(hemoresul);


                PdfPCell hemo_pre = new PdfPCell(new Phrase(result[9].split("#")[0] + " (g/dl)"));
                tableNew.addCell(hemo_pre);


                PdfPCell hemorange = new PdfPCell(new Phrase("12 to 14"));
                tableNew.addCell(hemorange);


            }
            //---------------------

            //GRADE


            PdfPCell grade = new PdfPCell(new Phrase("GRADE"));

            grade.setColspan(4);

            tableNew.addCell(grade);


            //--------


            PdfPCell bsa = new PdfPCell(new Phrase("BSA"));
            bsa.setColspan(3);
            tableNew.addCell(bsa);


            PdfPCell bsarange = new PdfPCell(new Phrase("1.9m(2)man-1.6 m(2)woman"));

            tableNew.addCell(bsarange);

            if (getIntent().hasExtra("6")) {
                PdfPCell bmi = new PdfPCell(new Phrase("BMI"));
                tableNew.addCell(bmi);
                PdfPCell bmival = new PdfPCell(new Phrase("0 (kg/m(2))"));
                bmival.setColspan(3);
                tableNew.addCell(bmival);
            }

            // cvd


            Phrase cvdPhase = new Phrase("CVD");

            cvdPhase.setFont(catFontf);

            PdfPCell cvd = new PdfPCell(cvdPhase);

            tableNew.addCell(cvd);


            Phrase cvdvaluePhrase = new Phrase(result[14].split("#")[0]);

            cvdvaluePhrase.setFont(catFontf);

            PdfPCell cvdvalue = new PdfPCell(cvdvaluePhrase);
            cvdvalue.setColspan(3);
            tableNew.addCell(cvdvalue);


//-------------

            Phrase cardiacindexPhrase = new Phrase("Cardiac Index");

            cardiacindexPhrase.setFont(catFontf);

            PdfPCell cardiacindex = new PdfPCell(cardiacindexPhrase);
            tableNew.addCell(cardiacindex);


            Phrase civaluePhase = new Phrase(result[12].split("#")[0]);

            civaluePhase.setFont(catFontf);

            PdfPCell civalue = new PdfPCell(civaluePhase);
            civalue.setColspan(2);
            tableNew.addCell(civalue);

            Phrase cirangePh = new Phrase("(2.5-4.2)");

            cirangePh.setFont(catFontf);

            PdfPCell cirange = new PdfPCell(cirangePh);
            tableNew.addCell(cirange);


            //----------


            PdfPCell vage = new PdfPCell(new Phrase("Vascular Age"));
            tableNew.addCell(vage);


            PdfPCell vagerange = new PdfPCell(new Phrase(result[13].split("#")[0]));
            vagerange.setColspan(3);
            tableNew.addCell(vagerange);


            document.add(tableNew);


            handlePdfResponse.sendEmptyMessage(1);


        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }

    }

    private void initGetAlgo() {
        progressDialogUtil.show();
        GetAlgo getAlgo = new GetAlgo();
        getAlgo.setOnAlgoSuccess(this);
    }

    @Override
    public void onAlgoSuccess(List<String> result) {
        progressDialogUtil.hide();
        if (!result.isEmpty()) {
            String strResult = iHeart.ProcessData((ArrayList) result);
            String strNodes[] = strResult.split(",");
            this.result = strNodes;
            lblPressure.setText(strNodes[0].split("#")[1]);
            lblRandomSugar.setText(strNodes[1].split("#")[1]);
            lblCholestrol.setText(strNodes[2].split("#")[1]);
            lblStress.setText(strNodes[3].split("#")[1]);
            lblECG.setText(strNodes[4].split("#")[1]);
            lblBMI.setText(strNodes[5].split("#")[1]);
            lblHemogram.setText(strNodes[6].split("#")[1]);
            lblVAH.setText(strNodes[13]);
            lblCI.setText(strNodes[12]);
            lblCVD.setText(strNodes[14]);
        }
    }
}
