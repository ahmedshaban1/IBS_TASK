package myt.ahmed.isbtask.Activities.ListData;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import myt.ahmed.isbtask.Activities.popUp.WeatherDetails;
import myt.ahmed.isbtask.R;
import myt.ahmed.isbtask.model.WeatherResponse;

/**
 * Created by ahmed shabaan on 5/30/2017.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private ArrayList<WeatherResponse.WeatherCity> dataSet;
    public static Context context;
    public WeatherAdapter(ArrayList<WeatherResponse.WeatherCity> os_versions, Context context) {

        dataSet = os_versions;
        this.context = context;
    }


    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.weather_item, null,false);
        itemLayoutView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final WeatherAdapter.ViewHolder viewHolder, final int i) {

        WeatherResponse.WeatherCity  fp = dataSet.get(i);
        viewHolder.name.setText(fp.name);
        viewHolder.temp.setText(fp.main.temp+"");
        viewHolder.wind_and_degree.setText(fp.wind.speed+" and "+fp.wind.deg);
        viewHolder.Humidity.setText(fp.main.humidity+"");

        viewHolder.feed = fp;
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView temp,wind_and_degree,Humidity;
        public TextView name;
        public  WeatherResponse.WeatherCity feed;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            name = (TextView) itemLayoutView
                    .findViewById(R.id.name);
            temp = (TextView) itemLayoutView
                    .findViewById(R.id.temp);
            wind_and_degree = (TextView) itemLayoutView
                    .findViewById(R.id.wind_and_degree);
            Humidity = (TextView) itemLayoutView
                    .findViewById(R.id.Humidity);

            itemLayoutView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    WeatherDetails weatherDetails = new WeatherDetails();
                    Bundle b = new Bundle();
                    b.putParcelable("item",feed);
                    weatherDetails.setArguments(b);
                    weatherDetails.show(((AppCompatActivity) context).getSupportFragmentManager(), "dialog");
                }

            });




        }

    }
}



