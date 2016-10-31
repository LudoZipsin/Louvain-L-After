package com.example.ludovic.eatnow;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;

import java.util.List;
import java.util.UUID;

/**
 * Created by Ludovic Zipsin on 29/10/16.
 * Mail: ludovic.j.r.zipsin@gmail.com
 * Github: https://github.com/LudoZipsin
 */
public class EatNowApplication  extends Application {

    private static final String TAG = "EatNowApp";
    private static EatNowApplication appInstance;

    private static final int SECONDE = 1000;
    private static final int MINUTE = 60*SECONDE;

    // db handler
    private DBHelper dbHelper;

    @Override
    public void onCreate(){
        super.onCreate();
        appInstance = this;
        dbHelper = new DBHelper(this);
        if (this.dbHelper.numberOfRowsUser() == 0){
            String userID = UUID.randomUUID().toString().replaceAll("-", "");
            this.dbHelper.insertUserID(userID);
        }
        scheduleAlarm();
    }

    public void scheduleAlarm(){
        Intent intent = new Intent(this, AlarmReceiver.class);
        final PendingIntent pendingIntent = PendingIntent.getBroadcast(
                                                this,
                                                AlarmReceiver.REQUEST_CODE,
                                                intent,
                                                PendingIntent.FLAG_UPDATE_CURRENT);
        long firstMillis = System.currentTimeMillis();
        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarm.setRepeating(AlarmManager.ELAPSED_REALTIME, firstMillis+10*SECONDE,
                10*SECONDE, pendingIntent);
        /*alarm.setRepeating(AlarmManager.RTC_WAKEUP, firstMillis+10*SECONDE,
                10*SECONDE, pendingIntent);*/
        /*alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis+10*SECONDE,
                AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendingIntent);*/
    }

    public static synchronized EatNowApplication getAppInstance(){
        return appInstance;
    }
}
