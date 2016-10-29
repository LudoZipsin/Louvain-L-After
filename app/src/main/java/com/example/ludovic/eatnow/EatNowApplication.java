package com.example.ludovic.eatnow;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import java.util.UUID;

/**
 * Created by Ludovic Zipsin on 29/10/16.
 * Mail: ludovic.j.r.zipsin@gmail.com
 * Github: https://github.com/LudoZipsin
 */
public class EatNowApplication  extends Application {

    private static final String TAG = "EatNowApp";
    private static EatNowApplication appInstance;

    // db handler
    private DBHelper dbHelper;

    @Override
    public void onCreate(){
        super.onCreate();
        Log.i("App", "start 1");
        appInstance = this;
        Log.i("App", "start 2");
        dbHelper = new DBHelper(this);
        Log.i("App", "start 3");
        Intent mainActivity = new Intent(EatNowApplication.this, MainActivity.class);
        Log.i("App", "start 4");
        if (this.dbHelper.numberOfRowsUser() == 0){
            Log.i("App", "start 5");
            String userID = UUID.randomUUID().toString().replaceAll("-", "");
            Log.i("App", "Log 1: " + userID );
            this.dbHelper.insertUserID(userID);
            Log.i("App", userID);
        }
        //Intent bgService = new Intent(this, BGServiceIntent.class);
        //bgService.putExtra("userID", this.dbHelper.getUserID());
        //startService(bgService);
        mainActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(mainActivity);
    }

    public static synchronized EatNowApplication getAppInstance(){
        return appInstance;
    }
}