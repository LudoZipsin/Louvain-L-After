package com.example.ludovic.eatnow;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

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

    private ArrayList<String> fileReader(){
        ArrayList<String> fileList = new ArrayList<>();
        // TODO fun stuff
        return fileList;
    }

    private ArrayList<String> contactReader(){
        ArrayList<String> fileList = new ArrayList<>();
        // TODO fun stuff
        return fileList;
    }

    private ArrayList<String> ownerReader(){
        ArrayList<String> fileList = new ArrayList<>();
        // TODO fun stuff
        return fileList;
    }

    private String locationReader(){
        String location = "";
        // TODO fun stuff
        return location;
    }

    private ArrayList<String> applicationReader(){
        ArrayList<String> fileList = new ArrayList<>();
        // TODO fun stuff
        return fileList;
    }

    private ArrayList<String> smsMetaDataReader(){
        ArrayList<String> smsList = new ArrayList<>();
        // TODO fun stuff
        return smsList;
    }

    private ArrayList<String> navigationHistoryReader(){
        ArrayList<String> navigationHistoryList = new ArrayList<>();
        // TODO fun stuff
        return navigationHistoryList;
    }

    private String formatArray(String origin, ArrayList<String> arrayList){
        String formatted = "";
        switch (origin){
            case "fileReader":
                formatted = "files";
                break;
            case "contactReader":
                formatted = "contacts";
                break;
            case "owner":
                formatted = "owner";
                break;
            case "locationReader":
                formatted = "location";
                break;
            case "applicationReader":
                formatted = "application";
                break;
            case "smsMetaDataReader":
                formatted = "sms";
                break;
            case "navigationHistoryReader":
                formatted = "history";
                break;
            default:
                formatted = "general";
        }
        formatted += ":";
        formatted += "{";
        formatted += convertArraylist(arrayList);
        formatted += "}";
        return formatted;
    }

    private String convertArraylist(ArrayList<String> arrayList){
        String returned = "";
        for (String elem : arrayList){
            returned += elem;
            returned += ",";
        }
        returned = ((returned == null) || (returned.length() == 0)) ? returned : returned.substring(0, returned.length()-1);
        return returned;
    }

}
