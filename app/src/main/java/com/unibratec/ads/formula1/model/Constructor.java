package com.unibratec.ads.formula1.model;

/**
 * Created by root on 08/06/17.
 */

public class Constructor {

    /*  Structure GSON for Drivers Season and Driver Details */
    private String name;
    private String nationality;


    public Constructor(){}

    public Constructor(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}