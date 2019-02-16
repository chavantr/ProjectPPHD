package com.mywings.patients;

import android.os.AsyncTask;

public class UpdateUser extends AsyncTask<String, Void, String> {

    private HttpConnectionUtil httpConnectionUtil = new HttpConnectionUtil();

    private OnUpdateUserListener updateUserListener;

    @Override
    protected String doInBackground(String... params) {
        return httpConnectionUtil.requestGet(Constant.URL + Constant.UPDATE_USER + "?id=" + params[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        updateUserListener.onUpdateUser(s);
    }


    public void setUpdateUserListener(OnUpdateUserListener updateUserListener, String id) {
        this.updateUserListener = updateUserListener;
        super.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, id);
    }
}
