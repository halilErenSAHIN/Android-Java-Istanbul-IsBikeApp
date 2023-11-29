package com.ben.istanbulbikeapp.models;

import com.ben.istanbulbikeapp.BikeStation;

import java.util.ArrayList;

public class Stations {
    private int seviceCode;
    private String ServiceDescribtion;
    private ArrayList<BikeStation> dataList;

    public int getSeviceCode() {
        return seviceCode;
    }

    public void setSeviceCode(int seviceCode) {
        this.seviceCode = seviceCode;
    }

    public String getServiceDescribtion() {
        return ServiceDescribtion;
    }

    public void setServiceDescribtion(String serviceDescribtion) {
        ServiceDescribtion = serviceDescribtion;
    }

    public ArrayList<BikeStation> getDataList() {
        return dataList;
    }

    public void setDataList(ArrayList<BikeStation> dataList) {
        this.dataList = dataList;
    }
}
