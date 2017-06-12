package com.unibratec.ads.formula1.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 10/06/17.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final int DB_VERTION = 1;
    public static final String DB_NAME = "formula1.db";


    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERTION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + DbContract.TABLE_NAME_FAVORITES + " (" +
                DbContract.FAVORITE_ID                   + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DbContract.FAVORITE_SEASON_YEAR          + " TEXT NOT NULL, " +
                DbContract.FAVORITE_ROUND                + " TEXT NOT NULL, " +
                DbContract.FAVORITE_POSITION             + " TEXT NOT NULL, " +
                DbContract.FAVORITE_POINTS               + " TEXT NOT NULL, " +
                DbContract.FAVORITE_WINS                 + " TEXT NOT NULL, " +
                DbContract.FAVORITE_URL_IMAGE            + " TEXT NOT NULL, " +
                DbContract.FAVORITE_URL_IMAGE_POSTER     + " TEXT NOT NULL, " +
                DbContract.FAVORITE_DRIVER_ID            + " TEXT NOT NULL, " +
                DbContract.FAVORITE_PERMANENT_NUMBER     + " TEXT NOT NULL, " +
                DbContract.FAVORITE_CODE_NAME            + " TEXT NOT NULL, " +
                DbContract.FAVORITE_GIVEN_NAME           + " TEXT NOT NULL, " +
                DbContract.FAVORITE_FAMILY_NAME          + " TEXT NOT NULL, " +
                DbContract.FAVORITE_NATIONALITY          + " TEXT NOT NULL, " +
                DbContract.FAVORITE_DATE_BIRTH           + " TEXT NOT NULL, " +
                DbContract.FAVORITE_CONSTRUCTOR_NAME     + " TEXT NOT NULL, " +
                DbContract.FAVORITE_CONSTRUCTOR_COUNTRY  + " TEXT NOT NULL) ");

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DbContract.TABLE_NAME_FAVORITES);
        onCreate(sqLiteDatabase);

    }




}