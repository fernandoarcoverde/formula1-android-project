package com.unibratec.ads.formula1.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by root on 08/06/17.
 */

public class SeasonYear {

    /*  Structure GSON for Drivers Season */
    @SerializedName("StandingsLists")
    public List<SeasonRound> seasonRounds;

}