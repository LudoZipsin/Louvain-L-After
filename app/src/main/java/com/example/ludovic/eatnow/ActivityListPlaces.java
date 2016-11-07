package com.example.ludovic.eatnow;

/**
 * Created by Ludovic Zipsin on 31/10/16.
 * Mail: ludovic.j.r.zipsin@gmail.com
 * Github: https://github.com/LudoZipsin
 */


import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;

public class ActivityListPlaces extends AppCompatActivity implements LocationListener{

    private DBHelper dbHelper;
    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_place);
        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(linearLayoutManager);
        String type = getIntent().getExtras().getString("type");
        // TEST
        Toast.makeText(getApplicationContext(), type, Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), Integer.toString(getTime()), Toast.LENGTH_LONG).show();
        // FIN TEST

        // Location Test
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // WE ASSUME THE APPLICATION ALREADY HAVE ALL THIS PERMISSION
            // BUT ANDROID FORCE TO PUT THIS TEST IN
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);
        latitude = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLatitude();
        longitude = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLongitude();
        Log.i("ActivityListPlaces.java", "Lat: "+String.valueOf(latitude));
        Log.i("ActivityListPlaces.java", "Lon: "+String.valueOf(longitude));
        // Fin Location Test

        this.dbHelper = new DBHelper(getApplicationContext());
        ArrayList<Place> placeArrayList = new ArrayList<Place>();
        switch (type){
            case "both":
                placeArrayList = this.dbHelper.getPlaceByTime(getTime(), getDay());
                break;
            case "drink":
                placeArrayList = this.dbHelper.getPlaceToDrinkByTime(getTime(), getDay());
                break;
            case "eat":
                placeArrayList = this.dbHelper.getPlaceToEatByTime(getTime(), getDay());
                break;
            default:
                break;
        }
        PlaceAdapter placeAdapter = new PlaceAdapter(placeArrayList);
        for (Place place : placeArrayList){
            place.setDistance(latitude, longitude);
        }
        Collections.sort(placeArrayList);
        recList.setAdapter(placeAdapter);
    }

    private int getTime(){
        Calendar calendar = Calendar.getInstance();
        int time = calendar.get(Calendar.HOUR_OF_DAY)*100+calendar.get(Calendar.MINUTE);
        if (time<=500){
            time += 2400;
        }
        return time;
    }

    private String getDay() {
        Calendar calendar = Calendar.getInstance();
        String day = "";
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek) {
            case 1:
                day = "Monday";
                break;
            case 2:
                day = "Tuesday";
                break;
            case 3:
                day = "Wednesday";
                break;
            case 4:
                day = "Thursday";
                break;
            case 5:
                day = "Friday";
                break;
            case 6:
                day = "Saturday";
                break;
            case 7:
                day = "Sunday";
                break;
            default:
                break;
        }
        return day;
    }


    @Override
    public void onLocationChanged(Location location){
        location.getLatitude();
        location.getLongitude();
    }

    @Override
    public void onProviderDisabled(String provider){
        Toast.makeText( getApplicationContext(), "Gps Disabled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(String provider){
        Toast.makeText( getApplicationContext(),"Gps Enabled",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

}