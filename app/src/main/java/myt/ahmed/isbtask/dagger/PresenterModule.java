package myt.ahmed.isbtask.dagger;

import android.content.Context;



import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import myt.ahmed.isbtask.Activities.home.HomePresenter;
import myt.ahmed.isbtask.Activities.home.HomePresenterImpl;

/**
 * Created by Ahmed shaban on 7/30/2017.
 */

@Module
public class PresenterModule {


    @Provides
    @Singleton
    HomePresenter provideTestPresenterImpl(Context context) {
        return new HomePresenterImpl(context);
    }



}

