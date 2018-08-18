package com.example.daniel.solarsystem;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class DetailActivity extends AppCompatActivity{

    private ImageView planetImage;
    private TextView planetName,planetDescription;
    private String PLANET_POSITION = "position";
    private int chosenPlanetPosition;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        database = new Database(getApplicationContext());

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){

            chosenPlanetPosition = bundle.getInt(PLANET_POSITION);

            planetName = findViewById(R.id.text_view_planet_name_detail);
            planetName.setText(database.getPlanetAtIndex(chosenPlanetPosition).getName());

            Drawable drawable;
            drawable = getResources().getDrawable(database.getPlanetAtIndex(chosenPlanetPosition).getImage());

            planetImage = findViewById(R.id.image_view_planet_detail);
            planetImage.setImageDrawable(drawable);

            planetDescription = findViewById(R.id.text_view_planet_description);
           planetDescription.setText(database.getPlanetAtIndex(chosenPlanetPosition).getDescription());
        }

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = database.getPlanetAtIndex(chosenPlanetPosition).getLink();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }
}
