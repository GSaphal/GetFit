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
import com.saphal.getfit.features.GoalsActivity;

public class GoalFragment extends Fragment {
    private Button btn_goal_add;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_goal_display, container, false);
        btn_goal_add=view.findViewById(R.id.btn_calorie_add);
        btn_goal_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GoalsActivity.class));
            }
        });
        return view;
    }
}
