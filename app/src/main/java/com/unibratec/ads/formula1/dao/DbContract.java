package com.unibratec.ads.formula1.dao;

import android.provider.BaseColumns;

/**
 * Created by root on 10/06/17.
 */

public interface DbContract extends BaseColumns {

    String TABLE_NAME_FAVORITES         = "favorites";
    String FAVORITE_ID                  = "_id";
    String FAVORITE_SEASON_YEAR         = "season_year";
    String FAVORITE_ROUND               = "round";
    String FAVORITE_POSITION            = "position";
    String FAVORITE_POINTS              = "points";
    String FAVORITE_WINS                = "wins";
    String FAVORITE_URL_IMAGE           = "url_image";
    String FAVORITE_URL_IMAGE_POSTER    = "url_image_poster";
    String FAVORITE_DRIVER_ID           = "driver_id";
    String FAVORITE_PERMANENT_NUMBER    = "permanent_number";
    String FAVORITE_CODE_NAME           = "code_name";
    String FAVORITE_GIVEN_NAME          = "given_name";
    String FAVORITE_FAMILY_NAME         = "family_name";
    String FAVORITE_NATIONALITY         = "nationality";
    String FAVORITE_DATE_BIRTH          = "date_birth";
    String FAVORITE_CONSTRUCTOR_NAME    = "constructor_name";
    String FAVORITE_CONSTRUCTOR_COUNTRY = "constructor_country";

}