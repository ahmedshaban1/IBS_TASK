package myt.ahmed.isbtask.Activities.home;

import android.content.Context;

import javax.inject.Inject;

import myt.ahmed.isbtask.R;
import myt.ahmed.isbtask.callBacks.ApiInterface;
import myt.ahmed.isbtask.dagger.DaggerApplication;
import myt.ahmed.isbtask.model.WeatherResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ahmed shaban on 8/15/2017.
 */
public class HomePresenterImpl implements HomePresenter {
    HomeView view;
    @Inject
    ApiInterface apiInterface;
    @Inject
    Context context;

    @Override
    public void setView(HomeView view) {
        this.view = view;
    }

    public HomePresenterImpl(Context context) {
        ((DaggerApplication)context).getAppComponent().inject(this);
    }

    @Override
    public void setRequestList() {
        view.showLoader();
        Call<WeatherResponse>   call  = apiInterface.getCities("24.7,22.0,34.21,31.5,10","bcdd2a56275b40e0b19a323f8cf155e6");
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                view.hideLoader();
                if(response.isSuccessful()){
                    view.RequestList(response.body().list);
                }else{
                    view.showWaring(context.getString(R.string.error_loading_cities));
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                view.hideLoader();
                view.showWaring(context.getString(R.string.error_loading_cities));
            }
        });
     }
}
