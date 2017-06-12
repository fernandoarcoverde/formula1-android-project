package com.unibratec.ads.formula1.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 12/06/17.
 */

public class RaceDriverCircuit {

    private String circuitName;
    @SerializedName("Location")
    private RaceDriverLocation raceDriverLocation;



    public RaceDriverCircuit(String circuitName, RaceDriverLocation raceDriverLocation){
        this.circuitName = circuitName;
        this.raceDriverLocation = raceDriverLocation;
    }



    public String getCircuitName() {
        return circuitName;
    }

    public void setCircuitName(String circuitName) {
        this.circuitName = circuitName;
    }

    public RaceDriverLocation getRaceDriverLocation() {
        return raceDriverLocation;
    }

    public void setRaceDriverLocation(RaceDriverLocation raceDriverLocation) {
        this.raceDriverLocation = raceDriverLocation;
    }
}
