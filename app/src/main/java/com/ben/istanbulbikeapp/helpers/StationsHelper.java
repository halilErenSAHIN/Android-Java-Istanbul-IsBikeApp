package com.ben.istanbulbikeapp.helpers;

import com.ben.istanbulbikeapp.models.Stations;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class StationsHelper {
    public Stations GetStations() {
        String url = "https://api.ibb.gov.tr/ispark-bike/GetAllStationStatus";
        String response = ApiHelper.getInstance().fetchStations(url);
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Boolean.class, new BooleanTypeAdapter());
        Gson gson = builder.create();
        return gson.fromJson(response, Stations.class);
    }
}
