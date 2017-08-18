package myt.ahmed.isbtask.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ahmed shaban on 8/15/2017.
 */

public class City {
    @SerializedName("id")
    public int id;
    @SerializedName("dt")
    public int dt;
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


    public class Coord {
        @SerializedName("Lat")
        public double Lat;
        @SerializedName("Lon")
        public double Lon;
    }

    public  class Main {
        @SerializedName("temp")
        public int temp;
        @SerializedName("temp_min")
        public int temp_min;
        @SerializedName("temp_max")
        public int temp_max;
        @SerializedName("pressure")
        public int pressure;
        @SerializedName("humidity")
        public int humidity;
    }

    public  class Wind {
        @SerializedName("speed")
        public double speed;
        @SerializedName("deg")
        public int deg;
    }

    public  class Clouds {
        @SerializedName("today")
        public int today;
    }

    public  class Weather {
        @SerializedName("id")
        public int id;
        @SerializedName("main")
        public String main;
        @SerializedName("description")
        public String description;
        @SerializedName("icon")
        public String icon;
    }
}
