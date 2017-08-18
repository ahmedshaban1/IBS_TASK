package myt.ahmed.isbtask.callBacks;

import myt.ahmed.isbtask.model.WeatherResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ahmed shaban on 8/15/2017.
 */

public interface ApiInterface {
    @GET("box/city")
    Call<WeatherResponse> getCities(@Query("bbox") String box , @Query("APPID") String APPID);
}

