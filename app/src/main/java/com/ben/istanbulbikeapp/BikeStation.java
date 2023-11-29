package com.ben.istanbulbikeapp;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class BikeStation {

    private int guid;
    @SerializedName("istasyon_no")
    private int station_no;
    @SerializedName("adi")
    private String name;
    @SerializedName("aktif")
    private int active;
    @SerializedName("bos")
    private int empty;
    @SerializedName("dolu")
    private int occupied;
    @SerializedName("lat")
    private double latitude;
    @SerializedName("lon")
    private double longitude;
    @SerializedName("sonBaglanti")
    private String lastConnection;

    public String getLastConnection() {
        return lastConnection;
    }

    public void setLastConnection(String lastConnection) {
        this.lastConnection = lastConnection;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getGuid() {
        return guid;
    }

    public void setGuid(int guid) {
        this.guid = guid;
    }

    public int getStation_no() {
        return station_no;
    }

    public void setStation_no(int station_no) {
        this.station_no = station_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmpty() {
        return empty;
    }

    public void setEmpty(int empty) {
        this.empty = empty;
    }

    public int getOccupied() {
        return occupied;
    }

    public void setOccupied(int occupied) {
        this.occupied = occupied;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
