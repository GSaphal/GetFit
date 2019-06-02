package com.saphal.getfit.features;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.saphal.getfit.R;
import com.saphal.getfit.menu.MenuActivity;
import com.saphal.getfit.models.NutritionFire;
import com.saphal.getfit.utils.FirebaseHelper;

public class NutritionActiivity extends AppCompatActivity {

    private EditText dietplan,breakfast,lunch,snacks,dinner,totalcalorie;
    private Toolbar activity_toolbar;
    private FirebaseHelper mFirebaseHelper;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private String userID;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_actiivity);

        activity_toolbar = findViewById(R.id.activity_toolbar);
        activity_toolbar.setTitle("Add Nutrition Plan");

        dietplan = findViewById(R.id.dietplan);
        breakfast = findViewById(R.id.breakfast);
        lunch = findViewById(R.id.lunch);
        snacks = findViewById(R.id.snacks);
        dinner = findViewById(R.id.dinner);
        totalcalorie = findViewById(R.id.totalcalorie);

        mFirebaseHelper=new FirebaseHelper(getApplicationContext());
        mStorageRef = FirebaseStorage.getInstance().getReference("nutrition");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("nutrition");

    }

    public void onBtnNutritionSave(View view) {

        String diet_plan,brkfast,lunch1,snacks1,dinner1,totcal;
        diet_plan= dietplan.getText().toString();
        brkfast = breakfast.getText().toString();
        lunch1 = lunch.getText().toString();
        snacks1 = snacks.getText().toString();
        dinner1 = dinner.getText().toString();
        totcal = totalcalorie.getText().toString();


        if (mFirebaseHelper.getmAuth().getCurrentUser() != null) {
            userID = mFirebaseHelper.getmAuth().getCurrentUser().getUid();
        };


        NutritionFire nutrition = new NutritionFire(
                diet_plan,
                brkfast,
                lunch1,
                snacks1,
                dinner1,
                totcal
        );

        addToDatabase(nutrition);
    }

    private void addToDatabase(NutritionFire nutrition) {
        mFirebaseHelper.getmRef().child("nutrition")
                .child(userID)
                .setValue(nutrition)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                        Toast.makeText(NutritionActiivity.this, "Nutrition Added Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(NutritionActiivity.this, "Error Adding Nutrtition", Toast.LENGTH_SHORT).show();
                    }
                });
        mFirebaseHelper.getmRef().child("nutrition").child(userID).removeValue();
    }
}
