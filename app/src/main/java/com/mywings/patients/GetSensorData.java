package com.mywings.patients;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;

public class GetSensorData extends AsyncTask<Void, Void, JSONArray> {


    private HttpConnectionUtil httpConnectionUtil = new HttpConnectionUtil();

    private OnSensorDataListener onSensorDataListener;

    @Override
    protected JSONArray doInBackground(Void... voids) {
        String response = httpConnectionUtil.requestGet(Constant.GET_RECORDS);
        try {
            JSONArray jsonArray = new JSONArray(response);
            return jsonArray;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setOnSensorDataListener(OnSensorDataListener onSensorDataListener) {
        this.onSensorDataListener = onSensorDataListener;
        super.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
    
}
