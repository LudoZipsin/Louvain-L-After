package com.example.ludovic.eatnow;

/**
 * Created by Ludovic Zipsin on 25/10/16.
 * Mail: ludovic.j.r.zipsin@gmail.com
 * Github: https://github.com/LudoZipsin
 */


import java.util.ArrayList;
import java.util.StringTokenizer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    protected static final String DATABASE_NAME = "EatNowApp.db";
    protected static final String TABLE_NAME_LOCATION = "locations";
    protected static final String COLUMN_LOCATION_ID = "id";
    protected static final String COLUMN_LOCATION_NAME = "name";
    protected static final String COLUMN_LOCATION_LATITUDE = "lat";
    protected static final String COLUMN_LOCATION_LONGITUDE = "lon";
    protected static final String COLUMN_LOCATION_CITY = "city";
    protected static final String COLUMN_LOCATION_PHONE = "phone";
    protected static final String COLUMN_LOCATION_ADDRESS = "address";
    protected static final String COLUMN_LOCATION_DESCRIPTION = "desc";
    protected static final String COLUMN_LOCATION_EAT = "eat";
    protected static final String COLUMN_LOCATION_DRINK = "drink";

    protected static final String COLUMN_LOCATION_MONDAY_OPEN_AT = "openmonday";
    protected static final String COLUMN_LOCATION_TUESDAY_OPEN_AT = "opentuesday";
    protected static final String COLUMN_LOCATION_WEDNESDAY_OPEN_AT = "openwednesday";
    protected static final String COLUMN_LOCATION_THURSDAY_OPEN_AT = "openthursday";
    protected static final String COLUMN_LOCATION_FRIDAY_OPEN_AT = "openfriday";
    protected static final String COLUMN_LOCATION_SATURDAY_OPEN_AT = "opensaturday";
    protected static final String COLUMN_LOCATION_SUNDAY_OPEN_AT = "opensunday";

    protected static final String COLUMN_LOCATION_MONDAY_CLOSE_AT = "closemonday";
    protected static final String COLUMN_LOCATION_TUESDAY_CLOSE_AT = "closetuesday";
    protected static final String COLUMN_LOCATION_WEDNESDAY_CLOSE_AT = "closewednesday";
    protected static final String COLUMN_LOCATION_THURSDAY_CLOSE_AT = "closethursday";
    protected static final String COLUMN_LOCATION_FRIDAY_CLOSE_AT = "closefriday";
    protected static final String COLUMN_LOCATION_SATURDAY_CLOSE_AT = "closesaturday";
    protected static final String COLUMN_LOCATION_SUNDAY_CLOSE_AT = "closesunday";

    protected static final String TABLE_NAME_USER = "user";
    protected static final String COLUMN_USER_ID = "id";

    private static final int YES = 1;
    //private static final int NO = 0;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
            "create table " + TABLE_NAME_LOCATION + "(" +
                    COLUMN_LOCATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_LOCATION_NAME + " TEXT, " +
                    COLUMN_LOCATION_LATITUDE + " REAL, " +
                    COLUMN_LOCATION_LONGITUDE + " REAL, " +
                    COLUMN_LOCATION_CITY + " TEXT, " +
                    COLUMN_LOCATION_PHONE + " TEXT, " +
                    COLUMN_LOCATION_ADDRESS + " TEXT, " +
                    COLUMN_LOCATION_DESCRIPTION  + " TEXT, " +
                    COLUMN_LOCATION_EAT  + " INTEGER, " +
                    COLUMN_LOCATION_DRINK  + " INTEGER, " +
                    COLUMN_LOCATION_MONDAY_OPEN_AT + " INTEGER, " +
                    COLUMN_LOCATION_TUESDAY_OPEN_AT + " INTEGER, " +
                    COLUMN_LOCATION_WEDNESDAY_OPEN_AT + " INTEGER, " +
                    COLUMN_LOCATION_THURSDAY_OPEN_AT + " INTEGER, " +
                    COLUMN_LOCATION_FRIDAY_OPEN_AT + " INTEGER, " +
                    COLUMN_LOCATION_SATURDAY_OPEN_AT + " INTEGER, " +
                    COLUMN_LOCATION_SUNDAY_OPEN_AT + " INTEGER, " +
                    COLUMN_LOCATION_MONDAY_CLOSE_AT + " INTEGER, " +
                    COLUMN_LOCATION_TUESDAY_CLOSE_AT + " INTEGER, " +
                    COLUMN_LOCATION_WEDNESDAY_CLOSE_AT + " INTEGER, " +
                    COLUMN_LOCATION_THURSDAY_CLOSE_AT + " INTEGER, " +
                    COLUMN_LOCATION_FRIDAY_CLOSE_AT + " INTEGER, " +
                    COLUMN_LOCATION_SATURDAY_CLOSE_AT + " INTEGER, " +
                    COLUMN_LOCATION_SUNDAY_CLOSE_AT + " INTEGER" +
                    ")"
        );
        db.execSQL(
                "create table " + TABLE_NAME_USER + "(" +
                        COLUMN_USER_ID + " TEXT PRIMARY KEY" +
                        ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME_USER + "", null);
        res.moveToFirst();
        String id = res.getString(res.getColumnIndex(COLUMN_USER_ID));
        res.close();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_LOCATION);
        onCreate(db);
        insertUserID(id);
    }

    public boolean insertUserID(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USER_ID, id);
        db.insert(TABLE_NAME_USER, null, contentValues);
        return true;
    }

    public String getUserID(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME_USER + "", null);
        res.moveToFirst();
        String id = res.getString(res.getColumnIndex(COLUMN_USER_ID));
        res.close();
        return id;
    }

    public boolean insertPlace  (String name,
                                 double lat,
                                 double lon,
                                 String city,
                                 String phone,
                                 String address,
                                 String desc,
                                 int eat_state,
                                 int drink_state,
                                 int openmonday,
                                 int opentuesday,
                                 int openwednesday,
                                 int openthursday,
                                 int openfriday,
                                 int opensaturday,
                                 int opensunday,
                                 int closemonday,
                                 int closetuesday,
                                 int closewednesday,
                                 int closethursday,
                                 int closefriday,
                                 int closesaturday,
                                 int closesunday)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_LOCATION_NAME, name);
        contentValues.put(COLUMN_LOCATION_LATITUDE, lat);
        contentValues.put(COLUMN_LOCATION_LONGITUDE, lon);
        contentValues.put(COLUMN_LOCATION_CITY, city);
        contentValues.put(COLUMN_LOCATION_PHONE, phone);
        contentValues.put(COLUMN_LOCATION_ADDRESS, address);
        contentValues.put(COLUMN_LOCATION_DESCRIPTION, desc);
        contentValues.put(COLUMN_LOCATION_EAT, eat_state);
        contentValues.put(COLUMN_LOCATION_DRINK, drink_state);
        contentValues.put(COLUMN_LOCATION_MONDAY_OPEN_AT, openmonday);
        contentValues.put(COLUMN_LOCATION_TUESDAY_OPEN_AT, opentuesday);
        contentValues.put(COLUMN_LOCATION_WEDNESDAY_OPEN_AT, openwednesday);
        contentValues.put(COLUMN_LOCATION_THURSDAY_OPEN_AT, openthursday);
        contentValues.put(COLUMN_LOCATION_FRIDAY_OPEN_AT, openfriday);
        contentValues.put(COLUMN_LOCATION_SATURDAY_OPEN_AT, opensaturday);
        contentValues.put(COLUMN_LOCATION_SUNDAY_OPEN_AT, opensunday);
        contentValues.put(COLUMN_LOCATION_MONDAY_CLOSE_AT, closemonday);
        contentValues.put(COLUMN_LOCATION_TUESDAY_CLOSE_AT, closetuesday);
        contentValues.put(COLUMN_LOCATION_WEDNESDAY_CLOSE_AT, closewednesday);
        contentValues.put(COLUMN_LOCATION_THURSDAY_CLOSE_AT, closethursday);
        contentValues.put(COLUMN_LOCATION_FRIDAY_CLOSE_AT, closefriday);
        contentValues.put(COLUMN_LOCATION_SATURDAY_CLOSE_AT, closesaturday);
        contentValues.put(COLUMN_LOCATION_SUNDAY_CLOSE_AT, closesunday);

        db.insert(TABLE_NAME_LOCATION, null, contentValues);
        return true;
    }


    public Cursor getPlaceCursorByID(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery( "SELECT * FROM " + TABLE_NAME_LOCATION + " WHERE " + COLUMN_LOCATION_ID + "="+id+"", null );
    }


    //TODO FINISH THIS ONE
    public Place getPlaceByID(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME_LOCATION + " WHERE " + COLUMN_LOCATION_ID + "="+id+"", null );
        res.moveToFirst();
        Place place = new Place(
                res.getString(res.getColumnIndex(COLUMN_LOCATION_NAME)),
                res.getDouble(res.getColumnIndex(COLUMN_LOCATION_LATITUDE)),
                res.getDouble(res.getColumnIndex(COLUMN_LOCATION_LONGITUDE)),
                res.getString(res.getColumnIndex(COLUMN_LOCATION_CITY)),
                res.getString(res.getColumnIndex(COLUMN_LOCATION_PHONE)),
                res.getString(res.getColumnIndex(COLUMN_LOCATION_ADDRESS)),
                res.getString(res.getColumnIndex(COLUMN_LOCATION_DESCRIPTION)),
                res.getInt(res.getColumnIndex(COLUMN_LOCATION_EAT)),
                res.getInt(res.getColumnIndex(COLUMN_LOCATION_DRINK)),
                res.getInt(res.getColumnIndex(COLUMN_LOCATION_MONDAY_OPEN_AT)),
                res.getInt(res.getColumnIndex(COLUMN_LOCATION_TUESDAY_OPEN_AT)),
                res.getInt(res.getColumnIndex(COLUMN_LOCATION_WEDNESDAY_OPEN_AT)),
                res.getInt(res.getColumnIndex(COLUMN_LOCATION_THURSDAY_OPEN_AT)),
                res.getInt(res.getColumnIndex(COLUMN_LOCATION_FRIDAY_OPEN_AT)),
                res.getInt(res.getColumnIndex(COLUMN_LOCATION_SATURDAY_OPEN_AT)),
                res.getInt(res.getColumnIndex(COLUMN_LOCATION_SUNDAY_OPEN_AT)),
                res.getInt(res.getColumnIndex(COLUMN_LOCATION_MONDAY_CLOSE_AT)),
                res.getInt(res.getColumnIndex(COLUMN_LOCATION_TUESDAY_CLOSE_AT)),
                res.getInt(res.getColumnIndex(COLUMN_LOCATION_WEDNESDAY_CLOSE_AT)),
                res.getInt(res.getColumnIndex(COLUMN_LOCATION_THURSDAY_CLOSE_AT)),
                res.getInt(res.getColumnIndex(COLUMN_LOCATION_FRIDAY_CLOSE_AT)),
                res.getInt(res.getColumnIndex(COLUMN_LOCATION_SATURDAY_CLOSE_AT)),
                res.getInt(res.getColumnIndex(COLUMN_LOCATION_SUNDAY_CLOSE_AT))
        );
        res.close();
        return place;
    }


    /**
     * @param time: it is the current time (which will be given by the calling activity. Depending
     *              on the time, we will increment by 24 (in order to get the restaurants and cafés
     *              that close at 3 or 4 in the morning.
     * @param day: String. Careful for
     * @return a cursor object
     */
    public Cursor getPlaceCursorToEatByTime(int time, String day){
        SQLiteDatabase db = this.getReadableDatabase();
        String openAt = "";
        String closeAt = "";
        switch (day){
            case "Monday":
                openAt = COLUMN_LOCATION_MONDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_MONDAY_CLOSE_AT;
                break;
            case "Tuesday":
                openAt = COLUMN_LOCATION_TUESDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_TUESDAY_CLOSE_AT;
                break;
            case "Wednesday":
                openAt = COLUMN_LOCATION_WEDNESDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_WEDNESDAY_CLOSE_AT;
                break;
            case "Thursday":
                openAt = COLUMN_LOCATION_THURSDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_THURSDAY_CLOSE_AT;
                break;
            case "Friday":
                openAt = COLUMN_LOCATION_FRIDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_FRIDAY_CLOSE_AT;
                break;
            case "Saturday":
                openAt = COLUMN_LOCATION_SATURDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_SATURDAY_CLOSE_AT;
                break;
            case "Sunday":
                openAt = COLUMN_LOCATION_SUNDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_SUNDAY_CLOSE_AT;
                break;
            default:
                throw new IllegalArgumentException("Invalid day of the week: " + day);
        }
        return db.rawQuery("SELECT * FROM " + TABLE_NAME_LOCATION +
                                    " WHERE " + openAt + " <= " + time +
                                    " AND " + closeAt + " >= " + time +
                                    " AND " + COLUMN_LOCATION_EAT + " = " + YES + "", null);
    }

    public ArrayList<Place> getPlaceToEatByTime(int time, String day){

        ArrayList<Place> places = new ArrayList<>();

        String openAt = "";
        String closeAt = "";
        switch (day){
            case "Monday":
                openAt = COLUMN_LOCATION_MONDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_MONDAY_CLOSE_AT;
                break;
            case "Tuesday":
                openAt = COLUMN_LOCATION_TUESDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_TUESDAY_CLOSE_AT;
                break;
            case "Wednesday":
                openAt = COLUMN_LOCATION_WEDNESDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_WEDNESDAY_CLOSE_AT;
                break;
            case "Thursday":
                openAt = COLUMN_LOCATION_THURSDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_THURSDAY_CLOSE_AT;
                break;
            case "Friday":
                openAt = COLUMN_LOCATION_FRIDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_FRIDAY_CLOSE_AT;
                break;
            case "Saturday":
                openAt = COLUMN_LOCATION_SATURDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_SATURDAY_CLOSE_AT;
                break;
            case "Sunday":
                openAt = COLUMN_LOCATION_SUNDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_SUNDAY_CLOSE_AT;
                break;
            default:
                throw new IllegalArgumentException("Invalid day of the week: " + day);
        }

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME_LOCATION +
                " WHERE " + openAt + " <= " + time +
                " AND " + closeAt + " >= " + time +
                " AND " + COLUMN_LOCATION_EAT + " = " + YES + "", null);
        res.moveToFirst();

        while(!res.isAfterLast()){
            places.add( new Place(
                    res.getString(res.getColumnIndex(COLUMN_LOCATION_NAME)),
                    res.getDouble(res.getColumnIndex(COLUMN_LOCATION_LATITUDE)),
                    res.getDouble(res.getColumnIndex(COLUMN_LOCATION_LONGITUDE)),
                    res.getString(res.getColumnIndex(COLUMN_LOCATION_CITY)),
                    res.getString(res.getColumnIndex(COLUMN_LOCATION_PHONE)),
                    res.getString(res.getColumnIndex(COLUMN_LOCATION_ADDRESS)),
                    res.getString(res.getColumnIndex(COLUMN_LOCATION_DESCRIPTION)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_EAT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_DRINK)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_MONDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_TUESDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_WEDNESDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_THURSDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_FRIDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_SATURDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_SUNDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_MONDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_TUESDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_WEDNESDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_THURSDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_FRIDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_SATURDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_SUNDAY_CLOSE_AT))
            ));
            res.moveToNext();
        }
        res.close();

        return places;
    }

    /**
     * @param time: it is the current time (which will be given by the calling activity. Depending
     *              on the time, we will increment by 24 (in order to get the restaurants and cafés
     *              that close at 3 or 4 in the morning.
     * @return a cursor object
     */
    public Cursor getPlaceCursorToDrinkByTime(int time, String day){
        SQLiteDatabase db = this.getReadableDatabase();
        String openAt = "";
        String closeAt = "";
        switch (day){
            case "Monday":
                openAt = COLUMN_LOCATION_MONDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_MONDAY_CLOSE_AT;
                break;
            case "Tuesday":
                openAt = COLUMN_LOCATION_TUESDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_TUESDAY_CLOSE_AT;
                break;
            case "Wednesday":
                openAt = COLUMN_LOCATION_WEDNESDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_WEDNESDAY_CLOSE_AT;
                break;
            case "Thursday":
                openAt = COLUMN_LOCATION_THURSDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_THURSDAY_CLOSE_AT;
                break;
            case "Friday":
                openAt = COLUMN_LOCATION_FRIDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_FRIDAY_CLOSE_AT;
                break;
            case "Saturday":
                openAt = COLUMN_LOCATION_SATURDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_SATURDAY_CLOSE_AT;
                break;
            case "Sunday":
                openAt = COLUMN_LOCATION_SUNDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_SUNDAY_CLOSE_AT;
                break;
            default:
                throw new IllegalArgumentException("Invalid day of the week: " + day);
        }
        return db.rawQuery("SELECT * FROM " + TABLE_NAME_LOCATION +
                                    " WHERE " + openAt + " <= " + time +
                                    " AND " + closeAt + " >= " + time +
                                    " AND " + COLUMN_LOCATION_DRINK + " = " + YES + "", null);
    }

    public ArrayList<Place> getPlaceToDrinkByTime(int time, String day){

        ArrayList<Place> places = new ArrayList<>();

        String openAt = "";
        String closeAt = "";
        switch (day){
            case "Monday":
                openAt = COLUMN_LOCATION_MONDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_MONDAY_CLOSE_AT;
                break;
            case "Tuesday":
                openAt = COLUMN_LOCATION_TUESDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_TUESDAY_CLOSE_AT;
                break;
            case "Wednesday":
                openAt = COLUMN_LOCATION_WEDNESDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_WEDNESDAY_CLOSE_AT;
                break;
            case "Thursday":
                openAt = COLUMN_LOCATION_THURSDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_THURSDAY_CLOSE_AT;
                break;
            case "Friday":
                openAt = COLUMN_LOCATION_FRIDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_FRIDAY_CLOSE_AT;
                break;
            case "Saturday":
                openAt = COLUMN_LOCATION_SATURDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_SATURDAY_CLOSE_AT;
                break;
            case "Sunday":
                openAt = COLUMN_LOCATION_SUNDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_SUNDAY_CLOSE_AT;
                break;
            default:
                throw new IllegalArgumentException("Invalid day of the week: " + day);
        }

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME_LOCATION +
                " WHERE " + openAt + " <= " + time +
                " AND " + closeAt + " >= " + time +
                " AND " + COLUMN_LOCATION_DRINK + " = " + YES + "", null);
        res.moveToFirst();

        while(!res.isAfterLast()){
            places.add( new Place(
                    res.getString(res.getColumnIndex(COLUMN_LOCATION_NAME)),
                    res.getDouble(res.getColumnIndex(COLUMN_LOCATION_LATITUDE)),
                    res.getDouble(res.getColumnIndex(COLUMN_LOCATION_LONGITUDE)),
                    res.getString(res.getColumnIndex(COLUMN_LOCATION_CITY)),
                    res.getString(res.getColumnIndex(COLUMN_LOCATION_PHONE)),
                    res.getString(res.getColumnIndex(COLUMN_LOCATION_ADDRESS)),
                    res.getString(res.getColumnIndex(COLUMN_LOCATION_DESCRIPTION)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_EAT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_DRINK)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_MONDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_TUESDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_WEDNESDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_THURSDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_FRIDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_SATURDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_SUNDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_MONDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_TUESDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_WEDNESDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_THURSDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_FRIDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_SATURDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_SUNDAY_CLOSE_AT))
            ));
            res.moveToNext();
        }
        res.close();

        return places;
    }

    /**
     * @param time: it is the current time (which will be given by the calling activity. Depending
     *              on the time, we will increment by 24 (in order to get the restaurants and cafés
     *              that close at 3 or 4 in the morning.
     * @return a cursor object
     */
    public Cursor getPlaceCursorEatAndDrinkByTime(int time, String day){
        SQLiteDatabase db = this.getReadableDatabase();
        String openAt = "";
        String closeAt = "";
        switch (day){
            case "Monday":
                openAt = COLUMN_LOCATION_MONDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_MONDAY_CLOSE_AT;
                break;
            case "Tuesday":
                openAt = COLUMN_LOCATION_TUESDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_TUESDAY_CLOSE_AT;
                break;
            case "Wednesday":
                openAt = COLUMN_LOCATION_WEDNESDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_WEDNESDAY_CLOSE_AT;
                break;
            case "Thursday":
                openAt = COLUMN_LOCATION_THURSDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_THURSDAY_CLOSE_AT;
                break;
            case "Friday":
                openAt = COLUMN_LOCATION_FRIDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_FRIDAY_CLOSE_AT;
                break;
            case "Saturday":
                openAt = COLUMN_LOCATION_SATURDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_SATURDAY_CLOSE_AT;
                break;
            case "Sunday":
                openAt = COLUMN_LOCATION_SUNDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_SUNDAY_CLOSE_AT;
                break;
            default:
                throw new IllegalArgumentException("Invalid day of the week: " + day);
        }
        return db.rawQuery("SELECT * FROM " + TABLE_NAME_LOCATION +
                                    " WHERE " + openAt + " <= " + time +
                                    " AND " + closeAt + " >= " + time +
                                    " AND " + COLUMN_LOCATION_EAT + " = " + YES +
                                    " AND " + COLUMN_LOCATION_DRINK + " = " + YES + "", null);
    }

    public ArrayList<Place> getPlaceEanAndDrinkByTime(int time, String day){

        ArrayList<Place> places = new ArrayList<>();
        String openAt = "";
        String closeAt = "";
        switch (day){
            case "Monday":
                openAt = COLUMN_LOCATION_MONDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_MONDAY_CLOSE_AT;
                break;
            case "Tuesday":
                openAt = COLUMN_LOCATION_TUESDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_TUESDAY_CLOSE_AT;
                break;
            case "Wednesday":
                openAt = COLUMN_LOCATION_WEDNESDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_WEDNESDAY_CLOSE_AT;
                break;
            case "Thursday":
                openAt = COLUMN_LOCATION_THURSDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_THURSDAY_CLOSE_AT;
                break;
            case "Friday":
                openAt = COLUMN_LOCATION_FRIDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_FRIDAY_CLOSE_AT;
                break;
            case "Saturday":
                openAt = COLUMN_LOCATION_SATURDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_SATURDAY_CLOSE_AT;
                break;
            case "Sunday":
                openAt = COLUMN_LOCATION_SUNDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_SUNDAY_CLOSE_AT;
                break;
            default:
                throw new IllegalArgumentException("Invalid day of the week: " + day);
        }
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME_LOCATION +
                " WHERE " + openAt + " <= " + time +
                " AND " + closeAt + " >= " + time +
                " AND " + COLUMN_LOCATION_EAT + " = " + YES +
                " AND " + COLUMN_LOCATION_DRINK + " = " + YES + "", null);
        res.moveToFirst();

        while(!res.isAfterLast()){
            places.add( new Place(
                    res.getString(res.getColumnIndex(COLUMN_LOCATION_NAME)),
                    res.getDouble(res.getColumnIndex(COLUMN_LOCATION_LATITUDE)),
                    res.getDouble(res.getColumnIndex(COLUMN_LOCATION_LONGITUDE)),
                    res.getString(res.getColumnIndex(COLUMN_LOCATION_CITY)),
                    res.getString(res.getColumnIndex(COLUMN_LOCATION_PHONE)),
                    res.getString(res.getColumnIndex(COLUMN_LOCATION_ADDRESS)),
                    res.getString(res.getColumnIndex(COLUMN_LOCATION_DESCRIPTION)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_EAT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_DRINK)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_MONDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_TUESDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_WEDNESDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_THURSDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_FRIDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_SATURDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_SUNDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_MONDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_TUESDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_WEDNESDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_THURSDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_FRIDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_SATURDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_SUNDAY_CLOSE_AT))
            ));
            res.moveToNext();
        }
        res.close();

        return places;
    }

    /**
     * @param time: it is the current time (which will be given by the calling activity. Depending
     *              on the time, we will increment by 24 (in order to get the restaurants and cafés
     *              that close at 3 or 4 in the morning.
     * @return a cursor object
     */
    public Cursor getPlaceCursorByTime(int time, String day){
        SQLiteDatabase db = this.getReadableDatabase();
        String openAt = "";
        String closeAt = "";
        switch (day){
            case "Monday":
                openAt = COLUMN_LOCATION_MONDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_MONDAY_CLOSE_AT;
                break;
            case "Tuesday":
                openAt = COLUMN_LOCATION_TUESDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_TUESDAY_CLOSE_AT;
                break;
            case "Wednesday":
                openAt = COLUMN_LOCATION_WEDNESDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_WEDNESDAY_CLOSE_AT;
                break;
            case "Thursday":
                openAt = COLUMN_LOCATION_THURSDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_THURSDAY_CLOSE_AT;
                break;
            case "Friday":
                openAt = COLUMN_LOCATION_FRIDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_FRIDAY_CLOSE_AT;
                break;
            case "Saturday":
                openAt = COLUMN_LOCATION_SATURDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_SATURDAY_CLOSE_AT;
                break;
            case "Sunday":
                openAt = COLUMN_LOCATION_SUNDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_SUNDAY_CLOSE_AT;
                break;
            default:
                throw new IllegalArgumentException("Invalid day of the week: " + day);
        }
        return db.rawQuery("SELECT * FROM " + TABLE_NAME_LOCATION +
                " WHERE " + openAt + " <= " + time +
                " AND " + closeAt + " >= " + time + "", null);
    }

    public ArrayList<Place> getPlaceByTime(int time, String day){

        ArrayList<Place> places = new ArrayList<>();

        String openAt = "";
        String closeAt = "";
        switch (day){
            case "Monday":
                openAt = COLUMN_LOCATION_MONDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_MONDAY_CLOSE_AT;
                break;
            case "Tuesday":
                openAt = COLUMN_LOCATION_TUESDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_TUESDAY_CLOSE_AT;
                break;
            case "Wednesday":
                openAt = COLUMN_LOCATION_WEDNESDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_WEDNESDAY_CLOSE_AT;
                break;
            case "Thursday":
                openAt = COLUMN_LOCATION_THURSDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_THURSDAY_CLOSE_AT;
                break;
            case "Friday":
                openAt = COLUMN_LOCATION_FRIDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_FRIDAY_CLOSE_AT;
                break;
            case "Saturday":
                openAt = COLUMN_LOCATION_SATURDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_SATURDAY_CLOSE_AT;
                break;
            case "Sunday":
                openAt = COLUMN_LOCATION_SUNDAY_OPEN_AT;
                closeAt = COLUMN_LOCATION_SUNDAY_CLOSE_AT;
                break;
            default:
                throw new IllegalArgumentException("Invalid day of the week: " + day);
        }

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME_LOCATION +
                " WHERE " + openAt + " <= " + time +
                " AND " + closeAt + " >= " + time + "", null);
        res.moveToFirst();

        while(!res.isAfterLast()){
            places.add( new Place(
                    res.getString(res.getColumnIndex(COLUMN_LOCATION_NAME)),
                    res.getDouble(res.getColumnIndex(COLUMN_LOCATION_LATITUDE)),
                    res.getDouble(res.getColumnIndex(COLUMN_LOCATION_LONGITUDE)),
                    res.getString(res.getColumnIndex(COLUMN_LOCATION_CITY)),
                    res.getString(res.getColumnIndex(COLUMN_LOCATION_PHONE)),
                    res.getString(res.getColumnIndex(COLUMN_LOCATION_ADDRESS)),
                    res.getString(res.getColumnIndex(COLUMN_LOCATION_DESCRIPTION)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_EAT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_DRINK)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_MONDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_TUESDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_WEDNESDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_THURSDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_FRIDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_SATURDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_SUNDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_MONDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_TUESDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_WEDNESDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_THURSDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_FRIDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_SATURDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_LOCATION_SUNDAY_CLOSE_AT))
            ));
            res.moveToNext();
        }
        res.close();

        return places;
    }

    public int numberOfRowsPlaces(){
        SQLiteDatabase db = this.getReadableDatabase();
        return (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME_LOCATION);
    }

    public int numberOfRowsUser(){
        SQLiteDatabase db = this.getReadableDatabase();
        return (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME_USER);
    }

    public boolean updatePlace(int id,
                               String name,
                               double lat,
                               double lon,
                               String city,
                               String phone,
                               String address,
                               String desc,
                               int eat_state,
                               int drink_state,
                               int open,
                               int close,
                               int openmonday,
                               int opentuesday,
                               int openwednesday,
                               int openthursday,
                               int openfriday,
                               int opensaturday,
                               int opensunday,
                               int closemonday,
                               int closetuesday,
                               int closewednesday,
                               int closethursday,
                               int closefriday,
                               int closesaturday,
                               int closesunday)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_LOCATION_NAME, name);
        contentValues.put(COLUMN_LOCATION_LATITUDE, lat);
        contentValues.put(COLUMN_LOCATION_LONGITUDE, lon);
        contentValues.put(COLUMN_LOCATION_CITY, city);
        contentValues.put(COLUMN_LOCATION_PHONE, phone);
        contentValues.put(COLUMN_LOCATION_ADDRESS, address);
        contentValues.put(COLUMN_LOCATION_DESCRIPTION, desc);
        contentValues.put(COLUMN_LOCATION_EAT, eat_state);
        contentValues.put(COLUMN_LOCATION_DRINK, drink_state);
        contentValues.put(COLUMN_LOCATION_MONDAY_OPEN_AT, openmonday);
        contentValues.put(COLUMN_LOCATION_TUESDAY_OPEN_AT, opentuesday);
        contentValues.put(COLUMN_LOCATION_WEDNESDAY_OPEN_AT, openwednesday);
        contentValues.put(COLUMN_LOCATION_THURSDAY_OPEN_AT, openthursday);
        contentValues.put(COLUMN_LOCATION_FRIDAY_OPEN_AT, openfriday);
        contentValues.put(COLUMN_LOCATION_SATURDAY_OPEN_AT, opensaturday);
        contentValues.put(COLUMN_LOCATION_SUNDAY_OPEN_AT, opensunday);
        contentValues.put(COLUMN_LOCATION_MONDAY_CLOSE_AT, closemonday);
        contentValues.put(COLUMN_LOCATION_TUESDAY_CLOSE_AT, closetuesday);
        contentValues.put(COLUMN_LOCATION_WEDNESDAY_CLOSE_AT, closewednesday);
        contentValues.put(COLUMN_LOCATION_THURSDAY_CLOSE_AT, closethursday);
        contentValues.put(COLUMN_LOCATION_FRIDAY_CLOSE_AT, closefriday);
        contentValues.put(COLUMN_LOCATION_SATURDAY_CLOSE_AT, closesaturday);
        contentValues.put(COLUMN_LOCATION_SUNDAY_CLOSE_AT, closesunday);
        db.update(TABLE_NAME_LOCATION, contentValues, COLUMN_LOCATION_ID + " = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deletePlace(Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_LOCATION,
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<Place> getAllPlaces()
    {
        ArrayList<Place> array_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + TABLE_NAME_LOCATION, null );
        res.moveToFirst();

        while(!res.isAfterLast()){

            array_list.add( new Place(
                        res.getString(res.getColumnIndex(COLUMN_LOCATION_NAME)),
                        res.getDouble(res.getColumnIndex(COLUMN_LOCATION_LATITUDE)),
                        res.getDouble(res.getColumnIndex(COLUMN_LOCATION_LONGITUDE)),
                        res.getString(res.getColumnIndex(COLUMN_LOCATION_CITY)),
                        res.getString(res.getColumnIndex(COLUMN_LOCATION_PHONE)),
                        res.getString(res.getColumnIndex(COLUMN_LOCATION_ADDRESS)),
                        res.getString(res.getColumnIndex(COLUMN_LOCATION_DESCRIPTION)),
                        res.getInt(res.getColumnIndex(COLUMN_LOCATION_EAT)),
                        res.getInt(res.getColumnIndex(COLUMN_LOCATION_DRINK)),
                        res.getInt(res.getColumnIndex(COLUMN_LOCATION_MONDAY_OPEN_AT)),
                        res.getInt(res.getColumnIndex(COLUMN_LOCATION_TUESDAY_OPEN_AT)),
                        res.getInt(res.getColumnIndex(COLUMN_LOCATION_WEDNESDAY_OPEN_AT)),
                        res.getInt(res.getColumnIndex(COLUMN_LOCATION_THURSDAY_OPEN_AT)),
                        res.getInt(res.getColumnIndex(COLUMN_LOCATION_FRIDAY_OPEN_AT)),
                        res.getInt(res.getColumnIndex(COLUMN_LOCATION_SATURDAY_OPEN_AT)),
                        res.getInt(res.getColumnIndex(COLUMN_LOCATION_SUNDAY_OPEN_AT)),
                        res.getInt(res.getColumnIndex(COLUMN_LOCATION_MONDAY_CLOSE_AT)),
                        res.getInt(res.getColumnIndex(COLUMN_LOCATION_TUESDAY_CLOSE_AT)),
                        res.getInt(res.getColumnIndex(COLUMN_LOCATION_WEDNESDAY_CLOSE_AT)),
                        res.getInt(res.getColumnIndex(COLUMN_LOCATION_THURSDAY_CLOSE_AT)),
                        res.getInt(res.getColumnIndex(COLUMN_LOCATION_FRIDAY_CLOSE_AT)),
                        res.getInt(res.getColumnIndex(COLUMN_LOCATION_SATURDAY_CLOSE_AT)),
                        res.getInt(res.getColumnIndex(COLUMN_LOCATION_SUNDAY_CLOSE_AT))
                    )
            );

            res.moveToNext();
        }
        res.close();
        return array_list;
    }
}
