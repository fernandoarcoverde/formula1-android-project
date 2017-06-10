package com.unibratec.ads.formula1.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by root on 08/06/17.
 */

public class RaceDriverRound {

    /*  Structure GSON for Driver Details */
    public String round;
    public String raceName;
    @SerializedName("Results")
    public List<RaceDriverDetail> raceDriverDetails;

}