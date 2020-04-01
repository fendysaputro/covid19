package com.fendy.covid19.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fendy.covid19.AppUtils;
import com.fendy.covid19.R;
import com.fendy.covid19.App;
import com.fendy.covid19.adapter.ListCountryAdapter;
import com.fendy.covid19.model.Covid;
import com.fendy.covid19.model.CovidIndonesia;
import com.fendy.covid19.network.OnGetDataFinish;

import java.util.ArrayList;
import java.util.List;

/**
 * created by phephen 2020
 */

public class CaseFragment extends Fragment {
    App app;
    AppUtils appUtils = null;
    Context context;
    ListView listCountry;
    ArrayList<Covid> covidArrayList = null;
    ArrayList<CovidIndonesia> covidIndonesia = null;
    ListCountryAdapter listCountryAdapter;
    SwipeRefreshLayout swipeRefreshLayout = null;

    public Context getCtx() {
        return context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_case, container, false);
        app = (App) getActivity().getApplication();
        context = getActivity();

        listCountry = (ListView) v.findViewById(R.id.listCase);
        covidArrayList = new ArrayList<Covid>();
        covidIndonesia = new ArrayList<CovidIndonesia>();

        listCountryAdapter = new ListCountryAdapter(getContext(), R.layout.custom_list_country_adapter, covidIndonesia);
        listCountry.setAdapter(listCountryAdapter);

//        getDataCase("", covidArrayList, listCountryAdapter);
        getDataCaseByProvince("", covidIndonesia, listCountryAdapter);

        listCountry.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                if (listCountry.getChildAt(0) != null){
                    swipeRefreshLayout.setEnabled(listCountry.getFirstVisiblePosition() == 0 &&
                            listCountry.getChildAt(0).getTop() == 0);
                }
            }
        });

        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataCase("", covidArrayList, listCountryAdapter);
            }
        });

        return v;
    }

    public void getDataCase (String data, final ArrayList<Covid> covids, final ListCountryAdapter adapter){
        appUtils = new AppUtils();
        appUtils.getCaseData(getContext(), app, covids, adapter);
        appUtils.setOnGetDataFinish(new OnGetDataFinish() {
            @Override
            public void OnGetDataComplete() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void getDataCaseByProvince (String data, final ArrayList<CovidIndonesia> covidIndonesias, final ListCountryAdapter adapter){
        appUtils = new AppUtils();
        appUtils.getCaseDataByProvince(getContext(), app, covidIndonesias, adapter);
        appUtils.setOnGetDataFinish(new OnGetDataFinish() {
            @Override
            public void OnGetDataComplete() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
