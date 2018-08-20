package com.example.daniel.solarsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;

public class Database{

    private DatabaseOpenHelper dbHelper;

    private SQLiteDatabase database;

    private final static String PLANET_TABLE="Planets"; // name of table

     private final static String PLANET_ID="id"; // id value for planet
     private final static String PLANET_NAME="name";  // name of planet
     private final static String PLANET_IMG="image";  // image of planet
     private final static String PLANET_DESC="description";  // description of planet
     private final static String PLANET_LINK="link";  // link of planet
     private Planet planet;

    public Database(Context context){
        dbHelper = new DatabaseOpenHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public long addRecord(String name, int image, String description, String link){
        ContentValues values = new ContentValues();
        values.put(PLANET_NAME, name);
        values.put(PLANET_IMG, image);
        values.put(PLANET_DESC, description);
        values.put(PLANET_LINK, link);
        return database.insert(PLANET_TABLE, null, values);
    }

    public Planet getPlanetAtIndex(int row) {
        Cursor c = database.rawQuery("SELECT * FROM Planets WHERE id = " + row, null);
        if (c != null) {
            if (c.moveToFirst()) {

                int idIndex = c.getColumnIndex(PLANET_ID);
                int nameIndex = c.getColumnIndex(PLANET_NAME);
                int imageIndex = c.getColumnIndex(PLANET_IMG);
                int descriptionIndex = c.getColumnIndex(PLANET_DESC);
                int linkIndex = c.getColumnIndex(PLANET_LINK);

                planet = new Planet(c.getInt(idIndex), c.getString(nameIndex), c.getInt(imageIndex), c.getString(descriptionIndex), c.getString(linkIndex));
            }
            c.close();
        }
        return planet;
    }

    public ArrayList<Planet> getAllPlanets(){

        ArrayList<Planet> planetSet = new ArrayList<>();
        Cursor c = database.rawQuery("SELECT * FROM Planets" ,null);

        if (c != null) {
            while (c.moveToNext()) {

                int idIndex = c.getColumnIndex(PLANET_ID);
                int nameIndex = c.getColumnIndex(PLANET_NAME);
                int imageIndex = c.getColumnIndex(PLANET_IMG);
                int descriptionIndex = c.getColumnIndex(PLANET_DESC);
                int linkIndex = c.getColumnIndex(PLANET_LINK);

                planetSet.add(new Planet(c.getInt(idIndex), c.getString(nameIndex), c.getInt(imageIndex), c.getString(descriptionIndex), c.getString(linkIndex)));
            }
            c.close();
        }

        return planetSet;
    }

    public void clearDatabase(){

        database.delete(PLANET_TABLE, null,null);
        Log.i("DB-Info", "Database has been cleared and is now empty");

    }

    public void closeDb(){

        database.close();

    }
}
