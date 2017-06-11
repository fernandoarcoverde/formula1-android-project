package com.unibratec.ads.formula1.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 10/06/17.
 */

public class DriverDbHelper extends SQLiteOpenHelper {

    public static final int DB_VERTION = 1;
    public static final String DB_NAME = "favorites_b.db";


    public DriverDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERTION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + DriverContract.TABLE_NAME + " (" +
                DriverContract._ID                    + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DriverContract.SEASON_YEAR            + " TEXT NOT NULL, " +
                DriverContract.ROUND                  + " TEXT NOT NULL, " +
                DriverContract.POSITION               + " TEXT NOT NULL, " +
                DriverContract.POINTS                 + " TEXT NOT NULL, " +
                DriverContract.WINS                   + " TEXT NOT NULL, " +
                DriverContract.URL_IMAGE              + " TEXT NOT NULL, " +
                DriverContract.URL_IMAGE_POSTER       + " TEXT NOT NULL, " +
                DriverContract.DRIVER_ID              + " TEXT NOT NULL, " +
                DriverContract.PERMANENT_NUMBER       + " TEXT NOT NULL, " +
                DriverContract.CODE_NAME              + " TEXT NOT NULL, " +
                DriverContract.GIVEN_NAME             + " TEXT NOT NULL, " +
                DriverContract.FAMILY_NAME            + " TEXT NOT NULL, " +
                DriverContract.NATIONALITY            + " TEXT NOT NULL, " +
                DriverContract.DATE_BIRTH             + " TEXT NOT NULL, " +
                DriverContract.CONSTRUCTOR_NAME       + " TEXT NOT NULL, " +
                DriverContract.CONSTRUCTOR_COUNTRY    + " TEXT NOT NULL) ");

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}