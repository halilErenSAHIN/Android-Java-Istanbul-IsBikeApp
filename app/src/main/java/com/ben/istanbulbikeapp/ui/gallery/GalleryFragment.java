package com.ben.istanbulbikeapp.ui.gallery;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_MEDIA_IMAGES;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Environment.MEDIA_MOUNTED;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ben.istanbulbikeapp.adapters.GalleryAdapter;
import com.ben.istanbulbikeapp.databinding.FragmentGalleryBinding;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private RecyclerView rvGallery;
    private ArrayList<String> images;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        rvGallery = binding.rvGallery;
        rvGallery.setLayoutManager(new GridLayoutManager(getContext(), 3));
        images = new ArrayList<>();


        GalleryAdapter adapter = new GalleryAdapter(images, getContext());
        rvGallery.setAdapter(adapter);
        checkPermissions();
        return root;
    }

    //Buraya hangi yetkileri istiyorsam onu yazıyorum. Karşılığında da override lazım.

    //Lokal Hafızaya Okuma izni var mı? Varsa resimleri yükle yoksa kullanıcıdan yetki iste.
    private boolean checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            return Environment.isExternalStorageManager();
        } else {
            int write = ContextCompat.checkSelfPermission(getContext(), WRITE_EXTERNAL_STORAGE);
            int read = ContextCompat.checkSelfPermission(getContext(), READ_EXTERNAL_STORAGE);
            int read_media = ContextCompat.checkSelfPermission(getContext(),READ_MEDIA_IMAGES);
            return read == PackageManager.PERMISSION_GRANTED && write == PackageManager.PERMISSION_GRANTED && read_media == PackageManager.PERMISSION_GRANTED;
        }
    }

   /* private void requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            try {
                Intent intent = new Intent();
                intent.setAction(Settings
                        .ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
                intent.setData(uri);
            } catch (Exception e)
        }
    }*/

    private void requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
            loadImages();
        } else {
            ActivityCompat.requestPermissions(
                    getActivity(),
                    new String[]{
                            WRITE_EXTERNAL_STORAGE,
                            READ_EXTERNAL_STORAGE,
                            READ_MEDIA_IMAGES
                    },
                    100
            );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //load images
                loadImages();
            } else {
                Toast.makeText(getContext(), "Access Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void loadImages() {
        boolean SDCard = Environment.getExternalStorageState().equals(MEDIA_MOUNTED);
        if (SDCard) {
            //Image Dosyalarının _ID (kimlik) kodları toplanır.
            final String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};
            //Image dosyaları tarihi olarak yakın zamandan uzak zamana doğru (DESC) sıralanır.
            final String order = MediaStore.Images.Media.DATE_TAKEN + "DESC";

            Cursor cursor = getActivity().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null, order);
            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                cursor.moveToPosition(i);
                int colindex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                images.add(cursor.getString(colindex));

            }
            rvGallery.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}