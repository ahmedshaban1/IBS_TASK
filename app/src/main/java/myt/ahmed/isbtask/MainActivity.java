package myt.ahmed.isbtask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import myt.ahmed.isbtask.Activities.ListData.ListData;
import myt.ahmed.isbtask.Activities.home.HomeMap;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.list_btn)
    Button list_btn;

    @Bind(R.id.map_btn)
    Button map_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ListData.class);
                startActivity(i);
            }
        });

        map_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, HomeMap.class);
                startActivity(i);
            }
        });

    }
}
