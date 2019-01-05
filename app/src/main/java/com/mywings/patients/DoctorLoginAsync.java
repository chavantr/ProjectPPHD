package com.mywings.patients;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

public class DoctorLoginAsync extends AsyncTask<JSONObject, Void, JSONObject> {

    private HttpConnectionUtil httpConnectionUtil = new HttpConnectionUtil();

    private OnDoctorLoginListener onDoctorLoginListener;

    @Override
    protected JSONObject doInBackground(JSONObject... params) {
        try {
            return new JSONObject(httpConnectionUtil.requestPost(Constant.URL + Constant.LOGIN_DOCTOR, params[0]));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(JSONObject result) {
        super.onPostExecute(result);
        onDoctorLoginListener.onDoctorLoginSuccess(result);
    }

    public void setOnDoctorLoginListener(OnDoctorLoginListener onDoctorLoginListener, JSONObject request) {
        this.onDoctorLoginListener = onDoctorLoginListener;
        super.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, request);
    }
}
