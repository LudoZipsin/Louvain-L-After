package com.example.ludovic.eatnow;

/**
 * Created by Ludovic Zipsin on 31/10/16.
 * Mail: ludovic.j.r.zipsin@gmail.com
 * Github: https://github.com/LudoZipsin
 */


import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
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
import java.util.Collections;

public class ActivityListPlaces extends AppCompatActivity implements LocationListener{

    private DBHelper dbHelper;
    private double latitude;
    private double longitude;
    private LocationManager locationManagerGPS;
    private LocationManager locationManagerNetwork;
    private EatNowApplication appInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appInstance = EatNowApplication.getAppInstance();
        
        this.dbHelper = appInstance.getDbHelper();

        setContentView(R.layout.activity_list_place);
        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(linearLayoutManager);
        String type = getIntent().getExtras().getString("type");
        // TEST
        Log.d("ActivityListPlace", "ok there");
        Toast.makeText(getApplicationContext(), type, Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), Integer.toString(getTime()), Toast.LENGTH_LONG).show();
        // FIN TEST

        Location location = getLocation();

        this.dbHelper = new DBHelper(getApplicationContext());
        ArrayList<Place> placeArrayList = new ArrayList<Place>();
        switch (type) {
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
        placeAdapter.setActivity((Activity) this);


        if (location == null){
            Log.d("ActivityListPlace", "Yep... that's null");
            Toast.makeText(getApplicationContext(), "Unable to find location. " + generateStringPlace(getIntent().getExtras().getString("type")) + " will be list by alphabetical order.", Toast.LENGTH_LONG).show();
        } else {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            Log.i("ActivityListPlaces.java", "Lat: " + String.valueOf(latitude));
            Log.i("ActivityListPlaces.java", "Lon: " + String.valueOf(longitude));
            // Fin Location Test
            for (Place place : placeArrayList) {
                place.setDistance(latitude, longitude);
            }
            Collections.sort(placeArrayList);
        }
        recList.setAdapter(placeAdapter);
    }

    private String generateStringPlace(String type){
        if (type.equals("both")) return "Bars and Restaurants";
        if (type.equals("drink")) return "Bars";
        if (type.equals("eat")) return "Restaurants";
        return null;
    }

    private Location getLocation(){
        locationManagerGPS = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationManagerNetwork = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // WE ASSUME THE APPLICATION ALREADY HAVE ALL THIS PERMISSION
            // BUT ANDROID FORCE TO PUT THIS TEST IN
            return null;
        }
        locationManagerGPS.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);
        locationManagerNetwork.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,this);

        Location locationGPS = locationManagerGPS.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Location locationNetwork = locationManagerGPS.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        return (locationGPS != null) ? locationGPS : locationNetwork;
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