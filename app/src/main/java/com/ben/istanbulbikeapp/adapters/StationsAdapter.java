package com.ben.istanbulbikeapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ben.istanbulbikeapp.BikeStation;
import com.ben.istanbulbikeapp.R;

import java.util.ArrayList;

public class StationsAdapter extends RecyclerView.Adapter<StationsAdapter.ViewHolder> {
    private ArrayList<BikeStation> stations;

    public StationsAdapter(ArrayList<BikeStation> stations) {
        this.stations = stations;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bikestations, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BikeStation station = stations.get(position);
        holder.name.setText(station.getName());
        holder.empty.setText("Boş:"+station.getEmpty());
        holder.occupied.setText("Dolu:"+station.getOccupied());

    }

    @Override
    public int getItemCount() {
        return stations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, empty, occupied;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_station_name);
            empty = itemView.findViewById(R.id.tv_empty);
            occupied = itemView.findViewById(R.id.tv_occupied);
        }
    }
}
