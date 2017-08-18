package myt.ahmed.isbtask.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmed shaban on 8/15/2017.
 */

public class WeatherResponse {


    @SerializedName("cod")
    public int cod;
    @SerializedName("calctime")
    public double calctime;
    @SerializedName("cnt")
    public int cnt;
    @SerializedName("list")
    public ArrayList<WeatherCity> list;

    public static class Coord {
        @SerializedName("Lat")
        public double Lat;
        @SerializedName("Lon")
        public double Lon;
    }

    public static class Main {
        @SerializedName("temp")
        public float temp;
        @SerializedName("temp_min")
        public float temp_min;
        @SerializedName("temp_max")
        public float temp_max;
        @SerializedName("pressure")
        public float pressure;
        @SerializedName("humidity")
        public float humidity;
    }

    public static class Wind {
        @SerializedName("speed")
        public double speed;
        @SerializedName("deg")
        public float deg;
    }

    public static class Clouds {
        @SerializedName("today")
        public float today;
    }

    public static class Weather {
        @SerializedName("id")
        public float id;
        @SerializedName("main")
        public String main;
        @SerializedName("description")
        public String description;
        @SerializedName("icon")
        public String icon;
    }

    public static class WeatherCity  implements Parcelable {
        @SerializedName("id")
        public float id;
        @SerializedName("dt")
        public float dt;
        @SerializedName("name")
        public String name;
        @SerializedName("coord")
        public Coord coord;
        @SerializedName("main")
        public Main main;
        @SerializedName("wind")
        public Wind wind;
        @SerializedName("rain")
        public String rain;
        @SerializedName("snow")
        public String snow;
        @SerializedName("clouds")
        public Clouds clouds;
        @SerializedName("weather")
        public java.util.List<Weather> weather;

        protected WeatherCity(Parcel in) {
            id = in.readFloat();
            dt = in.readFloat();
            name = in.readString();
            rain = in.readString();
            snow = in.readString();
        }

        public static final Creator<WeatherCity> CREATOR = new Creator<WeatherCity>() {
            @Override
            public WeatherCity createFromParcel(Parcel in) {
                return new WeatherCity(in);
            }

            @Override
            public WeatherCity[] newArray(int size) {
                return new WeatherCity[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeFloat(id);
            parcel.writeFloat(dt);
            parcel.writeString(name);
            parcel.writeString(rain);
            parcel.writeString(snow);
        }
    }
}
