package com.mywings.patients;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;

public class GetUsers extends AsyncTask<Void, Void, JSONArray> {

    private OnSensorDataListener onSensorDataListener;
    private HttpConnectionUtil httpConnectionUtil = new HttpConnectionUtil();

    @Override
    protected JSONArray doInBackground(Void... voids) {
        try {
            return new JSONArray(httpConnectionUtil.requestGet(Constant.URL + Constant.GET_PATIENTS));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(JSONArray jsonArray) {
        super.onPostExecute(jsonArray);
        onSensorDataListener.onSensorDataSuccess(jsonArray);
    }

    public void setOnSensorDataListener(OnSensorDataListener onSensorDataListener) {
        this.onSensorDataListener = onSensorDataListener;
        super.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

}
