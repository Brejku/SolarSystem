package com.example.daniel.solarsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;


import static com.example.daniel.solarsystem.Database.planetNames;

public class MainActivity extends AppCompatActivity{

    ListView planetListView;
    static final String PLANET_NAME = "PlanetName";
    static final String PLANET_POSITION = "PlanetPosition";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        planetListView = findViewById(R.id.planetListView);

        PlanetsAdapter planetsAdapter = new PlanetsAdapter(getApplicationContext());

        planetListView.setAdapter(planetsAdapter);

        planetListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(MainActivity.this,DetailActivity.class);

                intent.putExtra(PLANET_NAME,planetNames[i]);
                intent.putExtra(PLANET_POSITION,i);

                startActivity(intent);
            }
        });
    }
}
