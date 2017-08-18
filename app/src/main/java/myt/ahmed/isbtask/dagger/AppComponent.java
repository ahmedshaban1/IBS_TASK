package myt.ahmed.isbtask.dagger;


import javax.inject.Singleton;

import dagger.Component;
import myt.ahmed.isbtask.Activities.ListData.ListData;
import myt.ahmed.isbtask.baseClasses.BaseActivity;
import myt.ahmed.isbtask.baseClasses.BaseFragment;
import myt.ahmed.isbtask.Activities.home.HomeMap;
import myt.ahmed.isbtask.Activities.home.HomePresenterImpl;

/**
 * Created by Ahmed shaban on 7/30/2017.
 */

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class,PresenterModule.class})
public interface AppComponent {

    void inject(BaseActivity baseActivity);
    void inject(BaseFragment baseFragment);

    void inject(HomePresenterImpl homePresenter);
    void inject(HomeMap homeMap);
    void inject(ListData listData);

}
