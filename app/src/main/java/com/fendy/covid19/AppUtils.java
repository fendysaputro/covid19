package com.fendy.covid19;

import android.content.Context;
import android.util.Log;

import com.fendy.covid19.adapter.ListCountryAdapter;
import com.fendy.covid19.model.Covid;
import com.fendy.covid19.network.AsyncHttpTask;
import com.fendy.covid19.network.OnGetDataFinish;
import com.fendy.covid19.network.OnHttpCancel;
import com.fendy.covid19.network.OnHttpResponseListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AppUtils {

    OnGetDataFinish onGetDataFinish;
    OnHttpCancel onHttpCancel;

    public void setOnGetDataFinish(OnGetDataFinish getDataListener) {
        onGetDataFinish = getDataListener;
    }

    public void getCaseData (final Context context, final App app, final ArrayList<Covid> covids, final ListCountryAdapter listCountryAdapter){
        AsyncHttpTask caseHttpCase = new AsyncHttpTask("");
        caseHttpCase.execute(app.CASE_GLOBAL_URL, "GET");
        caseHttpCase.setHttpResponseListener(new OnHttpResponseListener() {
            @Override
            public void OnHttpResponse(String response) {
                try {
                    JSONArray jsonarray = new JSONArray(response);
                    for (int i = 0; i < jsonarray.length(); i++) {
                        JSONObject assignObj = jsonarray.getJSONObject(i);
                        JSONObject attributes = assignObj.getJSONObject("attributes");
                        Covid covid = new Covid();
                        covid.setObjectid(attributes.getInt("OBJECTID"));
                        covid.setCountry_Region(attributes.getString("Country_Region"));
                        covid.setLast_Update(attributes.getString("Last_Update"));
                        covid.setLat(attributes.getDouble("Lat"));
                        covid.setLong(attributes.getDouble("Long_"));
                        covid.setConfirmed(attributes.getInt("Confirmed"));
                        covid.setDeaths(attributes.getInt("Deaths"));
                        covid.setRecovered(attributes.getInt("Recovered"));
                        covid.setActive(attributes.getInt("Active"));
                        covids.add(covid);
                    }
                    listCountryAdapter.notifyDataSetChanged();
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }

    public void getCaseDataByProvince (final Context context, final App app){
        AsyncHttpTask getDataByProvince = new AsyncHttpTask("");
        getDataByProvince.execute(app.CASE_PROVINCE_URL, "GET");
        getDataByProvince.setHttpResponseListener(new OnHttpResponseListener() {
            @Override
            public void OnHttpResponse(String response) {

            }
        });
    }
}
