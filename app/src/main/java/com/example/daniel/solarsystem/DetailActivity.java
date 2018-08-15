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

import static com.example.daniel.solarsystem.Database.planetImages;
import static com.example.daniel.solarsystem.Database.planetInfo;
import static com.example.daniel.solarsystem.Database.planetLink;

public class DetailActivity extends AppCompatActivity{

    ImageView planetImage;
    TextView planetName,planetDescription;
    int position = 0;
    Drawable drawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){

            planetName = findViewById(R.id.text_view_planet_name_detail);
            planetName.setText(bundle.getString("PlanetName"));

            position = bundle.getInt("PlanetPosition");
            drawable = getResources().getDrawable(planetImages[position]);

            planetImage = findViewById(R.id.image_view_planet_detail);
            planetImage.setImageDrawable(drawable);

            planetDescription = findViewById(R.id.text_view_planet_description);
            planetDescription.setText(planetInfo[position]);
        }

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = planetLink[position];
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }
}
