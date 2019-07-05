package com.example.swusemiproject2019.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.swusemiproject2019.R;
import com.example.swusemiproject2019.activity.NewMemoActivity;

public class FragmentMemo extends Fragment {

    public FragmentMemo(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_memo, container, false);


        Button btnNewMemo = view.findViewById(R.id.btnNewMemo);
        btnNewMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), NewMemoActivity.class);
                startActivity(intent);
            }
        });


        return view;

    }
}
