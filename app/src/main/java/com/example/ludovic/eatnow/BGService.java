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
public class BGService extends IntentService {

    //TODO: make most of the collecting data here and send them periodically to a remote server
    //note: have to fetch the userID from db

    public BGService(){
        super("BGService");
    }

    @Override
    public void onHandleIntent(Intent intent){
        Log.i("BGService", "Service started");
        //DBHelper dbHelper = new DBHelper(getApplicationContext());
        //DBHelper dbHelper = new DBHelper(null);
        //final String userID = dbHelper.getUserID();
        Log.i("BGService", "userID = " + "nope");

        new Thread(new Runnable(){
            public void run() {
                //TODO task to send data to remote server
                Log.i("BGService", "Thread on start... userID = " + "nopenope");
                return;
            }
        }).start();
    }

}
