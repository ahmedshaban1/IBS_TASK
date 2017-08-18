package myt.ahmed.isbtask.Activities.ListData;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import myt.ahmed.isbtask.R;
import myt.ahmed.isbtask.baseClasses.BaseActivity;
import myt.ahmed.isbtask.dagger.DaggerApplication;
import myt.ahmed.isbtask.model.WeatherResponse;
import myt.ahmed.isbtask.Activities.home.HomePresenter;
import myt.ahmed.isbtask.Activities.home.HomeView;

public class ListData extends BaseActivity implements HomeView {
    @Inject
    HomePresenter presenter;

    @Bind(R.id.list)
    RecyclerView recyclerView;

    WeatherAdapter weatherAdapter;



    ArrayList<WeatherResponse.WeatherCity> cities;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowCustomEnabled(true);
        }
        ButterKnife.bind(this);
        ((DaggerApplication)getApplication()).getAppComponent().inject(this);
        cities = new ArrayList<>();
        presenter.setView(this);
        presenter.setRequestList();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        weatherAdapter = new WeatherAdapter(cities,this);
        recyclerView.setAdapter(weatherAdapter);
    }

    @Override
    public void showLoader() {
        showProgress();
    }

    @Override
    public void hideLoader() {
        hideProgress();
    }

    @Override
    public void RequestList(ArrayList<WeatherResponse.WeatherCity> cities) {
        this.cities.addAll(cities);
        weatherAdapter.notifyDataSetChanged();

    }

    @Override
    public void showWaring(String message) {
        getWaring(message,1);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.home, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }

        final SearchView finalSearchView = searchView;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Search(query);
                if( ! finalSearchView.isIconified()) {
                    finalSearchView.setIconified(true);
                }
                searchItem.collapseActionView();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
               Search(s);
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Toast.makeText(ListData.this, "Closed", Toast.LENGTH_SHORT).show();
                ResetList();
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }


    public void Search(String q){
         ArrayList<WeatherResponse.WeatherCity> temp = new ArrayList<>();
          for(int i = 0 ; i < cities.size();i++){
              if(cities.get(i).name.contains(q)){
                  temp.add(cities.get(i));
              }
          }

          if(temp.size() > 0){
              weatherAdapter = new WeatherAdapter(temp,this);
              recyclerView.setAdapter(weatherAdapter);
          }



    }

    public void ResetList(){
        weatherAdapter = new WeatherAdapter(cities,this);
        recyclerView.setAdapter(weatherAdapter);
    }

}
