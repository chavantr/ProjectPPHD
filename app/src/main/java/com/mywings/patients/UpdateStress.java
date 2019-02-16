package com.mywings.patients;

import android.os.AsyncTask;

public class UpdateStress extends AsyncTask<String, Void, String> {

    private HttpConnectionUtil httpConnectionUtil = new HttpConnectionUtil();

    private OnStressListener onStressListener;

    @Override
    protected String doInBackground(String... strings) {
        return httpConnectionUtil.requestGet(Constant.URL + Constant.UPDATE_STRESS + "?stress=" + strings[0].toString().split("#")[0] + "&id=" + strings[0].toString().split("#")[1]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        onStressListener.onStressSuccess(s);
    }

    public void setOnStressListener(OnStressListener onStressListener, String request) {
        this.onStressListener = onStressListener;
        super.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, request);
    }
}
