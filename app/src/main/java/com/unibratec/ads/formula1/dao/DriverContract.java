package com.unibratec.ads.formula1.dao;

import android.provider.BaseColumns;

/**
 * Created by root on 10/06/17.
 */

public interface DriverContract extends BaseColumns {

    String TABLE_NAME          = "favorites_b";

    String SEASON_YEAR         = "season_year";
    String ROUND               = "round";
    String POSITION            = "position";
    String POINTS              = "points";
    String WINS                = "wins";
    String URL_IMAGE           = "url_image";
    String URL_IMAGE_POSTER    = "url_image_poster";
    String DRIVER_ID           = "driver_id";
    String PERMANENT_NUMBER    = "permanent_number";
    String CODE_NAME           = "code_name";
    String GIVEN_NAME          = "given_name";
    String FAMILY_NAME         = "family_name";
    String NATIONALITY         = "nationality";
    String DATE_BIRTH          = "date_birth";
    String CONSTRUCTOR_NAME    = "constructor_name";
    String CONSTRUCTOR_COUNTRY = "constructor_country";


}