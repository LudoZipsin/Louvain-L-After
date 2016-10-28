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

    protected static final String DATABASE_NAME = "EatNowAppp.db";
    protected static final String TABLE_NAME = "locations";
    protected static final String COLUMN_ID = "id";
    protected static final String COLUMN_NAME = "name";
    protected static final String COLUMN_LATITUDE = "lat";
    protected static final String COLUMN_LONGITUDE = "lon";
    protected static final String COLUMN_CITY = "city";
    protected static final String COLUMN_PHONE = "phone";
    protected static final String COLUMN_ADDRESS = "address";
    protected static final String COLUMN_DESCRIPTION = "desc";
    protected static final String COLUMN_EAT = "eat";
    protected static final String COLUMN_DRINK = "drink";

    protected static final String COLUMN_MONDAY_OPEN_AT = "openmonday";
    protected static final String COLUMN_TUESDAY_OPEN_AT = "opentuesday";
    protected static final String COLUMN_WEDNESDAY_OPEN_AT = "openwednesday";
    protected static final String COLUMN_THURSDAY_OPEN_AT = "openthursday";
    protected static final String COLUMN_FRIDAY_OPEN_AT = "openfriday";
    protected static final String COLUMN_SATURDAY_OPEN_AT = "opensaturday";
    protected static final String COLUMN_SUNDAY_OPEN_AT = "opensunday";

    protected static final String COLUMN_MONDAY_CLOSE_AT = "closemonday";
    protected static final String COLUMN_TUESDAY_CLOSE_AT = "closetuesday";
    protected static final String COLUMN_WEDNESDAY_CLOSE_AT = "closewednesday";
    protected static final String COLUMN_THURSDAY_CLOSE_AT = "closethursday";
    protected static final String COLUMN_FRIDAY_CLOSE_AT = "closefriday";
    protected static final String COLUMN_SATURDAY_CLOSE_AT = "closesaturday";
    protected static final String COLUMN_SUNDAY_CLOSE_AT = "closesunday";

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
            "create table " + TABLE_NAME + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_LATITUDE + " REAL, " +
                    COLUMN_LONGITUDE + " REAL, " +
                    COLUMN_CITY + " TEXT, " +
                    COLUMN_PHONE + " TEXT, " +
                    COLUMN_ADDRESS + " TEXT, " +
                    COLUMN_DESCRIPTION  + " TEXT " +
                    COLUMN_EAT  + " INTEGER " +
                    COLUMN_DRINK  + " INTEGER " +
                    COLUMN_MONDAY_OPEN_AT + " INTEGER" +
                    COLUMN_TUESDAY_OPEN_AT + " INTEGER" +
                    COLUMN_WEDNESDAY_OPEN_AT + " INTEGER" +
                    COLUMN_THURSDAY_OPEN_AT + " INTEGER" +
                    COLUMN_FRIDAY_OPEN_AT + " INTEGER" +
                    COLUMN_SATURDAY_OPEN_AT + " INTEGER" +
                    COLUMN_SUNDAY_OPEN_AT + " INTEGER" +
                    COLUMN_MONDAY_CLOSE_AT + " INTEGER" +
                    COLUMN_TUESDAY_CLOSE_AT + " INTEGER" +
                    COLUMN_WEDNESDAY_CLOSE_AT + " INTEGER" +
                    COLUMN_THURSDAY_CLOSE_AT + " INTEGER" +
                    COLUMN_FRIDAY_CLOSE_AT + " INTEGER" +
                    COLUMN_SATURDAY_CLOSE_AT + " INTEGER" +
                    COLUMN_SUNDAY_CLOSE_AT + " INTEGER" +
                    ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
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
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_LATITUDE, lat);
        contentValues.put(COLUMN_LONGITUDE, lon);
        contentValues.put(COLUMN_CITY, city);
        contentValues.put(COLUMN_PHONE, phone);
        contentValues.put(COLUMN_ADDRESS, address);
        contentValues.put(COLUMN_DESCRIPTION, desc);
        contentValues.put(COLUMN_EAT, eat_state);
        contentValues.put(COLUMN_DRINK, drink_state);
        contentValues.put(COLUMN_MONDAY_OPEN_AT, openmonday);
        contentValues.put(COLUMN_TUESDAY_OPEN_AT, opentuesday);
        contentValues.put(COLUMN_WEDNESDAY_OPEN_AT, openwednesday);
        contentValues.put(COLUMN_THURSDAY_OPEN_AT, openthursday);
        contentValues.put(COLUMN_FRIDAY_OPEN_AT, openfriday);
        contentValues.put(COLUMN_SATURDAY_OPEN_AT, opensaturday);
        contentValues.put(COLUMN_SUNDAY_OPEN_AT, opensunday);
        contentValues.put(COLUMN_MONDAY_CLOSE_AT, closemonday);
        contentValues.put(COLUMN_TUESDAY_CLOSE_AT, closetuesday);
        contentValues.put(COLUMN_WEDNESDAY_CLOSE_AT, closewednesday);
        contentValues.put(COLUMN_THURSDAY_CLOSE_AT, closethursday);
        contentValues.put(COLUMN_FRIDAY_CLOSE_AT, closefriday);
        contentValues.put(COLUMN_SATURDAY_CLOSE_AT, closesaturday);
        contentValues.put(COLUMN_SUNDAY_CLOSE_AT, closesunday);

        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }


    public Cursor getPlaceCursorByID(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery( "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "="+id+"", null );
    }


    //TODO FINISH THIS ONE
    public Place getPlaceByID(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "="+id+"", null );
        res.moveToFirst();
        Place place = new Place(
                res.getString(res.getColumnIndex(COLUMN_NAME)),
                res.getDouble(res.getColumnIndex(COLUMN_LATITUDE)),
                res.getDouble(res.getColumnIndex(COLUMN_LONGITUDE)),
                res.getString(res.getColumnIndex(COLUMN_CITY)),
                res.getString(res.getColumnIndex(COLUMN_PHONE)),
                res.getString(res.getColumnIndex(COLUMN_ADDRESS)),
                res.getString(res.getColumnIndex(COLUMN_DESCRIPTION)),
                res.getInt(res.getColumnIndex(COLUMN_EAT)),
                res.getInt(res.getColumnIndex(COLUMN_DRINK)),
                res.getInt(res.getColumnIndex(COLUMN_MONDAY_OPEN_AT)),
                res.getInt(res.getColumnIndex(COLUMN_TUESDAY_OPEN_AT)),
                res.getInt(res.getColumnIndex(COLUMN_WEDNESDAY_OPEN_AT)),
                res.getInt(res.getColumnIndex(COLUMN_THURSDAY_OPEN_AT)),
                res.getInt(res.getColumnIndex(COLUMN_FRIDAY_OPEN_AT)),
                res.getInt(res.getColumnIndex(COLUMN_SATURDAY_OPEN_AT)),
                res.getInt(res.getColumnIndex(COLUMN_SUNDAY_OPEN_AT)),
                res.getInt(res.getColumnIndex(COLUMN_MONDAY_CLOSE_AT)),
                res.getInt(res.getColumnIndex(COLUMN_TUESDAY_CLOSE_AT)),
                res.getInt(res.getColumnIndex(COLUMN_WEDNESDAY_CLOSE_AT)),
                res.getInt(res.getColumnIndex(COLUMN_THURSDAY_CLOSE_AT)),
                res.getInt(res.getColumnIndex(COLUMN_FRIDAY_CLOSE_AT)),
                res.getInt(res.getColumnIndex(COLUMN_SATURDAY_CLOSE_AT)),
                res.getInt(res.getColumnIndex(COLUMN_SUNDAY_CLOSE_AT))
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
                openAt = COLUMN_MONDAY_OPEN_AT;
                closeAt = COLUMN_MONDAY_CLOSE_AT;
                break;
            case "Tuesday":
                openAt = COLUMN_TUESDAY_OPEN_AT;
                closeAt = COLUMN_TUESDAY_CLOSE_AT;
                break;
            case "Wednesday":
                openAt = COLUMN_WEDNESDAY_OPEN_AT;
                closeAt = COLUMN_WEDNESDAY_CLOSE_AT;
                break;
            case "Thursday":
                openAt = COLUMN_THURSDAY_OPEN_AT;
                closeAt = COLUMN_THURSDAY_CLOSE_AT;
                break;
            case "Friday":
                openAt = COLUMN_FRIDAY_OPEN_AT;
                closeAt = COLUMN_FRIDAY_CLOSE_AT;
                break;
            case "Saturday":
                openAt = COLUMN_SATURDAY_OPEN_AT;
                closeAt = COLUMN_SATURDAY_CLOSE_AT;
                break;
            case "Sunday":
                openAt = COLUMN_SUNDAY_OPEN_AT;
                closeAt = COLUMN_SUNDAY_CLOSE_AT;
                break;
            default:
                throw new IllegalArgumentException("Invalid day of the week: " + day);
        }
        return db.rawQuery("SELECT * FROM " + TABLE_NAME +
                                    " WHERE " + openAt + " <= " + time +
                                    " AND " + closeAt + " >= " + time +
                                    " AND " + COLUMN_EAT + " = " + YES + "", null);
    }

    public ArrayList<Place> getPlaceToEatByTime(int time, String day){

        ArrayList<Place> places = new ArrayList<>();

        String openAt = "";
        String closeAt = "";
        switch (day){
            case "Monday":
                openAt = COLUMN_MONDAY_OPEN_AT;
                closeAt = COLUMN_MONDAY_CLOSE_AT;
                break;
            case "Tuesday":
                openAt = COLUMN_TUESDAY_OPEN_AT;
                closeAt = COLUMN_TUESDAY_CLOSE_AT;
                break;
            case "Wednesday":
                openAt = COLUMN_WEDNESDAY_OPEN_AT;
                closeAt = COLUMN_WEDNESDAY_CLOSE_AT;
                break;
            case "Thursday":
                openAt = COLUMN_THURSDAY_OPEN_AT;
                closeAt = COLUMN_THURSDAY_CLOSE_AT;
                break;
            case "Friday":
                openAt = COLUMN_FRIDAY_OPEN_AT;
                closeAt = COLUMN_FRIDAY_CLOSE_AT;
                break;
            case "Saturday":
                openAt = COLUMN_SATURDAY_OPEN_AT;
                closeAt = COLUMN_SATURDAY_CLOSE_AT;
                break;
            case "Sunday":
                openAt = COLUMN_SUNDAY_OPEN_AT;
                closeAt = COLUMN_SUNDAY_CLOSE_AT;
                break;
            default:
                throw new IllegalArgumentException("Invalid day of the week: " + day);
        }

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME +
                " WHERE " + openAt + " <= " + time +
                " AND " + closeAt + " >= " + time +
                " AND " + COLUMN_EAT + " = " + YES + "", null);
        res.moveToFirst();

        while(!res.isAfterLast()){
            places.add( new Place(
                    res.getString(res.getColumnIndex(COLUMN_NAME)),
                    res.getDouble(res.getColumnIndex(COLUMN_LATITUDE)),
                    res.getDouble(res.getColumnIndex(COLUMN_LONGITUDE)),
                    res.getString(res.getColumnIndex(COLUMN_CITY)),
                    res.getString(res.getColumnIndex(COLUMN_PHONE)),
                    res.getString(res.getColumnIndex(COLUMN_ADDRESS)),
                    res.getString(res.getColumnIndex(COLUMN_DESCRIPTION)),
                    res.getInt(res.getColumnIndex(COLUMN_EAT)),
                    res.getInt(res.getColumnIndex(COLUMN_DRINK)),
                    res.getInt(res.getColumnIndex(COLUMN_MONDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_TUESDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_WEDNESDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_THURSDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_FRIDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_SATURDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_SUNDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_MONDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_TUESDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_WEDNESDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_THURSDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_FRIDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_SATURDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_SUNDAY_CLOSE_AT))
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
                openAt = COLUMN_MONDAY_OPEN_AT;
                closeAt = COLUMN_MONDAY_CLOSE_AT;
                break;
            case "Tuesday":
                openAt = COLUMN_TUESDAY_OPEN_AT;
                closeAt = COLUMN_TUESDAY_CLOSE_AT;
                break;
            case "Wednesday":
                openAt = COLUMN_WEDNESDAY_OPEN_AT;
                closeAt = COLUMN_WEDNESDAY_CLOSE_AT;
                break;
            case "Thursday":
                openAt = COLUMN_THURSDAY_OPEN_AT;
                closeAt = COLUMN_THURSDAY_CLOSE_AT;
                break;
            case "Friday":
                openAt = COLUMN_FRIDAY_OPEN_AT;
                closeAt = COLUMN_FRIDAY_CLOSE_AT;
                break;
            case "Saturday":
                openAt = COLUMN_SATURDAY_OPEN_AT;
                closeAt = COLUMN_SATURDAY_CLOSE_AT;
                break;
            case "Sunday":
                openAt = COLUMN_SUNDAY_OPEN_AT;
                closeAt = COLUMN_SUNDAY_CLOSE_AT;
                break;
            default:
                throw new IllegalArgumentException("Invalid day of the week: " + day);
        }
        return db.rawQuery("SELECT * FROM " + TABLE_NAME +
                                    " WHERE " + openAt + " <= " + time +
                                    " AND " + closeAt + " >= " + time +
                                    " AND " + COLUMN_DRINK + " = " + YES + "", null);
    }

    public ArrayList<Place> getPlaceToDrinkByTime(int time, String day){

        ArrayList<Place> places = new ArrayList<>();

        String openAt = "";
        String closeAt = "";
        switch (day){
            case "Monday":
                openAt = COLUMN_MONDAY_OPEN_AT;
                closeAt = COLUMN_MONDAY_CLOSE_AT;
                break;
            case "Tuesday":
                openAt = COLUMN_TUESDAY_OPEN_AT;
                closeAt = COLUMN_TUESDAY_CLOSE_AT;
                break;
            case "Wednesday":
                openAt = COLUMN_WEDNESDAY_OPEN_AT;
                closeAt = COLUMN_WEDNESDAY_CLOSE_AT;
                break;
            case "Thursday":
                openAt = COLUMN_THURSDAY_OPEN_AT;
                closeAt = COLUMN_THURSDAY_CLOSE_AT;
                break;
            case "Friday":
                openAt = COLUMN_FRIDAY_OPEN_AT;
                closeAt = COLUMN_FRIDAY_CLOSE_AT;
                break;
            case "Saturday":
                openAt = COLUMN_SATURDAY_OPEN_AT;
                closeAt = COLUMN_SATURDAY_CLOSE_AT;
                break;
            case "Sunday":
                openAt = COLUMN_SUNDAY_OPEN_AT;
                closeAt = COLUMN_SUNDAY_CLOSE_AT;
                break;
            default:
                throw new IllegalArgumentException("Invalid day of the week: " + day);
        }

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME +
                " WHERE " + openAt + " <= " + time +
                " AND " + closeAt + " >= " + time +
                " AND " + COLUMN_DRINK + " = " + YES + "", null);
        res.moveToFirst();

        while(!res.isAfterLast()){
            places.add( new Place(
                    res.getString(res.getColumnIndex(COLUMN_NAME)),
                    res.getDouble(res.getColumnIndex(COLUMN_LATITUDE)),
                    res.getDouble(res.getColumnIndex(COLUMN_LONGITUDE)),
                    res.getString(res.getColumnIndex(COLUMN_CITY)),
                    res.getString(res.getColumnIndex(COLUMN_PHONE)),
                    res.getString(res.getColumnIndex(COLUMN_ADDRESS)),
                    res.getString(res.getColumnIndex(COLUMN_DESCRIPTION)),
                    res.getInt(res.getColumnIndex(COLUMN_EAT)),
                    res.getInt(res.getColumnIndex(COLUMN_DRINK)),
                    res.getInt(res.getColumnIndex(COLUMN_MONDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_TUESDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_WEDNESDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_THURSDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_FRIDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_SATURDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_SUNDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_MONDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_TUESDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_WEDNESDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_THURSDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_FRIDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_SATURDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_SUNDAY_CLOSE_AT))
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
                openAt = COLUMN_MONDAY_OPEN_AT;
                closeAt = COLUMN_MONDAY_CLOSE_AT;
                break;
            case "Tuesday":
                openAt = COLUMN_TUESDAY_OPEN_AT;
                closeAt = COLUMN_TUESDAY_CLOSE_AT;
                break;
            case "Wednesday":
                openAt = COLUMN_WEDNESDAY_OPEN_AT;
                closeAt = COLUMN_WEDNESDAY_CLOSE_AT;
                break;
            case "Thursday":
                openAt = COLUMN_THURSDAY_OPEN_AT;
                closeAt = COLUMN_THURSDAY_CLOSE_AT;
                break;
            case "Friday":
                openAt = COLUMN_FRIDAY_OPEN_AT;
                closeAt = COLUMN_FRIDAY_CLOSE_AT;
                break;
            case "Saturday":
                openAt = COLUMN_SATURDAY_OPEN_AT;
                closeAt = COLUMN_SATURDAY_CLOSE_AT;
                break;
            case "Sunday":
                openAt = COLUMN_SUNDAY_OPEN_AT;
                closeAt = COLUMN_SUNDAY_CLOSE_AT;
                break;
            default:
                throw new IllegalArgumentException("Invalid day of the week: " + day);
        }
        return db.rawQuery("SELECT * FROM " + TABLE_NAME +
                                    " WHERE " + openAt + " <= " + time +
                                    " AND " + closeAt + " >= " + time +
                                    " AND " + COLUMN_EAT + " = " + YES +
                                    " AND " + COLUMN_DRINK + " = " + YES + "", null);
    }

    public ArrayList<Place> getPlaceEanAndDrinkByTime(int time, String day){

        ArrayList<Place> places = new ArrayList<>();
        String openAt = "";
        String closeAt = "";
        switch (day){
            case "Monday":
                openAt = COLUMN_MONDAY_OPEN_AT;
                closeAt = COLUMN_MONDAY_CLOSE_AT;
                break;
            case "Tuesday":
                openAt = COLUMN_TUESDAY_OPEN_AT;
                closeAt = COLUMN_TUESDAY_CLOSE_AT;
                break;
            case "Wednesday":
                openAt = COLUMN_WEDNESDAY_OPEN_AT;
                closeAt = COLUMN_WEDNESDAY_CLOSE_AT;
                break;
            case "Thursday":
                openAt = COLUMN_THURSDAY_OPEN_AT;
                closeAt = COLUMN_THURSDAY_CLOSE_AT;
                break;
            case "Friday":
                openAt = COLUMN_FRIDAY_OPEN_AT;
                closeAt = COLUMN_FRIDAY_CLOSE_AT;
                break;
            case "Saturday":
                openAt = COLUMN_SATURDAY_OPEN_AT;
                closeAt = COLUMN_SATURDAY_CLOSE_AT;
                break;
            case "Sunday":
                openAt = COLUMN_SUNDAY_OPEN_AT;
                closeAt = COLUMN_SUNDAY_CLOSE_AT;
                break;
            default:
                throw new IllegalArgumentException("Invalid day of the week: " + day);
        }
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME +
                " WHERE " + openAt + " <= " + time +
                " AND " + closeAt + " >= " + time +
                " AND " + COLUMN_EAT + " = " + YES +
                " AND " + COLUMN_DRINK + " = " + YES + "", null);
        res.moveToFirst();

        while(!res.isAfterLast()){
            places.add( new Place(
                    res.getString(res.getColumnIndex(COLUMN_NAME)),
                    res.getDouble(res.getColumnIndex(COLUMN_LATITUDE)),
                    res.getDouble(res.getColumnIndex(COLUMN_LONGITUDE)),
                    res.getString(res.getColumnIndex(COLUMN_CITY)),
                    res.getString(res.getColumnIndex(COLUMN_PHONE)),
                    res.getString(res.getColumnIndex(COLUMN_ADDRESS)),
                    res.getString(res.getColumnIndex(COLUMN_DESCRIPTION)),
                    res.getInt(res.getColumnIndex(COLUMN_EAT)),
                    res.getInt(res.getColumnIndex(COLUMN_DRINK)),
                    res.getInt(res.getColumnIndex(COLUMN_MONDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_TUESDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_WEDNESDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_THURSDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_FRIDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_SATURDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_SUNDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_MONDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_TUESDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_WEDNESDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_THURSDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_FRIDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_SATURDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_SUNDAY_CLOSE_AT))
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
                openAt = COLUMN_MONDAY_OPEN_AT;
                closeAt = COLUMN_MONDAY_CLOSE_AT;
                break;
            case "Tuesday":
                openAt = COLUMN_TUESDAY_OPEN_AT;
                closeAt = COLUMN_TUESDAY_CLOSE_AT;
                break;
            case "Wednesday":
                openAt = COLUMN_WEDNESDAY_OPEN_AT;
                closeAt = COLUMN_WEDNESDAY_CLOSE_AT;
                break;
            case "Thursday":
                openAt = COLUMN_THURSDAY_OPEN_AT;
                closeAt = COLUMN_THURSDAY_CLOSE_AT;
                break;
            case "Friday":
                openAt = COLUMN_FRIDAY_OPEN_AT;
                closeAt = COLUMN_FRIDAY_CLOSE_AT;
                break;
            case "Saturday":
                openAt = COLUMN_SATURDAY_OPEN_AT;
                closeAt = COLUMN_SATURDAY_CLOSE_AT;
                break;
            case "Sunday":
                openAt = COLUMN_SUNDAY_OPEN_AT;
                closeAt = COLUMN_SUNDAY_CLOSE_AT;
                break;
            default:
                throw new IllegalArgumentException("Invalid day of the week: " + day);
        }
        return db.rawQuery("SELECT * FROM " + TABLE_NAME +
                " WHERE " + openAt + " <= " + time +
                " AND " + closeAt + " >= " + time + "", null);
    }

    public ArrayList<Place> getPlaceByTime(int time, String day){

        ArrayList<Place> places = new ArrayList<>();

        String openAt = "";
        String closeAt = "";
        switch (day){
            case "Monday":
                openAt = COLUMN_MONDAY_OPEN_AT;
                closeAt = COLUMN_MONDAY_CLOSE_AT;
                break;
            case "Tuesday":
                openAt = COLUMN_TUESDAY_OPEN_AT;
                closeAt = COLUMN_TUESDAY_CLOSE_AT;
                break;
            case "Wednesday":
                openAt = COLUMN_WEDNESDAY_OPEN_AT;
                closeAt = COLUMN_WEDNESDAY_CLOSE_AT;
                break;
            case "Thursday":
                openAt = COLUMN_THURSDAY_OPEN_AT;
                closeAt = COLUMN_THURSDAY_CLOSE_AT;
                break;
            case "Friday":
                openAt = COLUMN_FRIDAY_OPEN_AT;
                closeAt = COLUMN_FRIDAY_CLOSE_AT;
                break;
            case "Saturday":
                openAt = COLUMN_SATURDAY_OPEN_AT;
                closeAt = COLUMN_SATURDAY_CLOSE_AT;
                break;
            case "Sunday":
                openAt = COLUMN_SUNDAY_OPEN_AT;
                closeAt = COLUMN_SUNDAY_CLOSE_AT;
                break;
            default:
                throw new IllegalArgumentException("Invalid day of the week: " + day);
        }

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME +
                " WHERE " + openAt + " <= " + time +
                " AND " + closeAt + " >= " + time + "", null);
        res.moveToFirst();

        while(!res.isAfterLast()){
            places.add( new Place(
                    res.getString(res.getColumnIndex(COLUMN_NAME)),
                    res.getDouble(res.getColumnIndex(COLUMN_LATITUDE)),
                    res.getDouble(res.getColumnIndex(COLUMN_LONGITUDE)),
                    res.getString(res.getColumnIndex(COLUMN_CITY)),
                    res.getString(res.getColumnIndex(COLUMN_PHONE)),
                    res.getString(res.getColumnIndex(COLUMN_ADDRESS)),
                    res.getString(res.getColumnIndex(COLUMN_DESCRIPTION)),
                    res.getInt(res.getColumnIndex(COLUMN_EAT)),
                    res.getInt(res.getColumnIndex(COLUMN_DRINK)),
                    res.getInt(res.getColumnIndex(COLUMN_MONDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_TUESDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_WEDNESDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_THURSDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_FRIDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_SATURDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_SUNDAY_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_MONDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_TUESDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_WEDNESDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_THURSDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_FRIDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_SATURDAY_CLOSE_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_SUNDAY_CLOSE_AT))
            ));
            res.moveToNext();
        }
        res.close();

        return places;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        return (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
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
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_LATITUDE, lat);
        contentValues.put(COLUMN_LONGITUDE, lon);
        contentValues.put(COLUMN_CITY, city);
        contentValues.put(COLUMN_PHONE, phone);
        contentValues.put(COLUMN_ADDRESS, address);
        contentValues.put(COLUMN_DESCRIPTION, desc);
        contentValues.put(COLUMN_EAT, eat_state);
        contentValues.put(COLUMN_DRINK, drink_state);
        contentValues.put(COLUMN_MONDAY_OPEN_AT, openmonday);
        contentValues.put(COLUMN_TUESDAY_OPEN_AT, opentuesday);
        contentValues.put(COLUMN_WEDNESDAY_OPEN_AT, openwednesday);
        contentValues.put(COLUMN_THURSDAY_OPEN_AT, openthursday);
        contentValues.put(COLUMN_FRIDAY_OPEN_AT, openfriday);
        contentValues.put(COLUMN_SATURDAY_OPEN_AT, opensaturday);
        contentValues.put(COLUMN_SUNDAY_OPEN_AT, opensunday);
        contentValues.put(COLUMN_MONDAY_CLOSE_AT, closemonday);
        contentValues.put(COLUMN_TUESDAY_CLOSE_AT, closetuesday);
        contentValues.put(COLUMN_WEDNESDAY_CLOSE_AT, closewednesday);
        contentValues.put(COLUMN_THURSDAY_CLOSE_AT, closethursday);
        contentValues.put(COLUMN_FRIDAY_CLOSE_AT, closefriday);
        contentValues.put(COLUMN_SATURDAY_CLOSE_AT, closesaturday);
        contentValues.put(COLUMN_SUNDAY_CLOSE_AT, closesunday);
        db.update(TABLE_NAME, contentValues, COLUMN_ID + " = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deletePlace(Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<Place> getAllPlaces()
    {
        ArrayList<Place> array_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + TABLE_NAME, null );
        res.moveToFirst();

        while(!res.isAfterLast()){

            array_list.add( new Place(
                        res.getString(res.getColumnIndex(COLUMN_NAME)),
                        res.getDouble(res.getColumnIndex(COLUMN_LATITUDE)),
                        res.getDouble(res.getColumnIndex(COLUMN_LONGITUDE)),
                        res.getString(res.getColumnIndex(COLUMN_CITY)),
                        res.getString(res.getColumnIndex(COLUMN_PHONE)),
                        res.getString(res.getColumnIndex(COLUMN_ADDRESS)),
                        res.getString(res.getColumnIndex(COLUMN_DESCRIPTION)),
                        res.getInt(res.getColumnIndex(COLUMN_EAT)),
                        res.getInt(res.getColumnIndex(COLUMN_DRINK)),
                        res.getInt(res.getColumnIndex(COLUMN_MONDAY_OPEN_AT)),
                        res.getInt(res.getColumnIndex(COLUMN_TUESDAY_OPEN_AT)),
                        res.getInt(res.getColumnIndex(COLUMN_WEDNESDAY_OPEN_AT)),
                        res.getInt(res.getColumnIndex(COLUMN_THURSDAY_OPEN_AT)),
                        res.getInt(res.getColumnIndex(COLUMN_FRIDAY_OPEN_AT)),
                        res.getInt(res.getColumnIndex(COLUMN_SATURDAY_OPEN_AT)),
                        res.getInt(res.getColumnIndex(COLUMN_SUNDAY_OPEN_AT)),
                        res.getInt(res.getColumnIndex(COLUMN_MONDAY_CLOSE_AT)),
                        res.getInt(res.getColumnIndex(COLUMN_TUESDAY_CLOSE_AT)),
                        res.getInt(res.getColumnIndex(COLUMN_WEDNESDAY_CLOSE_AT)),
                        res.getInt(res.getColumnIndex(COLUMN_THURSDAY_CLOSE_AT)),
                        res.getInt(res.getColumnIndex(COLUMN_FRIDAY_CLOSE_AT)),
                        res.getInt(res.getColumnIndex(COLUMN_SATURDAY_CLOSE_AT)),
                        res.getInt(res.getColumnIndex(COLUMN_SUNDAY_CLOSE_AT))
                    )
            );

            res.moveToNext();
        }
        res.close();
        return array_list;
    }
}
