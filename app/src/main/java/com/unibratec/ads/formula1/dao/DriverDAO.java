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

public class DriverDAO {
    private  static DriverDAO instance;
    private Context mContext;


    public DriverDAO(Context context) {
        this.mContext = context;
    }


    public static synchronized DriverDAO getInstance(Context context){
        if (instance == null){
            instance = new DriverDAO(context);
        }
        return instance;
    }


    public long insertDriverRace(RaceDriverRace dbObject) {
        DriverDbHelper dbHelper = new DriverDbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = valuesFromDriverRace(dbObject);

        long id = db.insert(DriverContract.TABLE_NAME, null, values);

        dbObject.getDriver().setId(id);

        db.close();

        return id;
    }


    public int updateDriverRace(RaceDriverRace dbObject) {
        DriverDbHelper dbHelper = new DriverDbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = valuesFromDriverRace(dbObject);

        int rowsUpdated = db.update(DriverContract.TABLE_NAME, values,
                DriverContract._ID + " = ?",
                new String[]{String.valueOf(dbObject.getDriver().getId())});

        db.close();

        return rowsUpdated;
    }


    public int deleteDriverRace(RaceDriverRace dbObject){
        DriverDbHelper dbHelper = new DriverDbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int rowsUpdated = db.delete(DriverContract.TABLE_NAME,
                DriverContract._ID + " = ?",
                new String[]{ String.valueOf(dbObject.getDriver().getId()) });

        db.close();

        return rowsUpdated;
    }


    public RaceDriverRace getDriverRace(String driverId){
        DriverDbHelper dbHelper = new DriverDbHelper(mContext);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        RaceDriverRace result = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + DriverContract.TABLE_NAME +
                        " WHERE " + DriverContract.DRIVER_ID + " = ?",
                new String[] {driverId});

        if (cursor.moveToFirst()) {
            result = getValuesFromCursorForDriverRace(cursor);
        }

        cursor.close();
        db.close();

