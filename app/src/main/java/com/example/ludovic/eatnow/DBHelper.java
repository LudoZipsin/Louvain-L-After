package com.example.ludovic.eatnow;

/**
 * Created by Ludovic Zipsin on 25/10/16.
 * Mail: ludovic.j.r.zipsin@gmail.com
 * Github: https://github.com/LudoZipsin
 */


import java.util.ArrayList;
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
    protected static final String COLUMN_OPEN_AT = "open";
    protected static final String COLUMN_CLOSE_AT = "close";

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
                    COLUMN_OPEN_AT  + " INTEGER " +
                    COLUMN_CLOSE_AT  + " INTEGER " +
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
                                 int open,
                                 int close)
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
        contentValues.put(COLUMN_OPEN_AT, open);
        contentValues.put(COLUMN_CLOSE_AT, close);
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
                res.getInt(res.getColumnIndex(COLUMN_OPEN_AT)),
                res.getInt(res.getColumnIndex(COLUMN_CLOSE_AT))
        );
        res.close();
        return place;
    }


    /**
     * @param time: it is the current time (which will be given by the calling activity. Depending
     *              on the time, we will increment by 24 (in order to get the restaurants and cafés
     *              that close at 3 or 4 in the morning.
     * @return a cursor object
     */
    public Cursor getPlaceCursorToEatByTime(int time){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME +
                                    " WHERE " + COLUMN_OPEN_AT + " <= " + time +
                                    " AND " + COLUMN_CLOSE_AT + " >= " + time +
                                    " AND " + COLUMN_EAT + " = " + YES + "", null);
    }

    public ArrayList<Place> getPlaceToEatByTime(int time){

        ArrayList<Place> places = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME +
                " WHERE " + COLUMN_OPEN_AT + " <= " + time +
                " AND " + COLUMN_CLOSE_AT + " >= " + time +
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
                    res.getInt(res.getColumnIndex(COLUMN_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_CLOSE_AT))
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
    public Cursor getPlaceCursorToDrinkByTime(int time){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME +
                                    " WHERE " + COLUMN_OPEN_AT + " <= " + time +
                                    " AND " + COLUMN_CLOSE_AT + " >= " + time +
                                    " AND " + COLUMN_DRINK + " = " + YES + "", null);
    }

    public ArrayList<Place> getPlaceToDrinkByTime(int time){

        ArrayList<Place> places = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME +
                " WHERE " + COLUMN_OPEN_AT + " <= " + time +
                " AND " + COLUMN_CLOSE_AT + " >= " + time +
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
                    res.getInt(res.getColumnIndex(COLUMN_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_CLOSE_AT))
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
    public Cursor getPlaceCursorEatAndDrinkByTime(int time){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME +
                                    " WHERE " + COLUMN_OPEN_AT + " <= " + time +
                                    " AND " + COLUMN_CLOSE_AT + " >= " + time +
                                    " AND " + COLUMN_EAT + " = " + YES +
                                    " AND " + COLUMN_DRINK + " = " + YES + "", null);
    }

    public ArrayList<Place> getPlaceEanAndDrinkByTime(int time){

        ArrayList<Place> places = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME +
                " WHERE " + COLUMN_OPEN_AT + " <= " + time +
                " AND " + COLUMN_CLOSE_AT + " >= " + time +
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
                    res.getInt(res.getColumnIndex(COLUMN_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_CLOSE_AT))
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
    public Cursor getPlaceCursorByTime(int time){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME +
                " WHERE " + COLUMN_OPEN_AT + " <= " + time +
                " AND " + COLUMN_CLOSE_AT + " >= " + time + "", null);
    }

    public ArrayList<Place> getPlaceByTime(int time){

        ArrayList<Place> places = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME +
                " WHERE " + COLUMN_OPEN_AT + " <= " + time +
                " AND " + COLUMN_CLOSE_AT + " >= " + time + "", null);
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
                    res.getInt(res.getColumnIndex(COLUMN_OPEN_AT)),
                    res.getInt(res.getColumnIndex(COLUMN_CLOSE_AT))
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
                               int close)
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
        contentValues.put(COLUMN_OPEN_AT, open);
        contentValues.put(COLUMN_CLOSE_AT, close);
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
                        res.getInt(res.getColumnIndex(COLUMN_OPEN_AT)),
                        res.getInt(res.getColumnIndex(COLUMN_CLOSE_AT))
                    )
            );

            res.moveToNext();
        }
        res.close();
        return array_list;
    }
}
