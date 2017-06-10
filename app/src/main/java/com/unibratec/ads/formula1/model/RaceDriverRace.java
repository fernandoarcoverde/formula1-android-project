package com.unibratec.ads.formula1.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 08/06/17.
 */

public class RaceDriverRace {
    private String seasonYear;
    private String round;
    private String urlImageDriver;
    private String urlImagePosterDriver;
    private String position;
    private String wins;
    private String points;
    private Driver driver;
    private List<Constructor> constructors;

    /*  Structure GSON for Driver Details */
    @SerializedName("Races")
    private List<RaceDriverRound> raceDriverRounds;


    public RaceDriverRace(){
        this.driver = new Driver();
        this.constructors = new ArrayList<>();
        this.constructors.add(new Constructor());

    }


    public RaceDriverRace(String seasonYear, String round, String urlImageDriver
            , String urlImagePosterDriver, String position, String wins
            , String points, Driver driver, List<Constructor> constructors
            , List<RaceDriverRound> raceDriverRounds) {
        this.seasonYear = seasonYear;
        this.round = round;
        this.urlImageDriver = urlImageDriver;
        this.urlImagePosterDriver = urlImagePosterDriver;
        this.position = position;
        this.wins = wins;
        this.points = points;
        this.driver = driver;
        this.constructors = constructors;
        this.raceDriverRounds = raceDriverRounds;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getWins() {
        return wins;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
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

    public List<RaceDriverRound> getRaceDriverRounds() {
        return raceDriverRounds;
    }

    public void setRaceDriverRounds(List<RaceDriverRound> raceDriverRounds) {
        this.raceDriverRounds = raceDriverRounds;
    }
}