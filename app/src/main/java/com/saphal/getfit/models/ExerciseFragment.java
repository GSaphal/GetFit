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
import com.saphal.getfit.features.CalorieActivity;
import com.saphal.getfit.features.ExerciseActivity;

public class ExerciseFragment extends Fragment {

    private Button exercisebtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_exercise, container, false);
        exercisebtn=view.findViewById(R.id.btn_calorie_add);
        exercisebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ExerciseActivity.class));
            }
        });
        return view;
    }
}
