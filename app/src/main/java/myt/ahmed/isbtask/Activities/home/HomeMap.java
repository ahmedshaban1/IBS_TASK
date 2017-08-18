package myt.ahmed.isbtask.Activities.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import javax.inject.Inject;

import myt.ahmed.isbtask.Activities.popUp.WeatherDetails;
import myt.ahmed.isbtask.R;
import myt.ahmed.isbtask.baseClasses.BaseActivity;
import myt.ahmed.isbtask.dagger.DaggerApplication;
import myt.ahmed.isbtask.model.WeatherResponse;

public class HomeMap extends BaseActivity implements OnMapReadyCallback,HomeView {

    private GoogleMap mMap;

    @Inject
    HomePresenter presenter;

    ArrayList<WeatherResponse.WeatherCity> cities;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_map);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowCustomEnabled(true);
        }
        ((DaggerApplication)getApplication()).getAppComponent().inject(this);
        cities = new ArrayList<>();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        presenter.setView(this);
        presenter.setRequestList();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng sydney = new LatLng(26.82, 30.80);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,5));
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                int index = (int) marker.getTag();
                WeatherDetails weatherDetails = new WeatherDetails();
                Bundle b = new Bundle();
                b.putParcelable("item",cities.get(index));
                weatherDetails.setArguments(b);
                weatherDetails.show(getSupportFragmentManager(), "dialog");
                return false;
            }
        });
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
        for(int i = 0 ; i < cities.size() ; i++){
            mMap.addMarker(new MarkerOptions().position(new LatLng(cities.get(i).coord.Lat,cities.get(i).coord.Lon)).title(cities.get(i).main.temp+"")).setTag(i);
        }
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
}
