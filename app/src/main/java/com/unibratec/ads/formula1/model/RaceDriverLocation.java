package com.unibratec.ads.formula1.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 12/06/17.
 */

public class RaceDriverLocation {

    @SerializedName("lat")
    private String latitude;
    @SerializedName("long")
    private String longitude;
    private String locality;


    public RaceDriverLocation(String latitude, String longitude, String locality){
        this.latitude = latitude;
        this.longitude = longitude;
        this.locality = longitude;
    }




    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String logitude) {
        this.longitude = logitude;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }
}
