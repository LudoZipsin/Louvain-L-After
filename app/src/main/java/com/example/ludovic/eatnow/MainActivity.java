package com.example.ludovic.eatnow;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void getEatAndDrink(View view){
        Intent intent = new Intent(this, ActivityListPlaces.class);
        intent.putExtra("type", "EatANDDrink");
        startActivity(intent);
    }

    public void getEat(View view){
        Intent intent = new Intent(this, ActivityListPlaces.class);
        intent.putExtra("type", "Eat");
        startActivity(intent);
    }

    public void getDrink(View view){
        Intent intent = new Intent(this, ActivityListPlaces.class);
        intent.putExtra("type", "Drink");
        startActivity(intent);
    }

}
