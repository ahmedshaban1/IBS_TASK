package myt.ahmed.isbtask.Activities.home;

import java.util.ArrayList;

import myt.ahmed.isbtask.model.City;
import myt.ahmed.isbtask.model.WeatherResponse;

/**
 * Created by Ahmed shaban on 8/15/2017.
 */


public interface HomeView {
    void showLoader();
    void hideLoader();
    void RequestList(ArrayList<WeatherResponse.WeatherCity> cities);
    void showWaring(String string);
}