        return result;
    }


    public List<RaceDriverRace> selectDriverRaces(){
        DriverDbHelper dbHelper = new DriverDbHelper(mContext);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        List<RaceDriverRace> dbObjects = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + DriverContract.TABLE_NAME, null);

        while (cursor.moveToNext()){
            dbObjects.add(getValuesFromCursorForDriverRace(cursor));

        }

        cursor.close();
        db.close();

        return dbObjects;
    }


    public List<DriverPosition> selectDriverPositions(){
        DriverDbHelper dbHelper = new DriverDbHelper(mContext);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        List<DriverPosition> dbObjects = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + DriverContract.TABLE_NAME, null);

        while (cursor.moveToNext()){
            dbObjects.add(getValuesFromCursorForDriverPosition(cursor));

        }

        cursor.close();
        db.close();

        return dbObjects;
    }


    private ContentValues valuesFromDriverRace(RaceDriverRace dbObject){
        ContentValues values = new ContentValues();
        values.put(DriverContract.SEASON_YEAR         , dbObject.getSeasonYear());
        values.put(DriverContract.ROUND               , dbObject.getRound());
        values.put(DriverContract.POSITION            , dbObject.getPosition());
        values.put(DriverContract.POINTS              , dbObject.getPoints());
        values.put(DriverContract.WINS                , dbObject.getWins());
        values.put(DriverContract.URL_IMAGE           , dbObject.getUrlImageDriver());
        values.put(DriverContract.URL_IMAGE_POSTER    , dbObject.getUrlImagePosterDriver());

        values.put(DriverContract.DRIVER_ID           , dbObject.getDriver().getDriverId());
        values.put(DriverContract.PERMANENT_NUMBER    , dbObject.getDriver().getPermanentNumber());
        values.put(DriverContract.CODE_NAME           , dbObject.getDriver().getCode());
        values.put(DriverContract.GIVEN_NAME          , dbObject.getDriver().getGivenName());
        values.put(DriverContract.FAMILY_NAME         , dbObject.getDriver().getFamilyName());
        values.put(DriverContract.NATIONALITY         , dbObject.getDriver().getNationality());
        values.put(DriverContract.DATE_BIRTH          , dbObject.getDriver().getDateOfBirth());

        values.put(DriverContract.CONSTRUCTOR_NAME    , dbObject.getConstructors().get(0).getName());
        values.put(DriverContract.CONSTRUCTOR_COUNTRY , dbObject.getConstructors().get(0).getNationality());

        return values;

    }


    private RaceDriverRace getValuesFromCursorForDriverRace(Cursor cursor) {
        RaceDriverRace raceDriverRace = new RaceDriverRace();

        raceDriverRace.setSeasonYear(cursor.getString(
                cursor.getColumnIndex(DriverContract.SEASON_YEAR)));
        raceDriverRace.setRound(cursor.getString(
                cursor.getColumnIndex(DriverContract.ROUND)));
        raceDriverRace.setPosition(cursor.getString(
                cursor.getColumnIndex(DriverContract.POSITION)));
        raceDriverRace.setPoints(cursor.getString(
                cursor.getColumnIndex(DriverContract.POINTS)));
        raceDriverRace.setWins(cursor.getString(
                cursor.getColumnIndex(DriverContract.WINS)));
        raceDriverRace.setUrlImageDriver(cursor.getString(
                cursor.getColumnIndex(DriverContract.URL_IMAGE)));
        raceDriverRace.setUrlImagePosterDriver(cursor.getString(
                cursor.getColumnIndex(DriverContract.URL_IMAGE_POSTER)));

        raceDriverRace.getDriver().setId(cursor.getLong(
                cursor.getColumnIndex(DriverContract._ID)));
        raceDriverRace.getDriver().setDriverId(cursor.getString(
                cursor.getColumnIndex(DriverContract.DRIVER_ID)));
        raceDriverRace.getDriver().setPermanentNumber(cursor.getString(
                cursor.getColumnIndex(DriverContract.PERMANENT_NUMBER)));
        raceDriverRace.getDriver().setCode(cursor.getString(
                cursor.getColumnIndex(DriverContract.CODE_NAME)));
        raceDriverRace.getDriver().setGivenName(cursor.getString(
                cursor.getColumnIndex(DriverContract.GIVEN_NAME)));
        raceDriverRace.getDriver().setFamilyName(cursor.getString(
                cursor.getColumnIndex(DriverContract.FAMILY_NAME)));
        raceDriverRace.getDriver().setNationality(cursor.getString(
                cursor.getColumnIndex(DriverContract.NATIONALITY)));
        raceDriverRace.getDriver().setDateOfBirth(cursor.getString(
                cursor.getColumnIndex(DriverContract.DATE_BIRTH)));

        raceDriverRace.getConstructors().get(0).setName(cursor.getString(
                cursor.getColumnIndex(DriverContract.CONSTRUCTOR_NAME)));
        raceDriverRace.getConstructors().get(0).setNationality(cursor.getString(
                cursor.getColumnIndex(DriverContract.CONSTRUCTOR_COUNTRY)));

        return raceDriverRace;
    }


    private DriverPosition getValuesFromCursorForDriverPosition(Cursor cursor) {
        DriverPosition dbObject = new DriverPosition();

        dbObject.setSeasonYear(cursor.getString(
                cursor.getColumnIndex(DriverContract.SEASON_YEAR)));
        dbObject.setRound(cursor.getString(
                cursor.getColumnIndex(DriverContract.ROUND)));
        dbObject.setPosition(cursor.getString(
                cursor.getColumnIndex(DriverContract.POSITION)));
        dbObject.setPoints(cursor.getString(
                cursor.getColumnIndex(DriverContract.POINTS)));
        dbObject.setWins(cursor.getString(
                cursor.getColumnIndex(DriverContract.WINS)));
        dbObject.setUrlImageDriver(cursor.getString(
                cursor.getColumnIndex(DriverContract.URL_IMAGE)));
        dbObject.setUrlImagePosterDriver(cursor.getString(
                cursor.getColumnIndex(DriverContract.URL_IMAGE_POSTER)));

        dbObject.getDriver().setId(cursor.getLong(
                cursor.getColumnIndex(DriverContract._ID)));
        dbObject.getDriver().setDriverId(cursor.getString(
                cursor.getColumnIndex(DriverContract.DRIVER_ID)));
        dbObject.getDriver().setPermanentNumber(cursor.getString(
                cursor.getColumnIndex(DriverContract.PERMANENT_NUMBER)));
        dbObject.getDriver().setCode(cursor.getString(
                cursor.getColumnIndex(DriverContract.CODE_NAME)));
        dbObject.getDriver().setGivenName(cursor.getString(
                cursor.getColumnIndex(DriverContract.GIVEN_NAME)));
        dbObject.getDriver().setFamilyName(cursor.getString(
                cursor.getColumnIndex(DriverContract.FAMILY_NAME)));
        dbObject.getDriver().setNationality(cursor.getString(
                cursor.getColumnIndex(DriverContract.NATIONALITY)));
        dbObject.getDriver().setDateOfBirth(cursor.getString(
                cursor.getColumnIndex(DriverContract.DATE_BIRTH)));

        dbObject.getConstructors().get(0).setName(cursor.getString(
                cursor.getColumnIndex(DriverContract.CONSTRUCTOR_NAME)));
        dbObject.getConstructors().get(0).setName(cursor.getString(
                cursor.getColumnIndex(DriverContract.CONSTRUCTOR_COUNTRY)));

        return dbObject;
    }


}