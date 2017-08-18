package myt.ahmed.isbtask.dagger;

import android.app.Application;


/**
 * Created by Ahmed shaban on 7/30/2017.
 */

public class DaggerApplication extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = initDagger(this);
    }


    protected AppComponent initDagger(DaggerApplication application) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }
    public AppComponent getAppComponent() {
        return appComponent;
    }
}
