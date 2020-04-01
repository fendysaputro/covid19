package com.fendy.covid19.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.fendy.covid19.App;
import com.fendy.covid19.R;
import com.fendy.covid19.model.Covid;

import java.util.ArrayList;

public class ListCountryAdapter extends ArrayAdapter<Covid> {
    private final ArrayList<Covid> values;
    private Context context;
    App app;

    public ListCountryAdapter(Context context, int textViewResourceId, ArrayList<Covid> values){
        super(context, textViewResourceId, values);
        this.values = values;
        this.context = context;
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.custom_list_country_adapter, null);
        TextView textView = (TextView) convertView.findViewById(R.id.tvListView);
        textView.setText("Nama Negara : " + values.get(position).getCountry_Region());
        TextView confirmed = (TextView) convertView.findViewById(R.id.confirmedCase);
        confirmed.setText("Jumlah Kasus : " + values.get(position).getConfirmed());

        if (position%2 == 1){
            textView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            confirmed.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else {
            textView.setBackgroundColor(Color.parseColor("#e1f0ee"));
            confirmed.setBackgroundColor(Color.parseColor("#e1f0ee"));
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("covid19", "test click");
            }
        });

        return convertView;
    }
}
