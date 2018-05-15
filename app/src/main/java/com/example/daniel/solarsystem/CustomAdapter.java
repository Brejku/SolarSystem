package com.example.daniel.solarsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.daniel.solarsystem.Database.planetImages;
import static com.example.daniel.solarsystem.Database.planetNames;

/**
 * Created by Brejku on 2018-04-01.
 */

class CustomAdapter extends BaseAdapter {

    Context context;

    public CustomAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return planetImages.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.custom_list_row,null);

        ImageView imageView = view.findViewById(R.id.imageViewPlanet);

        TextView textViewName = view.findViewById(R.id.textViewPlanetName);

        imageView.setImageResource(planetImages[i]);
        textViewName.setText(planetNames[i]);

        return view;
    }
}
