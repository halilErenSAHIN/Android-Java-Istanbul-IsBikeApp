package com.ben.istanbulbikeapp.helpers;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.OkHttp;

public class ApiHelper {
    private OkHttpClient client;
    private static ApiHelper instance;

    public ApiHelper() {
        this.client = new OkHttpClient();
    }


    public static synchronized ApiHelper getInstance() {
        if (instance == null) {
            instance = new ApiHelper();
        }
        return instance;
    }

    public String fetchStations(String url) {
        client = new OkHttpClient();
        try {
            Request request = new Request
                    .Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    return response.body().string();
                } else {
                    return "Body is null!!!";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return null;
    }
}
