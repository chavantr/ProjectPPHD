package com.mywings.patients;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetAlgo extends AsyncTask<Void, Void, List<String>> {

    private OnAlgoListener onAlgoSuccess;
    private HttpConnectionUtil httpConnectionUtil = new HttpConnectionUtil();

    @Override
    protected List<String> doInBackground(Void... params) {
        List<String> algo = new ArrayList<>();
        String response = httpConnectionUtil.requestGet(Constant.URL + Constant.GET_ALGO);

        try {
            JSONArray jComments = new JSONArray(response);
            for (int i = 0; i < jComments.length(); i++) {
                JSONObject node = jComments.getJSONObject(i);
                algo.add(node.getString("Comment"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return algo;
    }

    @Override
    protected void onPostExecute(List<String> result) {
        super.onPostExecute(result);
        onAlgoSuccess.onAlgoSuccess(result);
    }

    public void setOnAlgoSuccess(OnAlgoListener onAlgoSuccess) {
        this.onAlgoSuccess = onAlgoSuccess;
        super.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
