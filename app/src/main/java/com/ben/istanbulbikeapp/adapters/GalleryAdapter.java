package com.ben.istanbulbikeapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ben.istanbulbikeapp.R;
import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.Viewholder> {

    private ArrayList<String> imageList;
    private Context context;

    public GalleryAdapter(ArrayList<String> imageList, Context context) {
        this.imageList = imageList;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gallery, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        File image_file = new File(imageList.get(position));
        if (image_file.exists()) {
            //Glice: resim dosyalarında komponentlere doldurmayı sağlar dönüşümiçeride yapılır.
            Glide.with(context).load(image_file).into(holder.image);
        }

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private ImageView image;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_image);
        }
    }
}
