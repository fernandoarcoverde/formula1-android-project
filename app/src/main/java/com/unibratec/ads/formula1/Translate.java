package com.unibratec.ads.formula1;

import android.content.Context;

/**
 * Created by root on 10/06/17.
 */

public class Translate {
    private  static Translate instance;
    private Context mContext;


    public Translate(Context context) {
        this.mContext = context;
    }


    public static synchronized Translate getInstance(Context context){
        if (instance == null){
            instance = new Translate(context);
        }
        return instance;
    }


    public int Country(String country){
        switch (country){
            case "Australian" : return R.string.nationality_australian;
            case "Austrian"   : return R.string.nationality_austrian;
            case "Belgian"    : return R.string.nationality_belgian;
            case "Brazilian"  : return R.string.nationality_brazilian;
            case "British"    : return R.string.nationality_british;
            case "Danish"     : return R.string.nationality_danish;
            case "Dutch"      : return R.string.nationality_dutch;
            case "Finnish"    : return R.string.nationality_finnish;
            case "French"     : return R.string.nationality_french;
            case "German"     : return R.string.nationality_german;
            case "Japanese"   : return R.string.nationality_japanese;
            case "Indian"     : return R.string.nationality_indian;
            case "Indonesian" : return R.string.nationality_indonesian;
            case "Italian"    : return R.string.nationality_italian;
            case "Mexican"    : return R.string.nationality_mexican;
            case "Polish"     : return R.string.nationality_polish;
            case "Russian"    : return R.string.nationality_russian;
            case "Spanish"    : return R.string.nationality_spanish;
            case "Swedish"    : return R.string.nationality_swedish;
            case "Swiss"      : return R.string.nationality_swiss;
            case "Venezuelan" : return R.string.nationality_venezuelan;
            default           : return R.string.nationality_american;
        }
    }


}