package com.saphal.getfit.features;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.saphal.getfit.R;
import com.saphal.getfit.menu.MenuActivity;
import com.saphal.getfit.models.GoalFire;
import com.saphal.getfit.utils.FirebaseHelper;

public class GoalActivity extends AppCompatActivity {


    private EditText cal,steps,goal_cal,goal_steps,time;
    private Button onBtnGoalSave;
    private FirebaseHelper mFirebaseHelper;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        cal=findViewById(R.id.cal);
        steps=findViewById(R.id.steps);
        goal_cal=findViewById(R.id.goal_cal);
        goal_steps=findViewById(R.id.goal_steps);
        time=findViewById(R.id.time);
        mFirebaseHelper=new FirebaseHelper(getApplicationContext());
        mStorageRef = FirebaseStorage.getInstance().getReference("calorie");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("calorie");

    }

    public void onBtnGoalSave(View view) {
        String calorie,step,goal_calorie,goal_step,time_req;
        calorie=cal.getText().toString();
        step=steps.getText().toString();
        goal_calorie=goal_cal.getText().toString();
        goal_step=goal_steps.getText().toString();
        time_req=time.getText().toString();

        if (mFirebaseHelper.getmAuth().getCurrentUser() != null) {
            userID = mFirebaseHelper.getmAuth().getCurrentUser().getUid();
        };
        GoalFire goal=new GoalFire(
                calorie,
                step,
                goal_calorie,
                goal_step,
                time_req
        );
      addTodatabase(goal);
    }

    private void addTodatabase(GoalFire goal) {
        mFirebaseHelper.getmRef().child("goal")
                .child(userID)
                .setValue(goal)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                        Toast.makeText(GoalActivity.this, "Info Added Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(GoalActivity.this, "Error Adding Info", Toast.LENGTH_SHORT).show();
                    }
                });
        mFirebaseHelper.getmRef().child("goal").child(userID).removeValue();
    }

}
