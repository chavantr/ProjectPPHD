package com.mywings.patients;

import android.os.AsyncTask;

public class SendSMS extends AsyncTask<Void, Void, Boolean> {


    @Override
    protected Boolean doInBackground(Void... voids) {

        String[] strNumbers = {"9900000000", "8800000000", "7700000000", "6600000000", "5500000000"};


        for (int i = 0; i < strNumbers.length; i++) {




        }

        return true;
    }
}
