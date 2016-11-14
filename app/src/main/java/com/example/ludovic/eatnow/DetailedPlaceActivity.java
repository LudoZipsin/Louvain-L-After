package com.example.ludovic.eatnow;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Calendar;

/**
 * Created by Ludovic Zipsin on 04/11/16.
 * Mail: ludovic.j.r.zipsin@gmail.com
 * Github: https://github.com/LudoZipsin
 */
public class DetailedPlaceActivity  extends AppCompatActivity implements OnMapReadyCallback {

    private double lat;
    private double lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // fetching info on the place to display
        lat = getIntent().getExtras().getDouble("lat");
        lon = getIntent().getExtras().getDouble("lon");
        setContentView(R.layout.detailed_activity);

        TextView placeName = (TextView)findViewById(R.id.name);
        placeName.setText(getIntent().getExtras().getString("name"));
        TextView placeDesc = (TextView)findViewById(R.id.description);
        placeDesc.setText(getIntent().getExtras().getString("desc"));

        TextView placeMonday = (TextView)findViewById(R.id.monday_time);
        placeMonday.setText(openClose(getIntent().getExtras().getInt("openmonday"), getIntent().getExtras().getInt("closemonday")));
        TextView placeTuesday = (TextView)findViewById(R.id.tuesday_time);
        placeTuesday.setText(openClose(getIntent().getExtras().getInt("opentuesday"), getIntent().getExtras().getInt("closetuesday")));
        TextView placeWednesday = (TextView)findViewById(R.id.wednesday_time);
        placeWednesday.setText(openClose(getIntent().getExtras().getInt("openwednesday"), getIntent().getExtras().getInt("closewednesday")));
        TextView placeThursday = (TextView)findViewById(R.id.thursday_time);
        placeThursday.setText(openClose(getIntent().getExtras().getInt("openthursday"), getIntent().getExtras().getInt("closethursday")));
        TextView placeFriday = (TextView)findViewById(R.id.friday_time);
        placeFriday.setText(openClose(getIntent().getExtras().getInt("openfriday"), getIntent().getExtras().getInt("closefriday")));
        TextView placeSaturday = (TextView)findViewById(R.id.saturday_time);
        placeSaturday.setText(openClose(getIntent().getExtras().getInt("opensaturday"), getIntent().getExtras().getInt("closesaturday")));
        TextView placeSunday = (TextView)findViewById(R.id.sunday_time);
        placeSunday.setText(openClose(getIntent().getExtras().getInt("opensunday"), getIntent().getExtras().getInt("closesunday")));

        TextView placePhone = (TextView)findViewById(R.id.phone);
        placePhone.setText(getIntent().getExtras().getString("phone"));

        TextView placeAddress = (TextView)findViewById(R.id.location);
        placeAddress.setText(getIntent().getExtras().getString("address"));

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mapFragment.getMap().addMarker(new MarkerOptions().position(new LatLng(lat,lon)).title(getIntent().getExtras().getString("name")));
        Toast.makeText(this, getIntent().getExtras().getString("name"), Toast.LENGTH_LONG).show();

    }

    public void navigation(View view){
        // TODO
    }

    private String formatTime(int time){
        if (time == -1) return "- - - - -";
        String format = "";
        int hour = time / 100;
        int minute = time % 100;
        if (hour >= 24){
            hour -= 24;
        }
        if (hour < 10){
            format += "0";
        }
        format += hour;
        format += "h";
        if (minute < 10){
            format += "0";
        }
        format += minute;
        return format;
    }

    private String openClose(int open, int close){
        return formatTime(open) + " - " + formatTime(close);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        CameraPosition placePosition = CameraPosition.builder()
                .target(new LatLng(lat,lon))
                .zoom(18)
                .bearing(0)
                .tilt(45)
                .build();

        map.moveCamera(CameraUpdateFactory.newCameraPosition(placePosition));

    }

}





