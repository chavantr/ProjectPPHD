package com.mywings.patients;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

public class PatientsRegistrationAsync extends AsyncTask<JSONObject, Void, JSONObject> {

    private HttpConnectionUtil httpConnectionUtil = new HttpConnectionUtil();
    private OnRegistrationListener onRegistrationListener;

    @Override
    protected JSONObject doInBackground(JSONObject... params) {
        try {
            return new JSONObject(httpConnectionUtil.requestPost(Constant.URL + Constant.REGISTER_USER, params[0]));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setOnRegistrationListener(OnRegistrationListener onRegistrationListener, JSONObject request) {
        this.onRegistrationListener = onRegistrationListener;
        super.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, request);
    }

    @Override
    protected void onPostExecute(JSONObject result) {
        super.onPostExecute(result);
        onRegistrationListener.onSuccess(result);
    }
}
