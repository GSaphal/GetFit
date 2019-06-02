package com.saphal.getfit.features;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.saphal.getfit.R;

public class GoalsActivity extends AppCompatActivity {

    private TextView text;
    private TextInputEditText cal,step,goal_cal,goal_steps,time;
    private Button btn_finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        cal=findViewById(R.id.cal);
        step=findViewById(R.id.step);
        goal_cal=findViewById(R.id.goal_cal);
        goal_steps=findViewById(R.id.goal_steps);
        time=findViewById(R.id.time);
    }
}
