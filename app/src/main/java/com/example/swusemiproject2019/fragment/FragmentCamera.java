package com.example.swusemiproject2019.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.swusemiproject2019.R;

public class FragmentCamera extends Fragment {

    public String mPhotoPath = "/sdcard/hello/world.jpg"; //dummy data

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera, container, false);

        Button btnCamera2 = view.findViewById(R.id.btnCamera2);



        return view;

    }
}
