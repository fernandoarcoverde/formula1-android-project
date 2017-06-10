package com.unibratec.ads.formula1.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 08/06/17.
 */

public class DriverPosition  {
    private String seasonYear;
    private String round;
    private String urlImageDriver;
    private String urlImagePosterDriver;
    private List<RaceDriverRound> raceDriverRounds;

    /*  Structure GSON for Season */
    private String position;
    private String points;
    private String wins;
    @SerializedName("Driver")
    private Driver driver;
    @SerializedName("Constructors")
    private List<Constructor> constructors;


    public DriverPosition() {
        this.driver = new Driver();
        this.constructors = new ArrayList<>();
        this.constructors.add(new Constructor());

    }


    public DriverPosition(String seasonYear, String round, String position, String points
            , String wins, String urlImageDriver, String urlImagePosterDriver, Driver driver
            , List<Constructor> constructors) {
        this.seasonYear = seasonYear;
        this.round = round;
        this.position = position;
        this.points = points;
        this.wins = wins;
        this.urlImageDriver = urlImageDriver;
        this.urlImagePosterDriver = urlImagePosterDriver;
        this.driver = driver;
        this.constructors = constructors;
    }


    public String getSeasonYear() {
        return seasonYear;
    }

    public void setSeasonYear(String seasonYear) {
        this.seasonYear = seasonYear;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getWins() {
        return wins;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public List<Constructor> getConstructors() {
        return constructors;
    }

    public void setConstructors(List<Constructor> constructors) {
        this.constructors = constructors;
    }

    public String getUrlImageDriver() {
        return urlImageDriver;
    }

    public void setUrlImageDriver(String urlImageDriver) {
        this.urlImageDriver = urlImageDriver;
    }

    public String getUrlImagePosterDriver() {
        return urlImagePosterDriver;
    }

    public void setUrlImagePosterDriver(String urlImagePosterDriver) {
        this.urlImagePosterDriver = urlImagePosterDriver;
    }


}