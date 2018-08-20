package com.example.daniel.solarsystem;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PlanetsAdapter extends ArrayAdapter<Planet>{

    public PlanetsAdapter(Context context, ArrayList<Planet> planets) {
        super(context, 0, planets);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        Database database = new Database(getContext());

        // Get the data item for this position
        Planet planet = getItem(i);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_row, viewGroup, false);
        }
        // Lookup view for data population
        TextView plName = convertView.findViewById(R.id.text_view_planet_name);
        ImageView plImg = convertView.findViewById(R.id.image_view_planet);

        // Populate the data into the template view using the data object
        try {
            plName.setText(database.getPlanetAtIndex(i+1).getName());
            plImg.setImageResource(database.getPlanetAtIndex(i+1).getImage());
        }catch (Exception e){
            e.printStackTrace();
            Log.i("DB-Info", "Database is empty! - Adapter");
        }


        database.closeDb();

        // Return the completed view to render on screen
        return convertView;
    }
}
