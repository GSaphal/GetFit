package com.saphal.getfit.features;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.saphal.getfit.R;

public class ExerciseActivity extends AppCompatActivity {

    private TextView exercises;
    private CardView abs,chest,biceps,triceps,legs,back;
    private Toolbar activity_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_add);

        activity_toolbar = findViewById(R.id.activity_toolbar);
        exercises= findViewById(R.id.exercises);


        abs= findViewById(R.id.abs);
        chest= findViewById(R.id.chest);
        biceps= findViewById(R.id.biceps);
        triceps= findViewById(R.id.triceps);
        legs= findViewById(R.id.legs);
        back= findViewById(R.id.back);

        Typeface shadow = Typeface.createFromAsset(getAssets(), "fonts/Shadow.ttf");
        exercises.setTypeface(shadow);
    }
}
