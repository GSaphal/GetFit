package com.saphal.getfit.models;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.saphal.getfit.R;
import com.saphal.getfit.features.GoalsActivity;

public class Goals extends Fragment {
    private View view;
    private Button add_dataa;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      view=inflater.inflate(R.layout.activity_goals,container,false);
        add_dataa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), GoalsActivity.class));
            }
        });
        return view;
    }
    }
