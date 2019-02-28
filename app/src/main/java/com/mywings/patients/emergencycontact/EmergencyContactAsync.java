package com.mywings.patients.emergencycontact;

import android.os.AsyncTask;

import com.mywings.patients.Constant;
import com.mywings.patients.HttpConnectionUtil;

import org.json.JSONObject;

public class EmergencyContactAsync extends AsyncTask<JSONObject, Void, String> {


    private OnEmergencyContactListener onEmergencyContactListener;

    private final HttpConnectionUtil httpConnectionUtil = new HttpConnectionUtil();


    @Override
    protected String doInBackground(JSONObject... param) {
        return httpConnectionUtil.requestPost(Constant.URL + "", param[0]);
    }

    public void setOnEmergencyContactListener(OnEmergencyContactListener onEmergencyContactListener, JSONObject request) {
        this.onEmergencyContactListener = onEmergencyContactListener;
        super.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, request);
    }


}
