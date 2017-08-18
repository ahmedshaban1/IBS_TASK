package myt.ahmed.isbtask.Activities.popUp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import myt.ahmed.isbtask.R;
import myt.ahmed.isbtask.model.WeatherResponse;

/**
 * Created by Ahmed shaban on 8/6/2017.
 */

public class WeatherDetails extends android.support.v4.app.DialogFragment {
    @Bind(R.id.name)
    TextView name;

    @Bind(R.id.temp)
    TextView temp;

    @Bind(R.id.min_temp)
    TextView min_temp;

    @Bind(R.id.max_temp)
    TextView max_temp;

    @Bind(R.id.Humidity)
    TextView Humidity;

    @Bind(R.id.Pressure)
    TextView Pressure;

    @Bind(R.id.wind_and_degree)
    TextView wind_and_degree;

    @Bind(R.id.description)
    TextView description;

    @Bind(R.id.close)
    TextView close;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_details, container);
        ButterKnife.bind(this,view);
        getDialog().setTitle("ًWeather Details");
        WeatherResponse.WeatherCity item =  getArguments().getParcelable("item");
        name.setText(item.name);
        temp.setText(item.main.temp+"");
        min_temp.setText(item.main.temp_min+"");
        max_temp.setText(item.main.temp_max+"");
        Humidity.setText(item.main.humidity+"");
        Pressure.setText(item.main.pressure+"");
        wind_and_degree.setText(item.wind.speed+" and "+item.wind.deg);
        description.setText(item.weather.get(0).description);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }


}