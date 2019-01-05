package com.mywings.patients;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginAsync extends AsyncTask<JSONObject, Void, JSONObject> {

    private HttpConnectionUtil httpConnectionUtil = new HttpConnectionUtil();

    public void setLoginListener(OnLoginListener loginListener, JSONObject request) {
        this.loginListener = loginListener;
        super.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, request);
    }

    private OnLoginListener loginListener;

    @Override
    protected JSONObject doInBackground(JSONObject... params) {
        String response = httpConnectionUtil.requestPost(Constant.URL + Constant.LOGIN_USER, params[0]);
        try {
            return new JSONObject(response);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(JSONObject result) {
        super.onPostExecute(result);
        loginListener.onLoginListener(result);
    }
}
