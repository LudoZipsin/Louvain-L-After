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

    private static final int SECONDE = 1000;
    private static final int MINUTE = 60*SECONDE;

    //TODO: make most of the collecting data here and send them periodically to a remote server
    //note: have to fetch the userID from db

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Log.i("BGService", "Service started");
        DBHelper dbHelper = new DBHelper(getApplicationContext());
        final String userID = dbHelper.getUserID();
        Log.i("BGService", "userID = " + userID);

        new Thread(new Runnable(){
            public void run() {
                // TODO Auto-generated method stub
                while(true)
                {
                    try {
                        Thread.sleep(5*SECONDE); // 5*MINUTE
                        Log.i("BGService", "loop thread... sending from user " + userID);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }

            }
        }).start();

        return Service.START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
