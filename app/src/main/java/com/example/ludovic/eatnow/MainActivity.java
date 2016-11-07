package com.example.ludovic.eatnow;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // TEST
        DBHelper dbHelper = new DBHelper(getApplicationContext());
        String text = Integer.toString(dbHelper.numberOfRowsPlaces());
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
        // FIN TEST
    }

    public void getEatAndDrink(View view){
        String tag = view.getTag().toString();
        Intent intent = new Intent(this, ActivityListPlaces.class);
        switch (tag){
            case "eat":
                intent.putExtra("type","eat");
                break;
            case "drink":
                intent.putExtra("type","drink");
                break;
            case "both":
                intent.putExtra("type","both");
                break;
            default:
                break;
        }
        startActivity(intent);
    }
}
