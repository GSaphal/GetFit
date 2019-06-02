package com.saphal.getfit.models;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.saphal.getfit.R;
import com.saphal.getfit.features.NutritionActiivity;

public class NutritionFragment extends Fragment {


    private Button nutritionbtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_nutrition,container,false);

        nutritionbtn = view.findViewById(R.id.nutritionbtn);

        nutritionbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), NutritionActiivity.class));
            }
        });

        return view;
    }
}
