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
    ImageView imageViewMainScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findAllViews();

        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext());

        planetListView.setAdapter(customAdapter);

        planetListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(MainActivity.this,DetailActivity.class);

                intent.putExtra("PlanetName",planetNames[i]);
                intent.putExtra("PlanetPosition",i);

                startActivity(intent);
            }
        });
    }

    public void findAllViews(){
        planetListView = findViewById(R.id.planetListView);
        imageViewMainScreen = findViewById(R.id.imageViewMainScreen);
    }

}
