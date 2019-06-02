package com.saphal.getfit.features;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.saphal.getfit.R;
import com.saphal.getfit.menu.MenuActivity;
import com.saphal.getfit.models.ReminderFire;
import com.saphal.getfit.utils.FirebaseHelper;

import java.util.Calendar;

public class AddReminderActivity extends AppCompatActivity {

    private Button btnadd_date,btnadd_time;
    private DatePicker date_picker;
    private TimePicker time_picker;
    private TextView textview;
    private EditText rem_title,rem_note;
    private FirebaseHelper mFirebaseHelper;
    private String userID;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        rem_title = findViewById(R.id.rem_title);
        rem_note = findViewById(R.id.rem_note);
        btnadd_date = findViewById(R.id.btnadd_date);
        btnadd_time = findViewById(R.id.btnadd_time);

        date_picker = findViewById(R.id.date_picker);
        time_picker = findViewById(R.id.time_picker);
        textview = findViewById(R.id.textview);

        Calendar calendar = Calendar.getInstance();

        date_picker.init(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                        Toast.makeText(AddReminderActivity.this, "Year, Month Day"+
                                        i+i1+i2
                                , Toast.LENGTH_SHORT).show();
                    }
                });

        time_picker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                //i -> hourOfDay
                //i1 -> minute
                textview.setText("HOur: "+i);
            }
        });
    }

    public void onBtnClickDisplayDate(View view) {
        btnadd_date.setVisibility(View.VISIBLE);
    }

    public void onBtnClickDisplayTime(View view) {
        btnadd_time.setVisibility(View.VISIBLE);
    }

    public void onBtnReminderSave(View view) {
        String date_pick,time_pick,text,remindname,remindnote;

        remindname = rem_title.getText().toString();
        remindnote = rem_note.getText().toString();
        date_pick = date_picker.toString();
        time_pick = time_picker.toString();

                mFirebaseHelper=new FirebaseHelper(getApplicationContext());
        mStorageRef = FirebaseStorage.getInstance().getReference("reminder");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("reminder");

        if (mFirebaseHelper.getmAuth().getCurrentUser() != null) {
            userID = mFirebaseHelper.getmAuth().getCurrentUser().getUid();
        };
        ReminderFire reminderFire = new ReminderFire(remindname,remindnote,date_pick,time_pick);

        addToDatabase(reminderFire);
    }

    private void addToDatabase(ReminderFire reminderFire) {
        mFirebaseHelper.getmRef().child("reminder")
                .child(userID)
                .setValue(reminderFire)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                        Toast.makeText(AddReminderActivity.this, "Reminder Added Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddReminderActivity.this, "Error Adding Reminder", Toast.LENGTH_SHORT).show();
                    }
                });
        mFirebaseHelper.getmRef().child("reminder").child(userID).removeValue();
    }
}
