package com.unibratec.ads.formula1;

import android.content.Context;

/**
 * Created by root on 08/06/17.
 */

public class GetImage {
    private  static GetImage instance;
    private Context mContext;


    public GetImage(Context context) {
        this.mContext = context;
    }


    public static synchronized GetImage getInstance(Context context){
        if (instance == null){
            instance = new GetImage(context);
        }
        return instance;
    }


    public int Constructor(String contructor){
        switch (contructor){
            case "Ferrari"        : return R.drawable.constructor_ferrari;
            case "Force India"    : return R.drawable.constructor_forceindia;
            case "Haas F1 Team"   : return R.drawable.constructor_haasf1team;
            case "Manor Marussia" : return R.drawable.constructor_manormarussia;
            case "McLaren"        : return R.drawable.constructor_mclaren;
            case "Mercedes"       : return R.drawable.constructor_mercedes;
            case "Red Bull"        : return R.drawable.constructor_redbull;
            case "Renault"        : return R.drawable.constructor_renault;
            case "Sauber"         : return R.drawable.constructor_sauber;
            case "Toro Rosso"      : return R.drawable.constructor_tororosso;
            case "Williams"       : return R.drawable.constructor_williams;
            default               : return R.drawable.nologo;
        }
    }



    public int Flag(String flag){
        switch (flag){
            case "american"          : return R.drawable.flag_united_states_of_america;
            case "american-italian"  : return R.drawable.flag_united_states_of_america;
            case "argentine"         : return R.drawable.flag_argentina;
            case "argentine-italian" : return R.drawable.flag_argentina;
            case "australian"        : return R.drawable.flag_australia;
            case "austrian"          : return R.drawable.flag_austria;
            case "belgian"           : return R.drawable.flag_belgium;
            case "brazilian"         : return R.drawable.flag_brazil;
            case "british"           : return R.drawable.flag_united_kingdom;
            case "canadian"          : return R.drawable.flag_canada;
            case "chilean"           : return R.drawable.flag_chile;
            case "colombian"         : return R.drawable.flag_colombia;
            case "czech"             : return R.drawable.flag_czechia;
            case "danish"            : return R.drawable.flag_denmark;
            case "dutch"             : return R.drawable.flag_netherlands;
            case "east german"       : return R.drawable.flag_germany;
            case "finnish"           : return R.drawable.flag_finland;
            case "french"            : return R.drawable.flag_france;
            case "german"            : return R.drawable.flag_germany;
            case "hungarian"         : return R.drawable.flag_hungary;
            case "indian"            : return R.drawable.flag_india;
            case "indonesian"        : return R.drawable.flag_indonesia;
            case "irish"             : return R.drawable.flag_ireland;
            case "italian"           : return R.drawable.flag_italy;
            case "japanese"          : return R.drawable.flag_japan;
            case "liechtensteiner"   : return R.drawable.flag_liechtenstein;
            case "malaysian"         : return R.drawable.flag_malaysia;
            case "mexican"           : return R.drawable.flag_mexico;
            case "monegasque"        : return R.drawable.flag_monaco;
            case "new zealander"     : return R.drawable.flag_new_zealand;
            case "polish"            : return R.drawable.flag_poland;
            case "portuguese"        : return R.drawable.flag_portugal;
            case "rhodesian"         : return R.drawable.flag_rhodesia;
            case "russian"           : return R.drawable.flag_russia;
            case "south african"     : return R.drawable.flag_south_africa;
            case "spanish"           : return R.drawable.flag_spain;
            case "swedish"           : return R.drawable.flag_sweden;
            case "swiss"             : return R.drawable.flag_switzerland;
            case "thai"              : return R.drawable.flag_thailand;
            case "uruguayan"         : return R.drawable.flag_uruguay;
            case "venezuelan"        : return R.drawable.flag_venezuela;
            default                  : return R.drawable.noflag;
        }
    }

}