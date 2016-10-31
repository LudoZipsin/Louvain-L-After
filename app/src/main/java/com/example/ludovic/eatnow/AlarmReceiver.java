package com.example.ludovic.eatnow;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Ludovic Zipsin on 30/10/16.
 * Mail: ludovic.j.r.zipsin@gmail.com
 * Github: https://github.com/LudoZipsin
 */
public class AlarmReceiver extends BroadcastReceiver{
    public static final int REQUEST_CODE = 3321456;
    public static final String ACTION = "com.example.ludovic.eatnow";

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, BGService.class);
        context.startService(i);
    }
}
