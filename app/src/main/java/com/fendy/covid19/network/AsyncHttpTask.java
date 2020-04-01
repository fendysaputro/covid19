package com.fendy.covid19.network;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncHttpTask extends AsyncTask<String, Void, String> {

    OnHttpResponseListener onHttpResponseListener;
    OnHttpCancel onHttpCancel;
    String data;

    public void setHttpResponseListener(OnHttpResponseListener listener) {
        onHttpResponseListener = listener;
        Log.i("ini listener", onHttpResponseListener.toString());
    }

    public void setOnHttpCancel(OnHttpCancel onHttpCancel) {
        this.onHttpCancel = onHttpCancel;
    }

    public AsyncHttpTask(String data) {

        this.data = data;
    }

    @Override
    protected String doInBackground(String... params) {
        StringBuffer response = null;
        try {
            String url = params[0];
            String method = params[1];
            URL obj;
            if (method.equals("POST")) {
                obj = new URL(url);
            } else {
                obj = new URL(url + "?" + data);
            }
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod(method);
            if (method.equals("POST")) {
                con.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded; charset=UTF-8");
                con.setRequestProperty("Content-Length", "" +
                        Integer.toString(data.getBytes().length));
                con.setUseCaches (false);
                con.setDoInput(true);
                con.setDoOutput(true);

                DataOutputStream wr = new DataOutputStream (
                        con.getOutputStream ());
                wr.writeBytes (data);
                wr.flush ();
                wr.close ();
            }
            con.connect();
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
            }
            con.disconnect();
        } catch (Exception e) {
            Log.d("tracking", "error AsyncHTTPTask: "+e.getMessage());
        }

        if (response != null) {
            return response.toString();
        }else{
            return "";
        }

    }

    @Override
    protected void onPostExecute(String result) {
        if (onHttpResponseListener != null) {
            onHttpResponseListener.OnHttpResponse(result);
        }
    }

    @Override
    protected void onCancelled() {
        if (onHttpCancel != null) {
            onHttpCancel.OnHttpCancel();
        }
    }
}
