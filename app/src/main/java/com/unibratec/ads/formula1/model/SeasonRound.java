package com.unibratec.ads.formula1.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by root on 08/06/17.
 */

public class SeasonRound {

    /*  Structure GSON for Drivers Season */
    public String season;
    public String round;
    @SerializedName("DriverStandings")
    public List<DriverPosition> driverStandings;

}