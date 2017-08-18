package myt.ahmed.isbtask.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import myt.ahmed.isbtask.MainActivity;
import myt.ahmed.isbtask.R;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                try {
                    //   Thread.sleep(1000);
                    Intent i;
                    i = new Intent(splash.this, MainActivity.class);
                    startActivity(i);

                    finish();
                } catch (Exception e) {

                }
            }
        }, 2000);
    }
}
