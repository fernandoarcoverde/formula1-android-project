package com.unibratec.ads.formula1.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 08/06/17.
 */

public class RaceDriverDetail {

    /*  Structure GSON for Driver Details */
    @SerializedName("grid")
    public String gridPosition;
    @SerializedName("position")
    public String finalRacePosition;
    public String status;

}