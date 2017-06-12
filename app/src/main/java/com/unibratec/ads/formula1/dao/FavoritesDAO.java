package com.unibratec.ads.formula1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.unibratec.ads.formula1.model.DriverPosition;
import com.unibratec.ads.formula1.model.RaceDriverRace;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 10/06/17.
 */

public class FavoritesDAO {
    private  static FavoritesDAO instance;
    private Context mContext;


    public FavoritesDAO(Context context) {
        this.mContext = context;
    }


    public static synchronized FavoritesDAO getInstance(Context context){
        if (instance == null){
            instance = new FavoritesDAO(context);
        }
        return instance;
    }


    public long insertDriverRace(RaceDriverRace dbObject) {
        DbHelper dbHelper = new DbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = valuesFromDriverRace(dbObject);

        long id = db.insert(DbContract.TABLE_NAME_FAVORITES, null, values);

        dbObject.getDriver().setId(id);

        db.close();

        return id;
    }


    public int deleteDriverRace(RaceDriverRace dbObject){
        DbHelper dbHelper = new DbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int rowsUpdated = db.delete(DbContract.TABLE_NAME_FAVORITES,
                DbContract.FAVORITE_ID + " = ?",
                new String[]{ String.valueOf(dbObject.getDriver().getId()) });

        db.close();

        return rowsUpdated;
    }


    public RaceDriverRace getDriverRace(String driverId){
        DbHelper dbHelper = new DbHelper(mContext);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        RaceDriverRace result = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + DbContract.TABLE_NAME_FAVORITES +
                        " WHERE " + DbContract.FAVORITE_DRIVER_ID + " = ?",
                new String[] {driverId});

        if (cursor.moveToFirst()) {
            result = getValuesFromCursorForDriverRace(cursor);
        }

        cursor.close();
        db.close();

