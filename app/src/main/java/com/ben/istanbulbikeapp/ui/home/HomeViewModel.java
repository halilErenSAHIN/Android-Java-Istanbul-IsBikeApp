package com.ben.istanbulbikeapp.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ben.istanbulbikeapp.BikeStation;
import com.ben.istanbulbikeapp.helpers.ApiHelper;
import com.ben.istanbulbikeapp.helpers.StationsHelper;
import com.ben.istanbulbikeapp.models.Stations;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<ArrayList<BikeStation>> mData;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mData = new MutableLiveData<>();
        String url = "https://api.ibb.gov.tr/ispark-bike/GetAllStationStatus";
        String response = ApiHelper.getInstance().fetchStations(url);
        try {
            StationsHelper helper = new StationsHelper();
            Stations data = helper.GetStations();
            mData.setValue(data.getDataList());
            mText.setValue(data.getServiceDescribtion());
        } catch (Exception e) {
            mText.setValue(e.getMessage());
        }


    }


    public LiveData<String> getText() {
        return mText;
    }
}