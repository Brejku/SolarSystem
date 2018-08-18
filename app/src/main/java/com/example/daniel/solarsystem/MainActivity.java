package com.example.daniel.solarsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    public ArrayList<Planet> arrayOfPlanets = new ArrayList<>();
    public final static String PLANET_POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Database database = new Database(this);
        // Construct the data source and populate it
        arrayOfPlanets = database.getAllPlanets();
        database.closeDb();


        // Create the adapter to convert the array to views
        PlanetsAdapter adapter = new PlanetsAdapter(this, arrayOfPlanets);

        // Attach the adapter to a ListView
        ListView planetListView = findViewById(R.id.list_view_planet);
        planetListView.setAdapter(adapter);


        planetListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                int positionPressed = i+1;
                intent.putExtra(PLANET_POSITION,positionPressed);
                startActivity(intent);
            }
        });

    }

}