        return result;
    }


    public List<RaceDriverRace> selectDriverRaces(){
        DbHelper dbHelper = new DbHelper(mContext);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        List<RaceDriverRace> dbObjects = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + DbContract.TABLE_NAME_FAVORITES, null);

        while (cursor.moveToNext()){
            dbObjects.add(getValuesFromCursorForDriverRace(cursor));

        }

        cursor.close();
        db.close();

        return dbObjects;
    }


    public List<DriverPosition> selectDriverPositions(){
        DbHelper dbHelper = new DbHelper(mContext);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        List<DriverPosition> dbObjects = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + DbContract.TABLE_NAME_FAVORITES, null);

        while (cursor.moveToNext()){
            dbObjects.add(getValuesFromCursorForDriverPosition(cursor));

        }

        cursor.close();
        db.close();

        return dbObjects;
    }


    private ContentValues valuesFromDriverRace(RaceDriverRace dbObject){
        ContentValues values = new ContentValues();
        values.put(DbContract.FAVORITE_SEASON_YEAR         , dbObject.getSeasonYear());
        values.put(DbContract.FAVORITE_ROUND               , dbObject.getRound());
        values.put(DbContract.FAVORITE_POSITION            , dbObject.getPosition());
        values.put(DbContract.FAVORITE_POINTS              , dbObject.getPoints());
        values.put(DbContract.FAVORITE_WINS                , dbObject.getWins());
        values.put(DbContract.FAVORITE_URL_IMAGE           , dbObject.getUrlImageDriver());
        values.put(DbContract.FAVORITE_URL_IMAGE_POSTER    , dbObject.getUrlImagePosterDriver());

        values.put(DbContract.FAVORITE_DRIVER_ID           , dbObject.getDriver().getDriverId());
        values.put(DbContract.FAVORITE_PERMANENT_NUMBER    , dbObject.getDriver().getPermanentNumber());
        values.put(DbContract.FAVORITE_CODE_NAME           , dbObject.getDriver().getCode());
        values.put(DbContract.FAVORITE_GIVEN_NAME          , dbObject.getDriver().getGivenName());
        values.put(DbContract.FAVORITE_FAMILY_NAME         , dbObject.getDriver().getFamilyName());
        values.put(DbContract.FAVORITE_NATIONALITY         , dbObject.getDriver().getNationality());
        values.put(DbContract.FAVORITE_DATE_BIRTH          , dbObject.getDriver().getDateOfBirth());

        values.put(DbContract.FAVORITE_CONSTRUCTOR_NAME    , dbObject.getConstructors().get(0).getName());
        values.put(DbContract.FAVORITE_CONSTRUCTOR_COUNTRY , dbObject.getConstructors().get(0).getNationality());

        return values;

    }


    private RaceDriverRace getValuesFromCursorForDriverRace(Cursor cursor) {
        RaceDriverRace raceDriverRace = new RaceDriverRace();

        raceDriverRace.setSeasonYear(cursor.getString(
                cursor.getColumnIndex(DbContract.FAVORITE_SEASON_YEAR)));
        raceDriverRace.setRound(cursor.getString(
                cursor.getColumnIndex(DbContract.FAVORITE_ROUND)));
        raceDriverRace.setPosition(cursor.getString(
                cursor.getColumnIndex(DbContract.FAVORITE_POSITION)));
        raceDriverRace.setPoints(cursor.getString(
                cursor.getColumnIndex(DbContract.FAVORITE_POINTS)));
        raceDriverRace.setWins(cursor.getString(
                cursor.getColumnIndex(DbContract.FAVORITE_WINS)));
        raceDriverRace.setUrlImageDriver(cursor.getString(
                cursor.getColumnIndex(DbContract.FAVORITE_URL_IMAGE)));
        raceDriverRace.setUrlImagePosterDriver(cursor.getString(
                cursor.getColumnIndex(DbContract.FAVORITE_URL_IMAGE_POSTER)));

        raceDriverRace.getDriver().setId(cursor.getLong(
                cursor.getColumnIndex(DbContract.FAVORITE_ID)));
        raceDriverRace.getDriver().setDriverId(cursor.getString(
                cursor.getColumnIndex(DbContract.FAVORITE_DRIVER_ID)));
        raceDriverRace.getDriver().setPermanentNumber(cursor.getString(
                cursor.getColumnIndex(DbContract.FAVORITE_PERMANENT_NUMBER)));
        raceDriverRace.getDriver().setCode(cursor.getString(
                cursor.getColumnIndex(DbContract.FAVORITE_CODE_NAME)));
        raceDriverRace.getDriver().setGivenName(cursor.getString(
                cursor.getColumnIndex(DbContract.FAVORITE_GIVEN_NAME)));
        raceDriverRace.getDriver().setFamilyName(cursor.getString(
                cursor.getColumnIndex(DbContract.FAVORITE_FAMILY_NAME)));
        raceDriverRace.getDriver().setNationality(cursor.getString(
                cursor.getColumnIndex(DbContract.FAVORITE_NATIONALITY)));
        raceDriverRace.getDriver().setDateOfBirth(cursor.getString(
                cursor.getColumnIndex(DbContract.FAVORITE_DATE_BIRTH)));

        raceDriverRace.getConstructors().get(0).setName(cursor.getString(
                cursor.getColumnIndex(DbContract.FAVORITE_CONSTRUCTOR_NAME)));
        raceDriverRace.getConstructors().get(0).setNationality(cursor.getString(
                cursor.getColumnIndex(DbContract.FAVORITE_CONSTRUCTOR_COUNTRY)));

        return raceDriverRace;
    }


    private DriverPosition getValuesFromCursorForDriverPosition(Cursor cursor) {
        DriverPosition dbObject = new DriverPosition();

        dbObject.setSeasonYear(cursor.getString(
                cursor.getColumnIndex(DbContract.FAVORITE_SEASON_YEAR)));
        dbObject.setRound(cursor.getString(
                cursor.getColumnIndex(DbContract.FAVORITE_ROUND)));
        dbObject.setPosition(cursor.getString(
                cursor.getColumnIndex(DbContract.FAVORITE_POSITION)));
        dbObject.setPoints(cursor.getString(
                cursor.getColumnIndex(DbContract.FAVORITE_POINTS)));
        dbObject.setWins(cursor.getString(
                cursor.getColumnIndex(DbContract.FAVORITE_WINS)));
        dbObject.setUrlImageDriver(cursor.getString(
                cursor.getColumnIndex(DbContract.FAVORITE_URL_IMAGE)));
        dbObject.setUrlImagePosterDriver(cursor.getString(
                cursor.getColumnIndex(DbContract.FAVORITE_URL_IMAGE_POSTER)));

        dbObject.getDriver().setId(cursor.getLong(
                cursor.getColumnIndex(DbContract.FAVORITE_ID)));
        dbObject.getDriver().setDriverId(cursor.getString(
                cursor.getColumnIndex(DbContract.FAVORITE_DRIVER_ID)));
        dbObject.getDriver().setPermanentNumber(cursor.getString(
                cursor.getColumnIndex(DbContract.FAVORITE_PERMANENT_NUMBER)));
        dbObject.getDriver().setCode(cursor.getString(
                cursor.getColumnIndex(DbContract.FAVORITE_CODE_NAME)));
        dbObject.getDriver().setGivenName(cursor.getString(
                cursor.getColumnIndex(DbContract.FAVORITE_GIVEN_NAME)));
        dbObject.getDriver().setFamilyName(cursor.getString(
                cursor.getColumnIndex(DbContract.FAVORITE_FAMILY_NAME)));
        dbObject.getDriver().setNationality(cursor.getString(
                cursor.getColumnIndex(DbContract.FAVORITE_NATIONALITY)));
        dbObject.getDriver().setDateOfBirth(cursor.getString(
                cursor.getColumnIndex(DbContract.FAVORITE_DATE_BIRTH)));

        dbObject.getConstructors().get(0).setName(cursor.getString(
                cursor.getColumnIndex(DbContract.FAVORITE_CONSTRUCTOR_NAME)));
        dbObject.getConstructors().get(0).setName(cursor.getString(
                cursor.getColumnIndex(DbContract.FAVORITE_CONSTRUCTOR_COUNTRY)));

        return dbObject;
    }


}