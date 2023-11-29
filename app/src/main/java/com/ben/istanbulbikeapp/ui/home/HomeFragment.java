package com.ben.istanbulbikeapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ben.istanbulbikeapp.adapters.StationsAdapter;
import com.ben.istanbulbikeapp.databinding.FragmentHomeBinding;
import com.ben.istanbulbikeapp.helpers.ApiHelper;
import com.ben.istanbulbikeapp.helpers.StationsHelper;
import com.ben.istanbulbikeapp.models.Stations;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);


        final RecyclerView rvStations = binding.rvStations;
        rvStations.setLayoutManager(new LinearLayoutManager(getContext()));
        String url = "https://api.ibb.gov.tr/ispark-bike/GetAllStationStatus";
        String response = ApiHelper.getInstance().fetchStations(url);
        StationsHelper helper = new StationsHelper();
        Stations data = helper.GetStations();


        StationsAdapter adapter = new StationsAdapter(data.getDataList());
        rvStations.setAdapter(adapter);




        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}