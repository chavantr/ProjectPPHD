package com.mywings.patients.user;

import android.os.AsyncTask;

import com.mywings.patients.Constant;
import com.mywings.patients.HttpConnectionUtil;
import com.mywings.patients.OnUpdateUserListener;

import org.json.JSONObject;

public class UpdateUserAsync extends AsyncTask<JSONObject, Void, String> {

    private HttpConnectionUtil httpConnectionUtil = new HttpConnectionUtil();

    private OnUpdateUserListener onUpdateUserListener;

    @Override
    protected String doInBackground(JSONObject... param) {
        return httpConnectionUtil.requestPost(Constant.URL + Constant.UPDATE_PATIENT, param[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        onUpdateUserListener.onUpdateUser(s);
    }

    public void setOnUpdateUserListener(OnUpdateUserListener onUpdateUserListener, JSONObject request) {
        this.onUpdateUserListener = onUpdateUserListener;
        super.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, request);
    }
}
