package com.fendy.covid19.model;

import java.io.Serializable;

public class Covid implements Serializable {
    private int objectid;
    private String Country_Region;
    private String Last_Update;
    private double Lat;
    private double Long;
    private int Confirmed;
    private int Deaths;
    private int Recovered;
    private int Active;

    public int getObjectid() {
        return objectid;
    }

    public void setObjectid(int objectid) {
        this.objectid = objectid;
    }

    public String getCountry_Region() {
        return Country_Region;
    }

    public void setCountry_Region(String country_Region) {
        Country_Region = country_Region;
    }

    public String getLast_Update() {
        return Last_Update;
    }

    public void setLast_Update(String last_Update) {
        Last_Update = last_Update;
    }

    public double getLat() {
        return Lat;
    }

    public void setLat(double lat) {
        Lat = lat;
    }

    public double getLong() {
        return Long;
    }

    public void setLong(double aLong) {
        Long = aLong;
    }

    public int getConfirmed() {
        return Confirmed;
    }

    public void setConfirmed(int confirmed) {
        Confirmed = confirmed;
    }

    public int getDeaths() {
        return Deaths;
    }

    public void setDeaths(int deaths) {
        Deaths = deaths;
    }

    public int getRecovered() {
        return Recovered;
    }

    public void setRecovered(int recovered) {
        Recovered = recovered;
    }

    public int getActive() {
        return Active;
    }

    public void setActive(int active) {
        Active = active;
    }
}
