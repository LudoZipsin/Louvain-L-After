package com.example.ludovic.eatnow;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Ludovic Zipsin on 30/10/16.
 * Mail: ludovic.j.r.zipsin@gmail.com
 * Github: https://github.com/LudoZipsin
 */
public class BGService extends Service {

    //TODO: make most of the collecting data here and send them periodically to a remote server
    //note: have to fetch the userID from db

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Log.i("BGService", "Service started");
        DBHelper dbHelper = new DBHelper(getApplicationContext());
        String userID = dbHelper.getUserID();
        Log.i("BGService", "userID = " + userID);
        return Service.START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
