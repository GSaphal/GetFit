package com.saphal.getfit.models;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.saphal.getfit.MainActivity;
import com.saphal.getfit.R;
import com.saphal.getfit.features.CalorieActivity;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;


public class CalorieFragment extends Fragment {

    private ImageButton btn_calorie_add;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_calorie, container, false);
        btn_calorie_add=view.findViewById(R.id.btn_calorie_add);
        btn_calorie_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CalorieActivity.class));
            }
        });
        return view;
    }

}